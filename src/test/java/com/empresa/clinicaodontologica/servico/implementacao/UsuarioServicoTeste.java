package com.empresa.clinicaodontologica.servico.implementacao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import com.empresa.clinicaodontologica.entidade.TipoUsuarioEntidade;
import com.empresa.clinicaodontologica.entidade.UsuarioEntidade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ComponentScan({"com.empresa.clinicaodontologica.servico"})
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
public class UsuarioServicoTeste {
    @Autowired
    private UsuarioServicoImplementacao usuarioServico;

    @Test
    public void testarCrudTabelaUsuario() {
        // Lista de usuários
        List<UsuarioEntidade> listaUsuarios = new ArrayList<UsuarioEntidade>();

        listaUsuarios.add(new UsuarioEntidade("usuarioA@email.com", "senha A"));
        listaUsuarios.add(new UsuarioEntidade("usuarioB@email.com", "senha B"));
        listaUsuarios.add(new UsuarioEntidade("usuarioC@email.com", "senha C"));
        listaUsuarios.add(new UsuarioEntidade("usuarioD@email.com", "senha D"));
        listaUsuarios.add(new UsuarioEntidade("usuarioE@email.com", "senha E"));
    
        // Criação de registros 
        listaUsuarios.forEach(usuario -> {
            usuario.setDataHoraCadastro(LocalDateTime.now());
            usuario.setTipoUsuario(TipoUsuarioEntidade.ROLE_PACIENTE);
            assertTrue(usuarioServico.salvar(usuario).getIdUsuario() != null);
        });

        // Leitura de registros
        assertEquals(5, usuarioServico.buscarTudo().size());
        assertTrue(usuarioServico.buscarPorId(1).isPresent());

        // Atualização de registros
        listaUsuarios.get(0).setSenha("senha AA");
        assertEquals("senha AA", usuarioServico.atualizar(listaUsuarios.get(0)).getSenha());

        // Exclusão de registros
        assertTrue(usuarioServico.excluir(5));
        
        // Impressão dos registros no console
        System.out.println("\nLista de usuários registrados no banco de dados atualmente:\n");
        usuarioServico.buscarTudo().forEach(usuario -> System.out.println(usuario));
    }
}
