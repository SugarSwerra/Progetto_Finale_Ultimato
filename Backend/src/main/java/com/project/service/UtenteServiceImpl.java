package com.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.project.dao.CorsoDao;
import com.project.dao.UtenteDao;
import com.project.dto.UtenteDto;
import com.project.dto.UtenteLoginRequestDto;
import com.project.dto.UtenteModificatoDto;
import com.project.dto.UtenteSignupDto;
import com.project.model.Corso;
import com.project.model.Utente;



@Service
public class UtenteServiceImpl implements UtenteService {

	@Autowired
	private UtenteDao utenteDao;
	@Autowired
	private CorsoDao corsoDao;
	
	private ModelMapper modelMapper =  new ModelMapper();
	
	@Override
	public void UtenteSignup(UtenteSignupDto utenteDto) {
		String sha256hex = DigestUtils.sha256Hex(utenteDto.getPassword());
		Utente utente = new Utente();
		
		utente.setNome(utenteDto.getNome());
		utente.setCognome(utenteDto.getCognome());
		utente.setEmail(utenteDto.getEmail());
		utente.setPassword(sha256hex);
		
		utenteDao.save(utente);
		
	}

	@Override
	public Utente getUserByEmail(String email) {
		Optional<Utente> utenteOptional = utenteDao.findByEmail(email);
		
		if(utenteOptional.isEmpty()) {
			return new Utente();
		}
		
		return utenteOptional.get();
	}

	@Override
	public void updateUserData(UtenteModificatoDto utenteModificatoDto) {
		Optional<Utente> utenteOptional = utenteDao.findByEmail(utenteModificatoDto.getEmail());
		
		if (utenteOptional.isPresent()) {
			Utente utenteDB = utenteOptional.get();
			
			utenteDB.setNome(utenteModificatoDto.getNome());
			utenteDB.setCognome(utenteModificatoDto.getCognome());
			utenteDB.setEmail(utenteModificatoDto.getEmail());
			
			utenteDao.save(utenteDB);
		}

	}

	@Override
	@Transactional
	public List<UtenteDto> getUsers() {
		
		List<Utente> utenti = (List<Utente>) utenteDao.findAll();
		List<UtenteDto> utentiDto = new ArrayList<>();
		

		
		utenti.forEach(u -> utentiDto.add(modelMapper.map(u, UtenteDto.class)));
		
		return utentiDto;
	}

	@Override
	public void deleteUser(String email) {
		Optional<Utente> utenteOptional = utenteDao.findByEmail(email);
		
		if (utenteOptional.isPresent()) {
			utenteDao.delete(utenteOptional.get());
		}
		
	}

	@Override
	public boolean existsByEmail(String email) {
		
		return utenteDao.existsByEmail(email);
	}
	
	@Override
	public boolean login(UtenteLoginRequestDto utenteLoginRequestDto) {
		String email = utenteLoginRequestDto.getEmail();
		String password = utenteLoginRequestDto.getPassword();
		
		Utente utente = getUserByEmail(email);
		
		if (utente == null) {
			return false;
		}
		
		String passwordCrpitata = DigestUtils.sha256Hex(password);
		boolean passwordMatch = passwordCrpitata.equals(utente.getPassword());
		
		return passwordMatch;
	}

	@Override
	@Transactional
	public void iscrizioneCorso(int idUtente, int idCorso) {
		
		Optional<Utente> utenteOptional = utenteDao.findById(idUtente);
		Optional<Corso> corsoOptional = corsoDao.findById(idCorso);
		
		if (utenteOptional.isPresent() && corsoOptional.isPresent()) {
			Utente utenteDB = utenteOptional.get();
			Corso corsoDB = corsoOptional.get();
			
			try {
				utenteDB.getCorsi().add(corsoDB);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			utenteDao.save(utenteDB);
		}
	}

	


	@Override
	public UtenteDto getUtenteDtoByEmail(String email) {
		Optional<Utente> utenteOptional = utenteDao.findByEmail(email);
		
		if (utenteOptional.isPresent()) {
			Utente utente = (Utente) utenteOptional.get();
			
			return modelMapper.map(utente, UtenteDto.class);
		}
		
		return new UtenteDto();
	}

	@Override
	@Transactional
	public void discrizioneCorso(int idUtente, int idCorso) {
		Optional<Utente> utenteOptional = utenteDao.findById(idUtente);
		Optional<Corso> corsoOptional = corsoDao.findById(idCorso);
		
		if (utenteOptional.isPresent() && corsoOptional.isPresent()) {
			Utente utenteDB = (Utente) utenteOptional.get();
			Corso corsoDB = (Corso) corsoOptional.get();
			
			if (utenteDB.getCorsi().contains(corsoDB)) {
				utenteDB.getCorsi().remove(corsoDB);
				
				utenteDao.save(utenteDB);
			}
		}
		
	}

}
