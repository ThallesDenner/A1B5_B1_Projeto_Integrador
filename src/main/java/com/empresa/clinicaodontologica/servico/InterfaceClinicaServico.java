package com.empresa.clinicaodontologica.servico;

import java.util.List;
import java.util.Optional;

public interface InterfaceClinicaServico<T> {
    T salvar(T t);
    List<T> buscarTudo();
    Optional<T> buscarPorId(Integer id);
    T atualizar(T t);
    Boolean excluir(Integer id);
}
