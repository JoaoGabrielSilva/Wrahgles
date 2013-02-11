package br.com.wrahgles.roles;

import java.util.ArrayList;
import java.util.List;

import br.com.bronx.vraptor.restrictrex.interfaces.Profile;

public class Perfil implements Profile{
	
	private int accessLevel;
	private List<String> roles = new ArrayList<String>();
	private boolean loggedIn;
	
	@Override
	public boolean isLoggedIn() {
		return loggedIn;
	}
	@Override
	public List<String> getRoles() {
		return roles;
	}
	@Override
	public int getAccessLevel() {
       
		return this.accessLevel;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

}
