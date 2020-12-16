package com.tew.presentation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import com.tew.business.PublicacionService;
import com.tew.infraestructure.Factories;
import com.tew.model.Publicacion;

@ManagedBean
@SessionScoped
public class BeanPublicaciones implements Serializable {

	private static final long serialVersionUID = -1786803583194749666L;

	private Publicacion [] publicaciones = null;

	@ManagedProperty(value = "#{login}")
	private BeanLogin login;

	@ManagedProperty(value = "#{publicacion}")
	private BeanPublicacion publicacion;


	public BeanPublicacion getPublicacion() {
		return publicacion;
	}
	public void setPublicacion(BeanPublicacion publicacion) {

		this.publicacion = publicacion;
	}	
	public Publicacion[] getPublicaciones() {
		return publicaciones;
	}
	public void setPublicaciones(Publicacion[] publicaciones) {
		this.publicaciones= publicaciones;
	}

	ArrayList<Publicacion> auxiliar = new ArrayList<Publicacion>();



	public ArrayList<Publicacion> getAuxiliar() {
		return auxiliar;
	}
	public void setAuxiliar(ArrayList<Publicacion> auxiliar) {
		this.auxiliar = auxiliar;
	}



	public void iniciaPublicacion(ActionEvent event) {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle bundle = facesContext.getApplication().getResourceBundle(facesContext, "msgs");	
		publicacion.setID(null);
		publicacion.setEmail(bundle.getString("valorEmail"));
		publicacion.setTitulo(bundle.getString("valorTitulo"));
		publicacion.setTexto(bundle.getString("valorTexto"));
		publicacion.setFecha(0L);
	}


	public String listado(){

		PublicacionService service;
		try {

			service= Factories.services.createPublicacionService();
			publicaciones = (Publicacion []) service.getPublicaciones().toArray(new Publicacion[0]);
			return "exito";
		}
		catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	public String listadoEmail(){

		PublicacionService service;
		try {

			service= Factories.services.createPublicacionService();
			publicaciones = (Publicacion []) service.getPublicaciones(login.getEmail()).toArray(new Publicacion[0]);

			auxiliar = new ArrayList<Publicacion>();
			for(int i=0; i<publicaciones.length;i++) {

				auxiliar.add(publicaciones[i]);
			}

			return "exito2";
		}
		catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
	}


	public String listadoAmigos(){

		PublicacionService service;
		try {

			service= Factories.services.createPublicacionService();
			publicaciones = (Publicacion []) service.getPublicacionesAmigos(login.getEmail()).toArray(new Publicacion[0]);

			auxiliar = new ArrayList<Publicacion>();
			for(int i=0; i<publicaciones.length;i++) {

				auxiliar.add(publicaciones[i]);
			}

			return "exitoAmis";
		}
		catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
	}


	public String salva() {

		PublicacionService service;
		Publicacion p = new Publicacion();

		p.setEmail(login.getEmail());
		p.setTitulo(publicacion.getTitulo());
		p.setTexto(publicacion.getTexto());
		Date actual = new Date();
		long fecha= actual.getTime();
		p.setFecha(fecha);

		try {

			System.out.println(p.getTitulo());

			service = Factories.services.createPublicacionService();
			service.save(p);
			listadoEmail();

			return "exito4";
		}
		catch(Exception e) {
			e.printStackTrace();
			return "error";
		}	
	}


	@PostConstruct
	public void init() {

		System.out.println("BeanPublicaciones - PostConstruct");
		publicacion = (BeanPublicacion) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(new String("publicacion"));
		if(publicacion==null) {
			System.out.println("No existe BeanPublicaciones");
			publicacion = new BeanPublicacion();
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("publicacion", publicacion);
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

		System.out.println("BeanPublicaciones - PreDestroy");
	}	
}