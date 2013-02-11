package br.com.wrahgles.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class ValidaUtil {
	
	
	//metodo responsavel por validar email
	
	public boolean validaEmail(String email){
		boolean valor = true;
		
		Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@(([\\w-]{2,63}\\.)+[A-Za-z]{2,6}|\\[\\d{1,3}(\\.\\d{1,3}){3}\\])$");
		Matcher m = p.matcher(email);
		
		if(!m.find()){
			valor = false;
		}		
		return valor;
		
	}
	
	//metodo responsavel por acrescentar " - " quando ouver espaço na palavra.
	
	
	public String strEspeciais(String param){
		String strEspeciais = " ";
		StringBuffer str = new StringBuffer();
		for(int i = 0; i < param.length(); i++){
			if(strEspeciais.indexOf(param.charAt(i)) == -1){
				str.append(param.charAt(i));
			}else{
				str.append("-");
			}
		}
		return str.toString();
	}

}
