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

import com.empresa.clinicaodontologica.entidade.DentistaEntidade;
import com.empresa.clinicaodontologica.entidade.EnderecoEntidade;
import com.empresa.clinicaodontologica.entidade.UsuarioEntidade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ComponentScan({"com.empresa.clinicaodontologica.servico"})
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
public class DentistaServicoTeste {
    @Autowired
    private DentistaServicoImplementacao dentistaServico;

    @Test
    public void testarCrudTabelaDentista() {
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

        // Lista de dentistas
        List<DentistaEntidade> listaDentistas = new ArrayList<DentistaEntidade>();

        listaDentistas.add(new DentistaEntidade("Nome A", "Sobrenome A", LocalDate.parse("1995-02-08"), "12345678911", "CRM-SP123456", true, e1, u1));
        listaDentistas.add(new DentistaEntidade("Nome B", "Sobrenome B", LocalDate.parse("1991-10-06"), "78945123012", "CRM-GO456789", false, e2, u2));
        listaDentistas.add(new DentistaEntidade("Nome C", "Sobrenome C", LocalDate.parse("2000-05-12"), "74125896301", "CRM-PR789741", true, e3, u3 ));
        listaDentistas.add(new DentistaEntidade("Nome D", "Sobrenome D", LocalDate.parse("2002-12-03"), "96385274103", "CRM-SC852369", false, e4, u4));
        listaDentistas.add(new DentistaEntidade("Nome E", "Sobrenome E", LocalDate.parse("1998-05-28"), "75315964280", "CRM-MT753159", true, e5, u5));

        // Criação de registros 
        listaDentistas.forEach(dentista -> assertTrue(dentistaServico.salvar(dentista).getIdDentista() != null));

        // Leitura de registros
        assertEquals(5, dentistaServico.buscarTudo().size());
        assertTrue(dentistaServico.buscarPorId(1).isPresent());

        // Atualização de registros
        listaDentistas.get(0).getUsuario().setSenha("senha AA");
        listaDentistas.get(0).getEndereco().setCidade("Cidade AA");
        listaDentistas.get(0).setAtendeConvenio(false);

        DentistaEntidade dentistaAtualizado = dentistaServico.atualizar(listaDentistas.get(0));

        assertEquals("senha AA", dentistaAtualizado.getUsuario().getSenha());
        assertEquals("Cidade AA", dentistaAtualizado.getEndereco().getCidade());
        assertFalse(dentistaAtualizado.getAtendeConvenio());

        // Exclusão de registros
        assertTrue(dentistaServico.excluir(5));

        // Impressão dos registros no console
        System.out.println("\nLista de dentistas registrados no banco de dados atualmente:\n");
        dentistaServico.buscarTudo().forEach(dentista -> System.out.println(dentista));
    }
}
