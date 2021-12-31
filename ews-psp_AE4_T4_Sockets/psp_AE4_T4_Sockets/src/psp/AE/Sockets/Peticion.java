package psp.AE.Sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Peticion implements Runnable{
	//atributos
	Socket socketCliente;
	
	//CONSTRUCTORES
	/**Método: Peticion
	 * Descripción: inicializa los atributos del objeto.
	 * Parámetros de entrada: socketCliente (Socket)
	 * Parámetros de salida: no*/
	public Peticion(Socket socketCliente) {
		this.socketCliente = socketCliente;
	}
	
	//GETTERS Y SETTERS
	//MÉTODOS INTERFACE
	/**Método: run
	 * Descripción: prepara los canales de intercambio de datos (tipo objeto) con el cliente; instancia la clase
	 * 				Contrasenya y envia el objeto con los atributos inicializado a null, al servidor; recibe el objeto
	 * 				del cliente con los valores facilitado por el usuario a los atributos pwdPlano y tipoEncriptacion; 
	 *              llama al método aplicarEncriptacin() de la clase Contrasenya; envia el objeto Contrasenya con la
	 *              contraseña encriptada (atributo pwdEncriptado) al cliente; cierra la conexión.
	 * Parámetros de entrada: no
	 * Parámetros de salida: no*/	
	@Override
	public void run() {		
		try {
			//Preparamos al servidor para enviar datos (tipo objeto) al cliente a traves del
			//canal de salida de datos (tipo objeto), perteneciente a la conexión cliente-servidor.
			ObjectOutputStream outObjeto = new ObjectOutputStream(socketCliente.getOutputStream());
			//Creamos objeto a enviar, inicializandolo con los valors "Nombre" y 0.
			Contrasenya pwd = new Contrasenya(null, null);
			
			//El servidor envia el objeto al cliente a traves de la conexión.
			outObjeto.writeObject(pwd); //writeObject equivale a enviar objeto.
			System.err.println("SERVIDOR >> Envía a CLIENTE objeto Contrasenya con atributos: contraseña plana: "+pwd.getPwdPlano()
					+" - contraseña encriptada: "+pwd.getPwdEncriptado()+" - tipo de encriptación: "+pwd.getTipoEncriptacion());
			
			//Preparamos al servidor para recibir datos (tipo objeto) enviados por el cliente a traves de un
			//canal de entrada de datos (tipo objeto), perteneciente a la conexión cliente-servidor.
			ObjectInputStream inObjeto = new ObjectInputStream(socketCliente.getInputStream());			
			//Servidor espera a recibir el objeto generico enviado por el cliente, y los "castea" a un objeto de tipo Persona
			pwd = (Contrasenya) inObjeto.readObject(); //readObject equivale a recibir objeto.
			System.err.println("SERVIDOR >> Recibe de CLIENTE objeto Contrasenya con atributos: contraseña plana: "+pwd.getPwdPlano()
					+" - contraseña encriptada: "+pwd.getPwdEncriptado()+" - tipo de encriptación: "+pwd.getTipoEncriptacion());
			
			//Encriptamos contraseña recivida
			pwd.aplicarEncriptacion();
			//El servidor envia el objeto al cliente a traves de la conexión.
			outObjeto.writeObject(pwd); //writeObject equivale a enviar objeto.
			System.err.println("SERVIDOR >> Envía a CLIENTE objeto Contrasenya con atributos: contraseña plana: "+pwd.getPwdPlano()
					+" - contraseña encriptada: "+pwd.getPwdEncriptado()+" - tipo de encriptación: "+pwd.getTipoEncriptacion());
						
			//Cerramos el canal para enviar objetos al cliente a traves de la conexión
			outObjeto.close();
			//Cerramos el canal para recibir objetos del cliente a traves de la conexión
			inObjeto.close();
			//Cerramos la conexion cliente - servidor (socket enviado por el cliente al servidor).
			socketCliente.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	//MÉTODOS IMPLEMENTACIÓN

}
