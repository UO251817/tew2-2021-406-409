package com.tew.presentation;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.tew.business.AmigosService;
import com.tew.business.PublicacionService;
import com.tew.business.UsuariosService;
import com.tew.infraestructure.Factories;
import com.tew.model.Amigos;
import com.tew.model.Usuarios;


@ManagedBean
@SessionScoped
public class BeanUsuarios extends Usuarios implements  Serializable {

	private static final long serialVersionUID = -2530673901854142219L;

	private Usuarios[] usuarios = null;
	
	private String filtro="";
	
	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	//Inyeciion d dependencia
	@ManagedProperty(value="#{usuario}")
	private BeanUsuario usuario;
	
	@ManagedProperty(value = "#{login}")
	private BeanLogin login;
	
	public BeanUsuario getUsuario() { 
		return usuario;
	}

	public void setUsuario(BeanUsuario usuario) {
		this.usuario=usuario;
	}

	public Usuarios[] getUsuarios() {
		return(usuarios);
	}

	public void setUsuarios(Usuarios [] usuarios) {
		this.usuarios=usuarios;
	}

	public void iniciaUsuario(ActionEvent event) {

		FacesContext facesContext=FacesContext.getCurrentInstance();
		ResourceBundle bundle = facesContext.getApplication().getResourceBundle(facesContext, "msgs");	
		usuario.setEmail(bundle.getString("valorEmail"));
		usuario.setPasswd(bundle.getString("valorPasswd"));
		usuario.setPasswd2(bundle.getString("valorPasswd"));
		usuario.setRol("usuario");
		usuario.setNombre(bundle.getString("valorNombre"));		
	}


	public String listado() {

		UsuariosService service;
		try {

			service= Factories.services.createUsuariosService();
			usuarios = (Usuarios[]) service.getUsuarios().toArray(new Usuarios[0]);
			return "exito";
		}
		catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	public String filtrar() {

		UsuariosService service;
		try {
			
	
			service= Factories.services.createUsuariosService();
			usuarios = (Usuarios[]) service.getUsuarios(filtro,login.getEmail()).toArray(new Usuarios[0]);

			return "exito9";
			
		}
		catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	
	public String peticion(Usuarios u) {
		
		UsuariosService service;
		
		try {
			
			service= Factories.services.createUsuariosService();
			Amigos nuevoAmigo = new Amigos();
			
			nuevoAmigo.setEmail_usuario(login.getEmail());
			nuevoAmigo.setEmail_amigo(u.getEmail());
			nuevoAmigo.setAceptada(false);
			service.guardarAmigo(nuevoAmigo);
			filtrar();
			return "exito73";
			
		}
		catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
	}


	public String baja(Usuarios u) {
		
		FacesContext jsfCtx = FacesContext.getCurrentInstance();
		ResourceBundle bundle = jsfCtx.getApplication().getResourceBundle(jsfCtx, "msgs");
		FacesMessage msg = null;

		UsuariosService service;
		PublicacionService service2;
		AmigosService service3;
		try {
			
			if(sesion(u)) {

				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, bundle.getString("baja_form_sesion_error"), null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return "errorEstaSesion";
			}
			else {
				
				
			service = Factories.services.createUsuariosService();
			service2 = Factories.services.createPublicacionService();
			service3 = Factories.services.createAmigosService();
			System.out.println(u.getEmail());
			
			service3.delete(u.getEmail());
			service2.delete(u.getEmail());
			service.delete(u.getEmail());
			
			usuarios = (Usuarios[]) service.getUsuarios().toArray(new Usuarios[0]);
			return "exito";
			
			
			}	
		}
		catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	private boolean sesion(Usuarios u) {
		
		Map<String, Object> session =FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	
		if(session.containsKey(u.getEmail()))
			return true;
		return false;
	}

	public String edit() {

		UsuariosService service;
		try {
			service = Factories.services.createUsuariosService();
			Usuarios usuar = service.usuariosFindByEmail(usuario.getEmail());
			if(usuar==null) {
				return "exito";
			}
			return "error";
		}
		catch(Exception e) {
			e.printStackTrace();
			return "error";
		}	
	}

	public String salva() {
		
		FacesContext jsfCtx = FacesContext.getCurrentInstance();
		ResourceBundle bundle = jsfCtx.getApplication().getResourceBundle(jsfCtx, "msgs");
		FacesMessage msg = null;

		UsuariosService service;
		
		if(usuario.getPasswd().equals(usuario.getPasswd2())) {
			
			if(edit().equals("exito")) {
		
				try {

						service = Factories.services.createUsuariosService();
						service.save(usuario);
			
			
						usuarios = (Usuarios[])service.getUsuarios().toArray(new Usuarios[0]);
						
						
						Map<String, Object> session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
					
						session.put(usuario.getEmail(), usuario);
						
						login.setEmail(usuario.getEmail());
						login.setNombre(usuario.getNombre());
						login.setPasswd(usuario.getPasswd());
						login.setPasswd2(usuario.getPasswd2());
						login.setRol(usuario.getRol());
						
						return "exito";
				}
				catch(Exception e) {
					e.printStackTrace();
					return "error";
				}
			}else {
				
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, bundle.getString("alta_form_email_error"), null);
				FacesContext.getCurrentInstance().addMessage(null, msg);

				return "errorExisteUsuario";
				
				
			}
		}
		msg = new FacesMessage(FacesMessage.SEVERITY_WARN, bundle.getString("alta_form_password_error"), null);
		FacesContext.getCurrentInstance().addMessage(null, msg);

		return "errorcontra";
	}
	
	
	public String reset() {
		
		UsuariosService usu = Factories.services.createUsuariosService();
		try {
			

			Map<String, Object> session =FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
			Iterator<String> itr = session.keySet().iterator();
			while(itr.hasNext()){
			  String key = (String) itr.next();
			  session.remove(key);
			  }
			usu.reinicioBaseDatos();
			return "exitoTodosFuera";
			
		}catch(Exception e) {
			
			e.printStackTrace();
			return "error";
		}
		
	}


	@PostConstruct
	public void init() {

		System.out.println("BeanUsuarios - PostConstruct");
		
		usuario = (BeanUsuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(new String("usuario"));
		if(usuario==null) {
			System.out.println("No existe BeanUsuaruiso");
			usuario=new BeanUsuario();
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuario);
		}
		login = (BeanLogin) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(new String("login"));
		if(login==null) {
			System.out.println("No existe BeanLogin");
			login = new BeanLogin();
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("login", login);
		}
	}

	@PreDestroy
	public void end() {

		System.out.println("BeanUsuarios - PreDestroy");
	}
}
