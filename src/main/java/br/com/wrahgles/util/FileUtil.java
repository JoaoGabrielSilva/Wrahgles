package br.com.wrahgles.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtil {
	
	//metodo utilizado para copiar um arquivo de um local para o outro
	
	public static void copiaArquivo(String srFile, String dtFile){
		
		try{
			
			File f1 = new File(srFile);
			File f2 = new File(dtFile);
			InputStream in = new FileInputStream(f1);
			
			OutputStream out = new FileOutputStream(f2);
			
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf,0, len);
			}
			in.close();
			out.close();
			
		}catch(FileNotFoundException ex){
			ex.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}

}
