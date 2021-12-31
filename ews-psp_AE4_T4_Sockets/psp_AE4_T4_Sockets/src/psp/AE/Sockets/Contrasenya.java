package psp.AE.Sockets;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

@SuppressWarnings("serial")
public class Contrasenya implements Serializable{
	//atributos
	private String pwdPlano;
	private String pwdEncriptado;
	private String tipoEncriptacion;
	
	//constructores
	public Contrasenya(String pwdPlano, String pwdEncriptado) {
		this.pwdPlano = pwdPlano;
		this.pwdEncriptado = pwdEncriptado;
	}
	
	
	//getters y setters
	public String getPwdPlano () {		return pwdPlano;	}	
	public String getPwdEncriptado () {		return pwdEncriptado;	}	
	public String getTipoEncriptacion () {		return tipoEncriptacion;	}
	
	public void setPwdPlano (String pwdPlano) {		this.pwdPlano = pwdPlano;	}	
	public void setPwdEncriptado (String pwdEncriptado) {		this.pwdEncriptado = pwdEncriptado;	}	
	public void setTipoEncriptacion (String tipoEncriptacion) {		this.tipoEncriptacion = tipoEncriptacion;	}
	
	
	//otros métodos de interface
	public void pedirContrasenyaPlana(Scanner pEntradaTeclado) {
		//Scanner entradaTeclado = new Scanner(System.in);
		System.out.print("\n\tIntroduce una contraseña: ");
		pwdPlano = pEntradaTeclado.next();	
		//entradaTeclado.close();
	}
	
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
	
	public void aplicarEncriptacion() {
		if (tipoEncriptacion.equals("ASCII")) {
			encriptarASCII();
		} else if (tipoEncriptacion.equals("MD5")) {
			encriptarMD5();
		} else {
			System.out.println("Error al asignar tipo de encriptación.");
		}
		
		
	}
	
	//métodos de implementación
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
