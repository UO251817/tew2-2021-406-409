package com.tew.presentation;

import java.io.Serializable;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean
@SessionScoped
public class BeanSettings implements Serializable {
	private static final long serialVersionUID = 2L;
	private static final Locale ENGLISH = new Locale("en");
	private static final Locale SPANISH = new Locale("es");
	private Locale locale = new Locale("es");

	// uso de inyecciÃ³n de dependencia
	@ManagedProperty (value = "#{usuario}")
	private BeanUsuario usuario;

	@ManagedProperty (value = "#{publicacion}")
	private BeanPublicacion publicacion;

	@ManagedProperty (value = "#{amigo}")
	private BeanAmigo amigo;
	
	

	public BeanUsuario getUsuario() {
		return usuario;
	}

	public void setUsuario(BeanUsuario usuario) {
		this.usuario = usuario;
	}

	public BeanPublicacion getPublicacion() {
		return publicacion;
	}

	public void setPublicacion(BeanPublicacion publicacion) {
		this.publicacion = publicacion;
	}

	public BeanAmigo getAmigo() {
		return amigo;
	}

	public void setAmigo(BeanAmigo amigo) {
		this.amigo = amigo;
	}

	public Locale getLocale() {
		// Aqui habria que cambiar algo de cÃ³digo para coger locale del
		// navegador
		// la primera vez que se accede a getLocale(), de momento dejamos como
		// idioma de partida â€œesâ€�
		return (locale);
	}

	public void setSpanish(ActionEvent event) {
		locale = SPANISH;
		try {
			FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
			if (usuario != null)
				usuario.iniciaUsuario(null);
			
			if(publicacion != null) {
					publicacion.iniciaPublicacion(null);
			}

			if(amigo != null) {
					amigo.iniciaAmigo(null);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void setEnglish(ActionEvent event) {
		locale = ENGLISH;
		try {
			FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
			if (usuario != null)
				usuario.iniciaUsuario(null);
			
			if(publicacion != null) {
					publicacion.iniciaPublicacion(null);
			}

			if(amigo != null) {
					amigo.iniciaAmigo(null);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// Se inicia correctamente el Managed Bean inyectado si JSF lo hubiera
	// creado
	// y en caso contrario se crea.
	// (hay que tener en cuenta que es un Bean de sesiÃ³n)

	// Se usa @PostConstruct, ya que en el contructor no se sabe todavÃ­a si
	// el MBean ya estaba construido y en @PostConstruct SI.
	@PostConstruct
	public void init() {
		System.out.println("BeanSettings - PostConstruct");
		// Buscamos el alumno en la sesiÃ³n. Esto es un patrÃ³n factorÃ­a
		// claramente.
		usuario = (BeanUsuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(new String("usuario"));
		// si no existe lo creamos e inicializamos
		if (usuario == null) {
			System.out.println("BeanUsuario - No existia");
			usuario = new BeanUsuario();
			FacesContext.getCurrentInstance().getExternalContext()
					.getSessionMap().put("usuario", usuario);
		}
		
		publicacion = (BeanPublicacion) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(new String("publicacion"));
		if(publicacion==null) {
			System.out.println("No existe BeanPublicaciones");
			publicacion = new BeanPublicacion();
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("publicacion", publicacion);
		}

		amigo = (BeanAmigo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(new String("amigo"));
		if(amigo==null) {
			System.out.println("No existe BeanAmigos");
			amigo = new BeanAmigo();
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("amigo", amigo);
		}
	}

	// Es sÃ³lo a modo de traza.
	@PreDestroy
	public void end() {
		System.out.println("BeanSettings - PreDestroy");
	}

}

