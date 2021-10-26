package psp.AE.AE2_T2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;




public class CalculadorProbabilidad {
	//atributos
	//constructores
	//getters y setters	
	//métodos de interface
	
	/**Método: main
	 * Descripción: invoca los métodos "calcular()", "guardarEnArchivo()" y "mostrarMensaje()".	  				
	 * Parámetros de entrada: args (String[]) >> (args[0]: nombre del Neo; args[1]: posicion del Neo; args[2]: velocidad del NEO)
	 * Parámetros de salida: no   */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CalculadorProbabilidad probabilidad = new CalculadorProbabilidad();
		String nombreNEO = args[0];
		double posicion = Double.parseDouble(args[1]); //Ojo, el indice es 0 si no pasamos el nombre del Neo
		double velocidad = Double.parseDouble(args[2]); //Ojo, el indeice es 1 si no pasamos el nombre del Neo.
		double resultado = probabilidad.calcular(posicion, velocidad);
		//System.out.println(nombreNEO+": "+resultado+"%) --> guardada en archivo.");
		probabilidad.guardarEnArchivo(nombreNEO, resultado);
		probabilidad.mostrarMensaje(nombreNEO);
		
	}
	
	
	//métodos de implementación
	
	/**Método: calcular
	 * Descripción: calcula la probabilidad de colisión del NEO con los valores posición y velocidad pasados por parámetro 				
	 * Parámetros de entrada: posicionNEO (double, posición del NEO), velocidadNEO (double, velocidad del NEO)
	 * Parámetros de salida: (double) probabilidad de colisión del NEO   */
	private double calcular(double posicionNEO, double velocidadNEO) {
		double posicionTierra = 1;
		double velocidadTierra = 100;
		for (int i = 0; i < (50 * 365 * 24 * 60 * 60); i++) {
		posicionNEO = posicionNEO + velocidadNEO * i;
		posicionTierra = posicionTierra + velocidadTierra * i;
		}
		double resultado = 100 * Math.random() *
				Math.pow( ((posicionNEO-posicionTierra)/(posicionNEO+posicionTierra)), 2);		
		return resultado;
	}
	
	
	/**Método: guardarEnArchivo
	 * Descripción: crea el directorio "resultados", en caso de no existir, y guarda la probabilidad del colisión 
	 *              pasada por parámetro en un archivo .txt, llamado con el nombre del NEO pasado por parámetro.			
	 * Parámetros de entrada: pNombreNEO (String, nombre del NEO), pValorGuardar (double, probabilidad de colisión del NEO)
	 * Parámetros de salida: no   */
	private void guardarEnArchivo(String pNombreNEO, double pValorGuardar) {		
		File directorio = new File("resultados");
		if (!directorio.exists()) {
			if(directorio.mkdir()) {
				System.out.println("Directorio creado");
			} else {
				System.out.println("Error al crear el diretorio");
			}
		}		
		String nombreArchivo = "resultados"+File.separator+pNombreNEO+ ".txt";
		try {			
			FileWriter fr = new FileWriter(nombreArchivo);
			BufferedWriter br = new BufferedWriter(fr);
			br.write(String.valueOf(pValorGuardar));
			br.close();
			fr.close();			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**Método: mostrarMensaje
	 * Descripción: lee el archivo correspondiente al NEO pasado por parámetro, y muestra la probabilidad
	 *              de colisión y un mensaje de alerta.				
	 * Parámetros de entrada: pNombreNEO (String, nombre del NEO)
	 * Parámetros de salida: no   */
	private void mostrarMensaje(String pNombreNEO) {
		String archivo = "resultados"+File.separator+pNombreNEO+".txt";		
		try {
			FileReader fr = new FileReader(archivo);
			BufferedReader br = new BufferedReader(fr);			
			double contenidoArchivo = Double.parseDouble(br.readLine());
			DecimalFormat df = new DecimalFormat("#.00");
			String probabilidad = df.format(contenidoArchivo);
			if(contenidoArchivo > 10) {
				System.err.println("Probabilidad de colisión de "+pNombreNEO+": "+probabilidad+"% --> Vas a morir");
			}else {
				System.out.println("Probabilidad de colisión de "+pNombreNEO+": "+probabilidad+"% --> Vivirás otro día");
			}
			br.close();
			fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
	}	

	
	
	
	//métodos de interface
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		CalculadorProbabilidad probabilidad = new CalculadorProbabilidad();
//		String nombreNEO = args[0];
//		double posicionNEO = Double.parseDouble(args[1]); //Ojo, el indice es 0 si no pasamos el nombre del Neo
//		double velocidadNEO = Double.parseDouble(args[2]); //Ojo, el indeice es 1 si no pasamos el nombre del Neo.
//		//double resultado = probabilidad.calcular(posicion, velocidad);
//		double posicionTierra = 1;
//		double velocidadTierra = 100;
//		for (int i = 0; i < (50 * 365 * 24 * 60 * 60); i++) {
//		posicionNEO = posicionNEO + velocidadNEO * i;
//		posicionTierra = posicionTierra + velocidadTierra * i;
//		}
//		double resultado = 100*Math.random()*Math.pow(((posicionNEO-posicionTierra)/(posicionNEO+posicionTierra)), 2);		
//		//System.out.println(nombreNEO+": "+resultado+"%) --> guardada en archivo.");
//		//probabilidad.guardarEnArchivo(nombreNEO, resultado);
//		File directorio = new File("resultados");
//		if (!directorio.exists()) {
//			if(directorio.mkdir()) {
//				System.out.println("Directorio creado");
//			} else {
//				System.out.println("Error al crear el diretorio");
//			}
//		}		
//		String nombreArchivo = "resultados"+File.separator+nombreNEO+ ".txt";
//		try {			
//			FileWriter fr = new FileWriter(nombreArchivo);
//			BufferedWriter br = new BufferedWriter(fr);
//			br.write(String.valueOf(resultado));
//			br.close();
//			fr.close();			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		//probabilidad.mostrarMensaje(nombreNEO);
//		String archivo = "resultados"+File.separator+nombreNEO+".txt";		
//		try {
//			FileReader fr = new FileReader(archivo);
//			BufferedReader br = new BufferedReader(fr);			
//			double contenidoArchivo = Double.parseDouble(br.readLine());
//			DecimalFormat df = new DecimalFormat("#.00");
//			String probabi = df.format(contenidoArchivo);
//			if(contenidoArchivo > 10) {
//				System.err.println("Probabilidad de colisión de "+nombreNEO+": "+probabi+"% --> Vas a morir");
//			}else {
//				System.out.println("Probabilidad de colisión de "+nombreNEO+": "+probabi+"% --> Vivirás otro día");
//			}
//			br.close();
//			fr.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}				
//	}
	
}
