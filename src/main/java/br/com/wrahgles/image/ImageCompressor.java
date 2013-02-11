package br.com.wrahgles.image;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Locale;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;

import java.util.Iterator;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class ImageCompressor {
	
	public static final String JPEG_EXTENSION = "jpg";
	public static final String GIF_EXTENSION = "gif";
	public static final String PNG_EXTENSION = "png";
	
	public void compressJPEG(Image image, String outfile, int width, int height, int quality) throws Exception {
		BufferedImage buffImage = new BufferedImage(width, height, 1);
		Graphics2D graphics2D = buffImage.createGraphics();
		
		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
		graphics2D.drawImage(image, 0, 0,width, height, null);
		
		ImageWriter writer = null;
		Iterator iter = ImageIO.getImageWritersByFormatName(JPEG_EXTENSION);
		writer = (ImageWriter) iter.next();
		
		File fileImage = new File(outfile);
		ImageOutputStream ios = ImageIO.createImageOutputStream(fileImage);
		
		writer.setOutput(ios);
		ImageWriteParam iwparam = new JPEGImageWriteParam(Locale.getDefault());
		
		iwparam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		iwparam.setCompressionQuality((float)quality / 100);
		
		writer.write(null, new IIOImage(buffImage, null, null), iwparam);
		
		ios.flush();
		writer.dispose();
		ios.close();
		
	}
	
	
	public void compressGIF(Image image, String outfile, int width, int heigth, int quality) throws Exception{
		
		BufferedImage buffImage = new  BufferedImage(width, heigth, 1);
		Graphics2D graphic2D = buffImage.createGraphics();
		graphic2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
		graphic2D.drawImage(image, 0, 0, width, heigth, null);
		
		ImageWriter writer = null;
		Iterator iter = ImageIO.getImageWritersByFormatName(GIF_EXTENSION);
		writer = (ImageWriter) iter.next();
		
		File fileImage = new File(outfile);
		ImageOutputStream ios = ImageIO.createImageOutputStream(fileImage);
		
		writer.setOutput(ios);
		
		ImageWriteParam iwparam = new JPEGImageWriteParam(Locale.getDefault());
		iwparam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		iwparam.setCompressionQuality((float) quality / 100);
		
		writer.write(null, new IIOImage(buffImage, null, null), iwparam);
		
		ios.flush();
		writer.dispose();
		ios.close();
		
	}
	
	public void compressPNG(Image image, String outfile, int width, int height, int quality) throws Exception {	
		BufferedImage buffImage = new BufferedImage(width, height, 1);

		Graphics2D graphics2D = buffImage.createGraphics();
		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
		
		graphics2D.drawImage(image, 0, 0, width, height, null);
		
		ImageWriter writer = null;
		Iterator iter = ImageIO.getImageWritersByFormatName(PNG_EXTENSION);
		writer = (ImageWriter)iter.next();
		
		File fileImage = new File(outfile);
		ImageOutputStream ios = ImageIO.createImageOutputStream(fileImage);
		
		writer.setOutput(ios);
		
		ImageWriteParam iwparam = new JPEGImageWriteParam(Locale.getDefault());
		
		iwparam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		iwparam.setCompressionQuality((float)quality / 100);
		
		writer.write(null, new IIOImage(buffImage, null, null), iwparam);
		
		ios.flush();
		writer.dispose();
		ios.close();
	}
	
	

}
