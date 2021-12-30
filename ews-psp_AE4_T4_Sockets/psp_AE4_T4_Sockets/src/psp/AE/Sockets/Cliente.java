package psp.AE.Sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Cliente {

	public static void main(String[] args) throws IOException {
		System.out.println("CLIENTE "+args[3]+" >>> Arranca cliente");
		System.out.println("CLIENTE "+args[3]+" >>> Conexion al servidor");
		InetSocketAddress direccion = new InetSocketAddress("localhost", 9876);
		Socket socket = new Socket();
		//Cliente envia socket al servidor pasando por parametro la dirección del servidor.
		socket.connect(direccion);
		
		System.out.println("CLIENTE "+args[3]+" >>> Envia datos para el cálculo");
		//Preparamos a cliente para enviar datos al servidor a traves del 
		//canal de salida de datos perteneciente a la conexión cliente-servidor
		PrintWriter pw = new PrintWriter(socket.getOutputStream());
		//Cliente envia datos al servidor
		pw.print(args[0]+"\n");
		pw.print(args[1]+"\n");
		pw.print(args[2]+"\n");
		pw.print(args[3]+"\n");
		pw.flush(); //Borramos buffer datos enviados para que se ejecute el envio.
				
		System.out.println("CLIENTE "+args[3]+" >>> Preparando canal para recibir resultado");
		//Preparamos a cliente para recibir datos del servidor a traves del 
		//canal de entrada de datos perteneciente a la conexión cliente-servidor.
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader bfr = new BufferedReader(isr);
		//Cliente espera hasta recibir datos del servidor, y los lee.
		String resultado = bfr.readLine();
		String nombreCliente = bfr.readLine();
		System.out.println("CLIENTE "+nombreCliente+" >>> Recibe resultado: " + resultado);
		
		//Cerramos socket
		socket.close();
	}

}
