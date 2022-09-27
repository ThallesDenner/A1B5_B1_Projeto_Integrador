package com.empresa.clinicaodontologica.dto;

import java.time.LocalDate;

// import org.apache.logging.log4j.LogManager;
// import org.apache.logging.log4j.Logger;

import com.empresa.clinicaodontologica.entidade.PacienteEntidade;
import com.empresa.clinicaodontologica.entidade.EnderecoEntidade;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import lombok.extern.log4j.Log4j2;

@JsonIgnoreProperties(ignoreUnknown = true)  // quaisquer propriedades desconhecidas na entrada JSON serão ignoradas
@Log4j2
public class PacienteRequisicaoDto {
    private static final ObjectMapper mapeador = JsonMapper.builder().findAndAddModules().build();
    // private static final Logger log = LogManager.getLogger(PacienteRequisicaoDto.class);
    
    private Integer idPaciente;
    private String nome;
    private String sobrenome;
    private LocalDate dataNascimento;  
    private String cpf;
    private EnderecoEntidade endereco;
    private UsuarioRequisicaoDto usuario;

    public PacienteEntidade converterParaPacienteEntidade() {
        try {
            log.info("Convertendo de PacienteRequisicaoDto para PacienteEntidade.");
            return mapeador.convertValue(this, PacienteEntidade.class); 
        } catch (IllegalArgumentException excecao) {
            log.error("Erro ao converter de PacienteRequisicaoDto para PacienteEntidade.");
            excecao.printStackTrace();
            return null;
        }
    }

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public EnderecoEntidade getEndereco() {
        return endereco;
    }

    public UsuarioRequisicaoDto getUsuario() {
        return usuario;
    }
}
