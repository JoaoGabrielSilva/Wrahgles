package br.com.wrahgles.controller;

import java.util.List;

import br.com.bronx.vraptor.restrictrex.annotation.LoggedIn;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.wrahgles.annotation.ReturnPageAfterLogin;
import br.com.wrahgles.dao.ConviteDAO;
import br.com.wrahgles.model.Convite;
import br.com.wrahgles.threads.ManagerEmail;

@LoggedIn
@Resource
public class AdminConviteController {
	
	private final ManagerEmail managerThread;
	private final ConviteDAO dao;
	private final Result result;
	
	public AdminConviteController(ManagerEmail managerThread, ConviteDAO dao,
			Result result) {
		super();
		this.managerThread = managerThread;
		this.dao = dao;
		this.result = result;
	}
	
	@ReturnPageAfterLogin
	@Path("/admin/convite/enviarConviteAmigo")
	public void enviarConviteAmigo() throws InterruptedException {
		managerThread.enviarConvite();
	}
	
	@ReturnPageAfterLogin
	@Path("/admin/convite/list")
	public void list(){
		List<Convite> listaConvite = dao.findByFlgEnvioEmail(true);
		result.include("lista", listaConvite.size());
	}
	
	
	
	
	
	

}
