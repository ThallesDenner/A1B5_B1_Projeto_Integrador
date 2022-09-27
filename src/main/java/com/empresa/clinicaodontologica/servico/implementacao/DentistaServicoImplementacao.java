package com.empresa.clinicaodontologica.servico.implementacao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

// import org.apache.logging.log4j.LogManager;
// import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.empresa.clinicaodontologica.entidade.DentistaEntidade;
import com.empresa.clinicaodontologica.entidade.TipoUsuarioEntidade;
import com.empresa.clinicaodontologica.repositorio.InterfaceDentistaRepositorio;
import com.empresa.clinicaodontologica.servico.InterfaceClinicaServico;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class DentistaServicoImplementacao implements InterfaceClinicaServico<DentistaEntidade> {
    // private static final Logger log = LogManager.getLogger(DentistaServicoImplementacao.class);
    private final InterfaceDentistaRepositorio dentistaRepositorio;

    public DentistaServicoImplementacao(InterfaceDentistaRepositorio dentistaRepositorio) {
        this.dentistaRepositorio = dentistaRepositorio;
    }

    @Override
    public DentistaEntidade salvar(DentistaEntidade dentista) {
        log.info("Tabela Dentista: inserindo um novo registro.");
        dentista.getUsuario().setTipoUsuario(TipoUsuarioEntidade.ROLE_DENTISTA);
        dentista.getUsuario().setDataHoraCadastro(LocalDateTime.now());
        return dentistaRepositorio.save(dentista);
    }

    @Override
    public List<DentistaEntidade> buscarTudo() {
        log.info("Tabela Dentista: buscando todos os registros.");
        return dentistaRepositorio.findAll();
    }

    @Override
    public Optional<DentistaEntidade> buscarPorId(Integer id) {
        log.info(String.format("Tabela Dentista: buscando registro com ID = %d.", id));
        return dentistaRepositorio.findById(id);
    }

    @Override
    public DentistaEntidade atualizar(DentistaEntidade dentista) {
        if (dentista.getIdDentista() != null) {
            Optional<DentistaEntidade> registroDentista = buscarPorId(dentista.getIdDentista());

            if (registroDentista.isPresent()) {
                log.info(String.format("Tabela Dentista: registro com ID = %d encontrado.", dentista.getIdDentista()));
                
                dentista.getUsuario().setIdUsuario(registroDentista.get().getUsuario().getIdUsuario());
                dentista.getEndereco().setIdEndereco(registroDentista.get().getEndereco().getIdEndereco());
                
                log.info(String.format("Tabela Dentista: atualizando registro com ID = %d.", dentista.getIdDentista()));
                return dentistaRepositorio.saveAndFlush(dentista);
            } else if (registroDentista.isEmpty()) {
                return new DentistaEntidade();
            }
        }

        return null;
    }

    @Override
    public Boolean excluir(Integer id) {
        if (id != null && dentistaRepositorio.existsById(id)) {
            dentistaRepositorio.deleteById(id);
            log.info(String.format("Tabela Dentista: o registro com ID = %d foi excluído.", id));
            return true;
        }
        
        return false;
    }
}
