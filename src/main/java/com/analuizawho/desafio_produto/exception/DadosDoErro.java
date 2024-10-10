package com.analuizawho.desafio_produto.exception;

import org.springframework.validation.FieldError;

import java.util.Optional;

public class DadosDoErro {

    String mensagem;
    String campo;
    String dataDoErro;

    public DadosDoErro(String campo, String dataDoErro, String mensagem) {
        this.campo = campo;
        this.dataDoErro = dataDoErro;
        this.mensagem = mensagem;
    }

    public DadosDoErro(String dataDoErro, String mensagem) {
        this.dataDoErro = dataDoErro;
        this.mensagem = mensagem;
    }

    public DadosDoErro(FieldError fieldError, String dataFormatada, String camposInválidos) {
    }

    public DadosDoErro(Throwable cause, String dataFormatada, String tipoDosCamposSãoInválidos) {
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getDataDoErro() {
        return dataDoErro;
    }

    public void setDataDoErro(String dataDoErro) {
        this.dataDoErro = dataDoErro;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
