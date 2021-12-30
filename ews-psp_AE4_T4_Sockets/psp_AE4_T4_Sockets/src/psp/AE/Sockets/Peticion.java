package psp.AE.Sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Peticion implements Runnable{
	//atributos
	Socket socketCliente;
	
	//constructores
	public Peticion(Socket socketCliente) {
		this.socketCliente = socketCliente;
	}
	
	//getters y setters
	//métodos de interface
	@Override
	public void run() {
		//Preparamos servidor para recibir datots (linea de caracteres) a traves de un 
		//canal de entrada de datos perteneciente a la conexión cliente-servidor.
		InputStream is;
		try {
			is = socketCliente.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader bfr = new BufferedReader(isr);
			
			System.err.println("SERVIDOR Hilo "+Thread.currentThread().getName()+" >>> Lee datos para la operación");
			String linea = bfr.readLine();
			String num1 = bfr.readLine();
			String num2 = bfr.readLine();
			String nombre = bfr.readLine();
			
			System.err.println("SERVIDOR "+Thread.currentThread().getName()+" >>> Realiza la operación");
			Integer result = calcular(linea, num1, num2);
			
			System.err.println("SERVIDOR "+Thread.currentThread().getName()+" >>> Devuelve el resultado");
			//Preparamos servidor para enviar datos (flujo de salida de texto) a cliente a traves de un
			//canal de salida de datos perteneciente a la conexión cliente-servidor.
			OutputStream os = socketCliente.getOutputStream();
			PrintWriter pw = new PrintWriter(os); //objeto que representa un flujo de salida de texto
			//Servidor envia un objeto (tipo flujo salida de texto) al cliente a traves del canal de salida
			//de datos perteneciente a la conexión cliente-servidor.
			pw.write(result.toString()+"\n");
			pw.write(nombre+"\n");
			pw.flush(); //Borramos buffer datos enviados para que se ejecute el envio.
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("SERVIDOR "+Thread.currentThread().getName()+" >>> Error.");
		}
		
		
	}

	//métodos de implementación
	private static int calcular (String op, String n1, String n2) {
		int resultado = 0;
		char simbolo = op.charAt(0);		
		int num1 = extraerNumero(n1);
		int num2 = extraerNumero(n2);
		
		if(simbolo == '+') {
			resultado = num1+num2;
		} else if(simbolo =='-') {
			resultado = num1-num2;		
		} else if(simbolo == '*') {
			resultado = num1*num2;
		} else if(simbolo == '/') {
			resultado = num1/num2;
		}		
		return resultado;
	}
	
	private static int extraerNumero(String linea) {
		int numero;		
		try {
			numero = Integer.parseInt(linea);
		} catch (Exception e) {
			numero = 0;
		}				
		if (numero >= 100000000) { numero = 0; }				
		return numero;
	}
}
