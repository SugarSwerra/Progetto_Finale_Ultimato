package com.project.dto;

import java.util.ArrayList;
import java.util.List;


import com.project.model.NomeCategoria;


public class CategoriaDto {
	private int id;
	private NomeCategoria nomeCategoria;
	private List<CorsoDto> corsiDto = new ArrayList<>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public NomeCategoria getNomeCategoria() {
		return nomeCategoria;
	}
	public void setNomeCategoria(NomeCategoria nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}
	public List<CorsoDto> getCorsiDto() {
		return corsiDto;
	}
	public void setCorsiDto(List<CorsoDto> corsiDto) {
		this.corsiDto = corsiDto;
	}
	
	
	
}
