package com.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.CategoriaDao;
import com.project.dto.CategoriaDto;
import com.project.exception.ObjectNotFoundException;
import com.project.exception.UnauthorizedException;
import com.project.model.Categoria;

@Service
public class CategoriaServiceImpl implements CategoryService {

	@Autowired
	private CategoriaDao categoriaDao;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	
	
	@Override
	public List<CategoriaDto> trovaCategorie() {
		
		List<Categoria> categorie = (List<Categoria>) categoriaDao.findAll();
		List<CategoriaDto> categorieDto = new ArrayList<>();
		
			categorie.forEach(c -> categorieDto.add(modelMapper.map(c, CategoriaDto.class)));			
		
		return categorieDto;
	}

	@Override
	public void delete(int id) throws UnauthorizedException, ObjectNotFoundException {
		Optional<Categoria> categoriaOptional = categoriaDao.findById(id);
		
		if (categoriaOptional.isPresent()) {
			Categoria categoria = categoriaOptional.get();
			if (categoria.getCorsi().isEmpty()) {
				categoriaDao.delete(categoria);
			} else {
				throw new UnauthorizedException();
			}
		} else {
			throw new ObjectNotFoundException();
		}
		
		
	}

	@Override
	public CategoriaDto getCategoriaById(int id) {
		Optional<Categoria> optionalCategoria = categoriaDao.findById(id);
		
		if (optionalCategoria.isPresent()) {
			Categoria categoria = optionalCategoria.get();
			
			return modelMapper.map(categoria, CategoriaDto.class);
		}
		
		return new CategoriaDto();
	}


	@Override
	public void creaCategoria(CategoriaDto categoriaDto) {
		Categoria categoria = new Categoria();
		
		categoria.setNomeCategoria(categoriaDto.getNomeCategoria());
		
		categoriaDao.save(categoria);
		
	}
	
}
