package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.dto.CategoriaDto;
import com.project.exception.ObjectNotFoundException;
import com.project.exception.UnauthorizedException;
import com.project.jwt.JWTTokenNeeded;
import com.project.jwt.Secured;
import com.project.service.CategoryService;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@JWTTokenNeeded
@Path("/categoria")
@Secured(role = "Admin")
public class CategoriaController {
	
	@Autowired
	private CategoryService categoriaService;
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response trovaCategorie() {
		try {
			List<CategoriaDto> categorieDto = categoriaService.trovaCategorie();
			
			return Response.status(Response.Status.OK).entity(categorieDto).build();
		} catch (Exception e) {
			
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	@DELETE
	@Path("/{id}")
	public Response cancellaCategoria(@PathParam("id") int id) {
		try {
			categoriaService.delete(id);
			
			return Response.status(Response.Status.OK).build();
		} catch (ObjectNotFoundException e ) {
			
			return Response.status(Response.Status.NOT_FOUND).build();
		} catch (UnauthorizedException e) {
			
			return Response.status(Response.Status.FORBIDDEN).build();
		}
	}
}
