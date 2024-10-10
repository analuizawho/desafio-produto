package com.analuizawho.desafio_produto.service;

import com.analuizawho.desafio_produto.dto.DadosAtualizarProduto;
import com.analuizawho.desafio_produto.dto.DadosCadastroProduto;
import com.analuizawho.desafio_produto.dto.DadosDetalhamentoProduto;
import com.analuizawho.desafio_produto.dto.DadosListagemProduto;
import com.analuizawho.desafio_produto.entity.Produto;
import com.analuizawho.desafio_produto.exception.ErrosDaApiException;
import com.analuizawho.desafio_produto.mapper.ProdutoMapper;
import com.analuizawho.desafio_produto.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    ProdutoMapper produtoMapper;

    @Transactional
    public DadosDetalhamentoProduto cadastrar(DadosCadastroProduto dto){
        var newProduto = produtoMapper.paraProduto(dto);
        validarCampos(newProduto);
        newProduto.setDataDeCriacao(Instant.now());
        var produto = produtoRepository.save(newProduto);
        return produtoMapper.paraDetalhamento(produto);
    }

    public List<DadosListagemProduto> listar(){
        var lista = produtoRepository.findAll();
        return produtoMapper.paraListagem(lista);
    }

    public DadosDetalhamentoProduto detalhar(String id){
        var produto = produtoRepository.findById(id).
                orElseThrow(() -> new NoSuchElementException("Produto com id " + id + " não encontrado"));
        return produtoMapper.paraDetalhamento(produto);
    }

    @Transactional
    public DadosDetalhamentoProduto atualizar(String id, DadosAtualizarProduto dto){
        var produto = produtoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Produto com id " + id + " não encontrado"));
        produto.atualizarInformacoes(dto);
        validarCampos(produto);
        produto.getDataDeCriacao();
        produto.setDataDeAtualizacao(Instant.now());
        produto = produtoRepository.save(produto);
        return produtoMapper.paraDetalhamento(produto);
    }

    private void validarCampos(Produto produto) {
        if (produto.getNome().matches(".*\\d.*")) {
            throw new ErrosDaApiException("nome", "Favor inserir apenas letras.");
        }
        if(produto.getPreco().compareTo(BigDecimal.ZERO) <= 0){
            throw new ErrosDaApiException("preço", "Favor inserir um valor positivo ou maior que zero.");
        }
        if(produto.getQuantidadeEmEstoque() <  0){
            throw new ErrosDaApiException("quantidadeEmEstoque", "Favor inserir um valor positivo.");
        }
    }
}

