package com.analuizawho.desafio_produto;

import com.analuizawho.desafio_produto.entity.Produto;
import com.analuizawho.desafio_produto.repository.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DesafioProdutoApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private ProdutoRepository repository;

	private Produto produto;

	@Test
	void cadastro() {
		// Dado
		Produto produto = new Produto("Calça", "calça preta M", new BigDecimal(250.00), 20);

		// Quando
		var newProduto = repository.save(produto);

		// Então
		assertNotNull(newProduto, () -> "Não foi registrado o usuário");
		String idStr = "0";
		assertTrue(newProduto.getId().compareTo(idStr) > 0); // Foi criado um ID
	}

	@Test
	void listar() {
		// Dado
		Produto produto1 = new Produto("Calça", "calça preta M", new BigDecimal(250.00), 20);
		Produto produto2 = new Produto("Camisa", "camisa branca P", new BigDecimal(50.00), 35);

		// Quando
		repository.save(produto1);
		repository.save(produto2);
		List<Produto> produtoList = repository.findAll();

		// Então
		assertNotNull(produtoList, ()-> "Não foi registrado o usuário");
		assertTrue(produtoList.size() >= 2);

	}

	@Test
	void detalhar() {
		// Dado
		Produto produto = new Produto("Calça", "calça preta M", new BigDecimal(250.00), 20);
		var newProduto = repository.save(produto);

		// Quando
		var newProdutoById = repository.findById(newProduto.getId()).get();

		// Então
		assertNotNull(newProdutoById, ()-> "Produto não foi encontrado");
		assertEquals(newProduto.getId(), newProdutoById.getId());
	}

	@Test
	void atualizar() {
		// Dado
		Produto produto = new Produto("Calça", "calça preta M", new BigDecimal(250.00), 20);
		var newProduto = repository.save(produto);

		// Quando
		var produtoById = repository.findById(newProduto.getId()).get();
		produtoById.setNome("Calça social");
		produtoById.setDescricao("Calça social preta M");
		produtoById.setPreco(new BigDecimal(180.00));
		produtoById.setQuantidadeEmEstoque(15);

		var updateProduto = repository.save(produtoById);

		// Então
		assertNotNull(updateProduto, ()-> "Produto está nulo");
		assertEquals("Calça social", updateProduto.getNome());
		assertEquals("Calça social preta M", updateProduto.getDescricao());
		assertEquals(new BigDecimal(180.00), updateProduto.getPreco());
		assertEquals(15, updateProduto.getQuantidadeEmEstoque());
	}

	@Test
	void excluir() {
		// Dado
		Produto produto = new Produto("Calça", "calça preta M", new BigDecimal(250.00), 20);
		var newProduto = repository.save(produto);

		// Quando
		repository.deleteById(newProduto.getId());

		Optional<Produto> produtoById = repository.findById(newProduto.getId());

		// Então
		assertTrue(produtoById.isEmpty(), () -> "Produto não foi deletado corretamente");
	}

}
