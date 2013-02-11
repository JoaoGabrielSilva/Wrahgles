package br.com.wrahgles.controller;


import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.mail.EmailException;

import br.com.bronx.vraptor.restrictrex.annotation.LoggedIn;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.core.RequestInfo;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.caelum.vraptor.view.Results;
import br.com.wrahgles.criptografia.Criptografia;
import br.com.wrahgles.dao.ConviteCadastradoDAO;
import br.com.wrahgles.dao.ConviteDAO;
import br.com.wrahgles.dao.UsuarioDAO;
import br.com.wrahgles.email.Email;
import br.com.wrahgles.email.TemplateEmail;
import br.com.wrahgles.facade.PerfilInterface;
import br.com.wrahgles.image.ImageCompressor;
import br.com.wrahgles.image.ImageUtil;
import br.com.wrahgles.model.Convite;
import br.com.wrahgles.model.ConviteCadastrado;
import br.com.wrahgles.model.Usuario;
import br.com.wrahgles.model.UsuarioAmigoPK;
import br.com.wrahgles.model.UsuarioLogado;
import br.com.wrahgles.properties.UsuarioProperties;
import br.com.wrahgles.util.FileUtil;
import br.com.wrahgles.util.ValidaUtil;

@Resource
public class UsuarioController {
	
	private final UsuarioLogado usuarioLogado;
	private final Result result;
	private final UsuarioDAO dao;
	private final ConviteDAO daoConvite;
	private final ConviteCadastradoDAO daoConviteCadastrado;
	private final PerfilInterface facadePerfil;
	private final Validator validator;
	private final Email email;
	private final TemplateEmail template;
	private final ImageCompressor img;
	private final ImageUtil imgUtil;
	private final RequestInfo info;
	private final ValidaUtil valida;
	private final UsuarioProperties mensagem;
	
	public UsuarioController(UsuarioLogado usuarioLogado, Result result,
			UsuarioDAO dao, ConviteDAO daoConvite,
			ConviteCadastradoDAO daoConviteCadastrado,
			PerfilInterface facadePerfil, Validator validator, Email email,
			TemplateEmail template,ImageCompressor img, ImageUtil imgUtil, RequestInfo info,
			ValidaUtil valida, UsuarioProperties mensagem) {
		super();
		this.usuarioLogado = usuarioLogado;
		this.result = result;
		this.dao = dao;
		this.daoConvite = daoConvite;
		this.daoConviteCadastrado = daoConviteCadastrado;
		this.facadePerfil = facadePerfil;
		this.validator = validator;
		this.email = email;
		this.template = template;
		this.img = img;
		this.imgUtil = imgUtil;
		this.info = info;
		this.valida = valida;
		this.mensagem = mensagem;
	}
	
	public void form() {}
	
	
	/*
	 * Este metodo nao possui a anotacao @ReturnPageAfterLogin pois sempre que ele termina
	 * de preencher seus dados na alteracao e clica em salvar susas alteracoes a pafina deve ser direcionada para o home e nao manter o usuario na mesma pagia
	 * 
	 */ 
	@LoggedIn	
	public void alterar() throws Exception {
		Usuario usuario = usuarioLogado.getUsuario();
		usuario.setSenhaTemp(Criptografia.decrip(usuario.getSenha()));
		
		result.include("usuario", usuario);
		result.include("confSenha", usuario.getSenhaTemp());
		result.use(Results.logic()).redirectTo(UsuarioController.class).form();
		
	}
	 
	public void login(Usuario usuario) throws Exception {
		
		//valida login
		if(usuario.getEmail().trim().isEmpty())
			validator.add(new ValidationMessage(mensagem.getCampo_email_obrigatorio(),"error"));
		if(usuario.getSenhaTemp().trim().isEmpty())
			validator.add(new ValidationMessage(mensagem.getCampo_senha_obrigatorio(),"error"));
		    validator.onErrorUse(Results.logic()).redirectTo(HomeController.class).logar();

		   //procura usuario no banco
		   usuario.setSenha(Criptografia.crip(usuario.getSenhaTemp()));
		   usuario = dao.findByEmailAndSenha(usuario);
		    
		    //valida cadastro de usuario
		    if(usuario == null)
		       validator.add(new ValidationMessage(mensagem.getUsuario_senha_invalidos(),"error"));
		       validator.onErrorUse(Results.logic()).redirectTo(HomeController.class).logar();
		    
		    usuario.setDtUltimoAcesso(new Date());
		    usuario.setQtdAcesso(usuario.getQtdAcesso() + 1);
		    dao.saveOrUpdate(usuario);
		    
		    this.usuarioLogado.efetuaLogin(usuario);
		    
		    if(usuarioLogado.getUri() != null){
		    	result.use(Results.page()).redirect(usuarioLogado.getUri());
		    }else {
		    	result.use(Results.logic()).redirectTo(HomeController.class).home();
		    }
	}
	
	public void logoff(){
		usuarioLogado.efetuaLogoff();
		result.use(Results.logic()).redirectTo(HomeController.class).home();
	}
	@Post
	public void salvar(Usuario usuario, String confSenha, boolean termos) throws Exception {
		
		//valida dados
		validaForm(usuario, confSenha, termos);
		
		Date dtAtual = new Date();
		if(usuario.getId() == null) {
			usuario.setDtCadastro(dtAtual);
			usuario.setQtdAcesso(0);
			usuario.setRole("usuario");
			
			//envia email com novo usuario criado
			
			try{
				email.emailHtml(usuario.getEmail(), mensagem.getBem_vindo(), template.getEmailNewUserCabecalho() + usuario.getNome() + 
				template.getEmailNewUserCorpo() + usuario.getEmail() + template.getEmailNewUserRodape());
				
			}catch(EmailException e){
				e.printStackTrace();
			}
			
			result.include("mensagem", mensagem.getCadastro_wrahgles());
		}else {
			Usuario dbUser = dao.findById(usuario.getId());
			usuario.setQtdAcesso(dbUser.getQtdAcesso());
			usuario.setDtCadastro(dbUser.getDtCadastro());
			usuario.setRole(dbUser.getRole());
			result.include("mensagem", mensagem.getDados_atualizados());
			
		}
		
		//criptografa a senha
		usuario.setSenha(Criptografia.crip(confSenha));
		usuario.setDtUltimoAcesso(dtAtual);
		usuario.setDtUltimoUpdate(dtAtual);
		usuario.setFlgAtivo(true);
		dao.saveOrUpdate(usuario);
		
		//verifica se o usuario foi convidado para o wrahgles adiciona - o como amigo pelos que convidaram e salva historico de convidados que estao cadastrados
		
		List<Convite> convites = daoConvite.findByEmail(usuario.getEmail().trim());
		for(Convite convite : convites){
			UsuarioAmigoPK pk = new UsuarioAmigoPK();
			pk.setUsuario(convite.getUsuario());
			pk.setUsuarioAmigo(usuario);
			ConviteCadastrado convCad = new ConviteCadastrado();
			convCad.setId(pk);
			
			facadePerfil.adicionarAmigo(convite.getUsuario(), usuario.getId());
			daoConviteCadastrado.save(convCad);
			daoConvite.delete(convite);
			
		}
		
		//add avatar do usuario
		if(usuario.getFotoPerfil() != null) {
			usuario.setFotoPerfil(this.addAvatar(usuario));
			dao.saveOrUpdate(usuario);
		}
		
		result.redirectTo(this).login(usuario);
	}
	
	@Path("/usuario/senha")
	public void senha(Usuario usuario) throws Exception {
		if(usuario.getEmail().trim().isEmpty())
			validator.add(new ValidationMessage(mensagem.getReceber_email_senha(), "error"));
		    validator.onErrorUse(Results.logic()).redirectTo(HomeController.class).senha();
		
		    Usuario user = dao.findByEmail(usuario);
		    if(user == null)
		    	validator.add(new ValidationMessage(mensagem.getEmail_nao_encontrado(), "error"));
		        validator.onErrorUse(Results.logic()).redirectTo(HomeController.class).senha();
		        
		        try{
		        	user.setSenhaTemp(Criptografia.decrip(user.getSenha()));
		        	email.emailHtml(user.getEmail(), mensagem.getSenha_perdida(),
		        	template.getEmailEsqueceuSenhaCabecalho() + user.getNome() + template.getEmailEsqueceuSenhaCorpo1() + user.getSenhaTemp() + 
		        	template.getEmailEsqueceuSenhaCorpo2() + user.getEmail() + template.getEmailEsqueceuSenhaRodape());
		        }catch(EmailException e){
		        	
		        }
		        result.include("mensagem", mensagem.getSenha_enviada() + user.getEmail());
		        result.use(Results.logic()).redirectTo(HomeController.class).home();
		
	}
	
	//realiza a preview da imagem antes do cadastro
	@Path("/usuario/previewAvatar")
	public void previewAvatar(Usuario usuario, UploadedFile file, String confSenha) throws Exception {
		result.include("confSenha", confSenha);
		
		//pega extensao da imagem
		String tipo = null;
		if(file != null)
			tipo = file.getFileName().substring((file.getFileName().length() - 3),(file.getFileName().length()));
		
		//valida tipo da imagem
		if((tipo != null && !tipo.equalsIgnoreCase("jpg") 
				&& !tipo.equalsIgnoreCase("gif") 
				&& !tipo.equalsIgnoreCase("png")) || tipo == null)
			validator.add(new ValidationMessage(mensagem.getArquivo_invalido(), "error"));
		validator.onErrorUse(Results.page()).of(UsuarioController.class).form();
		
		//tratamento de imagem
		ByteArrayInputStream stream = imgUtil.toLocalInputStream(file.getFile());
		Image image = ImageIO.read(stream);
		int imageWidth = image.getWidth(null);
		int imageHeigth = image.getHeight(null);
		
		//gera nome da imafem aleatorio
		int rdn = (int) (Math.random() * 9999999);
		String nomeImagem = new Integer(rdn).toString();
		
		//tratamento de pastas no servidor
		String pasta = "images_site/usuario/preview";
		String realPath = info.getServletContext().getRealPath("/" + pasta);
		File newFile = new File(realPath);
		if(!newFile.exists()){
			newFile.mkdirs();
		}
		
		//deleta o ultimo avatar caso exista
		if(usuario.getFotoPerfil() != null && !usuario.getFotoPerfil().trim().isEmpty()){
			File delAvatar = new File(realPath + "/" + usuario.getFotoPerfil().substring(usuario.getFotoPerfil().lastIndexOf("/")));
			if(delAvatar.isFile() && delAvatar.exists())
				delAvatar.delete();
		}
		//salva as imagens em disco copactando o size de acordo com a extensao
		if(tipo.equalsIgnoreCase("jpg")){
			//redimensaiona a imagem e grava em disco - formato JPEG.
			nomeImagem += ".jpg";
			img.compressJPEG(image, realPath + "/" + nomeImagem, imageWidth, imageHeigth, 90);
		}else if(tipo.equalsIgnoreCase("gif")){
			//redimensiona a imagem e grava em disco - formato GIF
			nomeImagem += ".gif";
			img.compressGIF(image, realPath + "/" + nomeImagem, imageWidth, imageHeigth, 90);
		}else if(tipo.equalsIgnoreCase("png")){
			//redimensiona a imagem e grava em disco - formato PNG.
			nomeImagem += ".png";
			img.compressPNG(image, realPath + "/" + nomeImagem, imageWidth, imageHeigth, 90);
		}
		usuario.setFotoPerfil(pasta + "/" + nomeImagem);
		result.include("usuario",usuario);
		result.use(Results.logic()).redirectTo(UsuarioController.class).form();
		
		
		
	}
	
	/*
	 * Metodo usado internamente apenas quando vai salvar os dados do
	 * usuario para atualizar os valores do campo fotoPerfil
	 * 
	 */

	private String addAvatar(Usuario usuario) throws Exception {
		
		if(usuarioLogado.getUsuario() != null
				&& usuarioLogado.getUsuario().getFotoPerfil() != null
				&& usuarioLogado.getUsuario().getFotoPerfil().equalsIgnoreCase(usuario.getFotoPerfil())){
			return usuario.getFotoPerfil();
		}
		
		String nomeFile = usuario.getFotoPerfil().substring(usuario.getFotoPerfil().lastIndexOf("/"));
		String pasta = "images_site/usuario/avatar/IDUsuario_" + usuario.getId();
		String pathDestino = info.getServletContext().getRealPath("/" + pasta);
		String pathOrigem = info.getServletContext().getRealPath(usuario.getFotoPerfil());
		
		//cria diretorio de destino caso nao exista
		File fDestino = new File(pathDestino);
		if(!fDestino.exists()){
			fDestino.mkdirs();
		}
		
		//Cadastro
		if(usuarioLogado.getUsuario() == null){
			FileUtil.copiaArquivo(pathOrigem, pathDestino + "/" + nomeFile);
		
		
		//alterado
		}else {
			FileUtil.copiaArquivo(pathOrigem, pathDestino + "/" + nomeFile);
			
			//deleta ultima foto caso exista
			
			if(usuarioLogado.getUsuario().getFotoPerfil() != null){
				File fUpdate = new File(info.getServletContext().getRealPath(usuarioLogado.getUsuario().getFotoPerfil()));
				if(fUpdate.isFile() && fUpdate.exists())
					fUpdate.delete();
			}
			
		}
		
		//apaga arquivo temporario
		File fTemp = new File(pathOrigem);
		if(fTemp.isFile() && fTemp.exists())
		   fTemp.delete();
		
		return pasta + nomeFile;
		
	}
	
	private void validaForm(Usuario usuario, String confSenha, boolean termos){
		//nome
		if(usuario.getNome().trim().isEmpty())
			validator.add(new ValidationMessage(mensagem.getCampo_nome_obrigatorio(), "error"));
		
		//email
		if(usuario.getEmail().trim().isEmpty()){
			validator.add(new ValidationMessage(mensagem.getCampo_email_obrigatorio(), "error"));
		}else {
			if(!valida.validaEmail(usuario.getEmail().trim())){
				validator.add(new ValidationMessage(mensagem.getEmail_valido(), "error"));
			}
			
			Usuario user = dao.findByEmail(usuario);
			if(user != null && usuario.getId() == null)
				validator.add(new ValidationMessage(mensagem.getEmail_cadastrado(), "error"));
			
			if(usuario.getId() != null && user !=  null && usuario.getId().longValue() != user.getId().longValue())
				validator.add(new ValidationMessage(mensagem.getEmail_cadastrado(), "error"));
			
			
		}
		
		//senha e confirmar senha
		if(usuario.getSenhaTemp().trim().isEmpty())
			validator.add(new ValidationMessage(mensagem.getCampo_senha_obrigatorio(), "error"));
		if(usuario.getSenhaTemp().trim().length() < 6)
			validator.add(new ValidationMessage(mensagem.getCampo_senha_caracter(), "error"));
		if(confSenha.trim().isEmpty())
			validator.add(new ValidationMessage(mensagem.getCampo_senha_confirmar(), "error"));
		if(!usuario.getSenhaTemp().trim().isEmpty() && !confSenha.trim().isEmpty() && !usuario.getSenhaTemp().equals(confSenha))
			validator.add(new ValidationMessage(mensagem.getCampo_senha_confirmar(), "error"));
		
		//termos
		
		if(usuario.getId() == null && !termos)
			validator.add(new ValidationMessage(mensagem.getTermos_uso(), "error"));
		
		validator.onErrorUse(Results.page()).of(UsuarioController.class).form();
	}

}
