package com.project.dto;

import com.project.model.TipoRuolo;

public class UtenteRuoloDto {
	private int id;
	private TipoRuolo tipology;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public TipoRuolo getTipology() {
		return tipology;
	}
	public void setTipology(TipoRuolo tipology) {
		this.tipology = tipology;
	}
	
	
}
