package br.com.wrahgles.image;

import java.io.File;
import java.io.IOException;

import javax.imageio.stream.FileImageInputStream;

import org.apache.commons.codec.binary.Base64;

public class Convert {
	
	public Convert(){}
	
	
	//converte uma imagem para base64
	
	public String encode64ToString(String fileName) throws IOException{
		
		try{
			
			File file = new File(fileName);
			FileImageInputStream in = new FileImageInputStream(file);
			
			byte[] b = new byte[(int) in.length()];
			in.read(b);
			
			String s = new String(Base64.encodeBase64(b));
			in.close();
			
			return s;
			
			
		}catch(Exception e){
			e.printStackTrace();
			throw new IOException("Erro ao converter arquivo para base64");
			
		}
		
	}
	
	
	public byte[] decode64ToString(String valor) throws Exception{
		
		try{
			
			byte[] b = Base64.decodeBase64(valor.getBytes());
			return b;
			
		}catch(Exception e){
			throw new Exception("Erro ao realizar decode de base64 para array de bytes.");
		}
		
	}

}
