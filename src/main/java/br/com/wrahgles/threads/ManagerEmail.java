package br.com.wrahgles.threads;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.view.Results;
import br.com.wrahgles.controller.AdminController;
import br.com.wrahgles.dao.ConviteDAO;
import br.com.wrahgles.dao.UsuarioDAO;
import br.com.wrahgles.model.Convite;
import br.com.wrahgles.model.Notificacao;
import br.com.wrahgles.model.Usuario;
import br.com.wrahgles.properties.AdminProperties;

@Component
public class ManagerEmail {
    
	//controle Therad Convite
	static int threadConviteRunning = 0;
	static int threadConviteLimit = 4;
	
	//controle Thread notificacao
	static int threadNotificacaoRunning = 0;
	static int threadNotificacaoLimit = 4;
	
	private final Result result;
	private AdminProperties properties;
	private final ConviteDAO dao;
	private final UsuarioDAO daoUser;
	
	
	public ManagerEmail(Result result, AdminProperties properties, ConviteDAO dao, UsuarioDAO daoUser){
		this.result = result;
		this.properties = properties;
		this.dao = dao;
		this.daoUser = daoUser;
	}
	
	public void enviarConvite()throws InterruptedException{
		List<Convite> listaConvite = new ArrayList<Convite>();
		listaConvite = dao.findByFlgEnvioEmail(true);
		
		for(Convite convite : listaConvite){
			if(convite != null) {
				ConviteImportThread iit = new ConviteImportThread(convite);
				
				//atualiza flag com status de email enviado!
				convite.setFlgEnvioEmail(false);
				dao.update(convite);
				
				while(ManagerEmail.threadConviteRunning >= ManagerEmail.threadConviteLimit){
					Thread.sleep((int) (Math.random() * 1000));
				}
				
				ManagerEmail.threadConviteRunning = ManagerEmail.threadConviteRunning + 1;
				iit.start();
			}
		}
		
		result.include("mensagem", properties.getConvite_enviado());
		result.use(Results.logic()).redirectTo(AdminController.class).admin();
	}
	
	//multi-thread envio de email marketing
	
	public void enviarNotificacaoCliente(Notificacao notificacao) throws InterruptedException{
		List<Usuario> lista = new ArrayList<Usuario>();
		lista = daoUser.findByFlgInfoWrahgles(true);
		
		for(Usuario usuario : lista){
			if(usuario != null){
				NotificacaoImportThread iit = new NotificacaoImportThread(notificacao, usuario);
				
				while (ManagerEmail.threadNotificacaoRunning >= ManagerEmail.threadNotificacaoLimit){
					Thread.sleep((int) (Math.random() * 1000));
				}
				
				ManagerEmail.threadNotificacaoRunning = ManagerEmail.threadNotificacaoRunning + 1;
				iit.start();
			}
		}
		
		result.include("mensagem", properties.getNotificacao_enviado());
		result.use(Results.logic()).redirectTo(AdminController.class).admin();
		
		
	}
	
	
}
