package com.empresa.clinicaodontologica;

// import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.springframework.boot.test.context.SpringBootTest;

import com.empresa.clinicaodontologica.servico.implementacao.ConsultaServicoTeste;
import com.empresa.clinicaodontologica.servico.implementacao.DentistaServicoTeste;
import com.empresa.clinicaodontologica.servico.implementacao.EnderecoServicoTeste;
import com.empresa.clinicaodontologica.servico.implementacao.PacienteServicoTeste;
import com.empresa.clinicaodontologica.servico.implementacao.UsuarioServicoTeste;

@Suite
@SuiteDisplayName("Teste da Camada de Serviço")
@IncludeEngines("junit-jupiter")
@SelectClasses({
	EnderecoServicoTeste.class,
	UsuarioServicoTeste.class,
	PacienteServicoTeste.class,
	DentistaServicoTeste.class,
    ConsultaServicoTeste.class
})
@SpringBootTest
class ClinicaOdontologicaApplicationTests {

	// @Test
	// void contextLoads() {
	// }

}
