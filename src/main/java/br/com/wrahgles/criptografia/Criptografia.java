package br.com.wrahgles.criptografia;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author James Rugno
 *
 */
public class Criptografia   
{   
   /**
    * Constante Chave de Criptografia
    */
    private static final String PASSFRASE = "achavelcz052010";  
      
   /**
    * Criptografia - cript
    * 
    * @param  String str 
    * @return String strCript - Retorna a String criptografada
    * @throws Exception
    * @throws FactoryException 
    */
   public static String crip(String str) throws Exception   
   {   
      String strCript = str;   
         
      try  
      {   
         Cipher ch    = Cipher.getInstance("Blowfish");   
         SecretKey k1 = new SecretKeySpec(PASSFRASE.getBytes(), "Blowfish");   
            
         ch.init(Cipher.ENCRYPT_MODE, k1);  
         
         byte b[]  = ch.doFinal(strCript.getBytes());   
         
         String s1 = Conversao.byteArrayToBase64String(b);   
         strCript  = s1;   
      }   
      catch(Exception e)   
      {   
         e.printStackTrace();  
      }   
      
      //Adicionado para resolver o problema da url
      if(strCript.contains("=="))
      {
    	  strCript = strCript.substring(0,(strCript.lastIndexOf("=")-1));
    	 
      }else if(strCript.contains("="))
      {
    	  strCript = strCript.substring(0,strCript.lastIndexOf("="));
      }
      
      return strCript;   
   }   
      
   /**
    * Decriptografia de String
    * 
    * @param  String str
    * @return String strDecript - Retorna a String descriptografada
    * @throws Exception
    * @throws FactoryException 
    */
   public static String decrip(String str) throws Exception 
   {  
	   String strDecript = str;
	   
	   try  
      {   
    	 
         Cipher ch    = Cipher.getInstance("Blowfish");   
         SecretKey k1 = new SecretKeySpec(PASSFRASE.getBytes(), "Blowfish");   
            
         ch.init(Cipher.DECRYPT_MODE, k1);   
         
         byte b[]  = Conversao.base64StringToByteArray(strDecript);   
         byte b1[] = ch.doFinal(b);   
         
         String s1 = new String(b1);   
         
         strDecript = s1;   
         
    	  
      }   
      catch(Exception e)   
      {   
         e.printStackTrace();  
      }   
      
      return strDecript;   
   }   
  
}  

