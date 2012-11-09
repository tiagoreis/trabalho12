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


	//Map<String, String> map = new HashMap<String, String>();
	String config_dir = System
			.getProperty("user.dir")
			+ File.separatorChar
			+ "config"
			+ File.separatorChar;

	Integer port = null;
	String keyRootDir = "root_dir";
	String keyPort = "port";

	public static void validaArgumento(String[] args) {

		ArquivoProperties arquivoProperties = null;
		String argumento = null;
		File arquivo = null;

		arquivoProperties = new ArquivoProperties();

		if (args == null || args.length != 1) {
			throw new IllegalArgumentException();
		}

		argumento = args[0];

		if (args[0] == null) {
			throw new IllegalArgumentException();
		}

		arquivo = new File(DIRETORIO_CONFIG + argumento);

		if (arquivo.isDirectory() || !arquivo.exists()) {
			throw new IllegalArgumentException();
		}

		arquivoProperties.lerArquivo(arquivo);

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
