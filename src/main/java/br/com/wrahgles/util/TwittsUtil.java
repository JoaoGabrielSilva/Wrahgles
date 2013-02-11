package br.com.wrahgles.util;

import br.com.wrahgles.model.Categoria;
import br.com.wrahgles.model.Comentario;
import br.com.wrahgles.model.Estado;
import br.com.wrahgles.model.Localizacao;
import br.com.wrahgles.model.SubCategoria;

public class TwittsUtil {
	
	//monta mensagem que deve ser enviada pelo twitter, quando cadastra um novo local
	
	public String montaMsgCadastroLocalizacao(Localizacao local, String link, Estado estado, Categoria categoria, SubCategoria sub){
		
		//textos fixos
		String mini = "De sua nota ";
		String lk = "no @Wrahgles " + link;
		
		//mensagem
		String msg = mini + lk;
		
		//incluir categoria
		
		int tam = 140 - msg.length() - 2;
		String cat = this.criaHashTags(categoria.getNome(), tam);
		if(!cat.isEmpty())
			msg = cat + ": " + mini + lk;
		else
			return msg;
		
		
		//inclui nome
		tam = 140 - msg.length() - 4;
		String nome = this.criaHashTags(local.getNome(), tam);
		if(!nome.isEmpty())
			msg = cat +": " + mini + "p/ " + nome + " " + lk;
		else
			return msg;
		
		//inclui estado
		tam = 140 - msg.length() - 1;
		String uf = this.criaHashTags(estado.getSigla(), tam);
		if(!uf.isEmpty())
			msg = cat + ": " + mini + "p/ " + nome + " " + uf + " " + lk;
		else
			return msg;
		
		//inlcui  SUBCATEGORIA
		tam = 140 - msg.length() - 1;
		String subCat = this.criaHashTags(sub.getNome(), tam);
		if(!subCat.isEmpty())
			return cat + " " + subCat + ": " + mini + "p/ " + nome + " " + uf + " " + lk;
		else
			return msg;
	}
		
		
		//monta mensagem que deve ser enviada pelo twitter quandi efetua um comentario
		public String montaMsgComentario(Comentario com, String link, Estado estado, Categoria categoria){
			
			String nota = selecionaNota(com);
			
			//texto fixo minimo
			String mini = "avaliou como " + nota;
			String lk = "veja: " + link;
			
			//inclui nome
		    String nome = this.abreviaNomes(com.getUsuario().getNome());
		    String msg = nome + " " + mini + " " + lk;
		    
		    int tam = 140 - msg.length() - 6;
			String nomeLocal = this.criaHashTags(com.getLocalizacao().getNome(), tam);
			if(!nomeLocal.isEmpty())
				msg = nome + " " + mini + "o(a)" + nomeLocal + " " + lk;
			else 
				return msg;
			
			
			//inclui estado
			String uf = "";
			if(estado != null){
				tam = 140 - msg.length() - 1;
				uf = this.criaHashTags(estado.getSigla(), tam);
				if(!uf.isEmpty())
					msg = nome + " " + mini + "o(a) " + nomeLocal + " " + uf + " " + lk;
				else
					return msg;
			}
			
			//incluir categoria
			return incluirCategoria(categoria, mini, lk, nome, msg, nomeLocal, uf);
			
		}


		private String selecionaNota(Comentario com) {
			String nota = "";
			switch(com.getNota()){
			
			case 2:
				nota = "RUIM";
				break;
			case 4:
				nota = "REGULAR";
				break;
			case 6:
				nota = "BOM";
				break;
			case 8:
				nota = "ÓTIMO";
				break;
			case 10:
				nota = "EXCELENTE";
				break;
			}
			return nota;
		}


		private String incluirCategoria(Categoria categoria, String mini,
				String lk, String nome, String msg, String nomeLocal, String uf) {
			int tam;
			String cat = "";
			if(categoria != null) {
				tam = 140 - msg.length() - 2;
				cat = this.criaHashTags(categoria.getNome(), tam);
				if(!cat.isEmpty())
					return cat + ": " + nome + " " + mini + "o(a) " + nomeLocal + " " + uf + " " +lk;
				else
					return msg;
			}
			
			return msg;
		}
		
	//metodo que retorna hashTag de acordo com a String recebida e o tamnho restante para montagem da mensagem
		
		private String criaHashTags(String str, int tam){
			String hashTag = "#";
			String[] sVet = str.split(" ");
			
			for(String s: sVet)
				if(s.length() > 1)
					if((hashTag.length() + s.length()) <= tam)
						hashTag += s.trim();
					else
						break;
			
			if(hashTag.equalsIgnoreCase("#"))
				return "";
			else
				return hashTag;
		}
		
		public String abreviaNomes(String nome){
			String[] vNome = nome.split(" ");
			if(vNome.length >= 2)
				return vNome[0] + " " + vNome[1];
			else
				return vNome[0];
		}
	

}
