package es.florida.psp;

import java.util.ArrayList;

public class AE1_T1__5 {

	private static double devolverMayor(ArrayList<Double> listaNumeros) {
		double mayor = 0d;
		for (double i : listaNumeros) {
			if (i > mayor) {
				mayor = i;
			}
		}						
		return mayor;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Double> listNumber = new ArrayList<Double>();		
		try {
			for (String i : args) {
				listNumber.add(Double.parseDouble(i));
				//System.out.println(i + " es un string num�rico.");
			}		
			System.out.println("El mayor n�mero introducido es: " + devolverMayor(listNumber));
		} catch (NumberFormatException e) {
			System.out.println("Uno de los elementos introducidos NO es un string num�rico.");
		}		
	}	
}
