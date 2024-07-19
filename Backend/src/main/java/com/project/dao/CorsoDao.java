package com.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.project.model.Categoria;
import com.project.model.Corso;

public interface CorsoDao extends CrudRepository<Corso, Integer>, CorsoDaoPersonalizzato {
	List<Corso> findByCategoria(Categoria id);
	
	@Query
	(value = "SELECT * FROM corso WHERE nome LIKE :n AND durata=:d AND categoria:c", nativeQuery = true)
	List<Corso> searchCorso(@Param("n") String nome, @Param("d") int durata, @Param("c") Categoria categoria);
}
