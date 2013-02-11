package br.com.wrahgles.controller;

import static br.com.caelum.vraptor.view.Results.json;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

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
import br.com.wrahgles.annotation.ReturnPageAfterLogin;
import br.com.wrahgles.dao.CategoriaDAO;
import br.com.wrahgles.dao.CidadeDAO;
import br.com.wrahgles.dao.ComentarioDAO;
import br.com.wrahgles.dao.EstadoDAO;
import br.com.wrahgles.dao.LocalizacaoDAO;
import br.com.wrahgles.dao.LocalizacaoFotoDAO;
import br.com.wrahgles.dao.RelCategoriaDAO;
import br.com.wrahgles.dao.UsuarioDAO;
import br.com.wrahgles.dao.VisitaLocalizacaoDAO;
import br.com.wrahgles.image.ImageCompressor;
import br.com.wrahgles.image.ImageUtil;
import br.com.wrahgles.model.Estado;
import br.com.wrahgles.model.Localizacao;
import br.com.wrahgles.model.LocalizacaoFoto;
import br.com.wrahgles.model.RelCategoria;
import br.com.wrahgles.model.Usuario;
import br.com.wrahgles.model.UsuarioLocalizacaoPK;
import br.com.wrahgles.model.UsuarioLogado;
import br.com.wrahgles.model.VisitaLocalizacao;
import br.com.wrahgles.properties.LocalizacaoProperties;
import br.com.wrahgles.util.ValidaUtil;

@Resource
public class LocalizacaoController {
	
	private final Result result;
	private final Validator validator;
	private final UsuarioLogado usuarioLogado;
	private final LocalizacaoDAO dao;
	private final VisitaLocalizacaoDAO daoVisitaLocal;
	private final UsuarioDAO daoUsuario;
	private final LocalizacaoFotoDAO daoLocalFoto;
	private final ComentarioDAO daoComentario;
	private final CategoriaDAO daoCategoria;
	private final RelCategoriaDAO daoRelCategoria;
	private final EstadoDAO daoEstado;
	private final CidadeDAO daoCidade;
	private final ImageCompressor img;
	private final ImageUtil imgUtil;
	private final RequestInfo info;
	private final ValidaUtil valida;
	private final LocalizacaoProperties properties;
	
	public LocalizacaoController(Result result, Validator validator,
			UsuarioLogado usuarioLogado, LocalizacaoDAO dao,
			VisitaLocalizacaoDAO daoVisitaLocal, UsuarioDAO daoUsuario,
			LocalizacaoFotoDAO daoLocalFoto, ComentarioDAO daoComentario,
			CategoriaDAO daoCategoria, RelCategoriaDAO daoRelCategoria,
			EstadoDAO daoEstado, CidadeDAO daoCidade, ImageCompressor img,
			ImageUtil imgUtil, RequestInfo info, ValidaUtil valida,
			LocalizacaoProperties properties) {
		super();
		this.result = result;
		this.validator = validator;
		this.usuarioLogado = usuarioLogado;
		this.dao = dao;
		this.daoVisitaLocal = daoVisitaLocal;
		this.daoUsuario = daoUsuario;
		this.daoLocalFoto = daoLocalFoto;
		this.daoComentario = daoComentario;
		this.daoCategoria = daoCategoria;
		this.daoRelCategoria = daoRelCategoria;
		this.daoEstado = daoEstado;
		this.daoCidade = daoCidade;
		this.img = img;
		this.imgUtil = imgUtil;
		this.info = info;
		this.valida = valida;
		this.properties = properties;
	}
	
	public void form(Long id){
		Localizacao local = new Localizacao();
		
		//if para popular os combos na pagina de alterar localizacao
		if(id != null){
			local = dao.findById(id);
			
			if(local.getCategoria() != null)
				result.include("subCategorias", daoRelCategoria.findByIdCategoria(local.getCategoria().getCategoria().getId()));
		   }else {
			   local.setEstado(daoEstado.findById(new Long(25)));
		   }
		
		result.include("localizacao", local);
		result.include("categorias", daoCategoria.findAllOrderName());
		result.include("estados", daoEstado.findAllOrderNome());
	}

	@LoggedIn
	@Path("/localizacao/categoria/{idCategoria}")
	public List<RelCategoria> categoria(Long idCategoria){
	
		return daoRelCategoria.findByIdCategoria(idCategoria);
	}
	
	@Path("/localizacao/cidade/{sigla}")
	public void cidade(String sigla){
		Estado est = daoEstado.findBySigla(sigla);
		result.include("idEstado", est.getId());
	}
	
	@Path("/localizacao/cidade/listJSON/{idEstado}")
	public void listJSON(String q, Long idEstado){
		result.use(json()).from(daoCidade.findByIdEstadoAndNomeILike(idEstado, q)).exclude("id").serialize();
	}
	
	@Path("/local/{id}/{nome}")
	@ReturnPageAfterLogin
	public void detalhe(Long id, String nome){
		Localizacao local = dao.findById(id);
		
		long media = 0;
		double qtd = daoComentario.countComentarioByLocalizacao(id);
		double soma = daoComentario.sumNotasComentariosByLocalizacao(id);
		
		if (qtd > 0)
			media = Math.round(soma / qtd);
		
		result.include("localizacao", local);
		result.include("localFoto", daoLocalFoto.findByIdLocalPrimeiraFoto(id));
		result.include("Comentarios", daoComentario.findComentarioByLocalizacaoId(id));
		result.include("qtdVisitantes", daoVisitaLocal.countByIdLocalizacao(id));
		result.include("visitantes", daoVisitaLocal.findByIdLocalizacao(id, 20));
		result.include("qtdComentarios", (long) qtd);
		result.include("qtdFotos", daoLocalFoto.countByIdLocalizacao(id, true));
		result.include("fotos", daoLocalFoto.findByIdLocalizacao(id));
		result.include("maisComentario", dao.findLocalizacaoWithMoreComentario(10));
		result.include("newUsers", daoUsuario.findAllOrderDtCadastro(4, 0));
		result.include("nota", media);
		
		//se o usuario estiver logado verifique se ele comentou esse local
		if(usuarioLogado.getUsuario() != null){
			result.include("comentarioUsuario", daoComentario.findComentarioByUsuarioAndLocalizacao(usuarioLogado.getUsuario().getId(), id));
			
			UsuarioLocalizacaoPK pk = new UsuarioLocalizacaoPK();
			pk.setUsuario(usuarioLogado.getUsuario());
			pk.setLocalizacao(local);
			
			VisitaLocalizacao visita = daoVisitaLocal.findById(pk);
			if(visita == null){
				VisitaLocalizacao newVisita = new VisitaLocalizacao();
				newVisita.setId(pk);
				newVisita.setDtVisita(new Date());
				daoVisitaLocal.save(newVisita);
			}else {
				visita.setDtVisita(new Date());
				daoVisitaLocal.update(visita);
			}
		}
	}
	
	@Path("/local/fotos/{id}/{nome}")
	@ReturnPageAfterLogin
	public void fotos(Long id, String nome, Long idFoto){
	  
		long media = 0;
		double qtd = daoComentario.countComentarioByLocalizacao(id);
		double soma = daoComentario.sumNotasComentariosByLocalizacao(id);
		LocalizacaoFoto foto = daoLocalFoto.findByIdLocalPrimeiraFoto(id);
		
		if (qtd > 0)
			media = Math.round(soma / qtd);
		
		
		result.include("localizacao", dao.findById(id));
		result.include("localFoto", foto);
		result.include("qtdFotos", daoLocalFoto.countByIdLocalizacao(id, true));
		result.include("fotos", daoLocalFoto.findByIdLocalizacao(id));
		result.include("maisComentario", dao.findLocalizacaoWithMoreComentario(10));
		result.include("newUsers", daoUsuario.findAllOrderDtCadastro(4, 0));
		result.include("nota", media);
		
		if(idFoto != null)
			result.include("fotoAtiva", idFoto);
	   else 
		    result.include("fotoAtiva", foto.getId());
	}
	
	@ReturnPageAfterLogin
	@Path("/local/foto/{id}/{nome}/{idFoto}")
	public void foto(Long id, String nome, Long idFoto){
		result.use(Results.logic()).redirectTo(LocalizacaoController.class).fotos(id, nome, idFoto);
	}
	
	@ReturnPageAfterLogin
	@Path("/localizacao/detalhe/{id}")
	public void detalheOld(Long id){
		Localizacao local = dao.findById(id);
		result.use(Results.logic()).redirectTo(LocalizacaoController.class).detalhe(id, local.getNomeFormat());
	}
	@LoggedIn
	@Path("/localizacao/sendPicture/{idLocalizacao}")
	public void sendPicture(UploadedFile file, Long idLocalizacao) throws Exception {
		
		//pega extensao da imagem
		String tipo = null;
		if(file != null)
			tipo = file.getFileName().substring((file.getFileName().length() - 3), (file.getFileName().length()));
		
		//valida tipo de imagem
		if((tipo != null && !tipo.equalsIgnoreCase("jpg") 
				&& !tipo.equalsIgnoreCase("gif") 
				&& !tipo.equalsIgnoreCase("png")) || tipo == null)
			validator.add(new ValidationMessage(properties.getArquivo_invalido(), "error"));
		validator.onErrorUse(Results.page()).of(UsuarioController.class).form();
		
		//tratamento de imagem aleatorio
		ByteArrayInputStream stream = imgUtil.toLocalInputStream(file.getFile());
		Image images = ImageIO.read(stream);
		
		Integer[] newSizes = imgUtil.resizeImage(images, 655, null);
		String nomeImagem = null, pasta = "images_local/" + idLocalizacao;
		do{
			int rdn = (int) (Math.random() * 9999999);
			nomeImagem = new Integer(rdn).toString();
		}while(daoLocalFoto.existeNomeImagem(idLocalizacao, pasta + "/" + nomeImagem + "." + tipo));
		
		String realPath = info.getServletContext().getRealPath("/" + pasta);
		File newFile = new File(realPath);
		if(!newFile.exists()){
			newFile.mkdirs();
		}
		
		//salva as imagens em disco compactando o size de acordo com a extensao
		if(tipo.equalsIgnoreCase("jpg")){
		nomeImagem += ".jpg";
	    img.compressJPEG(images, realPath + "/" + nomeImagem, newSizes[0], newSizes[1], 90);						    		   
	
	    } else if(tipo.equalsIgnoreCase("gif")){
		// Redimensiona a imagem e grava em disco - formato GIF.	
		nomeImagem += ".gif";
		img.compressGIF(images, realPath + "/" + nomeImagem, newSizes[0], newSizes[1], 90);						      
	} else if(tipo.equalsIgnoreCase("png")){
		// Redimensiona a imagem e grava em disco - formato PNG.
		nomeImagem += ".png";
		img.compressPNG(images, realPath + "/" + nomeImagem, newSizes[0], newSizes[1], 90);						      
	}
	
	LocalizacaoFoto localFoto = new LocalizacaoFoto();
	localFoto.setLocalizacao(dao.findById(idLocalizacao));
	localFoto.setUsuario(usuarioLogado.getUsuario());
	localFoto.setImagem(pasta + "/" + nomeImagem);
	localFoto.setFlgAtivo(false);
	localFoto.setData(new Date());
	
	daoLocalFoto.saveOrUpdate(localFoto);
	result.include("mensagem", properties.getImagem_aprovacao());
	result.use(Results.logic()).redirectTo(LocalizacaoController.class).detalhe(idLocalizacao, localFoto.getLocalizacao().getNomeFormat());
		
	}
	
	@LoggedIn
	@Post
	public void salvar(Localizacao localizacao){
		validaForm(localizacao);
		Date dtAtual = new Date();
		Usuario user = usuarioLogado.getUsuario();
		
		//cadastro
		if(localizacao.getId() == null){
			localizacao.setDtCadastro(dtAtual);
			localizacao.setUsuarioInsert(user);
			
			if(user.getRole().trim().equalsIgnoreCase("admin")){
				localizacao.setFlgAtivo(true);
			}
			
			result.include("mensagem", properties.getLocalizacao_cadastrada());
			//atualizado
		}else {
			Localizacao other = dao.findById(localizacao.getId());
			localizacao.setDtCadastro(other.getDtCadastro());
			localizacao.setUsuarioInsert(other.getUsuarioInsert());
			
			result.include("mensagem", properties.getLocalizacao_atualizada());
		}
		
		localizacao.setDtUltimoUpdate(dtAtual);
		localizacao.setUsuarioUpdate(user);
		if(!user.getRole().trim().equalsIgnoreCase("admin")){
			localizacao.setFlgAtivo(false);
		}
		
		//add estado
		Estado est = daoEstado.findBySigla(localizacao.getEstado().getSigla());
		localizacao.setEstado(est);
		
		dao.saveOrUpdate(localizacao);
		
		result.use(Results.logic()).redirectTo(LocalizacaoController.class).detalhe(localizacao.getId(), localizacao.getNomeFormat());
	}
	
	private void validaForm(Localizacao localizacao){
		if(localizacao.getNome().trim().isEmpty())
			validator.add(new ValidationMessage(properties.getCampo_nome_obrigatorio(), "error"));
		if (localizacao.getCategoria().getId() == null)
			validator.add(new ValidationMessage(properties.getCategoria_subcategoria(), "error"));
		
		if (localizacao.getEndereco().trim().isEmpty())
			validator.add(new ValidationMessage(properties.getCampo_endereco_obrigatorio(), "error"));
		
		if (localizacao.getEstado().getSigla().trim().isEmpty())
			validator.add(new ValidationMessage(properties.getSelecione_estado(), "error"));
		
		if (localizacao.getCidade().trim().isEmpty())
			validator.add(new ValidationMessage(properties.getCampo_cidade_obrigatorio(), "error"));
		
		if(!localizacao.getEmail().trim().isEmpty()){
			if (!valida.validaEmail(localizacao.getEmail().trim())){
				validator.add(new ValidationMessage(properties.getEmail_valido(), "error"));
			}
		}

		if (localizacao.getDescricao().trim().isEmpty())
			validator.add(new ValidationMessage(properties.getCampo_descricao_obrigatorio(), "error"));
		
		if(localizacao.getLatitude() == null || localizacao.getLongitude() == null 
				|| localizacao.getLatitude().doubleValue() == 0 
				|| localizacao.getLongitude().doubleValue() == 0)
			validator.add(new ValidationMessage(properties.getEndereco_mapa(), "error"));
		
		if(localizacao.getSite() != null) {
			if(!localizacao.getSite().contains("http://") && !localizacao.getSite().contains("https://")) 
				localizacao.setSite("http://".concat(localizacao.getSite().trim()));
			if(localizacao.getSite().trim().equalsIgnoreCase("http://") 
					|| localizacao.getSite().trim().equalsIgnoreCase("https://"))
				localizacao.setSite("");
		} else {
			localizacao.setSite("");
		}
		
		if(validator.hasErrors()) {
			result.include("categorias", daoCategoria.findAllOrderName());
			result.include("estados", daoEstado.findAllOrderNome());
			
			if(localizacao.getCategoria() != null && localizacao.getCategoria().getCategoria() != null
					&& localizacao.getCategoria().getCategoria().getId() != null 
					&& localizacao.getCategoria().getCategoria().getId().longValue() != 0)
				result.include("subCategorias", daoRelCategoria.findByIdCategoria(localizacao.getCategoria().getCategoria().getId()));
		}
			
		
		validator.onErrorUse(Results.page()).of(LocalizacaoController.class).form(null);
	
	}
	
	

}
