package com.project.controller;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import com.project.dto.UtenteDto;
import com.project.dto.UtenteLoginRequestDto;
import com.project.dto.UtenteLoginResponseDto;
import com.project.dto.UtenteModificatoDto;
import com.project.dto.UtenteSignupDto;
import com.project.model.Ruolo;
import com.project.model.Utente;
import com.project.service.UtenteService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/utente")
public class UtenteController {
	
	@Autowired
	private UtenteService utenteService;
	
	public UtenteLoginResponseDto issueToken(String email) {
		byte[] secret = "f8932bg09bf302eb0eb08b2f0820h2n0f9h92e0".getBytes();
		Key key = Keys.hmacShaKeyFor(secret);
		
		Utente informazioniUtente = utenteService.getUserByEmail(email);
		Map<String, Object> map = new HashMap<>();
		map.put("nome", informazioniUtente.getNome());
		map.put("cognome", informazioniUtente.getCognome());
		map.put("email", email);
		
		List<String> ruoli = new ArrayList<>();
		
		for (Ruolo ruolo : informazioniUtente.getRuoli()) {
			ruoli.add(ruolo.getTipoRuolo().name());
		}
		
		map.put("ruoli", ruoli);
		
		Date creation = new Date();
		Date end = java.sql.Timestamp.valueOf(LocalDateTime.now().plusMinutes(15L));
		String tokenJwts = Jwts.builder()
				.setClaims(map)
				.setIssuer("http://localhost:8080")
				.setIssuedAt(creation)
				.setExpiration(end)
				.signWith(key)
				.compact();
		
		UtenteLoginResponseDto token = new UtenteLoginResponseDto();
		
		token.setToken(tokenJwts);
		token.setTokenCreationTime(creation);
		token.setTtl(end);
		
		return token;
	}
	
	@POST
	@Path("/reg")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response utenteSignUp(@Valid @RequestBody UtenteSignupDto utenteSignupDto) {
		if(!Pattern.matches("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,20}", utenteSignupDto.getPassword())) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		
		try {
			if(utenteService.existsByEmail(utenteSignupDto.getEmail())) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			}
			
			utenteService.UtenteSignup(utenteSignupDto);
			
			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		
			
			
			
		}
	
	@GET
	@Path("/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserByEmail(@PathParam("email") String email) {
		try {
			UtenteDto utente = utenteService.getUtenteDtoByEmail(email);
			
			return Response.status(Response.Status.OK).entity(utente).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	@DELETE
	@Path("/{email}")
	public Response deleteUser(@PathParam("email") String email) {
		try {
			utenteService.deleteUser(email);
			
			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateUser(@RequestBody UtenteModificatoDto utenteModificato) {
		try {
			utenteService.updateUserData(utenteModificato);
			
			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
		
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsers() {
		try {
			List <UtenteDto> listaUtenti = utenteService.getUsers();
			
			
			return Response.status(Response.Status.OK).entity(listaUtenti).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		
	}
	
	@PUT
	@Path("{id_utente}/corso/iscrizione/{id_corso}")
	public Response iscrizioneCorso(@PathParam("id_utente") int idUtente, @PathParam("id_corso") int idCorso) {
		try {
			utenteService.iscrizioneCorso(idUtente, idCorso);
			
			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	@PUT
	@Path("{id_utente}/corso/disiscrizione/{id_corso}")
	public Response deleteCourse(@PathParam ("id_utente") int idUtente, @PathParam("id_corso") int idCorso) {
		try {
			utenteService.discrizioneCorso(idUtente, idCorso);
			
			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(@RequestBody UtenteLoginRequestDto utenteLoginRequestDto) {
		try {
			if (utenteService.login(utenteLoginRequestDto)) {
				return Response.ok(issueToken(utenteLoginRequestDto.getEmail())).build();
			}
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		
		return Response.status(Response.Status.BAD_REQUEST).build();
	}
	
}
