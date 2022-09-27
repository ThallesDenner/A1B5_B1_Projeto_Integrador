package com.empresa.clinicaodontologica.servico.implementacao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

// import org.apache.logging.log4j.LogManager;
// import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.empresa.clinicaodontologica.entidade.ConsultaEntidade;
import com.empresa.clinicaodontologica.repositorio.InterfaceConsultaRepositorio;
import com.empresa.clinicaodontologica.servico.InterfaceClinicaServico;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ConsultaServicoImplementacao implements InterfaceClinicaServico<ConsultaEntidade> {
    // private static final Logger log = LogManager.getLogger(ConsultaServicoImplementacao.class);
    private final InterfaceConsultaRepositorio consultaRepositorio;

    public ConsultaServicoImplementacao(InterfaceConsultaRepositorio consultaRepositorio) {
        this.consultaRepositorio = consultaRepositorio;
    }

    @Override
    public ConsultaEntidade salvar(ConsultaEntidade consulta) {
        log.info("Tabela Consulta: inserindo um novo registro.");
        return consultaRepositorio.save(consulta);
    }

    @Override
    public List<ConsultaEntidade> buscarTudo() {
        log.info("Tabela Consulta: buscando todos os registros.");
        return consultaRepositorio.findAll();
    }

    @Override
    public Optional<ConsultaEntidade> buscarPorId(Integer id) {
        log.info(String.format("Tabela Consulta: buscando registro com ID = %d.", id));
        return consultaRepositorio.findById(id);
    }

    @Override
    public ConsultaEntidade atualizar(ConsultaEntidade consulta) {
        if (consulta.getIdConsulta() != null) {
            Optional<ConsultaEntidade> registroConsulta = buscarPorId(consulta.getIdConsulta());

            if (registroConsulta.isPresent()) {
                log.info(String.format("Tabela Consulta: registro com ID = %d encontrado.", consulta.getIdConsulta()));
                
                consulta.setPaciente(registroConsulta.get().getPaciente());
                consulta.setDentista(registroConsulta.get().getDentista());
                // consulta.getPaciente().setIdPaciente(registroConsulta.get().getPaciente().getIdPaciente());
                // consulta.getDentista().setIdDentista(registroConsulta.get().getDentista().getIdDentista());
                
                log.info(String.format("Tabela Consulta: atualizando registro com ID = %d.", consulta.getIdConsulta()));
                return consultaRepositorio.saveAndFlush(consulta);
            } else if (registroConsulta.isEmpty()) {
                return new ConsultaEntidade();
            }
        }

        return null;
    } 

    // @Override
    // public ConsultaEntidade atualizar(ConsultaEntidade consulta) {
    //     if (consulta != null && consultaRepositorio.existsById(consulta.getIdConsulta())) {
    //         log.info(String.format("Tabela Consulta: atualizando registro com ID = %d.", consulta.getIdConsulta()));
    //         return consultaRepositorio.saveAndFlush(consulta);
    //     }

    //     log.error(String.format("Tabela Consulta: a requisição para atualizar o registro com ID = %d está incorreta ou o registro não existe.", consulta.getIdConsulta()));
    //     return null;
    // }

    @Override
    public Boolean excluir(Integer id) {
        if (id != null && consultaRepositorio.existsById(id)) {
            consultaRepositorio.deleteById(id);
            log.info(String.format("Tabela Consulta: o registro com ID = %d foi excluído.", id));
            return true;
        }
        
        return false;
    }

    public List<ConsultaEntidade> buscarTodasConsultasDoPaciente(String nome) {
        log.info(String.format("Tabela Consulta: buscando todas as consultas com paciente: %s.", nome));
        return consultaRepositorio.buscarTodasConsultasDoPaciente(nome);
    }

    public List<ConsultaEntidade> buscarTodasConsultasExecutasPeloDentista(String nome) {
        log.info(String.format("Tabela Consulta: buscando todas as consultas executadas com dentista: %s", nome));
        return consultaRepositorio.buscarTodasConsultasExecutasPeloDentista(nome);
    }

    public List<ConsultaEntidade> buscarTodasConsultasPorData(LocalDate dataConsulta) {
        log.info(String.format("Tabela Consulta: buscando todas as consultas com data: %s.", dataConsulta));
        return consultaRepositorio.buscarTodasConsultasPorData(dataConsulta);
    }
}
