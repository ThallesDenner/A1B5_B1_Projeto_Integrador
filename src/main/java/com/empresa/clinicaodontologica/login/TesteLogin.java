// package com.empresa.clinicaodontologica.login;

// import java.time.LocalDateTime;

// // import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.ApplicationArguments;
// import org.springframework.boot.ApplicationRunner;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.stereotype.Component;

// import com.empresa.clinicaodontologica.entidade.TipoUsuarioEntidade;
// import com.empresa.clinicaodontologica.entidade.UsuarioEntidade;
// import com.empresa.clinicaodontologica.servico.implementacao.UsuarioServicoImplementacao;

// @Component
// public class TesteLogin implements ApplicationRunner {
//     private final UsuarioServicoImplementacao usuarioServico;

//     // @Autowired  // esta anotação não é obrigatória a partir do spring 4!
//     public TesteLogin(UsuarioServicoImplementacao usuarioServico) {
//         this.usuarioServico = usuarioServico;
//     }

//     @Override
//     public void run(ApplicationArguments args) throws Exception {
//         BCryptPasswordEncoder codificadorSenha = new BCryptPasswordEncoder();

//         String senha1 = codificadorSenha.encode("My_password_1");
//         String senha2 = codificadorSenha.encode("My_password_2");

//         UsuarioEntidade usuario1 = new UsuarioEntidade(1, "nathalia@webmail.com", senha1, TipoUsuarioEntidade.ROLE_PACIENTE, LocalDateTime.now());
//         UsuarioEntidade usuario2 = new UsuarioEntidade(2, "vanessa@webmail.com", senha2, TipoUsuarioEntidade.ROLE_DENTISTA, LocalDateTime.now());

//         usuarioServico.salvar(usuario1);
//         usuarioServico.salvar(usuario2);
//     }
// }

// // Obs: Essa classe é só para testes, ela não é necessária para implementar a parte de segurança no spring.