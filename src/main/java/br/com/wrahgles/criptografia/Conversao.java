package br.com.wrahgles.criptografia;

/**
 * @author James Rugno
 *
 */
public class Conversao{   
 
  /** 
   * Array com os digitos hexadecimais 
   */	
   private static String[] hexDigits = {"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};   
  
   /**
    * Conversão de byte para hexadecimal
    * 
    * @param  byte b
    * @return array
    */ 
   public static String byteToHexString(byte b)   
   {   
      int n = b;   
      if(n < 0) n = 256 + n;   
      int d1 = n / 16;   
      int d2 = n % 16;  
      
      return hexDigits[d1]+hexDigits[d2];   
   }   
  
   /**
    * Conversão o array de Byte para um hexadeciaal String
    * 
    * @param byte[] b
    * @return String result - Resultado da conversão
    */
   public static String byteArrayToHexString(byte[] b)   
   {   
      String result = "";   
      
      for(int i=0;i<b.length;++i)
      {
         result += byteToHexString(b[i]);    
      }
       
      return result;   
   }   
       
   /**
    * Converte array byte para uma String base 64
    * 
    * @param  byte[] b
    * @param  int len
    * @return String result - Resultado da conversão
    * 
    */
   public static String byteArrayToBase64String(byte[] b, int len)   
   {   
      String s = "";  
     
      int n = len / 3;   
      int m = len % 3;   
      
      for(int i=0; i<n; ++i)   
      {   
         int j = i * 3;   
         s += toBase64(b[j],b[j+1],b[j+2]);   
      }   
      
      if(m == 1)
      {   
         s += toBase64(b[len-1]);   
      }
      else if(m == 2)
      {
         s += toBase64(b[len-2],b[len-1]);   
      }
 
      String result = ""; 
      
      len = s.length(); 
      
      n = len / 64;   
      m = len % 64;   
  
      for(int i = 0; i < n; ++i)   
      {   
         result += s.substring(i*64,(i+1) * 64) + "\n";   
      }   
  
      if(m > 0)
      {
         result += s.substring(n*64, len) + "\n";   
      }
      
      return result;   
      
   }   
      
   /**
    * Converte array byte para uma String base 64
    * 
    * @param  byte[] b
    * @param  int len
    * @return base64StringToByteArray
    * 
    */ 
   public static String byteArrayToBase64String(byte[] b)   
   {   
      return byteArrayToBase64String(b, b.length); 
   }   
  
   /**
    * Transforma para base 64
    * 
    * @param  byte b1
    * @param  byte b2
    * @param  byte b3
    * @return String result - Resultado da transformação
    * 
    */
   private static String toBase64(byte b1, byte b2, byte b3)   
   {   
      int[] digit = new int[4];   
      digit[0] = (b1 & 0xFC) >>> 2;   
      digit[1] = (b1 & 0x03) << 4;   
      digit[1] |= (b2 & 0xF0) >> 4;   
      digit[2] = (b2 & 0x0F) << 2;   
      digit[2] |= (b3 & 0xC0) >> 6;   
      digit[3] = (b3 & 0x3F); 
      
      String result = "";  
      
      for(int i=0;i<digit.length;++i)  
      {
         result += base64Digit(digit[i]);   
      }
      
      return result;   
   }   
      
   /**
    * Perform a padded base64 transformation
    * 
    * @param  byte b1
    * @param  byte b2
    * @return String result - Resultado da transformação
    * 
    */
   private static String toBase64(byte b1, byte b2)   
   {   
      int[] digit = new int[3];   
      digit[0] = (b1 & 0xFC) >>> 2;   
      digit[1] = (b1 & 0x03) << 4;   
      digit[1] |= (b2 & 0xF0) >> 4;   
      digit[2] = (b2 & 0x0F) << 2;   
      String result = "";   
      for(int i=0;i<digit.length;++i)    
         result += base64Digit(digit[i]);   
      result += "=";   
      return result;   
   }   
      
   /**
    * Perform a padded base64 transformation
    * @since 07/05/2008
    * 
    * @param  byte b1
    * @return String result - Resultado da transformação
    * 
    */
   private static String toBase64(byte b1)   
   {   
      int[] digit = new int[2];  
      
      digit[0] = (b1 & 0xFC) >>> 2;   
      digit[1] = (b1 & 0x03) << 4;   
      
      String result = "";   
      
      for(int i=0;i<digit.length;++i)   
      {
         result += base64Digit(digit[i]);   
      }
      
      result += "==";   
      
      return result;   
   }   
      
   /**
    * Perform a padded base64 transformation
    * 
    * @param  int i
    * @return char result - Retorna uma caracter do tipo char
    */
   private static char base64Digit(int i)   
   {   
      if(i < 26)   
      {
         return (char) ('A' + i);   
      }
      
      if(i < 52)   
      {
    	  return (char) ('a' + (i - 26));   
      }
      
      if(i < 62)
      {
         return (char) ('0' + (i - 52));   
      }
      
      if(i == 62)
      {
         return '+';   
      }
      else  
      {
         return '/';  
      }
      
   }   
  
   /**
    * Perform a padded base64 transformation
    * 
    * @param  String s
    * @return byte b
    * @throws NumberFormatException
    */  
   public static byte[] base64StringToByteArray(String s) throws NumberFormatException   
   {   
      String t = "";   
      for(int i=0;i<s.length();++i)   
      {   
         char c = s.charAt(i);   
  
         if(c == '\n')   
            continue;   
  
         else if((c>='A' && c<='Z') ||    
                (c>='a' && c<='z') ||    
                (c>='0' && c<='9') ||    
                 c=='+' || c=='/')    
            t += c;   
         else if(c=='=')   
            break;   
         else    
            throw new NumberFormatException();   
      }   
  
      int len = t.length();   
      int n = 3*(len/4);   
  
      switch(len % 4)   
      {   
         case 1:   
            throw new NumberFormatException();   
         case 2:   
            len += 2;   
            n += 1;   
            t += "==";   
            break;   
         case 3:   
            ++len;   
            n += 2;   
            t += "=";   
            break;   
      }   
  
      byte[] b = new byte[n];   
  
      for(int i=0; i < len/4; ++i)   
      {   
         byte[] temp = base64ToBytes(t.substring(4*i,4*(i+1)));   
         for(int j=0;j<temp.length;++j)   
         {   
            b[3*i+j] = temp[j];   
         }   
      }   
      return b;   
   }   
  
   /**
    * 
    * @param  String s
    * @return byte b
    */
   private static byte[] base64ToBytes(String s)   
   {   
      int len = 0;  
      
      for(int i=0;i<s.length();++i)
      {
         if(s.charAt(i) != '=') 
         {
        	 ++len;   
      	 }
      }
      
      int[] digit = new int[len];   
      
      for(int i=0;i<len;++i)   
      {   
         char c = s.charAt(i);  
         
         if(c >= 'A' && c <= 'Z')  
         {
            digit[i] = c - 'A';   
         }
         else if(c >= 'a' && c <= 'z')
         {   
            digit[i] = c - 'a' + 26;   
         }
         else if(c >= '0' && c <= '9')
         {   
            digit[i] = c - '0' + 52;   
         }
         else if(c == '+')
         {   
            digit[i] = 62;   
         }
         else if(c == '/')
         {   
            digit[i] = 63;
         }
      }   
  
      byte[] b = new byte[len-1];   
      
      switch(len)   
      {   
         case 4:   
            b[2] = (byte) ((((digit[2]) & 0x03) << 6) | digit[3]);   
         case 3:   
            b[1] = (byte) ((((digit[1]) & 0x0F) << 4) | ((digit[2] & 0x3C) >>> 2));   
         case 2:   
            b[0] = (byte) ((digit[0] << 2) | ((digit[1] & 0x30) >>> 4));   
      }   
      return b;   
      
   }   
   
}  

