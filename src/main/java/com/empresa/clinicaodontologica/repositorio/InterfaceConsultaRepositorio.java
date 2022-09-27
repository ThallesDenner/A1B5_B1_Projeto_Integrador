package com.empresa.clinicaodontologica.repositorio;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.empresa.clinicaodontologica.entidade.ConsultaEntidade;

public interface InterfaceConsultaRepositorio extends JpaRepository<ConsultaEntidade, Integer> {
    @Query("SELECT c FROM ConsultaEntidade c WHERE c.paciente.nome = ?1")
    List<ConsultaEntidade> buscarTodasConsultasDoPaciente(String nome);

    @Query("SELECT c FROM ConsultaEntidade c WHERE c.dentista.nome = ?1")
    List<ConsultaEntidade> buscarTodasConsultasExecutasPeloDentista(String nome);

    @Query("SELECT c FROM ConsultaEntidade c WHERE c.dataConsulta = ?1")
    List<ConsultaEntidade> buscarTodasConsultasPorData(LocalDate dataConsulta);
}

// Obs: não é necessário colocar a anotação @Repository nas interfaces que estendem JpaRepository