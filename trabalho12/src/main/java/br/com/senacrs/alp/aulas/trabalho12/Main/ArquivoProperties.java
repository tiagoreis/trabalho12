package br.com.senacrs.alp.aulas.trabalho12.Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArquivoProperties {

	File arquivo = null;
	Map<String, String> map = new HashMap<String, String>();
	String token = "=";
	// String config_dir = null;
	// String config_dir = System.getProperty("user.dir") + File.separatorChar;
	private String DIRETORIO_CONFIG = System.getProperty("user.dir")
			+ File.separatorChar + "config" + File.separatorChar;

	Integer port = null;
	String keyRootConfig = "root_dir";
	String keyPort = "port";

	String keyGet = "get";
	String keyHost = "host";

	String DIRETORIO_HTML = System.getProperty("user.dir") + File.separatorChar
			+ "html" + File.separatorChar;

	String ROOT_DIR = System.getProperty("user.dir");

	String keyRootDir = "root_dir";

	public void lerArquivoConfig(File arquivoAux) {

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

		// System.out.println(port + ":" + DIRETORIO_CONFIG);
		// System.out.println(port + ":" + DIRETORIO_HTML);

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

	public void validaArgumento(String[] args) {

		String argumentoConfig = null;
		String argumentoHttp = null;
		File arquivoConfig = null;
		File arquivoHttp = null;

		if (args == null) {
			throw new IllegalArgumentException();
		}

		argumentoConfig = args[0];
		argumentoHttp = args[1];

		// TODO FAZER VALIDACAO
		if (args[0] == null) {
			throw new IllegalArgumentException();
		}

		arquivoConfig = new File(DIRETORIO_CONFIG + argumentoConfig);
		arquivoHttp = new File(DIRETORIO_CONFIG + argumentoHttp);

		if (arquivoConfig.isDirectory() || !arquivoConfig.exists()) {
			throw new IllegalArgumentException();
		}

		try {
			lerArquivoConfig(arquivoConfig);
			// System.out.println(getPort() + ":" + getDIRETORIO_CONFIG());
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}

		try {
			lerArquivoRequisicao(arquivoHttp);
			// System.out.println(getPort() + ":" + getDIRETORIO_HTML());
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}

	}

	public void validaDadosConfig(Map<String, String> map) {

		validaPorta(map);

		// config_dir = map.get(keyRootDir);
		// port = Integer.valueOf((map.get(keyPort)));

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

	public void lerArquivoRequisicao(File arquivoRequisicao) {

		Validation validation = null;
		validation = new Validation();

		// arquivo = arquivoRequisicao;

		if (!arquivoRequisicao.exists()) {
			throw new IllegalArgumentException();
		}

		String linhas = null;
		String key = null;
		String value = null;
		int contadorChave = 0;
		StringTokenizer stringToken = null;

		BufferedReader reader = null;

		String[] arrKeyValue = null;

		String linhaHost = null;
		String linhaGet = null;
		String caminho = null;
		String caminhoReal = null;

		try {

			reader = new BufferedReader(new FileReader(arquivoRequisicao));

			if (arquivoRequisicao.length() == 0) {
				throw new IllegalArgumentException();
			}

			try {

				linhaGet = reader.readLine();
				linhaHost = reader.readLine();

				// System.out.println("linhaGet " + linhaGet);
				// System.out.println("linhaHost " + linhaHost);

				caminho = lerCaminhoRequisicao(linhaGet);

				caminho = caminho.replace('/', File.separatorChar);

				validaHost(linhaHost);

				caminhoReal = ROOT_DIR + caminho;

				System.out.println("caminhoReal " + caminhoReal);

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

		// System.out.println(port + ":" + DIRETORIO_CONFIG);
		// System.out.println(port + ":" + DIRETORIO_HTML);

	}

	private void validaHost(String linhaHost) {

		String[] elementos = null;
		String espaco = " ";

		if (linhaHost.startsWith("Host: ")) {

			elementos = linhaHost.split(espaco);

			if (elementos.length == 2) {

				if (elementos[0].equalsIgnoreCase("Host:")) {
					// System.out.println("sem espaço");
				}

			}

		}

	}

	
	
	private String lerCaminhoRequisicao(String linhaGet) {
	
		String[] elementos = null;
		String espaco = " ";
		elementos = linhaGet.split(espaco);
		String caminho = null;

		if (elementos.length == 3) {

			if (elementos[0].equalsIgnoreCase("GET")) {

				if (elementos[2].equalsIgnoreCase("http/1.1")) {

					caminho = elementos[1];

					if (caminho.startsWith("/")) {

					}

				}

			}

		}

		return caminho;
	}

}
