package psp.AE.ServiciosRed;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import com.sun.net.httpserver.HttpServer;


public class Servidor {	
	
	// MÉTODOS DE INTERFACE
	
	/**Método: main
	 * Descripción: crea el servidor multihilo en el puerto 7777 del locahost, establece la ruta
	 * 				de contexto, y declara un objeto de la clase GestorHTTP que procesará las 
	 * 				peticiones de los clientes.
	 * Parámetros de entrada: no utilizados
	 * Parámetros de salida: no
	 */
	public static void main(String[] args) throws IOException {
		//Creamos el objeto que contiene la dirección IP y el puerto TCP del servidor Java HTTP
		String host = "localhost"; //IP 127.0.01
		int puerto = 7777;
		InetSocketAddress direccionTCPIP = new InetSocketAddress(host, puerto);
		//Establecemos el número máximo de conexiones en cola.
		int backlog = 0;
		//Creamos el servidor HTTP.
		HttpServer servidor = HttpServer.create(direccionTCPIP, backlog);
		
		//Creamos el gestor (manejador) que procesará las peticiones que los clientes
		//realicen al servidor HTTP.
		GestorHTTP gestorHTTP = new GestorHTTP();
		//Establecemos la ruta de contexto: ruta en la URL a partir de la cual el 
		//servidor dará respuesta a las peticiones de los clientes.
		String rutaRespuesta = "/estufa";
		//Creamos el contexto.
		servidor.createContext(rutaRespuesta, gestorHTTP);
		
		//Código para que el servidor sea multihilo
		ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
		servidor.setExecutor(threadPoolExecutor);
		servidor.start();
		
		System.out.println("Servidor HTTP arranca en el puerto "+puerto);		
	}

}
