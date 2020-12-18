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

import com.tew.business.PublicacionService;
import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Publicacion;


@Path("/PublicacionServicesRs")
public interface PublicacionServicesRs extends PublicacionService {
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Publicacion> getPublicaciones();
	
	@GET
	@Path("{email}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Publicacion> getPublicaciones(@PathParam("email") String email) throws EntityNotFoundException;
	
	@GET
	@Path("{email}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Publicacion> getPublicacionesAmigos(@PathParam("email") String email);
	
	@GET
	@Path("{ID}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	Publicacion findById(@PathParam("id") Long id) throws EntityNotFoundException;

	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	void update(Publicacion p) throws EntityNotFoundException;
	
	@PUT
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	void save( Publicacion p) throws EntityAlreadyExistsException;
	
	@DELETE
	@Path("{email}")
	void delete(@PathParam("email") String email) throws EntityNotFoundException;
}
