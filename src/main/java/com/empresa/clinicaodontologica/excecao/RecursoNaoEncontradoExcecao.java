package com.empresa.clinicaodontologica.excecao;

// @ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RecursoNaoEncontradoExcecao extends Exception {

    public RecursoNaoEncontradoExcecao(String mensagem) {
        super(mensagem);
    }

    // public String toString() {
    //     return String.format("Exceção: %s \nMensagem: %s", this.getClass().getName(), this.getMessage());
    // }
}
