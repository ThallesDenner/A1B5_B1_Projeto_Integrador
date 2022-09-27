package com.empresa.clinicaodontologica.login;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.empresa.clinicaodontologica.servico.implementacao.UsuarioServicoImplementacao;

@Configuration
@EnableWebSecurity
public class ConfiguracaoSeguranca extends WebSecurityConfigurerAdapter {
    // @Autowired
    // private UsuarioServicoImplementacao usuarioServico;

    // @Autowired
    // private BCryptPasswordEncoder codificadorSenha;

    private final UsuarioServicoImplementacao usuarioServico;
    private final BCryptPasswordEncoder codificadorSenha;

    public ConfiguracaoSeguranca(UsuarioServicoImplementacao usuarioServico, BCryptPasswordEncoder codificadorSenha) {
        this.usuarioServico = usuarioServico;
        this.codificadorSenha = codificadorSenha;
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
        .authorizeRequests()
        .antMatchers("/usuario/administrador").hasRole("ADMINISTRADOR")
        .antMatchers("/usuario/dentista").hasRole("DENTISTA")
        .antMatchers("/usuario/paciente").hasRole("PACIENTE")
        .anyRequest()
        .authenticated().and()
        .formLogin();
    }

    protected void configure(AuthenticationManagerBuilder autenticacao) throws Exception {
        autenticacao.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provedor = new DaoAuthenticationProvider();
        provedor.setPasswordEncoder(codificadorSenha);
        provedor.setUserDetailsService(usuarioServico);
        return provedor;
    }
}
