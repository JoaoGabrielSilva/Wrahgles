package br.com.wrahgles.image;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.awt.Image;


import br.com.caelum.vraptor.ioc.Component;

@Component
public class ImageUtil {

	public ByteArrayInputStream toLocalInputStream(InputStream notLocalStream) throws IOException {
		
		ByteArrayInputStream toLocalStream = null;
		
		
		if(notLocalStream != null){
			int value = -1;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			while((value = notLocalStream.read()) != -1)
				out.write(value);
			
			out.close();
			
			
		byte[] returnData = out.toByteArray();
		if(returnData != null)
			toLocalStream = new ByteArrayInputStream(returnData);
		}
		
		return toLocalStream;
		
	}
	
	//metodo para redefinir tamanho da imagem
	public Integer[] resizeImage(Image img, Integer maxWidth, Integer maxHeigth){
		Integer[] sizes  = {img.getWidth(null), img.getHeight(null)};
		
		if(maxWidth != null && sizes[0].intValue() > maxWidth.intValue()) {
			Double umPerc = sizes[0].intValue() * 0.01;
			Double percWidth = (maxWidth.intValue() / umPerc.doubleValue()) * 0.01;
			
			Double newSizeHeigth = sizes[1].intValue() * percWidth.doubleValue();
			
			sizes[0] = maxWidth;
			sizes[1] = newSizeHeigth.intValue();
		}
		
		if(maxHeigth != null && sizes[1].intValue() > maxHeigth.intValue()) {
			Double umPerc = sizes[1].intValue() * 0.01;
			Double percHeigth = (maxHeigth.intValue() / umPerc.doubleValue()) * 0.01;
			
			Double newSizeWidth = sizes[0].intValue() * percHeigth.doubleValue();
			
			sizes[1] = maxHeigth;
			sizes[0] = newSizeWidth.intValue();
		}
		return sizes;
	}
	
}
