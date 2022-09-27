package com.empresa.clinicaodontologica.servico.implementacao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import com.empresa.clinicaodontologica.entidade.ConsultaEntidade;
import com.empresa.clinicaodontologica.entidade.DentistaEntidade;
import com.empresa.clinicaodontologica.entidade.EnderecoEntidade;
import com.empresa.clinicaodontologica.entidade.PacienteEntidade;
import com.empresa.clinicaodontologica.entidade.UsuarioEntidade;

@DataJpaTest
@ComponentScan({"com.empresa.clinicaodontologica.servico"})
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
public class ConsultaServicoTeste {
    @Autowired
    private ConsultaServicoImplementacao consultaServico;
    
    @Autowired
    private PacienteServicoImplementacao pacienteServico;
    
    @Autowired
    private DentistaServicoImplementacao dentistaServico;

    @Test
    public void testarCrudTabelaConsulta() {
        // Objetos de EnderecoEntidade
        EnderecoEntidade e1 = new EnderecoEntidade("Cidade A", "Bairro A", "Rua A", "1");
        EnderecoEntidade e2 = new EnderecoEntidade("Cidade B", "Bairro B", "Rua B", "2");
        EnderecoEntidade e3 = new EnderecoEntidade("Cidade C", "Bairro C", "Rua C", "3");
        EnderecoEntidade e4 = new EnderecoEntidade("Cidade D", "Bairro D", "Rua D", "4");
        EnderecoEntidade e5 = new EnderecoEntidade("Cidade E", "Bairro E", "Rua E", "5");
        EnderecoEntidade e6 = new EnderecoEntidade("Cidade F", "Bairro F", "Rua F", "6");
        EnderecoEntidade e7 = new EnderecoEntidade("Cidade G", "Bairro G", "Rua G", "7");
        EnderecoEntidade e8 = new EnderecoEntidade("Cidade H", "Bairro H", "Rua H", "8");
        EnderecoEntidade e9 = new EnderecoEntidade("Cidade I", "Bairro I", "Rua I", "9");
        EnderecoEntidade e10 = new EnderecoEntidade("Cidade J", "Bairro J", "Rua J", "10");

        // Objetos de UsuarioEntidade
        UsuarioEntidade u1 = new UsuarioEntidade("usuarioA@email.com", "senha A");
        UsuarioEntidade u2 = new UsuarioEntidade("usuarioB@email.com", "senha B");
        UsuarioEntidade u3 = new UsuarioEntidade("usuarioC@email.com", "senha C");
        UsuarioEntidade u4 = new UsuarioEntidade("usuarioD@email.com", "senha D");
        UsuarioEntidade u5 = new UsuarioEntidade("usuarioE@email.com", "senha E");
        UsuarioEntidade u6 = new UsuarioEntidade("usuarioF@email.com", "senha F");
        UsuarioEntidade u7 = new UsuarioEntidade("usuarioG@email.com", "senha G");
        UsuarioEntidade u8 = new UsuarioEntidade("usuarioH@email.com", "senha H");
        UsuarioEntidade u9 = new UsuarioEntidade("usuarioI@email.com", "senha I");
        UsuarioEntidade u10 = new UsuarioEntidade("usuarioJ@email.com", "senha J");

        // Lista de pacientes
        List<PacienteEntidade> listaPacientes = new ArrayList<PacienteEntidade>();

        listaPacientes.add(new PacienteEntidade("Nome A", "Sobrenome A", LocalDate.parse("1995-02-08"), "12345678911", e1, u1));
        listaPacientes.add(new PacienteEntidade("Nome B", "Sobrenome B", LocalDate.parse("1991-10-06"), "78945123012", e2, u2));
        listaPacientes.add(new PacienteEntidade("Nome C", "Sobrenome C", LocalDate.parse("2000-05-12"), "74125896301", e3, u3 ));
        listaPacientes.add(new PacienteEntidade("Nome D", "Sobrenome D", LocalDate.parse("2002-12-03"), "96385274103", e4, u4));
        listaPacientes.add(new PacienteEntidade("Nome E", "Sobrenome E", LocalDate.parse("1998-05-28"), "75315964280", e5, u5));
        
        // Lista de dentistas
        List<DentistaEntidade> listaDentistas = new ArrayList<DentistaEntidade>();

        listaDentistas.add(new DentistaEntidade("Nome A", "Sobrenome A", LocalDate.parse("1995-02-08"), "12345678911", "CRM-SP123456", true, e6, u6));
        listaDentistas.add(new DentistaEntidade("Nome B", "Sobrenome B", LocalDate.parse("1991-10-06"), "78945123012", "CRM-GO456789", false, e7, u7));
        listaDentistas.add(new DentistaEntidade("Nome C", "Sobrenome C", LocalDate.parse("2000-05-12"), "74125896301", "CRM-PR789741", true, e8, u8 ));
        listaDentistas.add(new DentistaEntidade("Nome D", "Sobrenome D", LocalDate.parse("2002-12-03"), "96385274103", "CRM-SC852369", false, e9, u9));
        listaDentistas.add(new DentistaEntidade("Nome E", "Sobrenome E", LocalDate.parse("1998-05-28"), "75315964280", "CRM-MT753159", true, e10, u10));

        // Lista de consultas
        List<ConsultaEntidade> listaConsultas = new ArrayList<ConsultaEntidade>();

        listaConsultas.add(new ConsultaEntidade(listaPacientes.get(0), listaDentistas.get(0), LocalDate.parse("2020-03-16"), LocalTime.parse("15:30:00")));
        listaConsultas.add(new ConsultaEntidade(listaPacientes.get(1), listaDentistas.get(1), LocalDate.parse("2020-08-19"), LocalTime.parse("14:45:00")));
        listaConsultas.add(new ConsultaEntidade(listaPacientes.get(2), listaDentistas.get(2), LocalDate.parse("2021-05-03"), LocalTime.parse("09:30:00")));
        listaConsultas.add(new ConsultaEntidade(listaPacientes.get(3), listaDentistas.get(3), LocalDate.parse("2022-07-23"), LocalTime.parse("07:50:00")));
        listaConsultas.add(new ConsultaEntidade(listaPacientes.get(4), listaDentistas.get(4), LocalDate.parse("2022-08-28"), LocalTime.parse("16:00:00")));

        // Criação de registros 
        for (int i = 0; i < listaConsultas.size(); i++) {
            assertTrue(pacienteServico.salvar(listaPacientes.get(i)).getIdPaciente() != null);
            assertTrue(dentistaServico.salvar(listaDentistas.get(i)).getIdDentista() != null);
            assertTrue(consultaServico.salvar(listaConsultas.get(i)).getIdConsulta() != null);
        }

        // Leitura de registros
        assertEquals(5, consultaServico.buscarTudo().size());
        assertTrue(consultaServico.buscarPorId(1).isPresent());
        assertEquals(1, consultaServico.buscarTodasConsultasDoPaciente("Nome A").size());
        assertEquals(1, consultaServico.buscarTodasConsultasExecutasPeloDentista("Nome A").size());
        assertEquals(1, consultaServico.buscarTodasConsultasPorData(LocalDate.parse("2020-03-16")).size());

        // Atualização de registros
        listaConsultas.get(0).setDataConsulta(LocalDate.parse("2019-03-16"));
        assertEquals(LocalDate.parse("2019-03-16"), consultaServico.atualizar(listaConsultas.get(0)).getDataConsulta());

        // Exclusão de registros
        assertTrue(consultaServico.excluir(5));

        // Impressão dos registros no console
        System.out.println("\nLista de pacientes registrados no banco de dados atualmente:\n");
        pacienteServico.buscarTudo().forEach(paciente -> System.out.println(paciente));

        System.out.println("\nLista de dentistas registrados no banco de dados atualmente:\n");
        dentistaServico.buscarTudo().forEach(dentista -> System.out.println(dentista));

        System.out.println("\nLista de consultas registradas no banco de dados atualmente:\n");
        consultaServico.buscarTudo().forEach(consulta -> System.out.println(consulta));
    }
}
