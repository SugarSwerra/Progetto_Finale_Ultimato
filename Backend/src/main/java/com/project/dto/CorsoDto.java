package com.project.dto;

import java.util.ArrayList;
import java.util.List;


public class CorsoDto {

	private int id;
	private String Nome_Corso;
	private String Descrizione_Breve;
	private String Descrizione_Completa;
	private int Durata;
	private CategoriaDto categoriaDto;
	private List<UtenteDto> utentiDto = new ArrayList<>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome_Corso() {
		return Nome_Corso;
	}
	public void setNome_Corso(String Nome_Corso) {
		this.Nome_Corso = Nome_Corso;
	}
	public String getDescrizione_Breve() {
		return Descrizione_Breve;
	}
	public void setDescrizione_Breve(String Descrizione_Breve) {
		this.Descrizione_Breve = Descrizione_Breve;
	}
	public String getDescrizione_Completa() {
		return Descrizione_Completa;
	}
	public void setDescrizione_Completa(String Descrizione_Completa) {
		this.Descrizione_Completa = Descrizione_Completa;
	}
	public int getDurata() {
		return Durata;
	}
	public void setDurata(int Durata) {
		this.Durata = Durata;
	}
	public CategoriaDto getCategoriaDto() {
		return categoriaDto;
	}
	public void setCategoriaDto(CategoriaDto categoriaDto) {
		this.categoriaDto = categoriaDto;
	}
	public List<UtenteDto> getUtentiDto() {
		return utentiDto;
	}
	public void setUtentiDto(List<UtenteDto> utentiDto) {
		this.utentiDto = utentiDto;
	}

	
	
	
	
}
