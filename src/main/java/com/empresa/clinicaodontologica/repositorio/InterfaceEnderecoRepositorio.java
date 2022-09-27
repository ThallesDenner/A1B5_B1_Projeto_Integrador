package com.empresa.clinicaodontologica.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresa.clinicaodontologica.entidade.EnderecoEntidade;

public interface InterfaceEnderecoRepositorio extends JpaRepository<EnderecoEntidade, Integer> {
    
}

// Obs: não é necessário colocar a anotação @Repository nas interfaces que estendem JpaRepository