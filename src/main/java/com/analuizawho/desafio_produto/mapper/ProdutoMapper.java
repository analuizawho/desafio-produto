package com.analuizawho.desafio_produto.mapper;

import com.analuizawho.desafio_produto.dto.DadosCadastroProduto;
import com.analuizawho.desafio_produto.dto.DadosDetalhamentoProduto;
import com.analuizawho.desafio_produto.dto.DadosListagemProduto;
import com.analuizawho.desafio_produto.entity.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    DadosDetalhamentoProduto paraDetalhamento(Produto produto);

    Produto paraProduto(DadosCadastroProduto dto);

    @Mapping(target = "id", ignore = true)
    List<DadosListagemProduto> paraListagem(List<Produto> produto);

}
