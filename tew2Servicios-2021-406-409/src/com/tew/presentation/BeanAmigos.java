package com.tew.presentation;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import com.tew.business.AmigosService;
import com.tew.infraestructure.Factories;
import com.tew.model.Amigos;


@ManagedBean
@SessionScoped
public class BeanAmigos implements Serializable {

	private static final long serialVersionUID = 8292307775503190304L;
	
	private Amigos [] amigos = null;

	@ManagedProperty(value = "#{amigo}")
	private BeanAmigo amigo;
	

	@ManagedProperty(value = "#{login}")
	private BeanLogin login;
	
	
	
	public BeanAmigo getPublicacion() {
		return amigo;
	}
	public void setAmigo(BeanAmigo amigo) {

		this.amigo = amigo;
	}
	public Amigos[] getAmigos() {
		return amigos;
	}
	public void setAmigos(Amigos[] amigos) {
		this.amigos = amigos;
	}

	
	
	public void iniciaAmigos(ActionEvent event) {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle bundle = facesContext.getApplication().getResourceBundle(facesContext, "msgs");	
		amigo.setEmail_usuario(bundle.getString("valorEmail_usuario"));
		amigo.setEmail_amigo(bundle.getString("valorEmail_amigos"));
		amigo.setAceptada(true);
	}


	
	
	
	public String listado(){

		AmigosService service;
		try {

			service= Factories.services.createAmigosService();
			amigos = (Amigos []) service.getAmigos().toArray(new Amigos[0]);
			return "exito";
		}
		catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	
	public String listadoEmail2(){

		AmigosService service;
		try {

			service= Factories.services.createAmigosService();
			amigos = (Amigos []) service.getAmigos(login.getEmail()).toArray(new Amigos[0]);
			return "exito3";
		}
		catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	public String buscarAmigos() {
		
		
		AmigosService service;
		try {

			System.out.println("EMAIL CON LOGIN para buscar" + login.getEmail());
			service= Factories.services.createAmigosService();
			amigos = (Amigos []) service.getCandidatos(login.getEmail()).toArray(new Amigos[0]);
			return "exitoBuscar";
		}
		catch(Exception e) {
			e.printStackTrace();
			return "error";
		}

	}
	
	
	
	
	public String aceptar(String email_usuario) {
		
		AmigosService service;
		try {
			
			System.out.println("EMAIL_AMIGO: " + email_usuario);
			System.out.println("EMAIL CON LOGIN" + login.getEmail());
			service= Factories.services.createAmigosService();
			
			service.aceptar(email_usuario,login.getEmail());
			
			amigos = (Amigos []) service.getAmigos(login.getEmail()).toArray(new Amigos[0]);
			
			return "LCAP";
		}
		catch(Exception e) {
			e.printStackTrace();
			return "error";
		}		
	}

	
	
	
	public String salva() {

		AmigosService service;
		try {
			
			service = Factories.services.createAmigosService();
			service.save(amigo);
			amigos = (Amigos []) service.getAmigos().toArray(new Amigos[0]);
			return "exito";
		}
		catch(Exception e) {
			e.printStackTrace();
			return "error";
		}	
	}
	
	
	
	@PostConstruct
	public void init() {

		System.out.println("BeanAmigos - PostConstruct");
		amigo = (BeanAmigo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(new String("amigo"));
		if(amigo==null) {
			System.out.println("No existe BeanAmigos");
			amigo = new BeanAmigo();
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("amigo", amigo);
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

		System.out.println("BeanAmigos - PreDestroy");
	}
}
