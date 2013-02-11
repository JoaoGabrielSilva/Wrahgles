package br.com.wrahgles.util;

import org.junit.Test;

import junit.framework.Assert;


public class ValidaUtilTest {
	
	ValidaUtil util = new ValidaUtil();
	
	@Test
	public void testValidaEmail(){
		String email = "joao1.gabriel@hotmail.com";
		Assert.assertEquals(true, util.validaEmail(email));
	}
	
	@Test
	public void testValidaEmailSemArroba(){
		String email = "joao1.gabriel#hotmail.com";
		Assert.assertEquals(true, util.validaEmail(email));
	}

	@Test
	public void testValidaEmailSemArrobaFalse(){
		String email = "joao1.gabriel#hotmail.com";
		Assert.assertEquals(false, util.validaEmail(email));
	}
	
	@Test
	public void testValidaEmailComDoisPontos(){
		String email = "joao1.gabriel@.hotmail..com";
		Assert.assertEquals(false, util.validaEmail(email));
	}
	
	@Test
	public void testStringEspeciais(){
		String email = "João Gabriel / Oliveira Silva";
		Assert.assertEquals("Joao Gabriel Oliveira Silva", util.strEspeciais(email));
	}
	
	@Test
	public void testStringEspeciaisTrue(){
		String email = "João Gabriel / Oliveira Silva";
		Assert.assertEquals("João-Gabriel-/-Oliveira-Silva", util.strEspeciais(email));
	}

	
	

}
