package com.empresa.clinicaodontologica.excecao;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.log4j.Log4j2;

@ControllerAdvice  // esta anotação permite agrupar todas as @ExceptionHandlers em um único componente global de tratamento de erros.
@Log4j2
public class GlobalExcecao {
    // @ExceptionHandler(RecursoNaoEncontradoExcecao.class)  // esta anotação faz com que o método abaixo seja executado quando uma exceção do tipo RecursoNaoEncontradoExcecao ocorrer  
    @ExceptionHandler  // esta anotação faz com que o método abaixo seja executado quando uma exceção do tipo RecursoNaoEncontradoExcecao ocorrer
    public ResponseEntity<String> lancarRecursoNaoEncontradoExcecao(RecursoNaoEncontradoExcecao excecao) {
        log.error(excecao);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(excecao.getMessage());
    }

    // @ExceptionHandler(RequisicaoIncorretaExcecao.class)  // esta anotação faz com que o método abaixo seja executado quando uma exceção do tipo RequisicaoIncorretaExcecao ocorrer
    @ExceptionHandler  // esta anotação faz com que o método abaixo seja executado quando uma exceção do tipo RecursoNaoEncontradoExcecao ocorrer
    public ResponseEntity<String> lancarRequisicaoIncorretaExcecao(RequisicaoIncorretaExcecao excecao) {
        log.error(excecao);       
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(excecao.getMessage());
    }
}
