package com.project.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ruolo")
public class Ruolo {

	@Id
	@Column(name = "ID_G")
	private int id;
	
	@Column(name = "tipologia")
	@Enumerated(EnumType.STRING)
	private TipoRuolo tipoRuolo;
	
	@ManyToMany (cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinTable( name = "utente_ruolo", joinColumns = @JoinColumn( name = "FK_R", referencedColumnName = "ID_G"),
	inverseJoinColumns = @JoinColumn(name = "FK_U", referencedColumnName = "ID_U")
	)
	private List<Utente> utenti = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TipoRuolo getTipoRuolo() {
		return tipoRuolo;
	}

	public void setTipoRuolo(TipoRuolo tipoRuolo) {
		this.tipoRuolo = tipoRuolo;
	}

	public List<Utente> getUtenti() {
		return utenti;
	}

	public void setUtenti(List<Utente> utenti) {
		this.utenti = utenti;
	}
	
	
}
