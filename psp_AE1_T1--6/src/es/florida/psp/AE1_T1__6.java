package es.florida.psp;

import java.util.ArrayList;
import java.util.Scanner;

public class AE1_T1__6 {

	private static int pedirYOperarNumeros() {		
		ArrayList<Integer> listaEnteros = new ArrayList<Integer>();		
		Scanner entradaTeclado = new Scanner(System.in);				
		for (int i=0; i<5; i++) {
			System.out.print("Introduzca n�mero entero: ");
			listaEnteros.add(entradaTeclado.nextInt());
		}		
		entradaTeclado.close();
		//System.out.println(listaEnteros);		
		int suma = 0;		
		for (int i=listaEnteros.size()-1; i>=0; i--) {
			suma += listaEnteros.get(i);
			System.out.println(listaEnteros.get(i));
		}		
		return suma;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("La suma de los valores enteros introducidos es: " + pedirYOperarNumeros());

	}

}
