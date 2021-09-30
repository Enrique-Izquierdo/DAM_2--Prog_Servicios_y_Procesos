package es.florida.psp;

import java.util.Scanner;

public class AE1_T1__7 {

	private static void determinarCatProfesional() {
		Scanner entradaTeclado = new Scanner(System.in);		
		System.out.print("Introduzca el nombre: ");
		String nombre = entradaTeclado.next();		
		System.out.print("Introduzca los a�os de experiencia: ");
		float anyos = entradaTeclado.nextFloat();		
		entradaTeclado.close();
		
		if(anyos>8) {
			System.out.println(nombre + " con " + anyos + " a�os de experiencia es Analista / Arquitecto. "
					+ "Salario a convenir en base a rol");
		} else if (anyos<=8 && anyos>5) {
			System.out.println(nombre + " con " + anyos + " a�os de experiencia es Desarrollador Senior L2 � 28000-36000");
		} else if (anyos<=5 && anyos>3) {
			System.out.println(nombre + " con " + anyos + " a�os de experiencia es Desarrollador Senior L1 � 22000-28000");
		} else if (anyos<=3 && anyos>=1) {
			System.out.println(nombre + " con " + anyos + " a�os de experiencia es Desarrollador Junior L2 � 18000-22000");
		} else {
			System.out.println(nombre + " con " + anyos + " a�os de experiencia es Desarrollador Junior L1 � 15000-18000");
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		determinarCatProfesional();
	}

}
