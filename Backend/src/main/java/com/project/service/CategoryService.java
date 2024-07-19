package com.project.service;

import java.util.List;

import com.project.dto.CategoriaDto;
import com.project.exception.ObjectNotFoundException;
import com.project.exception.UnauthorizedException;

public interface CategoryService {
	
	void creaCategoria(CategoriaDto corsoDto);
	
	List<CategoriaDto> trovaCategorie();
	
	void delete(int id) throws UnauthorizedException, ObjectNotFoundException;
	
	CategoriaDto getCategoriaById(int id);
	
	
	
}
