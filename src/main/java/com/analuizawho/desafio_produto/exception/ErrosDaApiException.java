package com.analuizawho.desafio_produto.exception;

public class ErrosDaApiException extends RuntimeException{

    private final String campo;

    public ErrosDaApiException(String campo, String exception) {
        super(exception);
        this.campo = campo;
    }

    public String getCampo() {
        return campo;
    }

}
