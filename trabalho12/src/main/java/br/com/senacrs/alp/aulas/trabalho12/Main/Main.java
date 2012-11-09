package br.com.senacrs.alp.aulas.trabalho12.Main;


public class Main {

	public static void main(String[] args) {

		Validation validation = null;
		validation = new Validation();
		
		try {
			validation.validaArgumento(args);
		} catch (Exception e) {
			System.out.println("Erro");
			throw new IllegalArgumentException();

		}

	}

}
