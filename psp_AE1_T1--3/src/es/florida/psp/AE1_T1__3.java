package es.florida.psp;

public class AE1_T1__3 {
	
	private static int sumarPares (int pNumero) {		
		int suma = 0;		
		for (int i = pNumero; i>1; i--) {
			if(i%2 == 0) {
				suma += i;
			}
		}		
		return suma;
	}	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int numero = Integer.parseInt(args[0]);
		System.out.println("La suma de los números pares incluidos en " + args[0] + " es : "+ sumarPares(numero));
	}
}
