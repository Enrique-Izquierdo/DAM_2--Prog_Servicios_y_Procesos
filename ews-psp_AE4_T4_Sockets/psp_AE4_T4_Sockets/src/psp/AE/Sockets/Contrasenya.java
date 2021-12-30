package psp.AE.Sockets;

import java.io.Serializable;
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
	
	public void setPwdPlano (String pwdPlano) {		this.pwdPlano = pwdEncriptado;	}	
	public void setPwdEncriptado (String pwdEncriptado) {		this.pwdEncriptado = pwdEncriptado;	}	
	public void setTipoEncriptacion (String tipoEncriptacion) {		this.tipoEncriptacion = tipoEncriptacion;	}
	
	
	//otros métodos de interface
	public void pedirContrasenyaPlana() {
		Scanner entradaTeclado = new Scanner(System.in);
		System.out.print("Introduce una contraseña: ");
		pwdPlano = entradaTeclado.nextLine();
		entradaTeclado.close();
	}
	
	public void pedirTipoEncriptacion() {
		Scanner entradaTeclado = new Scanner(System.in);
		System.out.print("Introduzca la letra correspondiente al tipo de encriptación a aplicar \n(A - encriptación ASCII, B - encriptación MD5: ");
		String tipo = entradaTeclado.nextLine();
		
		switch (tipo) {
		case "A":
			tipoEncriptacion = "ASCII";
			break;			
		case "B":
			tipoEncriptacion = "MD5";
			break;
		default:
			System.out.print("El valor introducido no es válido. Se aplicará la encriptación por defecto (ASCII).");
			tipoEncriptacion = "ASCII";
			break;
		}
		
//		if (!tipo.equals("A") && !tipo.equals("B")) {
//			System.out.print("El valor introducido no es válido. Se aplicará la encriptación por defecto (ASCII).");
//			tipoEncriptacion = "A";
//		}
		entradaTeclado.close(); 
	}
	
	public void aplicarEncriptacion() {
		if (tipoEncriptacion.equals("ASCII")) {
			//encriptarASCII();
			System.out.println("Aplicada encriptación ASCII.");
		} else if (tipoEncriptacion.equals("MD5")) {
			//encriptarMD5();
			System.out.println("Aplicada encriptación MD5.");
		} else {
			System.out.println("Error al asignar tipo de encriptación.");
		}
		
		
	}
	
	//métodos de implementación
//	private void encriptarASCII() {
//		
//	}
//	
//	private void encriptarMD5() {
//		
//	}
}
