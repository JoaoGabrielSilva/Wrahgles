package br.com.wrahgles.facade;

import org.apache.commons.mail.EmailException;

import br.com.caelum.vraptor.ioc.Component;
import br.com.wrahgles.dao.UsuarioAmigoDAO;
import br.com.wrahgles.dao.UsuarioDAO;
import br.com.wrahgles.email.Email;
import br.com.wrahgles.email.TemplateEmail;
import br.com.wrahgles.exception.UsuarioAdicionaEleMesmoException;
import br.com.wrahgles.exception.UsuarioJaFoiAdicionadoException;
import br.com.wrahgles.exception.UsuarioNaoExisteException;
import br.com.wrahgles.exception.UsuarioNaoTeAdicionouException;
import br.com.wrahgles.exception.UsuarioDeveConfirmarAmigoException;
import br.com.wrahgles.model.Usuario;
import br.com.wrahgles.model.UsuarioAmigo;
import br.com.wrahgles.model.UsuarioAmigoPK;

@Component
public class PerfilFacade implements PerfilInterface {

	private final UsuarioDAO dao;
	private final UsuarioAmigoDAO daoAmigo;
	private final Email email;
	private final TemplateEmail template;
	
	public PerfilFacade(UsuarioDAO dao, UsuarioAmigoDAO daoAmigo, Email email, TemplateEmail template){
		this.dao = dao;
		this.daoAmigo = daoAmigo;
		this.email = email;
		this.template = template;
	}
	
	
	@Override
	public void adicionarAmigo(Usuario usuarioLogado, Long idAmigo)
			throws UsuarioNaoExisteException, UsuarioAdicionaEleMesmoException,
			UsuarioJaFoiAdicionadoException, UsuarioDeveConfirmarAmigoException {
		
		//verifica se existe o usuario
		String origemFoto;
		Usuario user = dao.findById(idAmigo);
		if(user == null){
			throw new UsuarioNaoExisteException();
		}
		UsuarioAmigoPK pk = new UsuarioAmigoPK();
		pk.setUsuario(usuarioLogado);
		pk.setUsuarioAmigo(user);
		
		//usuario add ele mesmo
		if(idAmigo.longValue() == usuarioLogado.getId().longValue()){
			throw new UsuarioAdicionaEleMesmoException();
		}
		
		//usuario add alguem que ja e amigo
		UsuarioAmigo amigoVerifica = daoAmigo.findById(pk);
		if(amigoVerifica != null){
			throw new UsuarioJaFoiAdicionadoException();
		}
		
		UsuarioAmigoPK usId = new UsuarioAmigoPK();
		usId.setUsuario(pk.getUsuarioAmigo());
		usId.setUsuarioAmigo(pk.getUsuario());
		
		if(daoAmigo.findById(usId) != null){
			throw new UsuarioDeveConfirmarAmigoException();
		}else {
			UsuarioAmigo usAmigo = new UsuarioAmigo();
			usAmigo.setId(pk);
			usAmigo.setFlgConfirmacao(false);
			
			daoAmigo.save(usAmigo);
			
			try{
				if(usuarioLogado.getFotoPerfil() == null){
					origemFoto = "http://www.wrahgles.com.br/images/default.jpg";
				}else{
					origemFoto = "http://www.wrahgles.com.br/" + usuarioLogado.getFotoPerfil();
				}
				
				email.emailHtml(usAmigo.getId().getUsuarioAmigo().getEmail(),
						usuarioLogado.getNome() + " deseja começar uma amizade no Wrahgles.",
						template.getEmailPerfilAddAmigoCabacalho() + usAmigo.getId().getUsuarioAmigo().getNome() +
						template.getEmailPerfilAddAmigoCorpo1() + usuarioLogado.getNome() + " " + template.getEmailPerfilAddAmigoCorpo2() +
						template.getEmailPerfilAddAmigoFoto_src() + origemFoto + template.getEmailPerfilAddAmigoFoto_alt() +
						usuarioLogado.getNome() + template.getEmailPerfilAddAmigoFoto_title() + usuarioLogado.getNome() +
						template.getEmailPerfilAddAmigoFoto_div() + template.getEmailPerfilAddAmigoCorpo3() + usuarioLogado.getNome() +
						template.getEmailPerfilAddAmigoCorpo4() + "Olá" + usAmigo.getId().getUsuarioAmigo().getNome() + ", gostaria de adicionar você a minha lista de amigos!" +
						template.getEmailPerfilAddAmigoCorpo5() + usuarioLogado.getId() +
						template.getEmailPerfilAddAmigoCorpo6() +
						template.getEmailPerfilAddAmigoCorpo7() + usAmigo.getId().getUsuarioAmigo().getId() + "/" + usAmigo.getId().getUsuarioAmigo().getNomeFormat() +
						template.getEmailPerfilAddAmigoCorpo8() + usAmigo.getId().getUsuarioAmigo().getId() + "/" + usAmigo.getId().getUsuarioAmigo().getNomeFormat() +
						template.getEmailPerfilAddAmigoCorpo9() + usAmigo.getId().getUsuarioAmigo().getEmail() + template.getEmailPerfilAddAmigoRodape());
				
			}catch(EmailException e){
				
			}
			
		}
		
	}

	@Override
	public void confirmarAmigo(Usuario usuarioLogado, Long idAmigo)
			throws UsuarioNaoExisteException, UsuarioAdicionaEleMesmoException,
			UsuarioNaoTeAdicionouException, UsuarioJaFoiAdicionadoException {
		
		//verifica se existe usuario
		String origemFoto;
		Usuario user = dao.findById(idAmigo);
		if(user == null){
			throw new UsuarioNaoExisteException();
		}
		
		UsuarioAmigoPK pkSolicita = new UsuarioAmigoPK();
		pkSolicita.setUsuario(user);
		pkSolicita.setUsuarioAmigo(usuarioLogado);
		
		//usuario confirma a ele mesmo
		if(idAmigo.longValue() == usuarioLogado.getId().longValue()){
			throw new UsuarioAdicionaEleMesmoException();
		}
		
		//usuario confirma alguem e nao add ele
		UsuarioAmigo amigoSolicita = daoAmigo.findById(pkSolicita);
		if(amigoSolicita == null){
			throw new UsuarioNaoTeAdicionouException();
		}else{
			UsuarioAmigoPK usId = new UsuarioAmigoPK();
			usId.setUsuario(amigoSolicita.getId().getUsuarioAmigo());
			usId.setUsuarioAmigo(amigoSolicita.getId().getUsuario());
			
			if(daoAmigo.findById(usId) != null){
				throw new UsuarioJaFoiAdicionadoException();
			}
		}
		
		amigoSolicita.setFlgConfirmacao(true);
		daoAmigo.update(amigoSolicita);
		
		UsuarioAmigoPK pk = new UsuarioAmigoPK();
		pk.setUsuario(usuarioLogado);
		pk.setUsuarioAmigo(amigoSolicita.getId().getUsuario());
		
		UsuarioAmigo amigo = new UsuarioAmigo();
		amigo.setId(pk);
		amigo.setFlgConfirmacao(true);
		
		daoAmigo.save(amigo);
		
		try{
			if(usuarioLogado.getFotoPerfil() == null){
				origemFoto = "http://www.wrahgles.com.br/images/default.jpg";
			}else{
				origemFoto = "http://www.wrahgles.com.br/" + usuarioLogado.getFotoPerfil();
			}
			
			email.emailHtml(amigo.getId().getUsuarioAmigo().getEmail(),
					usuarioLogado.getNome() + "confirmou seu pedido de amizade no Wrahgles",
					template.getEmailPerfilConfAddAmigoCabecalho() + amigo.getId().getUsuarioAmigo().getNome() +
					template.getEmailPerfilConfAddAmigoCorpo1() + usuarioLogado.getNome() + " " +
					template.getEmailPerfilConfAddAmigoCorpo2() + template.getEmailPerfilConfAddAmigoFoto_src() + origemFoto +
					template.getEmailPerfilConfAddAmigoFoto_alt() + usuarioLogado.getNome() +
					template.getEmailPerfilConfAddAmigoFoto_title() + usuarioLogado.getNome() + ":" +
					template.getEmailPerfilConfAddAmigoCorpo3() + usuarioLogado.getNome() +
					template.getEmailPerfilConfAddAmigoCorpo4() + usuarioLogado.getNome() + ":" +
					template.getEmailPerfilConfAddAmigoCorpo5() + usuarioLogado.getId() + "/" + usuarioLogado.getNomeFormat() +
					template.getEmailPerfilConfAddAmigoCorpo6() + usuarioLogado.getNome() + " " +
					template.getEmailPerfilConfAddAmigoCorpo7() + usuarioLogado.getId() + "/" + usuarioLogado.getNomeFormat() +
					template.getEmailPerfilConfAddAmigoCorpo8() + usuarioLogado.getId() + "/" + usuarioLogado.getNomeFormat() +
					template.getEmailPerfilConfAddAmigoCorpo9() + amigo.getId().getUsuarioAmigo().getEmail() +
					template.getEmailPerfilConfAddAmigoRodape());
			
		}catch(EmailException e){
			
		}
		
		
	}

	@Override
	public void recusarAmigo(Usuario usuarioLogado, Long idAmigo) {
		
		
	}

	@Override
	public void removerAmigo(Usuario usuarioLogado, Long idAmigo) {
		
		
	}

}
