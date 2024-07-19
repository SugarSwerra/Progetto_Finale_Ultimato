package com.project.dao;

import java.util.List;

import com.project.model.Corso;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class CorsoDaoImpl implements CorsoDaoPersonalizzato {

	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<Corso> findCorso(String nome, int idCategoria){
		String query = """
                     SELECT *\
                     FROM corso\
                     WHERE Nome_Corso LIKE :nome AND FK_CA = :id\
                     """;
		
		return (List<Corso>) entityManager.createQuery(query)
			    .setParameter("nome", nome)
			    .setParameter("id", idCategoria).getResultList();
	}

}
