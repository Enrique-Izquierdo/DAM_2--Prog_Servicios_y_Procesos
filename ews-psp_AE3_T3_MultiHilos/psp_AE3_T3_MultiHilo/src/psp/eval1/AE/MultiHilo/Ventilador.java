package psp.eval1.AE.MultiHilo;

public class Ventilador{
	//ATRIBUTOS
	Boolean encendido = true;
	int tiempo = 1000;
	
	//CONSTRUCTORES
	//GETTERS Y SETTERS
	//OTROS MÉTODOS DE INTERFACE
	/**Método: encenderVentilador
	 * Descripción: mantiene encendido el ventilador durante el tiempo establecido para, 
	 * 				posteriormente, notificar al resto de hilos que hacen uso del recurso
	 * 				Ventilador, que el recurso puede ser utilizado (lo desbloquea), manteniendose
	 * 				a la espera de una notificación por parte de otro hilo para volver a realizar
	 * 				todo el proceso, siempre que la variable estática minerosTrabajando tenga
	 * 				asignada el valor true.
	 * Parámetros de entrada: no
	 * Parámetres de salida: no*/
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
	
	/**Método: apagarVentilador
	 * Descripción: mantiene apagado el ventilador durante el tiempo establecido para, 
	 * 				posteriormente, notificar al resto de hilos que hacen uso del recurso
	 * 				Ventilador, que el recurso puede ser utilizado (lo desbloquea), manteniendose
	 * 				a la espera de una notificación por parte de otro hilo para volver a realizar
	 * 				todo el proceso, siempre que la variable estática minerosTrabajando tenga
	 * 				asignada el valor true.
	 * Parámetros de entrada: no
	 * Parámetres de salida: no*/
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
