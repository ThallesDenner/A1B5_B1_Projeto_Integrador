package com.empresa.clinicaodontologica.controlador;

import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.clinicaodontologica.dto.ConsultaRespostaDto;
import com.empresa.clinicaodontologica.dto.ConsultaRequisicaoDto;
import com.empresa.clinicaodontologica.entidade.ConsultaEntidade;
import com.empresa.clinicaodontologica.entidade.DentistaEntidade;
import com.empresa.clinicaodontologica.entidade.PacienteEntidade;
import com.empresa.clinicaodontologica.excecao.RecursoNaoEncontradoExcecao;
import com.empresa.clinicaodontologica.excecao.RequisicaoIncorretaExcecao;
import com.empresa.clinicaodontologica.servico.implementacao.ConsultaServicoImplementacao;
import com.empresa.clinicaodontologica.servico.implementacao.DentistaServicoImplementacao;
import com.empresa.clinicaodontologica.servico.implementacao.PacienteServicoImplementacao;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/consulta")
public class ConsultaControlador {   
    private final ConsultaServicoImplementacao consultaServico;
    private final DentistaServicoImplementacao dentistaServico;
    private final PacienteServicoImplementacao pacienteServico;
  
    public ConsultaControlador(ConsultaServicoImplementacao consultaServico,
            DentistaServicoImplementacao dentistaServico, PacienteServicoImplementacao pacienteServico) {
        this.consultaServico = consultaServico;
        this.dentistaServico = dentistaServico;
        this.pacienteServico = pacienteServico;
    }

    // @PostMapping("/salvar")
    // public ResponseEntity<ConsultaEntidade> salvar(@RequestBody ConsultaEntidade consulta) {
    //     ConsultaEntidade consultaEntidade = consultaServico.salvar(consulta);
        
    //     if (consultaEntidade == null) {
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    //     }
        
    //     return ResponseEntity.ok().body(consultaEntidade);
    // }

    // @GetMapping("/buscar_tudo")
    // public ResponseEntity<List<ConsultaEntidade>> buscarTudo() {
    //     return ResponseEntity.ok().body(consultaServico.buscarTudo());
    // }

    // @GetMapping("/buscar/{id}")
    // public ResponseEntity<ConsultaEntidade> buscarPorId(@PathVariable Integer id) {
    //     Optional<ConsultaEntidade> consultaEntidade = consultaServico.buscarPorId(id);

    //     if (consultaEntidade.isPresent()) {
    //         return ResponseEntity.ok().body(consultaEntidade.get());
    //     } else if (consultaEntidade.isEmpty()) {
    //         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    //     } 
        
    //     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    // }

    // @PutMapping("/atualizar")
    // public ResponseEntity<ConsultaEntidade> atualizar(@RequestBody ConsultaEntidade consulta) {
    //     ConsultaEntidade consultaEntidade = consultaServico.atualizar(consulta);
        
    //     if (consultaEntidade == null) {
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    //     }

    //     return ResponseEntity.ok().body(consultaEntidade);
    // }  

    @Operation(summary = "Registrar uma nova consulta.")
    @PostMapping("/salvar")
    public ResponseEntity<ConsultaRespostaDto> salvar(@RequestBody ConsultaRequisicaoDto consulta) throws RecursoNaoEncontradoExcecao, RequisicaoIncorretaExcecao {
        ConsultaEntidade consultaEntidade = null;

        if (consulta != null && consulta.getIdPaciente() != null && consulta.getIdDentista() != null) {
            Optional<PacienteEntidade> paciente = pacienteServico.buscarPorId(consulta.getIdPaciente());
            Optional<DentistaEntidade> dentista = dentistaServico.buscarPorId(consulta.getIdDentista());
    
            if (dentista.isPresent() && paciente.isPresent()) {
                consultaEntidade = consultaServico.salvar(consulta.converterParaConsultaEntidade(paciente.get(), dentista.get()));
                return ResponseEntity.ok().body(ConsultaRespostaDto.converterParaConsultaRespostaDto(consultaEntidade));
            } else if (dentista.isEmpty() || paciente.isEmpty()) {
                throw new RecursoNaoEncontradoExcecao("Registro de dentista e/ou paciente não encontrado.");
            }
        }
        
        throw new RequisicaoIncorretaExcecao("Tabela Consulta: a requisição para inserir um novo registro está incorreta.");    
    }

    @Operation(summary = "Buscar todas as consultas.")
    @GetMapping("/buscar_tudo")
    public ResponseEntity<List<ConsultaRespostaDto>> buscarTudo() {
        List<ConsultaRespostaDto> listaConsultas = new ArrayList<ConsultaRespostaDto>();
        consultaServico.buscarTudo().forEach(consultaEntidade -> {
            listaConsultas.add(ConsultaRespostaDto.converterParaConsultaRespostaDto(consultaEntidade));
        });

        return ResponseEntity.ok().body(listaConsultas);
    }

    @Operation(summary = "Buscar uma consulta.")
    @GetMapping("/buscar/{id}")
    public ResponseEntity<ConsultaRespostaDto> buscarPorId(@PathVariable Integer id) throws RequisicaoIncorretaExcecao, RecursoNaoEncontradoExcecao {
        if (id != null) {
            Optional<ConsultaEntidade> consultaEntidade = consultaServico.buscarPorId(id);

            if (consultaEntidade.isPresent()) {
                return ResponseEntity.ok().body(ConsultaRespostaDto.converterParaConsultaRespostaDto(consultaEntidade.get()));
            } else if (consultaEntidade.isEmpty()) {
                throw new RecursoNaoEncontradoExcecao(String.format("Tabela Consulta: registro com ID = %d não encontrado.", id));
            } 
        }

        throw new RequisicaoIncorretaExcecao("Tabela Consulta: não é possível buscar um registro com ID nulo.");
    }

    @Operation(summary = "Buscar todas as consultas de um paciente.")
    @GetMapping("/buscar_por_paciente/")
    public ResponseEntity<List<ConsultaRespostaDto>> buscarTodasConsultasDoPaciente(@RequestParam String nome) {
        List<ConsultaRespostaDto> listaConsultas = new ArrayList<ConsultaRespostaDto>();
        consultaServico.buscarTodasConsultasDoPaciente(nome).forEach(consultaEntidade -> {
            listaConsultas.add(ConsultaRespostaDto.converterParaConsultaRespostaDto(consultaEntidade));
        });

        return ResponseEntity.ok().body(listaConsultas);
    }

    @Operation(summary = "Buscar todas as consultas executas por um dentista.")
    @GetMapping("/buscar_por_dentista/")
    public ResponseEntity<List<ConsultaRespostaDto>> buscarTodasConsultasExecutasPeloDentista(@RequestParam String nome) {
        List<ConsultaRespostaDto> listaConsultas = new ArrayList<ConsultaRespostaDto>();
        consultaServico.buscarTodasConsultasExecutasPeloDentista(nome).forEach(consultaEntidade -> {
            listaConsultas.add(ConsultaRespostaDto.converterParaConsultaRespostaDto(consultaEntidade));
        });

        return ResponseEntity.ok().body(listaConsultas);
    }

    @Operation(summary = "Buscar todas as consultas marcadas numa determinada data.")
    @GetMapping("/buscar_por_data/{dia}/{mes}/{ano}")
    public ResponseEntity<List<ConsultaRespostaDto>> buscarTodasConsultasPorData(@PathVariable Integer dia, @PathVariable Integer mes, @PathVariable Integer ano) {
        List<ConsultaRespostaDto> listaConsultas = new ArrayList<ConsultaRespostaDto>();
        consultaServico.buscarTodasConsultasPorData(LocalDate.of(ano, mes, dia)).forEach(consultaEntidade -> {
            listaConsultas.add(ConsultaRespostaDto.converterParaConsultaRespostaDto(consultaEntidade));
        });

        return ResponseEntity.ok().body(listaConsultas);
    } 

    @Operation(summary = "Atualizar uma consulta.")
    @PutMapping("/atualizar")
    public ResponseEntity<ConsultaRespostaDto> atualizar(@RequestBody ConsultaRequisicaoDto consulta) throws RequisicaoIncorretaExcecao, RecursoNaoEncontradoExcecao {
        ConsultaEntidade consultaEntidade = consultaServico.atualizar(consulta.converterParaConsultaEntidade());
        
        if (consultaEntidade == null) {
            throw new RequisicaoIncorretaExcecao(String.format("Tabela Consulta: a requisição para atualizar o registro com ID = %d está incorreta.", consulta.getIdConsulta()));
        } else if (consultaEntidade.getIdConsulta() == null) {
            throw new RecursoNaoEncontradoExcecao(String.format("Tabela Consulta: o registro com ID = %d não foi encontrado, portanto não pode atualizá-lo.", consulta.getIdConsulta()));
        }

        return ResponseEntity.ok().body(ConsultaRespostaDto.converterParaConsultaRespostaDto(consultaEntidade));
    }   

    @Operation(summary = "Excluir uma consulta.")
    @DeleteMapping("/excluir/{id}") 
    public ResponseEntity<String> excluir(@PathVariable Integer id) throws RecursoNaoEncontradoExcecao {
        if (consultaServico.excluir(id)) {
            return ResponseEntity.ok().body(String.format("O registro com ID = %d foi excluído com sucesso.", id));
        } 

        throw new RecursoNaoEncontradoExcecao("Tabela Consulta: o registro com ID = %d não foi encontrado, portanto não pode excluí-lo.");
    }
}
