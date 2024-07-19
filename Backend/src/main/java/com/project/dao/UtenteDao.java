package com.project.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.project.model.Utente;

public interface UtenteDao extends CrudRepository<Utente, Integer> {
	
	Optional<Utente> findByEmail(String email);
	
	Optional<Utente> findByEmailAndPassword(String email, String password);
	
	boolean existsByEmail(String email);
}
