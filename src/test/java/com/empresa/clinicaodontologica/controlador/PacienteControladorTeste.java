package com.empresa.clinicaodontologica.controlador;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.empresa.clinicaodontologica.entidade.EnderecoEntidade;
import com.empresa.clinicaodontologica.entidade.PacienteEntidade;
import com.empresa.clinicaodontologica.entidade.UsuarioEntidade;
import com.empresa.clinicaodontologica.servico.implementacao.PacienteServicoImplementacao;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
public class PacienteControladorTeste {
    @Autowired
    private PacienteServicoImplementacao pacienteServico;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testarBuscarPacientePorId() throws Exception {
        EnderecoEntidade e1 = new EnderecoEntidade("Cidade A", "Bairro A", "Rua A", "1");
        UsuarioEntidade u1 = new UsuarioEntidade("usuarioA@email.com", "senha A");
        PacienteEntidade p1 = new PacienteEntidade("Nome A", "Sobrenome A", LocalDate.parse("1995-02-08"), "12345678911", e1, u1);
        assertTrue(pacienteServico.salvar(p1).getIdPaciente() != null);

        mockMvc.perform(MockMvcRequestBuilders.get("/paciente/buscar/{id}",1).accept(MediaType.APPLICATION_JSON))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
