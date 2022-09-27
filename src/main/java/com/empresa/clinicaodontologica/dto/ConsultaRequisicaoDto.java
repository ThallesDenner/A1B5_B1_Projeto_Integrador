package com.empresa.clinicaodontologica.dto;

import java.time.LocalDate;
import java.time.LocalTime;

// import org.apache.logging.log4j.LogManager;
// import org.apache.logging.log4j.Logger;

import com.empresa.clinicaodontologica.entidade.ConsultaEntidade;
import com.empresa.clinicaodontologica.entidade.DentistaEntidade;
import com.empresa.clinicaodontologica.entidade.PacienteEntidade;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.extern.log4j.Log4j2;

@JsonIgnoreProperties(ignoreUnknown = true)  // quaisquer propriedades desconhecidas na entrada JSON serão ignoradas
@Log4j2
public class ConsultaRequisicaoDto {   
    // private static final Logger log = LogManager.getLogger(DentistaRequisicaoDto.class);
    private Integer idConsulta;
    private Integer idPaciente;
    private Integer idDentista;
    private LocalDate dataConsulta;
    private LocalTime horaConsulta;

    // public ConsultaEntidade converterParaConsultaEntidade(PacienteEntidade pacienteEntidade, DentistaEntidade dentistaEntidade) {
    //     log.info("Convertendo de ConsultaRequisicaoDto para ConsultaEntidade.");
    //     ConsultaEntidade consultaEntidade = new ConsultaEntidade(pacienteEntidade, dentistaEntidade, dataConsulta, horaConsulta);

    //     if (idConsulta != null) {
    //         consultaEntidade.setIdConsulta(idConsulta);
    //     }

    //     return consultaEntidade;
    // }

    public ConsultaEntidade converterParaConsultaEntidade(PacienteEntidade pacienteEntidade, DentistaEntidade dentistaEntidade) {
        log.info("Convertendo de ConsultaRequisicaoDto para ConsultaEntidade.");
        return new ConsultaEntidade(idConsulta, pacienteEntidade, dentistaEntidade, dataConsulta, horaConsulta);
    }

    public ConsultaEntidade converterParaConsultaEntidade() {
        log.info("Convertendo de ConsultaRequisicaoDto para ConsultaEntidade.");
        return new ConsultaEntidade(idConsulta, null, null, dataConsulta, horaConsulta);
    }

    public Integer getIdConsulta() {
        return idConsulta;
    }

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public Integer getIdDentista() {
        return idDentista;
    }

    public LocalDate getDataConsulta() {
        return dataConsulta;
    }

    public LocalTime getHoraConsulta() {
        return horaConsulta;
    }
}
