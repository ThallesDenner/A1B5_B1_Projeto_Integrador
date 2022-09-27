package com.empresa.clinicaodontologica.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresa.clinicaodontologica.entidade.DentistaEntidade;

public interface InterfaceDentistaRepositorio extends JpaRepository<DentistaEntidade, Integer> {
    
}

// Obs: não é necessário colocar a anotação @Repository nas interfaces que estendem JpaRepository