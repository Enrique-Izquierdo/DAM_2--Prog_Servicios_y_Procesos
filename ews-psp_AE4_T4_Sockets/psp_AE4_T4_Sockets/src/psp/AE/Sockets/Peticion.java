package psp.AE.Sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
		try {
			//Preparamos al servidor para enviar datos (tipo objeto) al cliente a traves del
			//canal de salida de datos (tipo objeto), perteneciente a la conexión cliente-servidor.
			ObjectOutputStream outObjeto = new ObjectOutputStream(socketCliente.getOutputStream());
			//Creamos objeto a enviar, inicializandolo con los valors "Nombre" y 0.
			Contrasenya pwd = new Contrasenya(null, null);
			
			//El servidor envia el objeto al cliente a traves de la conexión.
			outObjeto.writeObject(pwd); //writeObject equivale a enviar objeto.
			System.err.println("SERVIDOR >> Envia a cliente un objeto Contrasenya SIN valores asignados: contraseña plana: "+pwd.getPwdPlano()
					+" - contraseña encriptada: "+pwd.getPwdEncriptado()+" - tipo de encriptación: "+pwd.getTipoEncriptacion());
			
			//Preparamos al servidor para recibir datos (tipo objeto) enviados por el cliente a traves de un
			//canal de entrada de datos (tipo objeto), perteneciente a la conexión cliente-servidor.
			ObjectInputStream inObjeto = new ObjectInputStream(socketCliente.getInputStream());			
			//Servidor espera a recibir el objeto generico enviado por el cliente, y los "castea" a un objeto de tipo Persona
			pwd = (Contrasenya) inObjeto.readObject(); //readObject equivale a recibir objeto.
			System.err.println("SERVIDOR >> Recibe de cliente el objeto Contrasenya CON valores asignados:"
					+ " contraseña plana: "+pwd.getPwdPlano()+" - tipo de encriptación: "+pwd.getTipoEncriptacion());
			
			//Encriptamos contraseña recivida
			pwd.aplicarEncriptacion();
			//El servidor envia el objeto al cliente a traves de la conexión.
			outObjeto.writeObject(pwd); //writeObject equivale a enviar objeto.
			System.err.println("SERVIDOR >> Envia a cliente el objeto Contrasenya CON valores asignados: contraseña plana: "+pwd.getPwdPlano()
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

	//métodos de implementación

}
