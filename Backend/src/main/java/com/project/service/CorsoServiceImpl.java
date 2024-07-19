package com.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.CategoriaDao;
import com.project.dao.CorsoDao;
import com.project.dto.AddCorsoRequestDto;
import com.project.dto.CorsoDto;
import com.project.dto.CorsoModificatoDto;
import com.project.model.Categoria;
import com.project.model.Corso;

@Service
public class CorsoServiceImpl implements CorsoService {
	
	@Autowired
	private CorsoDao corsoDao;
	
	@Autowired
	private CategoriaDao categoriaDao;
	
	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public void creaCorso(AddCorsoRequestDto corsoDto) {
		Corso corso = new Corso();
		
		corso.setNome_corso(corsoDto.getNome_corso());
		corso.setDescrizione_breve(corsoDto.getDescrizione_breve());
		corso.setDescrizione_completa(corsoDto.getDescrizione_completa());
		corso.setDurata(corso.getDurata());
		
		corsoDao.save(corso);
	}

	@Override
	public void modificaCorso(CorsoModificatoDto corsoModificatoDto) {
		Optional<Corso> optionalCorso = corsoDao.findById(corsoModificatoDto.getId());
		
		if (optionalCorso.isPresent()) {
			Corso corsoDB = optionalCorso.get();
			
			corsoDB.setNome_corso(corsoModificatoDto.getNome());
			corsoDB.setDescrizione_breve(corsoModificatoDto.getDescrizione_breve());
			corsoDB.setDescrizione_completa(corsoModificatoDto.getDescrizione_completa());
			corsoDB.setDurata(corsoModificatoDto.getDurata());
			
			corsoDao.save(corsoDB);
		}
		
	}


	@Override
	public CorsoDto getCorsoById(int id) {
		Optional<Corso> optionalCorso = corsoDao.findById(id);
		
		if (optionalCorso.isPresent()) {
			Corso corso = optionalCorso.get();
			
			return modelMapper.map(corso, CorsoDto.class);
		}
		
		return new CorsoDto();
	}

	@Override
	public List<CorsoDto> getAllCorsi() {
		
		List<Corso> corsi = (List<Corso>) corsoDao.findAll();
		List<CorsoDto> corsiDto = new ArrayList<>();
		
		corsi.forEach(u -> corsiDto.add(modelMapper.map(u, CorsoDto.class)));
		
		
		return corsiDto;
	}

	@Override
	public void deleteByCategoria(int id) {

		List<Corso> corsoDB = findByCategoria(id);
		
		corsoDao.deleteAll(corsoDB);
		
	}


	@Override
	public List<Corso> findByCategoria(int id) {
		Optional <Categoria> optionalCategoria = categoriaDao.findById(id);
		
		if (optionalCategoria.isPresent()) {
			return corsoDao.findByCategoria(optionalCategoria.get());
		} else {
			return new ArrayList<>();
		}
	}
	

}
