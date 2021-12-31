package psp.AE.Sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorMultiHilo {
	
	//MÉTODOS INTERFACE
	/**Método: main
	 * Descripción: arranca el servidor y establece un puerto de escucha que mantiene permanentente abierto, 
	 * 				esperando la llegada de conexiones de clientes que son asignadas, cada una de las conexiones,
	 * 				a hilos para permitir al servidor seguir recibiendo más conexiones.
	 * Parámetros de entrada: args (String[]) no utilizado
	 * Parámetros de salida: no*/
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		System.err.println("SERVIDOR >>> Arranca el servidor, espera petición");
		//Establecemos puerto de escucha del servidor
		ServerSocket socketEscucha = null;
		try {
			socketEscucha = new ServerSocket(1234);
		} catch (IOException e) {
			System.err.println("SERVIDOR >>> Error");
			e.printStackTrace();
		}
		
		while (true) {
			//Servidor espera el socket enviado por un cliente para establecer
			//la conexión cliente-servidor.
			Socket conexion = socketEscucha.accept();
			System.err.println("SERVIDOR >>> Conexión recibida --> Lanza hilo clase Petición");
			Peticion p = new Peticion(conexion);
			Thread hilo = new Thread(p);
			hilo.start();
		}
	}

}
