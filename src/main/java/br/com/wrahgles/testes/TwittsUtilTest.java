package br.com.wrahgles.testes;

import org.junit.Assert;
import org.junit.Test;

import br.com.wrahgles.util.TwittsUtil;


public class TwittsUtilTest {
	
	TwittsUtil twitter = new TwittsUtil();
	
	@Test
	public void testAbreviaNome(){
		String nome = "Joao Gabriel Oliveira Silva";
		Assert.assertEquals("Joao Gabriel", twitter.abreviaNomes(nome));
	}
	
	@Test
	public void testAbreviaNomeComAcento(){
		String nome = "Jo�o Gabriel Oliveira Silva";
		Assert.assertEquals("Jo�o Gabriel", twitter.abreviaNomes(nome));
	}
	
	@Test
	public void testAbreviaNomeErrado(){
		String nome = "Joao Pedro Oliviera Silva";
		Assert.assertEquals("Joao Gabriel", twitter.abreviaNomes(nome));
	}
	

}
