package br.com.financas.main;

import java.net.URL;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class BuscaCEP {
	public void buscarCEP(String numeroDoCEP) {
		String cep = numeroDoCEP;
		String logradouro = "";
		String tipoLogradouro = "";
		String resultado = null;
		String bairro = "";
		String cidade = "";
		String uf = "";

		try {

			URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml");
			System.out.println(url.toString());
			// Classe modelo para criarmos o XML
			SAXReader xml = new SAXReader();
			// Irar capturar os dados do XML
			Document document = xml.read(url);
			// Será usado como apoio para a verredura do documento XML
			Element element = document.getRootElement();
			
			// Laço para varrer o documento XML, usando o Iterator do JAVA.util
			for (Iterator<Element> it = element.elementIterator(); it.hasNext();) {
				Element elementTemp = it.next();
				// Se ele achar o elemento com o nome logradouro, irar setar a variavel com o
				// texto do elemento
				if (elementTemp.getQualifiedName().equals("1")) {
					resultado = element.getText();
					if (elementTemp.getQualifiedName().equals("logradouro")) {
						logradouro = element.getText();
					}

					if (elementTemp.getQualifiedName().equals("tipo_logradouro")) {
						tipoLogradouro = element.getText();
					}

					if (elementTemp.getQualifiedName().equals("bairro")) {
						bairro = element.getText();
					}

					if (elementTemp.getQualifiedName().equals("cidade")) {
						cidade = element.getText();
					}

					if (elementTemp.getQualifiedName().equals("cep")) {
						cep = element.getText();
					}

					if (elementTemp.getQualifiedName().equals("uf")) {
						uf = element.getText();
					}

				} else {
					System.out.println("Endereço não encontrado!");
				}
			}

			System.out.println("Logradouro: " + logradouro);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BuscaCEP buscaCEP = new BuscaCEP();
		buscaCEP.buscarCEP("52280235");
	}

}
