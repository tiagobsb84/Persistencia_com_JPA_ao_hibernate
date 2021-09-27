package com.tiago.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.tiago.loja.models.Produto;

public class CadastroDeProdutos {

	public static void main(String[] args) {
		
		Produto celular = new Produto();
		celular.setNome("samsung");
		celular.setDescricao("muito massa");
		celular.setPreco(new BigDecimal("800"));
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("loja");
		EntityManager em = factory.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(celular);
		em.getTransaction().commit();
		em.close();
	}
}
