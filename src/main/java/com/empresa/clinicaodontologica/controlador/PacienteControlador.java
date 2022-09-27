package com.empresa.clinicaodontologica.controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.clinicaodontologica.dto.PacienteRespostaDto;
import com.empresa.clinicaodontologica.dto.PacienteRequisicaoDto;
import com.empresa.clinicaodontologica.entidade.PacienteEntidade;
import com.empresa.clinicaodontologica.excecao.RecursoNaoEncontradoExcecao;
import com.empresa.clinicaodontologica.excecao.RequisicaoIncorretaExcecao;
import com.empresa.clinicaodontologica.servico.implementacao.PacienteServicoImplementacao;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/paciente")
public class PacienteControlador {   
    private final PacienteServicoImplementacao pacienteServico;
    
    public PacienteControlador(PacienteServicoImplementacao pacienteServico) {
        this.pacienteServico = pacienteServico;
    }

    // @PostMapping("/salvar")
    // public ResponseEntity<PacienteEntidade> salvar(@RequestBody PacienteEntidade paciente) {
    //     PacienteEntidade pacienteEntidade = pacienteServico.salvar(paciente);
        
    //     if (pacienteEntidade == null) {
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    //     }
        
    //     return ResponseEntity.ok().body(pacienteEntidade);
    // }

    // @GetMapping("/buscar_tudo")
    // public ResponseEntity<List<PacienteEntidade>> buscarTudo() {
    //     return ResponseEntity.ok().body(pacienteServico.buscarTudo());
    // }

    // @GetMapping("/buscar/{id}")
    // public ResponseEntity<PacienteEntidade> buscarPorId(@PathVariable Integer id) {
    //     Optional<PacienteEntidade> pacienteEntidade = pacienteServico.buscarPorId(id);

    //     if (pacienteEntidade.isPresent()) {
    //         return ResponseEntity.ok().body(pacienteEntidade.get());
    //     } else if (pacienteEntidade.isEmpty()) {
    //         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    //     } 
        
    //     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    // }

    // @PutMapping("/atualizar")
    // public ResponseEntity<PacienteEntidade> atualizar(@RequestBody PacienteEntidade paciente) {
    //     PacienteEntidade pacienteEntidade = pacienteServico.atualizar(paciente);
        
    //     if (pacienteEntidade == null) {
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    //     }

    //     return ResponseEntity.ok().body(pacienteEntidade);
    // }  

    @Operation(summary = "Registrar uma novo paciente.")
    @PostMapping("/salvar")
    public ResponseEntity<PacienteRespostaDto> salvar(@RequestBody PacienteRequisicaoDto paciente) throws RequisicaoIncorretaExcecao {
        try {
            PacienteEntidade pacienteEntidade = pacienteServico.salvar(paciente.converterParaPacienteEntidade());
            return ResponseEntity.ok().body(PacienteRespostaDto.converterParaPacienteRespostaDto(pacienteEntidade));
        } catch (Exception e) {
            throw new RequisicaoIncorretaExcecao("Tabela Paciente: a requisição para inserir um novo registro está incorreta.");
        }      
    }

    @Operation(summary = "Buscar todos os pacientes.")
    @GetMapping("/buscar_tudo")
    public ResponseEntity<List<PacienteRespostaDto>> buscarTudo() {
        List<PacienteRespostaDto> listaPacientes = new ArrayList<PacienteRespostaDto>();
        pacienteServico.buscarTudo().forEach(pacienteEntidade -> {
            listaPacientes.add(PacienteRespostaDto.converterParaPacienteRespostaDto(pacienteEntidade));
        });

        return ResponseEntity.ok().body(listaPacientes);
    }

    @Operation(summary = "Buscar um paciente.")
    @GetMapping("/buscar/{id}")
    public ResponseEntity<PacienteRespostaDto> buscarPorId(@PathVariable Integer id) throws RequisicaoIncorretaExcecao, RecursoNaoEncontradoExcecao {
        if (id != null) {
            Optional<PacienteEntidade> pacienteEntidade = pacienteServico.buscarPorId(id);

            if (pacienteEntidade.isPresent()) {
                return ResponseEntity.ok().body(PacienteRespostaDto.converterParaPacienteRespostaDto(pacienteEntidade.get()));
            } else if (pacienteEntidade.isEmpty()) {
                throw new RecursoNaoEncontradoExcecao(String.format("Tabela Paciente: registro com ID = %d não encontrado.", id));
            } 
        }

        throw new RequisicaoIncorretaExcecao("Tabela Paciente: não é possível buscar um registro com ID nulo.");
    }

    @Operation(summary = "Atualizar um paciente.")
    @PutMapping("/atualizar")
    public ResponseEntity<PacienteRespostaDto> atualizar(@RequestBody PacienteRequisicaoDto paciente) throws RequisicaoIncorretaExcecao, RecursoNaoEncontradoExcecao {
        PacienteEntidade pacienteEntidade = pacienteServico.atualizar(paciente.converterParaPacienteEntidade());
        
        if (pacienteEntidade == null) {
            throw new RequisicaoIncorretaExcecao(String.format("Tabela Paciente: a requisição para atualizar o registro com ID = %d está incorreta.", paciente.getIdPaciente()));
        } else if (pacienteEntidade.getIdPaciente() == null) {
            throw new RecursoNaoEncontradoExcecao(String.format("Tabela Paciente: o registro com ID = %d não foi encontrado, portanto não pode atualizá-lo.", paciente.getIdPaciente()));
        }

        return ResponseEntity.ok().body(PacienteRespostaDto.converterParaPacienteRespostaDto(pacienteEntidade));
    }  

    @Operation(summary = "Excluir um paciente.")
    @DeleteMapping("/excluir/{id}") 
    public ResponseEntity<String> excluir(@PathVariable Integer id) throws RecursoNaoEncontradoExcecao {
        if (pacienteServico.excluir(id)) {
            return ResponseEntity.ok().body(String.format("O registro com ID = %d foi excluído com sucesso.", id));
        } 

        throw new RecursoNaoEncontradoExcecao("Tabela Paciente: o registro com ID = %d não foi encontrado, portanto não pode excluí-lo.");
    }
}
