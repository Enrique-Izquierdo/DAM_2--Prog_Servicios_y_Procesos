package es.florida.psp;

import java.util.Scanner;

public class AE1_T1__8 {
	
	private static void operarIntervaloNumerico() {		
		Scanner entradaTeclado = new Scanner(System.in);		
		System.out.print("Introduce un n�mero: ");
		int num1 = entradaTeclado.nextInt();		
		System.out.print("Introduce otro n�mero: ");
		int num2 = entradaTeclado.nextInt();		
		entradaTeclado.close();
		
		long inicio = System.currentTimeMillis();
		int mayor, menor;
		if (num1<num2) {
			menor = num1;
			mayor = num2;
		} else {
			menor = num2;
			mayor = num1;
		}
		
		for (int i=menor; i<=mayor; i++) {
			boolean primo = true;
			for (int j=2; j<i; j++) {				
				if (i%j==0) {
					primo = false;
				}				
			}
			if (primo == true) {
				System.out.println(i + " es primo, ");				
			} else {
				System.out.println(i + " es compuesto, ");
			}			
		}
		
		long fin = System.currentTimeMillis();
		long tiempo = fin - inicio;
		//double tiempo = (double) ((fin-inicio)/1000);
		System.out.println("Tiempo de ejecuci�n: "+ tiempo +" milisegundos");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		operarIntervaloNumerico();
	}

}
