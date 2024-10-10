package com.analuizawho.desafio_produto.controller;

import com.analuizawho.desafio_produto.dto.DadosAtualizarProduto;
import com.analuizawho.desafio_produto.dto.DadosCadastroProduto;
import com.analuizawho.desafio_produto.dto.DadosDetalhamentoProduto;
import com.analuizawho.desafio_produto.dto.DadosListagemProduto;
import com.analuizawho.desafio_produto.repository.ProdutoRepository;
import com.analuizawho.desafio_produto.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @Autowired
    ProdutoRepository produtoRepository;

    @PostMapping
    public ResponseEntity<DadosDetalhamentoProduto> cadastro(@RequestBody @Valid DadosCadastroProduto dto){
        var produto = produtoService.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }

    @GetMapping
    public ResponseEntity<List<DadosListagemProduto>> listar(){
        var lista = produtoService.listar();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoProduto> detalhar(@PathVariable String id){
        var produto = produtoService.detalhar(id);
        return ResponseEntity.ok(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoProduto> atualizar(@PathVariable String id, @RequestBody DadosAtualizarProduto dto){
        var produto = produtoService.atualizar(id, dto);
        return ResponseEntity.ok(produto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id){
        produtoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
