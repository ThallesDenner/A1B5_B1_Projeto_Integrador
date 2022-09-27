package com.empresa.clinicaodontologica.servico.implementacao;

import java.util.List;
import java.util.Optional;

// import org.apache.logging.log4j.LogManager;
// import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.empresa.clinicaodontologica.entidade.EnderecoEntidade;
import com.empresa.clinicaodontologica.repositorio.InterfaceEnderecoRepositorio;
import com.empresa.clinicaodontologica.servico.InterfaceClinicaServico;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class EnderecoServicoImplementacao implements InterfaceClinicaServico<EnderecoEntidade> {
    // private static final Logger log = LogManager.getLogger(EnderecoServicoImplementacao.class);
    private final InterfaceEnderecoRepositorio enderecoRepositorio;

    public EnderecoServicoImplementacao(InterfaceEnderecoRepositorio enderecoRepositorio) {
        this.enderecoRepositorio = enderecoRepositorio;
    }

    @Override
    public EnderecoEntidade salvar(EnderecoEntidade endereco) {
        if (endereco != null) {
            log.info("Tabela Endereco: inserindo um novo registro.");
            return enderecoRepositorio.save(endereco);
        }

        log.error("Tabela Endereco: a requisição para inserir um novo registro está incorreta.");
        return null;
    }

    @Override
    public List<EnderecoEntidade> buscarTudo() {
        log.info("Tabela Endereco: buscando todos os registros.");
        return enderecoRepositorio.findAll();
    }

    @Override
    public Optional<EnderecoEntidade> buscarPorId(Integer id) {
        if (id != null) {
            Optional<EnderecoEntidade> endereco = enderecoRepositorio.findById(id);

            if (endereco.isPresent()) {
                log.info(String.format("Tabela Endereco: registro com ID = %d encontrado.", id));
                return endereco;
            } else if (endereco.isEmpty()) {
                log.warn(String.format("Tabela Endereco: registro com ID = %d não encontrado.", id));
                return endereco;
            }
        }

        log.warn("Tabela Endereco: não é possível buscar um registro com ID nulo.");
        return null;
    }

    @Override
    public EnderecoEntidade atualizar(EnderecoEntidade endereco) {
        if (endereco != null && enderecoRepositorio.existsById(endereco.getIdEndereco())) {
            log.info(String.format("Tabela Endereco: atualizando registro com ID = %d.", endereco.getIdEndereco()));
            return enderecoRepositorio.saveAndFlush(endereco);
        }

        log.error(String.format("Tabela Endereco: a requisição para atualizar o registro com ID = %d está incorreta ou o registro não existe.", endereco.getIdEndereco()));
        return null;
    }

    @Override
    public Boolean excluir(Integer id) {
        if (id != null && enderecoRepositorio.existsById(id)) {
            enderecoRepositorio.deleteById(id);
            log.info(String.format("Tabela Endereco: o registro com ID = %d foi excluído.", id));
            return true;
        }
        
        log.warn(String.format("Tabela Endereco: não é possível excluir o registro com ID = %d porque ele não existe.", id));
        return false;
    }
}
