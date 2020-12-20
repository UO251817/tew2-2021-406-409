package com.tew.business.resteasy;

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.tew.business.UsuariosService;
import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Amigos;
import com.tew.model.Usuarios;


@Path("/UsuariosServicesRs")
public interface UsuariosServicesRs extends UsuariosService {
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Usuarios> getUsuarios();
	
	@GET
	@Path("{filtro}/{email}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	List<Usuarios> getUsuarios2(@PathParam("filtro") String filtro, @PathParam("email") String email);
	
	@GET
	@Path("buscaFiltro/{email}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	Usuarios findByEmail(@PathParam("email") String email) throws EntityNotFoundException;
	
	@DELETE
	@Path("{email}")
	void delete(@PathParam("email") String email) throws EntityNotFoundException;
	
	@PUT
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	void save(Usuarios u) throws EntityAlreadyExistsException;
	
	@POST
	void update(Usuarios usu) throws EntityNotFoundException;
	
	@PUT
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	void guardarAmigo(Amigos ami) throws EntityAlreadyExistsException;
	
	@POST
	void reinicioBaseDatos();
	
	
}