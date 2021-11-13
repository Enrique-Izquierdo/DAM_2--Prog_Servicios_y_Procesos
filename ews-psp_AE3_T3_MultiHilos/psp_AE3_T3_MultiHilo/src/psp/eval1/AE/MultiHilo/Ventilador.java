package psp.eval1.AE.MultiHilo;

public class Ventilador{
	//ATRIBUTOS
//	Boolean minerosTrabajando;
	Boolean encendido = true;
	int tiempo = 1000;
	//CONSTRUCTORES
//	public Ventilador (Boolean minerosTrabajando) {
//		this.minerosTrabajando = minerosTrabajando;
//	}
	
	//GETTERS Y SETTERS
	//OTROS MÉTODOS DE INTERFACE	
	public void encenderVentilador() {	
		while(App.minerosTrabajando) {
			synchronized (this) {
				try {
					while (!encendido) {wait();}			
					//System.err.print("Semáforo 2: rojo");
					System.out.println("\n    ----> ventilador: encendido durante " 
										+tiempo  + " milisegundos <----\n");
					Thread.sleep(tiempo);
					encendido = false;
					notify();
				} catch (InterruptedException e) {				
					e.printStackTrace();
				}			
			}			
		}			
	}
	
	public void apagarVentilador() {	
		while(App.minerosTrabajando) {
			synchronized (this) {
				try {
					while (encendido) {wait();}			
					//System.err.print("Semáforo 2: rojo");
					System.err.println("\n    ----> ventilador: apagado durante " 
										+ tiempo + " milisegundos <----\n");
					Thread.sleep(tiempo);
					encendido = true;
					notify();
				} catch (InterruptedException e) {				
					e.printStackTrace();
				}			
			}			
		}			
	}
	
	//MÉTODOS DE IMPLEMENTACIÓN
	
}
