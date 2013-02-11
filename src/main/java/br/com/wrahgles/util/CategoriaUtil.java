package br.com.wrahgles.util;

import java.util.HashMap;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class CategoriaUtil {
	
	public String getNameCat(){
		int i = (int) (Math.random() * 11);
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		map.put(0, "Restaurante");
		map.put(1, "Advogado");
		map.put(2, "Sushi");
		map.put(3, "Academia");
		map.put(4, "Bares");
		map.put(5, "Baladas");
		map.put(6, "Pizzaria");
		map.put(7, "Lojas");
		map.put(8, "Hospital");
		map.put(9, "PetShop");
		map.put(10, "Cabeleireiros");
		map.put(11,"Estética");
		
		return map.get(i);
	}
	
	public static void main(String[] args){
		doIt(new Byte("3"));
	}
	
	public static void doIt(long valor){
		System.out.println("doIt com long");
	}
	
	public static void doIt(int valor){
		System.out.println("doIt com int");
	}
	
	public static void doIt(Long valor){
	  System.out.println("doIt com Long");
	}
	
	public static void doIt(Integer valor){
	  System.out.println("doIt com Integer");
	}

	
}
