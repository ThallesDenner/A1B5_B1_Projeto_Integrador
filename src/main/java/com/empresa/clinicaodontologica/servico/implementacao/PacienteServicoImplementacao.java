package com.empresa.clinicaodontologica.servico.implementacao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

// import org.apache.logging.log4j.LogManager;
// import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.empresa.clinicaodontologica.entidade.PacienteEntidade;
import com.empresa.clinicaodontologica.entidade.TipoUsuarioEntidade;
import com.empresa.clinicaodontologica.repositorio.InterfacePacienteRepositorio;
import com.empresa.clinicaodontologica.servico.InterfaceClinicaServico;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class PacienteServicoImplementacao implements InterfaceClinicaServico<PacienteEntidade> {
    // private static final Logger log = LogManager.getLogger(PacienteServicoImplementacao.class);
    private final InterfacePacienteRepositorio pacienteRepositorio;

    public PacienteServicoImplementacao(InterfacePacienteRepositorio pacienteRepositorio) {
        this.pacienteRepositorio = pacienteRepositorio;
    }

    @Override
    public PacienteEntidade salvar(PacienteEntidade paciente) {
        log.info("Tabela Paciente: inserindo um novo registro.");
        paciente.getUsuario().setTipoUsuario(TipoUsuarioEntidade.ROLE_PACIENTE);
        paciente.getUsuario().setDataHoraCadastro(LocalDateTime.now());
        return pacienteRepositorio.save(paciente);
    }

    @Override
    public List<PacienteEntidade> buscarTudo() {
        log.info("Tabela Paciente: buscando todos os registros.");
        return pacienteRepositorio.findAll();
    }

    @Override
    public Optional<PacienteEntidade> buscarPorId(Integer id) {
        log.info(String.format("Tabela Paciente: buscando registro com ID = %d.", id));
        return pacienteRepositorio.findById(id);
    }

    @Override
    public PacienteEntidade atualizar(PacienteEntidade paciente) {
        if (paciente.getIdPaciente() != null) {
            Optional<PacienteEntidade> registroPaciente = buscarPorId(paciente.getIdPaciente());

            if (registroPaciente.isPresent()) {
                log.info(String.format("Tabela Paciente: registro com ID = %d encontrado.", paciente.getIdPaciente()));
                
                paciente.getUsuario().setIdUsuario(registroPaciente.get().getUsuario().getIdUsuario());
                paciente.getEndereco().setIdEndereco(registroPaciente.get().getEndereco().getIdEndereco());
                
                log.info(String.format("Tabela Paciente: atualizando registro com ID = %d.", paciente.getIdPaciente()));
                return pacienteRepositorio.saveAndFlush(paciente);
            } else if (registroPaciente.isEmpty()) {
                return new PacienteEntidade();
            }
        }

        return null;
    }

    @Override
    public Boolean excluir(Integer id) {
        if (id != null && pacienteRepositorio.existsById(id)) {
            pacienteRepositorio.deleteById(id);
            log.info(String.format("Tabela Paciente: o registro com ID = %d foi excluído.", id));
            return true;
        }
        
        return false;
    }
}
