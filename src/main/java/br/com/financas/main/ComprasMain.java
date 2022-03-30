package br.com.financas.main;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.text.ParseException;

import com.mysql.cj.protocol.Resultset.Concurrency;

public class ComprasMain {

	public static void main(String[] args) throws ParseException, IOException {
		// TODO Auto-generated method stub

		String url = "https://h-apigateway.conectagov.estaleiro.serpro.gov.br/api-cep/v1/consulta/cep/";
		String cep = "52280235";

		String json = "https://viacep.com.br/ws/" + cep + "/json/";
		URL u = null;
		HttpURLConnection con = null;
		try {
			u = new URL(json);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			con = (HttpURLConnection) u.openConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int responseCode = con.getResponseCode();
		
		System.out.println(responseCode);
	}

}
