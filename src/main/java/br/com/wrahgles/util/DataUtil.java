package br.com.wrahgles.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//classe utilizada para validar apenas datas


public class DataUtil {
	
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
	private static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
	

	public static String formatarDataTimestamp(Date data){
		if(data == null){return null;}
		return new SimpleDateFormat("dd/MM/yyyy HH:mm").format(data);
	}
	
	public static String formatarDataTimestampSegundos(Date data){
		if(data == null)
			return null;
		return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(data);
	}
	public static String formatarData(Date data){
		if(data == null)
			return null;
		return dateFormat.format(data);
	}

	public static String formatarHora(Date data){
		if(data == null)
			return null;
		return new SimpleDateFormat("HH:mm").format(data);
	}
	
	public static double transformarMillisegundosEmDia(double diferencaDeTempoEmMillisegundos){
		return diferencaDeTempoEmMillisegundos / 1000 / 60 / 60 / 24;
	}
	
	public static boolean dataFinalEhMaiorDoQueDataInicial(String dataInicialParam, String dataFinalParam){
		Date dataInicial = obterData(dataInicialParam);
		Date dataFinal = obterData(dataFinalParam);
		return dataFinal.compareTo(dataInicial) == 1;
	}
	
	public static Date somaDiasNaData(Date data, int dias){
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(data);
		calendario.add(Calendar.DAY_OF_MONTH, dias);
		return calendario.getTime();
	}
	
	public static Date obterData(String dataString){
		if(dataString == null)
			return null;
		try{
			return dateFormat.parse(dataString);
			
		}catch(ParseException e){
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static Timestamp obterDataTimestamp(String dataString){
		if(dataString == null)
			return null;
		try{
			return new Timestamp(dateTimeFormat.parse(dataString).getTime());
		}catch(ParseException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static String obterDataFormatada(Date data){
		if(data == null)
			return null;
		return dateFormat.format(data);
	}
	
	public static long getDiferencaEntreData(Date dataInicio, Date dataFim){
		long diff = dataInicio.getTime() - dataFim.getTime();
		diff = diff / (1000 * 60 * 60 * 24);
		return diff;
	}
	
	public static String[] timeStampToStringArray(Timestamp ts){
		String tm = ts.toString().trim();
		String[] data = new String[6];
		
		data[0]=tm.substring(8,11);//dia
		data[1]=tm.substring(5,7);//mes
		data[2]=tm.substring(0,4);//ano
		data[3]=tm.substring(11, 13);//hora
		data[4]=tm.substring(14,16);//min
		data[5]=tm.substring(17,19);//seg
		
		return data;
	}
	
	//recebe um objeto do tipo timestamp e retorn uma string no formato 44/mm/aa
	public static String timeStampToStringFormated(Timestamp ts){
		String tm = ts.toString().trim();
		
		return tm.substring(8, 11).trim()+"/"+tm.substring(5,7).trim()+"/"+tm.substring(0,4).trim();
	}
	
	
	
	
	

}
