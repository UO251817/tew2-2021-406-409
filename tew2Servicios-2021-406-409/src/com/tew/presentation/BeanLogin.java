package com.tew.presentation;

import java.io.Serializable;
import java.util.Map;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import com.tew.business.LoginService;
import com.tew.infraestructure.Factories;
import com.tew.model.Usuarios;


@ManagedBean(name = "login")
@SessionScoped
public class BeanLogin extends Usuarios implements Serializable{

	private static final long serialVersionUID = 6940871785970821564L;
	
	private String email = "";
	private String password = "";
		
	Usuarios user = null;
	
	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String verify() {
		FacesContext jsfCtx = FacesContext.getCurrentInstance();
		ResourceBundle bundle = jsfCtx.getApplication().getResourceBundle(jsfCtx, "msgs");
		FacesMessage msg = null;
		LoginService login = Factories.services.createLoginService();

		user = login.verify(email, password);

		if (user != null) {
			
			putUserInSession(user);
			
			if(user.getRol().equals("administrador")) {
			
				return "successAdmin";
				
			}else {
				return "successUsuario";
			}
		}

		msg = new FacesMessage(FacesMessage.SEVERITY_WARN, bundle.getString("login_form_result_error"), null);
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return "login";
	}
	
	

	public void putUserInSession(Usuarios user) {
		Map<String, Object> session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		session.put(user.getEmail(), user);
	}

	public String logout(String userEmail) {
		
		FacesContext jsfCtx = FacesContext.getCurrentInstance();
		ResourceBundle bundle = jsfCtx.getApplication().getResourceBundle(jsfCtx, "msgs");
		FacesMessage msg = null;
		System.out.println("Intentamos borrar:" + user);
		System.out.println(userEmail);
		
		if(user != null) {
			
			Map<String, Object> session =FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
			session.remove(user.getEmail(), user);
			return "exito";
		}
		
		msg = new FacesMessage(FacesMessage.SEVERITY_WARN, bundle.getString("logout_form_result_error"), null);
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return "error";
	}
}
