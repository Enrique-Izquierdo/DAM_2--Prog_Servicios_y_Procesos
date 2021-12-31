package psp.AE.Sockets;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

@SuppressWarnings("serial")
public class Contrasenya implements Serializable{
	//ATRIBUTOS
	private String pwdPlano;
	private String pwdEncriptado;
	private String tipoEncriptacion;
	
	
	//CONSTRUCTORES
	/**Método: Contrasenya
	 * Descripción: inicializa los atributos del objeto.
	 * Parámetros de entrada: pwdPlano (String), pwdEncriptado (String)
	 * Parámetros de salida: no*/
	public Contrasenya(String pwdPlano, String pwdEncriptado) {
		this.pwdPlano = pwdPlano;
		this.pwdEncriptado = pwdEncriptado;
	}
	
	
	//GETTERS Y SETTERS
	public String getPwdPlano () {		return pwdPlano;	}	
	public String getPwdEncriptado () {		return pwdEncriptado;	}	
	public String getTipoEncriptacion () {		return tipoEncriptacion;	}
	
	public void setPwdPlano (String pwdPlano) {		this.pwdPlano = pwdPlano;	}	
	public void setPwdEncriptado (String pwdEncriptado) {		this.pwdEncriptado = pwdEncriptado;	}	
	public void setTipoEncriptacion (String tipoEncriptacion) {		this.tipoEncriptacion = tipoEncriptacion;	}
	
	
	//OTROS MÉTODOS INTERFACE
	/**Método: pedirContrasenyaPlana
	 * Descripción: Solicita la introducción de una contraseña por teclado a traves de la consola, y la asigna al atributo pwdPlano.
	 * Parámetros de entrada: pEntradaTeclado (Scanner)
	 * Parámetros de salida: no*/
	public void pedirContrasenyaPlana(Scanner pEntradaTeclado) {
		//Scanner entradaTeclado = new Scanner(System.in);
		System.out.print("\n\tIntroduce una contraseña: ");
		pwdPlano = pEntradaTeclado.next();	
		//entradaTeclado.close();
	}
	
	/**Método: pedirTipoEncriptación
	 * Descripción: Solicita la introducción del tipo de encriptación a aplicar, y la asigna al atributo tipoEncriptacion.
	 * Parámetros de entrada: pEntradaTeclado (Scanner)
	 * Parámetros de salida: no*/
	public void pedirTipoEncriptacion(Scanner pEntradaTeclado) {
		System.out.print("\tIntroduzca la letra correspondiente al tipo de encriptación a aplicar (A - encriptación ASCII, B - encriptación MD5): ");
		String tipo = pEntradaTeclado.next();
		
		switch (tipo.toUpperCase()) {
		case "A":
			tipoEncriptacion = "ASCII";
			break;			
		case "B":
			tipoEncriptacion = "MD5";
			break;
		default:
			System.out.println("\tEl valor introducido no es válido. Se aplicará la encriptación por defecto (MD5).");
			tipoEncriptacion = "MD5";
			break;
		}
		System.out.println("");
	}
	
	/**Método: aplicarEncriptacion
	 * Descripción: LLama al método privado de encriptación asignado al atributo tipoEncriptacion.
	 * Parámetros de entrada: no
	 * Parámetros de salida: no*/
	public void aplicarEncriptacion() {
		if (tipoEncriptacion.equals("ASCII")) {
			encriptarASCII();
		} else if (tipoEncriptacion.equals("MD5")) {
			encriptarMD5();
		} else {
			System.out.println("Error al asignar tipo de encriptación.");
		}		
	}
	
	
	//MÉTODOS IMPLEMENTACIÓN
	/**Método: encriptarASCII
	 * Descripción: Encripta la cadena guardada en el atributo pwdPlano, convirtiendo cada uno de los carácteres 
	 * 				de la cadena en el siguiente del código ASCII; y asigna la nueva cadena al atributo pwdEncriptado.
	 * Parámetros de entrada: no
	 * Parámetros de salida: no*/
	private void encriptarASCII() {	
		//System.out.println(pwdPlano);
		String[] nuevaContrasenya = new String[pwdPlano.length()];		
		for(int i=0; i<pwdPlano.length(); i++) {
			 char caracter = pwdPlano.charAt(i);
			 int valorAscii = caracter;
			 int nuevoValorAscii = valorAscii+1;
			 if (nuevoValorAscii<33 || nuevoValorAscii>126) {
				 nuevoValorAscii = 42;
			 }
			 char nuevoCaracter = (char) nuevoValorAscii;
			 nuevaContrasenya[i] = String.valueOf(nuevoCaracter);
		}		
		pwdEncriptado = String.join("",nuevaContrasenya);				
		//System.out.println(pwdEncriptado);
	}
	
	/**Método: encriptarMD5
	 * Descripción: Encripta la cadena guardada en el atributo pwdPlano mediante la aplicación del 
	 * 				algoritmo MD5, y asigna la nueva cadena al atributo pwdEncriptado.
	 * Parámetros de entrada: no
	 * Parámetros de salida: no*/
	private void encriptarMD5() {
		try {
			 MessageDigest md = MessageDigest.getInstance("MD5");
			 byte[] messageDigest = md.digest(pwdPlano.getBytes());
			 BigInteger number = new BigInteger(1, messageDigest);
			 String hashtext = number.toString(16);

			 while (hashtext.length() < 32) {
			 hashtext = "0" + hashtext;
			 }
			 pwdEncriptado = hashtext;
		}
		catch (NoSuchAlgorithmException e) {
		throw new RuntimeException(e);
		}
	}
}
