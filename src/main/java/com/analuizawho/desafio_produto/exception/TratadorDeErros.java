package com.analuizawho.desafio_produto.exception;

import jakarta.validation.UnexpectedTypeException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TratadorDeErros {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DadosDoErro> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Date data = new Date();
        String dataFormatada = sdf.format(data);
        var dadosDoErro = new DadosDoErro(ex.getFieldError(), dataFormatada, "Campos Inválidos");

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).contentType(MediaType.APPLICATION_JSON).body(dadosDoErro);
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    public ResponseEntity<DadosDoErro> unexpectedTypeException(UnexpectedTypeException ex) {
        Date data = new Date();
        String dataFormatada = sdf.format(data);
        var dadosDoErro = new DadosDoErro(ex.getCause() ,dataFormatada, "Tipo dos campos são Inválidos");

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).contentType(MediaType.APPLICATION_JSON).body(dadosDoErro);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<DadosDoErro> unexpectedTypeException(DataIntegrityViolationException ex) {
        Date data = new Date();
        String dataFormatada = sdf.format(data);
        var dadosDoErro = new DadosDoErro(ex.getCause(), dataFormatada, "Violação de integridade de dados no banco de dados");

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).contentType(MediaType.APPLICATION_JSON).body(dadosDoErro);
    }
}
