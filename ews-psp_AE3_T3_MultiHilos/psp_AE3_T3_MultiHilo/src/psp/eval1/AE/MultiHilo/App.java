package psp.eval1.AE.MultiHilo;

public class App {
	//ATRIBUTOS
	//CONSTRUCTORES
	//GETTERS Y SETTERS
	
	//OTROS MÉTODOS DE INTERFACE
	public static void main(String[] args) {
		//Declaramos e inicializamos el objeto mina, con el contenido total de oro pasada por parámetro,
		//fuera del bucle "for" porque hay una única mina que es compartida por todos los mineros (hilos). 
		Mina mina = new Mina(10000);
		Minero objMinero;
		Thread hiloMinero;
		for(int i=0; i<10; i++) {
			//Asociamos el único objeto mina declarado en la aplicación (fuera del "for"), al 
			//objMinero (tipo Minero) que se inicializa en cada vuelta del bucle "for".
			objMinero = new Minero(mina);
			//Asociamos el objeto objMinero (tipo Minero) al -hilo de procesamiento-.
			hiloMinero = new Thread(objMinero);
			//Asignamos un nombre al hilo de procesamiento.
			hiloMinero.setName("Minero "+(i+1));
			//Indicamos que el hilo de procesamiento (hiloMinero) esta preparado para ejecutar
			//el método run del objeto objMinero asociado. Nota: en este momento el hilo se pone
			//en la cola gestionada por el programador -S.O.-.
			hiloMinero.start();
		}
		//Establecemos una pausa en el hilo principal (asociada al método main) para que
		//los hilos secundarios terminen su ejecución antes de mostrar por pantalla el
		//recuento final del oro extraído.
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\nTotal oro extraido: "+mina.getOroExtraido()+"\n");
	}

	//MÉTODOS DE IMPLEMENTACIÓN	
}
