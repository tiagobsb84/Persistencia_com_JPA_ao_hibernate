package com.tiago.loja.DAO;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import com.tiago.loja.models.Produto;

public class ProdutoDAO {

	private EntityManager em;
	
	public ProdutoDAO(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Produto produto) {
		this.em.persist(produto);
	}
	
	public void atualizar(Produto produto) {
		this.em.merge(produto);
	}
	
	public void remover(Produto produto) {
		produto = em.merge(produto);
		this.em.remove(produto);
	}
	
	public Produto buscarPorId(Long id) {
		return em.find(Produto.class, id);
		
	}
	
	//createQuery() ele somente monta a Query o getResultList que faz dispara para o banco de dados.
	public List<Produto> buscarTodos(){
		String spql = "SELECT p FROM Produto p";
		return em.createQuery(spql, Produto.class).getResultList();		
	}
	
	//p.nome e o nome da variavel da classe produtos
	//:nome e parametro podia ser qualquer outro nome.
	//:nome podia ser substituido por ?1, ?2 dependendo da quantidade de nome, essa e a outra maneira 
	public List<Produto> buscarPorNome(String nome){
		String spql = "SELECT p FROM Produto p WHERE p.nome = :nome";
		return em.createQuery(spql, Produto.class)
				//setParameter(1, nome) seria para outra maneira.
				//setParameter e que vai indicar que o nome da spql e do parametro.
				.setParameter("nome", nome)
				.getResultList();
	}
	
	//Buscando por no da classe categoria.
	public List<Produto> buscarPorNomeCategoria(String nome){
		String spql = "SELECT p FROM Produto p WHERE p.categoria.nome = :nome";
		return em.createQuery(spql, Produto.class).setParameter("nome", nome).getResultList();
		
	}
	
	//Buscando apenas por um atributo
	public BigDecimal buscarPorPrecoProdutoComNome(String nome) {
		String spql = "SELECT p.preco FROM Produto p WHERE p.nome = :nome";
		//agora esta usando o getSingleResult() porque ele dispara apenas um atributo e não uma lista.
		return em.createQuery(spql, BigDecimal.class).setParameter("nome", nome).getSingleResult();
	}
}
