
//IMPORTANTE: No utilizar esta clase para ejecutar la aplicación; ya que inheritIO, que permite visualizar
//en la misma consola las salidas de todos los procesos (clases) ejecutadas por el lanzador multiProcesos,  
//no permite la captura de las datos introducidos por teclado. En su lugar, probar la aplicación ejecutando   
//las clases ServidorMultiHilo y Cliente en consolas independidentes o, mediante la exportación a JAR y 
//ejecución en consolas CMD.

package psp.AE.Sockets;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LanzadorMultiProceso {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String javaHome = System.getProperty("java.home");
		String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
		String classpath = System.getProperty("java.class.path");
		
		//Ejecutamos servidor
		String className = "psp.AE.Sockets.ServidorMultiHilo";
		List<String> command = new ArrayList<>();
		command.add(javaBin);
		command.add("-cp");
		command.add(classpath);
		command.add(className);
		ProcessBuilder builder = new ProcessBuilder(command);
		builder.inheritIO().start();
		
		//Ejecutamos cliente A
		className = "psp.AE.Sockets.Cliente";
		command = new ArrayList<>();
		command.add(javaBin);
		command.add("-cp");
		command.add(classpath);
		command.add(className);
		builder = new ProcessBuilder(command);
		builder.inheritIO().start();

//		//Ejecutamos cliente B
//		className = "psp.AE.Sockets.Cliente";
//		command = new ArrayList<>();
//		command.add(javaBin);
//		command.add("-cp");
//		command.add(classpath);
//		command.add(className);
//		builder = new ProcessBuilder(command);
//		builder.inheritIO().start();
//		
//		//Ejecutamos cliente C
//		className = "psp.AE.Sockets.Cliente";
//		command = new ArrayList<>();
//		command.add(javaBin);
//		command.add("-cp");
//		command.add(classpath);
//		command.add(className);
//		builder = new ProcessBuilder(command);
//		builder.inheritIO().start();
	}

}
