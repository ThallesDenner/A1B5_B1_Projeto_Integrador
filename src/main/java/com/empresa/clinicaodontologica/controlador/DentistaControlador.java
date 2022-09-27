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

import com.empresa.clinicaodontologica.dto.DentistaRespostaDto;
import com.empresa.clinicaodontologica.dto.DentistaRequisicaoDto;
import com.empresa.clinicaodontologica.entidade.DentistaEntidade;
import com.empresa.clinicaodontologica.excecao.RecursoNaoEncontradoExcecao;
import com.empresa.clinicaodontologica.excecao.RequisicaoIncorretaExcecao;
import com.empresa.clinicaodontologica.servico.implementacao.DentistaServicoImplementacao;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/dentista")
public class DentistaControlador {   
    private final DentistaServicoImplementacao dentistaServico;
  
    public DentistaControlador(DentistaServicoImplementacao dentistaServico) {
        this.dentistaServico = dentistaServico;
    }

    // @PostMapping("/salvar")
    // public ResponseEntity<DentistaEntidade> salvar(@RequestBody DentistaEntidade dentista) {
    //     DentistaEntidade dentistaEntidade = dentistaServico.salvar(dentista);
        
    //     if (dentistaEntidade == null) {
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    //     }
        
    //     return ResponseEntity.ok().body(dentistaEntidade);
    // }

    // @GetMapping("/buscar_tudo")
    // public ResponseEntity<List<DentistaEntidade>> buscarTudo() {
    //     return ResponseEntity.ok().body(dentistaServico.buscarTudo());
    // }

    // @GetMapping("/buscar/{id}")
    // public ResponseEntity<DentistaEntidade> buscarPorId(@PathVariable Integer id) {
    //     Optional<DentistaEntidade> dentistaEntidade = dentistaServico.buscarPorId(id);

    //     if (dentistaEntidade.isPresent()) {
    //         return ResponseEntity.ok().body(dentistaEntidade.get());
    //     } else if (dentistaEntidade.isEmpty()) {
    //         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    //     } 
        
    //     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    // }

    // @PutMapping("/atualizar")
    // public ResponseEntity<DentistaEntidade> atualizar(@RequestBody DentistaEntidade dentista) {
    //     DentistaEntidade dentistaEntidade = dentistaServico.atualizar(dentista);
        
    //     if (dentistaEntidade == null) {
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    //     }

    //     return ResponseEntity.ok().body(dentistaEntidade);
    // }  

    @Operation(summary = "Registrar um novo dentista.")
    @PostMapping("/salvar")
    public ResponseEntity<DentistaRespostaDto> salvar(@RequestBody DentistaRequisicaoDto dentista) throws RequisicaoIncorretaExcecao {
        try {
            DentistaEntidade dentistaEntidade = dentistaServico.salvar(dentista.converterParaDentistaEntidade());
            return ResponseEntity.ok().body(DentistaRespostaDto.converterParaDentistaRespostaDto(dentistaEntidade));
        } catch (Exception e) {
            throw new RequisicaoIncorretaExcecao("Tabela Dentista: a requisição para inserir um novo registro está incorreta.");
        }      
    }

    @Operation(summary = "Buscar todos os dentista.")
    @GetMapping("/buscar_tudo")
    public ResponseEntity<List<DentistaRespostaDto>> buscarTudo() {
        List<DentistaRespostaDto> listaDentistas = new ArrayList<DentistaRespostaDto>();
        dentistaServico.buscarTudo().forEach(dentistaEntidade -> {
            listaDentistas.add(DentistaRespostaDto.converterParaDentistaRespostaDto(dentistaEntidade));
        });

        return ResponseEntity.ok().body(listaDentistas);
    }

    @Operation(summary = "Buscar um dentista.")
    @GetMapping("/buscar/{id}")
    public ResponseEntity<DentistaRespostaDto> buscarPorId(@PathVariable Integer id) throws RequisicaoIncorretaExcecao, RecursoNaoEncontradoExcecao {
        if (id != null) {
            Optional<DentistaEntidade> dentistaEntidade = dentistaServico.buscarPorId(id);

            if (dentistaEntidade.isPresent()) {
                return ResponseEntity.ok().body(DentistaRespostaDto.converterParaDentistaRespostaDto(dentistaEntidade.get()));
            } else if (dentistaEntidade.isEmpty()) {
                throw new RecursoNaoEncontradoExcecao(String.format("Tabela Dentista: registro com ID = %d não encontrado.", id));
            } 
        }

        throw new RequisicaoIncorretaExcecao("Tabela Dentista: não é possível buscar um registro com ID nulo.");
    }

    @Operation(summary = "Atualizar um dentista.")
    @PutMapping("/atualizar")
    public ResponseEntity<DentistaRespostaDto> atualizar(@RequestBody DentistaRequisicaoDto dentista) throws RequisicaoIncorretaExcecao, RecursoNaoEncontradoExcecao {
        DentistaEntidade dentistaEntidade = dentistaServico.atualizar(dentista.converterParaDentistaEntidade());
        
        if (dentistaEntidade == null) {
            throw new RequisicaoIncorretaExcecao(String.format("Tabela Dentista: a requisição para atualizar o registro com ID = %d está incorreta.", dentista.getIdDentista()));
        } else if (dentistaEntidade.getIdDentista() == null) {
            throw new RecursoNaoEncontradoExcecao(String.format("Tabela Dentista: o registro com ID = %d não foi encontrado, portanto não pode atualizá-lo.", dentista.getIdDentista()));
        }

        return ResponseEntity.ok().body(DentistaRespostaDto.converterParaDentistaRespostaDto(dentistaEntidade));
    }  

    @Operation(summary = "Excluir um dentista.")
    @DeleteMapping("/excluir/{id}") 
    public ResponseEntity<String> excluir(@PathVariable Integer id) throws RecursoNaoEncontradoExcecao {
        if (dentistaServico.excluir(id)) {
            return ResponseEntity.ok().body(String.format("O registro com ID = %d foi excluído com sucesso.", id));
        } 

        throw new RecursoNaoEncontradoExcecao("Tabela Dentista: o registro com ID = %d não foi encontrado, portanto não pode excluí-lo.");
    }
}
