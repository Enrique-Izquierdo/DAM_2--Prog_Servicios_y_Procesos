package psp.eval1.AE.MultiHilo;

public class Mina{
	//ATRIBUTOS
	private int stock;
	private int oroExtraido;
	
	//CONSTRUCTORES
	/**Método: Mina
	 * Descripción: permite la instanciación de un objeto de tipo Mina, pasandole por parámetro 
	 * 				el contenido inicila de oro de la Mina
	 * Parámetros de entrada: stock (int, cantidad inicial de oro de la Mina)
	 * Parámetres de salida: no*/
	public Mina (int pStock) {
		stock = pStock;
	}
	
	//GETTERS Y SETTERS
	public int getStock() { return stock; }	
	public void setStock(int pStock) { stock = pStock; }
	
	public int getOroExtraido() { return oroExtraido; }	
	public void setOroExtraido(int pOroExtraido) { oroExtraido = pOroExtraido; }
	
	//OTROS MÉTODOS DE INTERFACE	
	//MÉTODOS DE IMPLEMENTACIÓN

}
