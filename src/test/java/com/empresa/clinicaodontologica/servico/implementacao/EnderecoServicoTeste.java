package com.empresa.clinicaodontologica.servico.implementacao;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import com.empresa.clinicaodontologica.entidade.EnderecoEntidade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ComponentScan({"com.empresa.clinicaodontologica.servico"})
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
public class EnderecoServicoTeste {
    @Autowired
    private EnderecoServicoImplementacao enderecoServico;

    @Test
    public void testarCrudTabelaEndereco() {
        // Lista de endereços
        List<EnderecoEntidade> listaEnderecos = new ArrayList<EnderecoEntidade>();
        
        listaEnderecos.add(new EnderecoEntidade("Cidade A", "Bairro A", "Rua A", "1"));
        listaEnderecos.add(new EnderecoEntidade("Cidade B", "Bairro B", "Rua B", "2"));
        listaEnderecos.add(new EnderecoEntidade("Cidade C", "Bairro C", "Rua C", "3"));
        listaEnderecos.add(new EnderecoEntidade("Cidade D", "Bairro D", "Rua D", "4"));
        listaEnderecos.add(new EnderecoEntidade("Cidade E", "Bairro E", "Rua E", "5"));

        // Criação de registros 
        listaEnderecos.forEach(endereco -> assertTrue(enderecoServico.salvar(endereco).getIdEndereco() != null));

        // Leitura de registros
        assertEquals(5, enderecoServico.buscarTudo().size());
        assertTrue(enderecoServico.buscarPorId(1).isPresent());

        // Atualização de registros
        listaEnderecos.get(0).setCidade("Cidade AA");
        assertEquals("Cidade AA", enderecoServico.atualizar(listaEnderecos.get(0)).getCidade());

        // Exclusão de registros
        assertTrue(enderecoServico.excluir(5)); 

        // Impressão dos registros no console
        System.out.println("\nLista de endereços registrados no banco de dados atualmente:\n");
        enderecoServico.buscarTudo().forEach(endereco -> System.out.println(endereco));
    }
}
