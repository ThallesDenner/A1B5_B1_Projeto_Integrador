package com.empresa.clinicaodontologica.entidade;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
// import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Usuario")
@Data  // atalho para @ToString, @EqualsAndHashCode, @Getter, @Setter e @RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})  // veja a observação 1
public class UsuarioEntidade implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;
    
    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    // @Size(min = 6, max = 20)  // obs: max e length deve ser maior porque ao codificar a senha o tamanho aumenta 
    // @Column(length = 20)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(updatable = false)
    private TipoUsuarioEntidade tipoUsuario;

    @Column(updatable = false)
    private LocalDateTime dataHoraCadastro;

    public UsuarioEntidade(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(tipoUsuario.name());
        return Collections.singleton(grantedAuthority);
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // public UsuarioEntidade() {
    // }

    // @Override
    // public String toString() {
    //     return String.format(
    //         "Usuario {idUsuario: %d, email: %s, senha: %s, tipoUsuario: %s, dataHoraCadastro: %s}", 
    //         idUsuario, email, senha, tipoUsuario, dataHoraCadastro
    //     );
    // }

    // public Integer getIdUsuario() {
    //     return idUsuario;
    // }

    // public void setIdUsuario(Integer idUsuario) {
    //     this.idUsuario = idUsuario;
    // }

    // public String getEmail() {
    //     return email;
    // }

    // public void setEmail(String email) {
    //     this.email = email;
    // }

    // public String getSenha() {
    //     return senha;
    // }

    // public void setSenha(String senha) {
    //     this.senha = senha;
    // }

    // public TipoUsuarioEntidade getTipoUsuario() {
    //     return tipoUsuario;
    // }

    // public void setTipoUsuario(TipoUsuarioEntidade tipoUsuario) {
    //     this.tipoUsuario = tipoUsuario;
    // }

    // public LocalDateTime getDataHoraCadastro() {
    //     return dataHoraCadastro;
    // }

    // public void setDataHoraCadastro(LocalDateTime dataHoraCadastro) {
    //     this.dataHoraCadastro = dataHoraCadastro;
    // }
}


/*
 * Observação 1:
 * Para lidar com o problema relacionado à serialização do modelo usando a API Jackson quando os atributos do modelo 
 * têm um carregamento lento definido (FetchType.LAZY), temos que dizer ao serializador para ignorar a cadeia ou lixo 
 * útil que o Hibernate adiciona às classes, para que ele possa gerenciar o carregamento lento de dados declarando a 
 * anotação: @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}).
 * 
 * Links:
 * https://dzone.com/articles/key-java-annotations-to-build-full-spring-boot-res
 */