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
import com.tew.business.UsuariosService;
import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Amigos;
import com.tew.model.Usuarios;


@Path("/UsuariosServiceRS")
public interface UsuariosServicesRs extends UsuariosService {
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Usuarios> getUsuarios();
	
	@GET
	@Path("{filter}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Usuarios> getUsuarios(@PathParam("filter") String filter, String email) throws EntityNotFoundException;
	
	@DELETE
	@Path("{email}")
	void delete(@PathParam("email") String email) throws EntityNotFoundException;
	
	@PUT
	@Path("{usu}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	void save(@PathParam("usu") Usuarios u) throws EntityAlreadyExistsException;
	
	@POST
	@Path("{usu}")
	void update(@PathParam("usu") Usuarios usu) throws EntityNotFoundException;
	
	@GET
	@Path("{email}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Usuarios findByEmail(@PathParam("email") String email) throws EntityNotFoundException;
	
	@PUT
	@Path("{email}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	void guardarAmigo(@PathParam("email") Amigos email) throws EntityAlreadyExistsException;
	
	@POST
	void reinicioBaseDatos();
}