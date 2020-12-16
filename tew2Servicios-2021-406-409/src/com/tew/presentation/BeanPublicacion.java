package com.tew.presentation;

import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.ResourceBundle;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import com.tew.model.Publicacion;


@ManagedBean(name = "publicacion")
@SessionScoped
public class BeanPublicacion extends Publicacion implements Serializable {


	private static final long serialVersionUID = 2942110129546598207L;

	public BeanPublicacion() {
		iniciaPublicacion(null);
	}
	
	public void setPublicacion(Publicacion p) {
		setID(p.getID());
		setEmail(p.getEmail());
		setTitulo(p.getTitulo());
		setTexto(p.getTexto());
		setFecha(p.getFecha());	
	}
	
	public void iniciaPublicacion(ActionEvent event) {
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle bundle = facesContext.getApplication().getResourceBundle(facesContext, "msgs");	
		setID(null);
		setEmail(bundle.getString("valorDefectoNombre"));
		setTitulo(bundle.getString("tablaTitulo"));
		setTexto(bundle.getString("tablaTexto"));
		setFecha(0L);
	}
}
