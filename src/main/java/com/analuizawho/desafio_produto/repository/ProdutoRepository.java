package com.analuizawho.desafio_produto.repository;

import com.analuizawho.desafio_produto.entity.Produto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends MongoRepository<Produto, String> {}

