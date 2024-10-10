package com.analuizawho.desafio_produto.entity;

import com.analuizawho.desafio_produto.dto.DadosAtualizarProduto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;

@Document(collection = "produtos")
public class Produto {

    @Id
    private String id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Integer quantidadeEmEstoque;
    private Instant dataDeCriacao;
    private Instant dataDeAtualizacao;

    public Produto() {
    }

    public Produto(String nome, String descricao, BigDecimal preco, Integer quantidadeEmEstoque) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    public Instant getDataDeAtualizacao() {
        return dataDeAtualizacao;
    }

    public void setDataDeAtualizacao(Instant dataDeAtualizacao) {
        this.dataDeAtualizacao = dataDeAtualizacao;
    }

    public Instant getDataDeCriacao() {
        return dataDeCriacao;
    }

    public void setDataDeCriacao(Instant dataDeCriacao) {
        this.dataDeCriacao = dataDeCriacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    public void setQuantidadeEmEstoque(Integer quantidadeEmEstoque) {
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    public void atualizarInformacoes(DadosAtualizarProduto dados){
        if(dados.nome() != null){
            this.nome = dados.nome();
        }

        if(dados.descricao() != null){
            this.descricao = dados.descricao();
        }

        if(dados.dataDeAtualizacao() != null){
            this.dataDeAtualizacao = dados.dataDeAtualizacao();
        }

        if (dados.preco() != null && dados.preco().compareTo(BigDecimal.ZERO) > 0) {
            this.preco = dados.preco();
        }

        if(dados.quantidadeEmEstoque() != null){
            this.quantidadeEmEstoque = dados.quantidadeEmEstoque();
        }
    }
}