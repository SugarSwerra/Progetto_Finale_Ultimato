package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import com.project.dto.AddCorsoRequestDto;
import com.project.dto.CorsoDto;
import com.project.jwt.JWTTokenNeeded;
import com.project.jwt.Secured;
import com.project.service.CorsoService;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

	
	@Path("/corso")
	public class CorsoController {
	
	@Autowired
	private CorsoService corsoService;
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllCourse() {
	  
	  try {
	
	    List<CorsoDto> listaCorsi = corsoService.getAllCorsi();
	    
	    return Response.status(Response.Status.OK).entity(listaCorsi).build();
	    
	  } catch(Exception e) {
	    return Response.status(Response.Status.BAD_REQUEST).build();
	  }
	}
	
	@JWTTokenNeeded
	@Secured(role = "Admin")
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response iscriviUtente(@Valid @RequestBody AddCorsoRequestDto corso) {
	  
	  try {
	    
	    corsoService.creaCorso(corso);
	    
	    return Response.status(Response.Status.OK).build();
	    
	  } catch(Exception e) {
	    return Response.status(Response.Status.BAD_REQUEST).build();
	  }
	}
	
	@JWTTokenNeeded
	@Secured(role = "Admin")
	@DELETE
	@Path("/category/{id}")
	public Response deleteCorsoByCategoria(@PathParam("id") int id) {
	  
	  try {
	
	    corsoService.deleteByCategoria(id);
	
	    return Response.status(Response.Status.OK).build();
	    
	  } catch(Exception e) {
	    return Response.status(Response.Status.BAD_REQUEST).build();
	  }
	}
	
	}