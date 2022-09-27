package com.empresa.clinicaodontologica.servico.implementacao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import com.empresa.clinicaodontologica.entidade.EnderecoEntidade;
import com.empresa.clinicaodontologica.entidade.PacienteEntidade;
import com.empresa.clinicaodontologica.entidade.UsuarioEntidade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ComponentScan({"com.empresa.clinicaodontologica.servico"})
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
public class PacienteServicoTeste {
    @Autowired
    private PacienteServicoImplementacao pacienteServico;

    @Test
    public void testarCrudTabelaPaciente() {
        // Objetos de EnderecoEntidade
        EnderecoEntidade e1 = new EnderecoEntidade("Cidade A", "Bairro A", "Rua A", "1");
        EnderecoEntidade e2 = new EnderecoEntidade("Cidade B", "Bairro B", "Rua B", "2");
        EnderecoEntidade e3 = new EnderecoEntidade("Cidade C", "Bairro C", "Rua C", "3");
        EnderecoEntidade e4 = new EnderecoEntidade("Cidade D", "Bairro D", "Rua D", "4");
        EnderecoEntidade e5 = new EnderecoEntidade("Cidade E", "Bairro E", "Rua E", "5");

        // Objetos de UsuarioEntidade
        UsuarioEntidade u1 = new UsuarioEntidade("usuarioA@email.com", "senha A");
        UsuarioEntidade u2 = new UsuarioEntidade("usuarioB@email.com", "senha B");
        UsuarioEntidade u3 = new UsuarioEntidade("usuarioC@email.com", "senha C");
        UsuarioEntidade u4 = new UsuarioEntidade("usuarioD@email.com", "senha D");
        UsuarioEntidade u5 = new UsuarioEntidade("usuarioE@email.com", "senha E");

        // Lista de pacientes
        List<PacienteEntidade> listaPacientes = new ArrayList<PacienteEntidade>();

        listaPacientes.add(new PacienteEntidade("Nome A", "Sobrenome A", LocalDate.parse("1995-02-08"), "12345678911", e1, u1));
        listaPacientes.add(new PacienteEntidade("Nome B", "Sobrenome B", LocalDate.parse("1991-10-06"), "78945123012", e2, u2));
        listaPacientes.add(new PacienteEntidade("Nome C", "Sobrenome C", LocalDate.parse("2000-05-12"), "74125896301", e3, u3 ));
        listaPacientes.add(new PacienteEntidade("Nome D", "Sobrenome D", LocalDate.parse("2002-12-03"), "96385274103", e4, u4));
        listaPacientes.add(new PacienteEntidade("Nome E", "Sobrenome E", LocalDate.parse("1998-05-28"), "75315964280", e5, u5));

        // Criação de registros 
        listaPacientes.forEach(paciente -> assertTrue(pacienteServico.salvar(paciente).getIdPaciente() != null));

        // Leitura de registros
        assertEquals(5, pacienteServico.buscarTudo().size());
        assertTrue(pacienteServico.buscarPorId(1).isPresent());

        // Atualização de registros
        listaPacientes.get(0).getUsuario().setSenha("senha AA");
        listaPacientes.get(0).getEndereco().setCidade("Cidade AA");
        listaPacientes.get(0).setSobrenome("Sobrenome AA");

        PacienteEntidade pacienteAtualizado = pacienteServico.atualizar(listaPacientes.get(0));

        assertEquals("senha AA", pacienteAtualizado.getUsuario().getSenha());
        assertEquals("Cidade AA", pacienteAtualizado.getEndereco().getCidade());
        assertEquals("Sobrenome AA", pacienteAtualizado.getSobrenome());

        // Exclusão de registros
        assertTrue(pacienteServico.excluir(5));

        // Impressão dos registros no console
        System.out.println("\nLista de pacientes registrados no banco de dados atualmente:\n");
        pacienteServico.buscarTudo().forEach(paciente -> System.out.println(paciente));
    }
}
