package com.analuizawho.desafio_produto.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record DadosDetalhamentoProduto(
        String id,

        String nome,

        String descricao,

        BigDecimal preco,

        Integer quantidadeEmEstoque,

        Instant dataDeCriacao,

        Instant dataDeAtualizacao
) {
}
