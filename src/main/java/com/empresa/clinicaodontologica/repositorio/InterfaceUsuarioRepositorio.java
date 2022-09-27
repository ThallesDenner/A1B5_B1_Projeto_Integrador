package com.empresa.clinicaodontologica.repositorio;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresa.clinicaodontologica.entidade.UsuarioEntidade;

@Transactional
public interface InterfaceUsuarioRepositorio extends JpaRepository<UsuarioEntidade, Integer> {
    // Optional<UsuarioEntidade> buscarPorEmail(String email);
    Optional<UsuarioEntidade> findByEmail(String email);
}

// Obs: não é necessário colocar a anotação @Repository nas interfaces que estendem JpaRepository