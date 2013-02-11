package br.com.wrahgles.controller;

import br.com.bronx.vraptor.restrictrex.annotation.LoggedIn;
import br.com.bronx.vraptor.restrictrex.annotation.Roles;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;

@LoggedIn
@Roles(roles = {"admin", "moderador"})
@Resource
public class AdminController {
	
	public AdminController(
			){}
	
	@Path("/admin")
	public void admin(){}

}
