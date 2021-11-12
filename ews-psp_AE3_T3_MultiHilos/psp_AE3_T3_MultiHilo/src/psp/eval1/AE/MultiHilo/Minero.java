package psp.eval1.AE.MultiHilo;

public class Minero implements Runnable{
	//ATRIBUTOS
	private int bolsa;
	private int tiempoExtraccion = 1000;
	private Mina mina;
	
	//CONSTRUCTORES
	public Minero(Mina pMina) {
		bolsa = 0;
		mina = pMina;
	}
	
	//GETTERS Y SETTERS
	
	//OTROS MÉTODOS DE INTERFACE	
	@Override
	public void run() {
		extraerRecurso();		
	}
	
	//MÉTODOS DE IMPLEMENTACIÓN
	private void extraerRecurso() {		
		//Sincronizamos el bloque de código, dónde los hilos (mineros) hacen uso del
		//recurso compartido (mina.getStock), para impedir que varios hilos intenten
		//utilizarlo (acceder al mismo) simultaneamente. Nota: cuando un hilo accede
		//a la parte del código sincronizada, se bloquea el acceso del resto de hilos.
		synchronized (this) {		
			while(mina.getStock()>0) {
				bolsa++;
				System.out.println(Thread.currentThread().getName()
						+" ha extraido 1 oro; tiene un total de "+bolsa+" oros");
				mina.setStock(mina.getStock()-1);
				mina.setOroExtraido(mina.getOroExtraido()+1);
//				//La creación de una pausa dentro del código sincronizado ocasiona una
//				//homogeinización del número de veces que los hilos ejecutan este código
//				//y, por tanto, que tienen acceso al recursos compartido; obteniendo
				//todos los mineros cantidades similares de oro
//				try {
//					Thread.sleep(tiempoExtraccion);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			}
			System.err.println("Mina AGOTADA. "+Thread.currentThread().getName()
					+" no ha podido extraer 1 oro; tiene un total de "+bolsa+" oros");
		}
		//La creación de una pausa fuera del código sincronizado, aunque dentro del método
		//ejecutado por los hilos, ocasiona una mayor diferencia de resultados (cantidad
		//de oro extraída por cada minero).
			try {
			Thread.sleep(tiempoExtraccion);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
			
	}	
}
