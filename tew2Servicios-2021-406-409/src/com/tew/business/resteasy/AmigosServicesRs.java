package com.tew.business.resteasy;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.tew.business.AmigosService;
import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Amigos;


@Path("/AmigosServicesRs")
public interface AmigosServicesRs extends AmigosService {
/*
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Amigos> getAmigos();
	*/
	@GET
	@Path("solicitudes/{email}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Amigos> getAmigos(@PathParam("email") String email) throws Exception;
	/*
	@GET
	@Path("listaposi/{email}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Amigos> getCandidatos(@PathParam("email") String email) throws Exception;
	
	@GET
	@Path("{email_usuario}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	Amigos findByEmail(@PathParam("email_usuario") String email_usuario) throws EntityNotFoundException;

	@POST
	void update(Amigos a) throws EntityNotFoundException;
	

	@PUT
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	void save(Amigos a) throws EntityAlreadyExistsException;
	
	@DELETE
	@Path("{email_amigo}")
	void delete(@PathParam("email_amigo") String email_amigo) throws EntityNotFoundException;
	
	@POST
	@Path("{email_usuario}")
	void aceptar(@PathParam("email_usuario") String email_usuario, String email_amigo) throws EntityNotFoundException;
*/
}
