package com.project.service;

import java.util.List;

import com.project.dto.AddCorsoRequestDto;
import com.project.dto.CorsoDto;
import com.project.dto.CorsoModificatoDto;
import com.project.model.Corso;

public interface CorsoService {
	void creaCorso (AddCorsoRequestDto addCorsoRequestDto);
	
	void modificaCorso(CorsoModificatoDto corsoDto);
	
	CorsoDto getCorsoById(int id);
	
	List<CorsoDto> getAllCorsi();
	
	List<Corso> findByCategoria(int id);
	
	void deleteByCategoria(int id);
	
}
