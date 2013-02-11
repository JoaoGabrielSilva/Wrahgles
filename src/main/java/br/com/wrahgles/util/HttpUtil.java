package br.com.wrahgles.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public final class HttpUtil {
	
	public static BufferedReader getCodigoFonteURL(final String strUrl) throws IOException {
		
		URL url = new URL(strUrl);
		URLConnection connection = url.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
		return in;
	}

}
