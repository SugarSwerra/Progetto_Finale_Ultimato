package com.project.service;

import java.util.List;

import com.project.dto.UtenteDto;
import com.project.dto.UtenteLoginRequestDto;
import com.project.dto.UtenteModificatoDto;
import com.project.dto.UtenteSignupDto;
import com.project.model.Utente;


public interface UtenteService {
	
	void UtenteSignup(UtenteSignupDto utenteDto);
	
	void updateUserData(UtenteModificatoDto utente);
	
	Utente getUserByEmail(String email);
	
	UtenteDto getUtenteDtoByEmail(String email);
	
	List<UtenteDto> getUsers();
	
	boolean login(UtenteLoginRequestDto utenteLoginRequestDto);
	
	boolean existsByEmail(String email);
	
	void deleteUser(String email);
	
	void discrizioneCorso(int idUtente, int idCorso);
	
	void iscrizioneCorso(int idUtente, int idCorso);
}
