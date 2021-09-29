package com.tiago.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import com.tiago.loja.DAO.CategoriaDAO;
import com.tiago.loja.DAO.ProdutoDAO;
import com.tiago.loja.UTIL.JpaUtil;
import com.tiago.loja.models.Categoria;
import com.tiago.loja.models.Produto;

public class CadastroDeProdutos {

	public static void main(String[] args) {
		cadastraProduto();
		
		//Pesquisar o produto da classe produto.
		EntityManager em = JpaUtil.getEntityManager();
		ProdutoDAO dao = new ProdutoDAO(em);
		
		Produto prod = dao.buscarPorId(1L);
		System.out.println(prod.getPreco());
		
		List<Produto> produto = dao.buscarTodos();
		produto.forEach(p -> System.out.println(p.getNome()));
		
		List<Produto> categoria = dao.buscarPorNomeCategoria("CELULARES");
		categoria.forEach(p2 -> System.out.println(p2.getNome()));
		
		BigDecimal precoProduto = dao.buscarPorPrecoProdutoComNome("samsung");
		System.out.println("Preço do Produto: "+precoProduto);
		
	}
	
	public static void cadastraProduto() {
		
		Categoria celulares = new Categoria("CELULARES");
		
		Produto celular = new Produto("samsung", "muito massa", new BigDecimal("800"), celulares);
		
		EntityManager em = JpaUtil.getEntityManager();
		
		ProdutoDAO dao = new ProdutoDAO(em); 
		CategoriaDAO daoCat = new CategoriaDAO(em);
		
		em.getTransaction().begin();
		
		daoCat.cadastra(celulares);
		
		dao.cadastrar(celular);
		
		em.getTransaction().commit();
		em.close();
	}
}
