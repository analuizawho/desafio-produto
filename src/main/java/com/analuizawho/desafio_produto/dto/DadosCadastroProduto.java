package com.analuizawho.desafio_produto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.Instant;

public record DadosCadastroProduto(

        @NotBlank
        String nome,

        @NotBlank
        String descricao,

        @NotNull
        BigDecimal preco,

        @NotNull
        Integer quantidadeEmEstoque,

        Instant dataDeCriacao
) {
}
