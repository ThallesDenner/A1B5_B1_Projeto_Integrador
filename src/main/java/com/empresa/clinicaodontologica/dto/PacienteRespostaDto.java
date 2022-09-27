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
public class PacienteRespostaDto {
    private static final ObjectMapper mapeador = JsonMapper.builder().findAndAddModules().build();
    // private static final Logger log = LogManager.getLogger(PacienteRespostaDto.class);
    
    private Integer idPaciente;
    private String nome;
    private String sobrenome;
    private LocalDate dataNascimento;  
    private String cpf;
    private EnderecoEntidade endereco;
    private UsuarioRespostaDto usuario;

    public static PacienteRespostaDto converterParaPacienteRespostaDto(PacienteEntidade pacienteEntidade) {
        try {
            if (pacienteEntidade != null) {
                log.info("Convertendo de PacienteEntidade para PacienteRespostaDto.");
                return mapeador.convertValue(pacienteEntidade, PacienteRespostaDto.class);
            }

            log.error("Não foi possível converter de PacienteEntidade para PacienteRespostaDto porque o objeto pacienteEntidade é nulo.");
            return null;
        } catch (IllegalArgumentException excecao) {
            log.error("Erro ao converter de PacienteEntidade para PacienteRespostaDto.");
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

    public UsuarioRespostaDto getUsuario() {
        return usuario;
    }  
}
