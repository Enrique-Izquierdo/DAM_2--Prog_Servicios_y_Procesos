package psp.AE.Sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		String host = "localhost"; //ruta de acceso al servidor.
		int puerto = 1234; //puerto de escucha del servidor.
		System.out.println("CLIENTE >> Arranca cliente");
		//Cliente establece conexión (envia socket) con el servidor.
		Socket socketCliente = new Socket(host, puerto);
		//Preparamos a cliente para recibir objetos enviados por el servidor a traves del
		//canal de entrada de datos (tipo objeto), perteneciente a la conexión cliente-servidor.
		ObjectInputStream inObjecto = new ObjectInputStream(socketCliente.getInputStream());
		//Cliente espera hasta recibir un objeto generico, enviado por el servidor a traves
		//del canal de entrada de datos (tipo objeto); y lo "castea" a un objeto de tipo Persona.
		Contrasenya pwd = (Contrasenya) inObjecto.readObject(); //readObject equivale a recibir objeto.
		System.out.println("CLIENTE >> Recibe del servidor objeto Contrasenya SIN valores asignados: contraseña plana: "+pwd.getPwdPlano()
							+" - contraseña encriptada: "+pwd.getPwdEncriptado()+" - tipo de encriptación: "+pwd.getTipoEncriptacion());
		
		//Solicitamos contraseña plana y tipo de encriptación a usuario; modificando
		//el contenido del objeto recibido por el cliente (enviado por el servidor).
		pwd.pedirContrasenyaPlana();
		pwd.pedirTipoEncriptacion();
		
		//Preparamos a cliente para enviar datos (tipo objeto) al servidor a traves de un
		//canal de salida de datos (tipo objeto), perteneciente a la conexión cliente-servidor.
		ObjectOutputStream outObjeto = new ObjectOutputStream(socketCliente.getOutputStream());
		//Cliente envia el objeto modificado "p" al servidor a traves del canal para envio de datos (tipo objeto).
		outObjeto.writeObject(pwd); //writeObject equivale a enviar objeto.
		System.out.println("CLIENTE >> Envia al servidor el objeto Contrasenya CON valores asignados:"
				+ " contraseña plana: "+pwd.getPwdPlano()+" - tipo de encriptación: "+pwd.getTipoEncriptacion());
		
		//Cliente espera hasta recibir un objeto generico, enviado por el servidor a traves
		//del canal de entrada de datos (tipo objeto); y lo "castea" a un objeto de tipo Persona.
		pwd = (Contrasenya) inObjecto.readObject(); //readObject equivale a recibir objeto.
		System.out.println("CLIENTE >> Recibe del servidor el objeto Contrasenya CON valores asignados: contraseña plana: "+pwd.getPwdPlano()
							+" - contraseña encriptada: "+pwd.getPwdEncriptado()+" - tipo de encriptación: "+pwd.getTipoEncriptacion());
		
		//Cerramos el canal para recibir objetos del servidor a traves de la conexión.
		inObjecto.close();
		//Cerramos el canal para enviar objetos al servidor a traves de la conexión.
		outObjeto.close();
		//Cerramos la conexión cliente-servidor (socket enviado por el cliente al servidor).
		socketCliente.close();	
	}

}
