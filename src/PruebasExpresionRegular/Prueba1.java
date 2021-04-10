package PruebasExpresionRegular;

public class Prueba1 {

	public static void main(String[] args) {
		/* System.out.println("CLI11".matches("CLI[0-9]{2}")); */
		/* System.out.println("Clinsman ".matches("[[a-zA-Z]\\s]{1,20}")); */
		/*
		 * System.out.println("atunquitar@gmail.com".matches(
		 * "[[a-zA-Z][_.][a-zA-Z]]{1,20}@[a-z]{5,7}.[a-z]{2,3}"));
		 */
		System.out.println("".matches("([0-9]{11})?"));
	}

}
