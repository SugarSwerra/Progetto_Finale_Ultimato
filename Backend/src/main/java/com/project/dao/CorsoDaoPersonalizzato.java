package com.project.dao;

import java.util.List;

import com.project.model.Corso;

public interface CorsoDaoPersonalizzato {
	
	List<Corso> findCorso(String name, int idCategoria);
}
