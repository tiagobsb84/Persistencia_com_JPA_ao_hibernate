package com.tiago.loja.DAO;

import javax.persistence.EntityManager;

import com.tiago.loja.models.Categoria;

public class CategoriaDAO {

	private EntityManager em;
	
	public CategoriaDAO(EntityManager em) {
		this.em = em;
	}
	
	public void cadastra(Categoria categoria) {
		this.em.persist(categoria);
	}
	
	public void atualizar(Categoria categoria) {
		this.em.merge(categoria);
	}
	
	//O merge e para evitar uma exception ao remove.
	public void remover(Categoria categoria) {
		categoria = em.merge(categoria);
		this.em.remove(categoria);
	}
}
