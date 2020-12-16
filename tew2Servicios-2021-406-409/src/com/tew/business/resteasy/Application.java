package com.tew.business.resteasy;

import java.util.HashSet;
import java.util.Set;
import java.util.Collections;

import impl.tew.business.resteasy.AmigosServicesRsImpl;
import impl.tew.business.resteasy.PublicacionServicesRsImpl;
import impl.tew.business.resteasy.UsuariosServicesRsImpl;

@SuppressWarnings("unchecked")
public class Application extends javax.ws.rs.core.Application {
	
    private Set<Class<?>> classes = new HashSet<Class<?>>();
	
	public Application() {
		classes.add(AmigosServicesRsImpl.class);
		classes.add(UsuariosServicesRsImpl.class);
		classes.add(PublicacionServicesRsImpl.class);
	}
	
	@Override
	public Set<Class<?>> getClasses() {
		return classes;
	}
	
	@Override
	public Set<Object> getSingletons() {
		return Collections.EMPTY_SET;
	}

}
