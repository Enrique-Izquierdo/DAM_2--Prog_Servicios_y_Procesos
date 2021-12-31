package psp.AE.Sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

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
		System.out.println("CLIENTE >> Recibe de SERVIDOR objeto Contrasenya con atributos: contraseña plana: "+pwd.getPwdPlano()
							+" - contraseña encriptada: "+pwd.getPwdEncriptado()+" - tipo de encriptación: "+pwd.getTipoEncriptacion());
		
//		//OPCIÓN A
//		//Asignamos valores a los atributos "contraseña de texto plano" y "tipo de encriptación" 
//		//para comprobar correcto funcionamiento aplicación
//		pwd.setPwdPlano("Tomas");
//		pwd.setTipoEncriptacion("MD5");
		
		
		//Solicitamos contraseña plana y tipo de encriptación a usuario para modificar
		//el contenido del objeto recibido por el cliente (enviado por el servidor).
		//OPCIÓN B		
		Scanner entradaTeclado = new Scanner(System.in);
		pwd.pedirContrasenyaPlana(entradaTeclado);
		pwd.pedirTipoEncriptacion(entradaTeclado);
		entradaTeclado.close();
		
//		//OPCIÓN C
//		InputStreamReader isr = new InputStreamReader(System.in);
//		BufferedReader br = new BufferedReader(isr);
//		System.out.println("Introduce una contraseña: ");
//		String constrasenya = br.readLine();
//		System.out.println("El valor introducido es "+ constrasenya);
//		System.out.println("Introduzca la letra correspondiente al tipo de encriptación a aplicar \n(encriptación ASCII --> A, encriptación MD5 --> B): ");
//		String tipo = br.readLine();
//		System.out.println("El valor introducido es "+ tipo);
//		br.close();
//		isr.close();
				
		
		//Preparamos a cliente para enviar datos (tipo objeto) al servidor a traves de un
		//canal de salida de datos (tipo objeto), perteneciente a la conexión cliente-servidor.
		ObjectOutputStream outObjeto = new ObjectOutputStream(socketCliente.getOutputStream());
		//Cliente envia el objeto modificado "p" al servidor a traves del canal para envio de datos (tipo objeto).
		outObjeto.writeObject(pwd); //writeObject equivale a enviar objeto.
		System.out.println("CLIENTE >> Envía a SERVIDOR objeto Contrasenya con atributos: contraseña plana: "+pwd.getPwdPlano()
				+" - contraseña encriptada: "+pwd.getPwdEncriptado()+" - tipo de encriptación: "+pwd.getTipoEncriptacion());
		
		//Cliente espera hasta recibir un objeto generico, enviado por el servidor a traves
		//del canal de entrada de datos (tipo objeto); y lo "castea" a un objeto de tipo Persona.
		pwd = (Contrasenya) inObjecto.readObject(); //readObject equivale a recibir objeto.
		System.out.println("CLIENTE >> Recibe de SERVIDOR objeto Contrasenya con atributos: contraseña plana: "+pwd.getPwdPlano()
							+" - contraseña encriptada: "+pwd.getPwdEncriptado()+" - tipo de encriptación: "+pwd.getTipoEncriptacion());
		
		//Cerramos el canal para recibir objetos del servidor a traves de la conexión.
		inObjecto.close();
		//Cerramos el canal para enviar objetos al servidor a traves de la conexión.
		outObjeto.close();
		//Cerramos la conexión cliente-servidor (socket enviado por el cliente al servidor).
		socketCliente.close();	
	}

}
