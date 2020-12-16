package com.tew.presentation;

import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.ResourceBundle;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import com.tew.model.Amigos;



@ManagedBean(name = "amigo")
@SessionScoped
public class BeanAmigo extends Amigos implements Serializable {

	private static final long serialVersionUID = -2265078916548054891L;

	public BeanAmigo() {
		iniciaAmigo(null);
	}
	
	public void setAmigo(Amigos a) {
		setEmail_usuario(a.getEmail_usuario());
		setEmail_amigo(a.getEmail_amigo());
		setAceptada(a.isAceptada());
	}
	
	public void iniciaAmigo(ActionEvent event) {
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle bundle = facesContext.getApplication().getResourceBundle(facesContext, "msgs");	
		setEmail_usuario(bundle.getString("valorDefectoNombre"));
		setEmail_amigo(bundle.getString("valorDefectoNombre"));
		setAceptada(true);
	}
}