package br.com.senacrs.alp.aulas.trabalho12.Main;

import java.io.File;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
	
	
	private final static String DIRETORIO_CONFIG = System
			.getProperty("user.dir")
			+ File.separatorChar
			+ "config"
			+ File.separatorChar;

	private final static String DIRETORIO_HTTP = System
			.getProperty("user.dir")
			+ File.separatorChar
			+ "html"
			+ File.separatorChar;


	//Map<String, String> map = new HashMap<String, String>();

	Integer port = null;
	String keyRootConfig = "root_dir";
	String keyPort = "port";

	String keyGet = "get";
	String keyHost = "host";

	public static void validaArgumento(String[] args) {

		ArquivoProperties arquivoProperties = null;
		String argumentoConfig = null;
		String argumentoHttp = null;
		File arquivoConfig = null;
		File arquivoHttp = null;
		

		arquivoProperties = new ArquivoProperties();

		if (args == null) {
			throw new IllegalArgumentException();
		}

		argumentoConfig = args[0];
		argumentoHttp = args[1];

		// FAZER VALIDACAO
		if (args[0] == null) {
			throw new IllegalArgumentException();
		}

		arquivoConfig = new File(DIRETORIO_CONFIG + argumentoConfig);
		arquivoHttp = new File(DIRETORIO_HTTP + argumentoHttp);

		if (arquivoConfig.isDirectory() || !arquivoConfig.exists()) {
			throw new IllegalArgumentException();
		}


		
		try {
			arquivoProperties.lerArquivo(arquivoConfig);
			System.out.println(arquivoProperties.getPort() + ":" + arquivoProperties.getDIRETORIO_CONFIG());
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
		
		try {
			arquivoProperties.lerArquivo(arquivoHttp);			
			System.out.println(arquivoProperties.getPort() + ":" + arquivoProperties.getDIRETORIO_HTML());
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}		


	}

	public void validaDadosConfig(Map<String, String> map) {

		validaPorta(map);

		//config_dir = map.get(keyRootDir);
		//port = Integer.valueOf((map.get(keyPort)));

	}

	public void validaPorta(Map<String, String> map) {

		try {

			Pattern pattern = Pattern.compile("[0-9]");
			Matcher matcher = pattern.matcher(map.get(keyPort));

			if (matcher.matches() == true) {
				throw new IllegalArgumentException();
			}

		} catch (Exception e) {
			throw new IllegalArgumentException(e);

		}

	}



}
