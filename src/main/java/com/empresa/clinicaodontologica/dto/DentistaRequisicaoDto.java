package com.empresa.clinicaodontologica.dto;

import java.time.LocalDate;

// import org.apache.logging.log4j.LogManager;
// import org.apache.logging.log4j.Logger;

import com.empresa.clinicaodontologica.entidade.DentistaEntidade;
import com.empresa.clinicaodontologica.entidade.EnderecoEntidade;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import lombok.extern.log4j.Log4j2;

@JsonIgnoreProperties(ignoreUnknown = true)  // quaisquer propriedades desconhecidas na entrada JSON serão ignoradas
@Log4j2
public class DentistaRequisicaoDto {
    private static final ObjectMapper mapeador = JsonMapper.builder().findAndAddModules().build();
    // private static final Logger log = LogManager.getLogger(DentistaRequisicaoDto.class);
    
    private Integer idDentista;
    private String nome;
    private String sobrenome;
    private LocalDate dataNascimento;  
    private String cpf;
    private String crm;
    private Boolean atendeConvenio;
    private EnderecoEntidade endereco;
    private UsuarioRequisicaoDto usuario;

    public DentistaEntidade converterParaDentistaEntidade() {
        try {
            log.info("Convertendo de DentistaRequisicaoDto para DentistaEntidade.");
            return mapeador.convertValue(this, DentistaEntidade.class); 
        } catch (IllegalArgumentException excecao) {
            log.error("Erro ao converter de DentistaRequisicaoDto para DentistaEntidade.");
            excecao.printStackTrace();
            return null;
        }
    }

    public Integer getIdDentista() {
        return idDentista;
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

    public String getCrm() {
        return crm;
    }

    public Boolean getAtendeConvenio() {
        return atendeConvenio;
    }

    public EnderecoEntidade getEndereco() {
        return endereco;
    }

    public UsuarioRequisicaoDto getUsuario() {
        return usuario;
    }
}
