package br.com.wrahgles.util;

import java.text.Normalizer;

public class TextoUtil {
	
	//monta textos para serem usados nas URLS
	
	
	public String retirarAcentosECaracteresEspeciais(String texto){
		
		if(texto != null) {
			texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
			texto = texto.replaceAll("[^\\p{ASCII}]", "");
			texto = texto.replaceAll("[^0-9a-zA-Z- ]+?", "");
			texto = texto.replaceAll("[\\s]+", " ");
			texto = texto.replaceAll(" ", "-");
		}
		return texto;
	}

}
