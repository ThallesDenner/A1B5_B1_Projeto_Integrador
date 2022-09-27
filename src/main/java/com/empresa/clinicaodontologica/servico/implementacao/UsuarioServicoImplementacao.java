package com.empresa.clinicaodontologica.servico.implementacao;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.apache.logging.log4j.LogManager;
// import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.empresa.clinicaodontologica.entidade.UsuarioEntidade;
import com.empresa.clinicaodontologica.repositorio.InterfaceUsuarioRepositorio;
import com.empresa.clinicaodontologica.servico.InterfaceClinicaServico;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class UsuarioServicoImplementacao implements InterfaceClinicaServico<UsuarioEntidade>, UserDetailsService {
    // private static final Logger log = LogManager.getLogger(UsuarioServicoImplementacao.class);
    private final InterfaceUsuarioRepositorio usuarioRepositorio;

    public UsuarioServicoImplementacao(InterfaceUsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    @Override
    public UsuarioEntidade salvar(UsuarioEntidade usuario) {
        if (usuario != null) {
            log.info("Tabela Usuario: inserindo um novo registro.");
            return usuarioRepositorio.save(usuario);
        }

        log.error("Tabela Usuario: a requisição para inserir um novo registro está incorreta.");
        return null;
    }

    @Override
    public List<UsuarioEntidade> buscarTudo() {
        log.info("Tabela Usuario: buscando todos os registros.");
        return usuarioRepositorio.findAll();
    }

    @Override
    public Optional<UsuarioEntidade> buscarPorId(Integer id) {
        if (id != null) {
            Optional<UsuarioEntidade> usuario = usuarioRepositorio.findById(id);

            if (usuario.isPresent()) {
                log.info(String.format("Tabela Usuario: registro com ID = %d encontrado.", id));
                return usuario;
            } else if (usuario.isEmpty()) {
                log.warn(String.format("Tabela Usuario: registro com ID = %d não encontrado.", id));
                return usuario;
            }
        }

        log.warn("Tabela Usuario: não é possível buscar um registro com ID nulo.");
        return null;
    }

    @Override
    public UsuarioEntidade atualizar(UsuarioEntidade usuario) {
        if (usuario != null && usuarioRepositorio.existsById(usuario.getIdUsuario())) {
            log.info(String.format("Tabela Usuario: atualizando registro com ID = %d.", usuario.getIdUsuario()));
            return usuarioRepositorio.saveAndFlush(usuario);
        }

        log.error(String.format("Tabela Usuario: a requisição para atualizar o registro com ID = %d está incorreta ou o registro não existe.", usuario.getIdUsuario()));
        return null;
    }

    @Override
    public Boolean excluir(Integer id) {
        if (id != null && usuarioRepositorio.existsById(id)) {
            usuarioRepositorio.deleteById(id);
            log.info(String.format("Tabela Usuario: o registro com ID = %d foi excluído.", id));
            return true;
        }
        
        log.warn(String.format("Tabela Usuario: não é possível excluir o registro com ID = %d porque ele não existe.", id));
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usuarioRepositorio.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado!"));
    }
}
