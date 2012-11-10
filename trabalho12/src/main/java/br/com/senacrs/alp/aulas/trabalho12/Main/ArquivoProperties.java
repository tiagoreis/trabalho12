package br.com.senacrs.alp.aulas.trabalho12.Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class ArquivoProperties {

	File arquivo = null;
	Map<String, String> map = new HashMap<String, String>();
	String token = "=";
	//String config_dir = null;
	//String config_dir = System.getProperty("user.dir") + File.separatorChar;
	String DIRETORIO_HTML = System
			.getProperty("user.dir")
			+ File.separatorChar
			+ "html"
			+ File.separatorChar;

	String DIRETORIO_CONFIG = System
			.getProperty("user.dir")
			+ File.separatorChar
			+ "config"
			+ File.separatorChar;

	Integer port = null;
	String keyRootDir = "root_dir";
	String keyPort = "port";

	public void lerArquivo(File arquivoAux) {

		Validation validation = null;
		validation = new Validation();
		
		
		arquivo = arquivoAux;

		if (!arquivo.exists()) {
			throw new IllegalArgumentException();
		}

		String linhas = null;
		String key = null;
		String value = null;
		int contadorChave = 0;
		StringTokenizer stringToken = null;

		BufferedReader reader = null;

		String[] arrKeyValue = null;

		try {

			reader = new BufferedReader(new FileReader(arquivo));

			if (arquivo.length() == 0) {
				throw new IllegalArgumentException();
			}

			try {

				while ((linhas = reader.readLine()) != null) {

					stringToken = new StringTokenizer(linhas, token);
					contadorChave = stringToken.countTokens();

					if (linhas.indexOf(token) == -1 || contadorChave > 2) {
						throw new IllegalArgumentException();
					} else {

						arrKeyValue = linhas.split(token);

						key = arrKeyValue[0].trim();
						value = arrKeyValue[1].trim();

						
						map.put(key, value);

					}
				}
				
				validation.validaDadosConfig(map);
				port = Integer.valueOf((map.get(keyPort)));
				 
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

//		System.out.println(port + ":" + DIRETORIO_CONFIG);
//		System.out.println(port + ":" + DIRETORIO_HTML);
		

	}

	public String getDIRETORIO_HTML() {
		return DIRETORIO_HTML;
	}

	public void setDIRETORIO_HTML(String dIRETORIO_HTML) {
		DIRETORIO_HTML = dIRETORIO_HTML;
	}

	public String getDIRETORIO_CONFIG() {
		return DIRETORIO_CONFIG;
	}

	public void setDIRETORIO_CONFIG(String dIRETORIO_CONFIG) {
		DIRETORIO_CONFIG = dIRETORIO_CONFIG;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	
	
	
}
