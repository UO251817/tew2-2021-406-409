package com.tew.business;


import com.tew.model.Usuarios;

public interface LoginService {
	
	Usuarios verify(String login, String password);
	boolean validLogin (String login, String password);

}
