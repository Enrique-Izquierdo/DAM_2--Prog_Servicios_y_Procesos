package psp.AE.AE2_T2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class LanzadorRutaDinamica {
	//atributos	
	//constructores
	//getters y setters	
	//métodos interface	
	
	/**Método: main
	 * Descripción: invoca los métodos "obtenerLineaDatosNeo" y "lanzar_CalculadorProbabilidad" en bloques de
	 *              tantos procesos como número de cores del procesador, de modo que la aplicación se ejecute
	 *              en modo multiproceso paralelo.
	 * Parámetros de entrada: no
	 * Parámetros de salida: no   */
	public static void main(String[] args) {		
		// TODO Auto-generated method stub		
		long tiempoInicio = System.nanoTime();		
		int numeroCores = Runtime.getRuntime().availableProcessors();
		System.out.println("Número de cores: "+numeroCores);
		int numProcesosLanzados=0;
		LanzadorRutaDinamica l = new LanzadorRutaDinamica();
		String lineaDatosNEO = l.obtenerLineaDatosNEO(numProcesosLanzados);		
		do {
			int i=1;		
			while(lineaDatosNEO!=null && i<=numeroCores){	

				String[] datosNEO = lineaDatosNEO.split(",");
				String nombreNEO = datosNEO[0];
				String posicionNEO = datosNEO[1];
				String velocidadNEO = datosNEO[2];
				Boolean ultimaLineaBloque = false;
				numProcesosLanzados++;
				lineaDatosNEO = l.obtenerLineaDatosNEO(numProcesosLanzados);
				
				if(i==numeroCores || lineaDatosNEO==null) {
					ultimaLineaBloque = true;
				}
				//System.out.print("Proceso "+i+": cálculo probabilidad colisión NEO "+datosNEO[0]+" --> ");
				//System.out.println("Lanzado");
				//l.lanzar_CalculadorProbabilidad(datosNEO[0], datosNEO[1], datosNEO[2], ultimaLineaBloque);
				l.lanzar_CalculadorProbabilidad(nombreNEO, posicionNEO, velocidadNEO, ultimaLineaBloque);
				//numProcesosLanzados++;
				//lineaDatosNEO = l.obtenerLineaDatosNEO(numProcesosLanzados);
				i++;
			}			
		}while(lineaDatosNEO!=null);	
		
		long tiempoFin = System.nanoTime();
		long duracionTotal = (tiempoFin-tiempoInicio)/1000000;
		System.out.println("Tiempo de ejecución total de la aplicación: "+duracionTotal+ "ms");
		long duracionPromedioProceso = duracionTotal/numProcesosLanzados;
		System.out.println("Tiempo de ejecución promedio de los "+numProcesosLanzados +" procesos: "
				+duracionPromedioProceso+ "ms");
	}	
	
	
	//métodos implementación
	
	/**Método: ObtenerLineaDatosNeo
	 * Descripción: recibe un array con los datos de la línea leída en el archivo txt
	 * Parámetros de entrada: numeroLineasLeidas (int, número entero correspondiente al numero de procesos 
	 *                        lanzados por main, que es igual al número total de líneas leídas del archivo .txt.
	 * Parámetros de salida: (String) contenido de la línea leída del archivo .txt*/
	private String obtenerLineaDatosNEO(int numeroLineasLeidas) {
		String linea="";		
		try {			
			FileReader fr = new FileReader("NEOs.txt");
			BufferedReader br = new BufferedReader(fr);
			int contador = 0;			
			do {
				linea = br.readLine();
				contador++;		
			} while (contador<=numeroLineasLeidas);
			numeroLineasLeidas++;
			br.close();
			fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return linea;
	}
	
	/**Método: ObtenerLineaDatosNeo
	 * Descripción: metodo que construye e inicializa un proceso con los datos pasados por parámetro y, 
	 * 				en caso de indicarle por parámetro que el proceso corresponde a la última línea de un bloque, 
	 * 				mantiene en espera el proceso principal hasta que finalizan todos los procesos secundarios lanzados.
	 * Parámetros de entrada: n1 (String, nombre del NEO), n2 (String, posición del NEO), n3 (String, velocidad del NEO),
	 * 						  ultimaLinea (Boolean, verdadero si los datos corresponden a la última línea de un bloque).
	 * Parámetros de salida: no */	
	private void lanzar_CalculadorProbabilidad(String n1, String n2, String n3, Boolean ultimaLinea) {
		String clase = "psp.AE.AE2_T2.CalculadorProbabilidad";
		String javaHome = System.getProperty("java.home");
		String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
		String classpath = System.getProperty("java.class.path");
		String className = clase;
			
		List<String> command = new ArrayList<>();
		//ArrayList<String> commando = new ArrayList<>();
		command.add(javaBin);
		command.add("-cp");
		command.add(classpath);
		command.add(className);
		command.add(n1);
		command.add(n2);
		command.add(n3);
		
		ProcessBuilder constructorProceso = new ProcessBuilder(command);
		try {
			Process proceso = constructorProceso.inheritIO().start();
			//proceso.waitFor();
			if(ultimaLinea) {
				proceso.waitFor();
				System.out.println(proceso.exitValue());
			}						
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}	
	
	
	
	
	
	
//	/**main que recibe un array con los datos de la línea leída en el archivo txt.*/
//	public static void main(String[] args) {		
//	// TODO Auto-generated method stub
//	
//	long tiempoInicio = System.nanoTime();		
//	
//	int numeroCores = Runtime.getRuntime().availableProcessors();
//	System.out.println("Número de cores: "+numeroCores);
//	int numProcesosLanzados=0;
//	LanzadorRutaDinamica l = new LanzadorRutaDinamica();
//	String lineaDatosNEO = l.obtenerLineaDatosNEO(numProcesosLanzados);	
//	Process proceso = null;
//	do {
//		int i=1;		
//		while(lineaDatosNEO!=null && i<=numeroCores){	
//			Boolean ultimaLineaBloque = false;			
//
//			String[] datosNEO = lineaDatosNEO.split(",");
//			System.out.print("Proceso "+i+": cálculo probabilidad colisión NEO "+datosNEO[0]+" --> ");
//			System.out.println("Lanzado");
//			//l.lanzar_CalculadorProbabilidad(datosNEO[0], datosNEO[1], datosNEO[2], ultimaLineaBloque);
//			proceso = l.lanzar_CalculadorProbabilidad(datosNEO[0], datosNEO[1], datosNEO[2], ultimaLineaBloque);
//			numProcesosLanzados++;
//			lineaDatosNEO = l.obtenerLineaDatosNEO(numProcesosLanzados);
//			if(i==numeroCores) {
//				try {
//					proceso.waitFor();
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				System.out.println(proceso.exitValue());
//			}
//			i++;
//		}
//	}while(lineaDatosNEO!=null);
//	
//	long tiempoFin = System.nanoTime();
//	long duracionTotal = (tiempoFin-tiempoInicio)/1000000;
//	System.out.println("Tiempo de ejecución total de la aplicación: "+duracionTotal+ "ms");
//	long duracionPromedioProceso = duracionTotal/numProcesosLanzados;
//	System.out.println("Tiempo de ejecución promedio de los "+numProcesosLanzados +" procesos: "
//			+duracionPromedioProceso+ "ms");
//}	
//	
//	
//	//Método que lanza el proceso CalcularProbabilidad.
//	private Process lanzar_CalculadorProbabilidad(String n1, String n2, String n3, Boolean ultimaLinea) {
//		String clase = "psp.AE.AE2_T2.CalculadorProbabilidad";
//		String javaHome = System.getProperty("java.home");
//		String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
//		String classpath = System.getProperty("java.class.path");
//		String className = clase;
//			
//		List<String> command = new ArrayList<>();
//		//ArrayList<String> commando = new ArrayList<>();
//		command.add(javaBin);
//		command.add("-cp");
//		command.add(classpath);
//		command.add(className);
//		command.add(n1);
//		command.add(n2);
//		command.add(n3);
//		
//		ProcessBuilder constructorProceso = new ProcessBuilder(command);
//		Process proceso = null;
//		try {
//			proceso = constructorProceso.inheritIO().start();					
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return proceso;
//	}		
	
	

	
}
