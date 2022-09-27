package com.empresa.clinicaodontologica.excecao;

// @ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class RequisicaoIncorretaExcecao extends Exception {

    public RequisicaoIncorretaExcecao(String mensagem) {
        super(mensagem);
    }
    
    // public String toString() {
    //     return String.format("Exceção: %s \nMensagem: %s", this.getClass().getName(), this.getMessage());
    // }
}
