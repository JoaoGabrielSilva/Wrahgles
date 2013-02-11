package br.com.wrahgles.util;

import junit.framework.Assert;

import org.junit.Test;


public class TextoUtilTest {

	
	TextoUtil util = new TextoUtil();
	
	@Test
	public void testRetiraAcento(){
		String texto = "joãocaspêr";
		Assert.assertEquals("joaocasper", util.retirarAcentosECaracteresEspeciais(texto));
	}
}
