package com.empresa.clinicaodontologica.dto;

import java.time.LocalDate;
import java.time.LocalTime;

// import org.apache.logging.log4j.LogManager;
// import org.apache.logging.log4j.Logger;

import com.empresa.clinicaodontologica.entidade.ConsultaEntidade;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.extern.log4j.Log4j2;

@JsonIgnoreProperties(ignoreUnknown = true)  // quaisquer propriedades desconhecidas na entrada JSON serão ignoradas
@Log4j2
public class ConsultaRespostaDto {
    // private static final Logger log = LogManager.getLogger(ConsultaRespostaDto.class);
    
    private Integer idConsulta;
    private String nomePaciente;
    private String nomeDentista;
    private LocalDate dataConsulta;
    private LocalTime horaConsulta;

    public ConsultaRespostaDto(Integer idConsulta, String nomePaciente, String nomeDentista, LocalDate dataConsulta,
            LocalTime horaConsulta) {
        this.idConsulta = idConsulta;
        this.nomePaciente = nomePaciente;
        this.nomeDentista = nomeDentista;
        this.dataConsulta = dataConsulta;
        this.horaConsulta = horaConsulta;
    }

    public static ConsultaRespostaDto converterParaConsultaRespostaDto(ConsultaEntidade consultaEntidade) {
        if (consultaEntidade != null) {
            log.info("Convertendo de ConsultaEntidade para ConsultaRespostaDto.");
            return new ConsultaRespostaDto(
                consultaEntidade.getIdConsulta(),
                consultaEntidade.getPaciente().getNome(),
                consultaEntidade.getDentista().getNome(),
                consultaEntidade.getDataConsulta(),
                consultaEntidade.getHoraConsulta()
            );
        }

        log.error("Não foi possível converter de ConsultaEntidade para ConsultaRespostaDto porque o objeto consultaEntidade é nulo.");
        return null;
    }

    public ConsultaRespostaDto() {
    }

    public Integer getIdConsulta() {
        return idConsulta;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public String getNomeDentista() {
        return nomeDentista;
    }

    public LocalDate getDataConsulta() {
        return dataConsulta;
    }

    public LocalTime getHoraConsulta() {
        return horaConsulta;
    }
}
