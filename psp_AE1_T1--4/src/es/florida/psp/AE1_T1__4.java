package es.florida.psp;

public class AE1_T1__4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		long factorial = 1;
		for (int i=Integer.parseInt(args[0]); i>=1; i--) {
			factorial *= i;
		}		
		System.out.println("El factorial de " + args[0] + " es: " + factorial);
	}

}
