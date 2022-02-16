package psp.AE.ServiciosRed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class GestorHTTP implements HttpHandler {
	// ATRIBUTOS
	private int temperaturaActual = 15;
	private int temperaturaTermostato = 15;
	
	
	// MÉTODOS DE INTERFACE
	
	/**Método: handle
	 * Descripción: determina el tipo de petición GET o POST recibida por el servidor,
	 * 				e invoca el método request y response correspondiente al tipo de petición.
	 * Parámetros de entrada: HttpExchange httpExchange
	 * Parámetros de salida: no
	 */
	@Override
	public void handle(HttpExchange httpExchange) throws IOException {
		String requestParamValue = null;
		if("GET".equals(httpExchange.getRequestMethod())) {
			//Si URL = http://localhost:7777/estufa?temperaturaActual
			requestParamValue = handleGetRequest(httpExchange);
			handleGETResponse(httpExchange,requestParamValue);
		} else if("POST".equals(httpExchange.getRequestMethod())) {
			requestParamValue = handlePostRequest(httpExchange);
			try {
				handlePOSTResponse(httpExchange, requestParamValue);
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
	// MÉTODOS DE IMPLEMENTACIÓN
	
	/**Método: handleGetRequest
	 * Descripción: Obtiene la información enviada por el cliente mediante la petición GET a traves del URI.
	 * Parámetros de entrada: HttpExchange httpExchange
	 * Parámetros de salida: String con la información facilitada por el cliente en el URI
	 */	
	private String handleGetRequest(HttpExchange httpExchange) {
		//Obtenemos la parte del URI, transformado a string, que está detras del interrogante (símbolo GET).
		return httpExchange.getRequestURI().toString().split("\\?")[1];
	}
	
	
	/**Método: handleGETResponse
	 * Descripción: Genera la respuesta a la petición de tipo GET efectuada por el cliente.
	 * Parámetros de entrada: HttpExchange httpExchange, String requestParamValue
	 * Parámetros de salida: no
	 */
	private void handleGETResponse(HttpExchange httpExchange, String requestParamValue) throws IOException {
		OutputStream outputStream = httpExchange.getResponseBody();
		String htmlResponse = null;		
		// Comprobamos que la petición enviada por el cliente es conocida por el servidor HTTP.
		if (requestParamValue.equals("temperaturaActual")) {
			htmlResponse = "<html><body>Temperatura Actual: "+temperaturaActual+
					"<br>Temperatura Termostato: "+temperaturaTermostato+"</body></html>";
			httpExchange.sendResponseHeaders(200, htmlResponse.length());
			System.out.println("Petición GET: aceptada");
		} else {
			htmlResponse = "<html><body>Petición desconocida</body></html>";
			httpExchange.sendResponseHeaders(404, htmlResponse.length());
			System.out.println("Petición GET: rechazada");
		}
		outputStream.write(htmlResponse.getBytes());
		outputStream.flush();
		outputStream.close();		
	}

	
	/**Método: handlePostRequest
	 * Descripción: Obtiene la información enviada por el cliente en el body de la petición POST (no en el URI).
	 * Parámetros de entrada: HttpExchange httpExchange
	 * Parámetros de salida: String con la información facilitada por el cliente en el body
	 */
	private String handlePostRequest(HttpExchange httpExchange) throws IOException {
		InputStream is = httpExchange.getRequestBody();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader bfr = new BufferedReader(isr);		
		String postRequest = bfr.readLine();		
		return postRequest;
	}
	
	
	/**Método: handlePOSTResponse
	 * Descripción: Genera la respuesta a la petición de tipo POST efectuada por el cliente;
	 * 				comprobando que la información recibida es válida y, en caso afirmativo,
	 * 				establece el nuevo valor de la variables temperaturaTermostato e
	 * 				invoca al método regularTemperatura().
	 * Parámetros de entrada: HttpExchange httpExchange, String requestParamValue
	 * Parámetros de salida: no
	 */
	private void handlePOSTResponse(HttpExchange httpExchange, String requestParamValue) throws IOException, InterruptedException {
		OutputStream outputStream = httpExchange.getResponseBody();
		String htmlResponse = null;	
		// Comprobamos que la petición enviada por el cliente es reconocida por el servidor HTTP.
		String instruccion = requestParamValue.split("=")[0];
		String valor = requestParamValue.split("=")[1];	
		if (instruccion.equals("setTemperatura")) {
			temperaturaTermostato=Integer.parseInt(valor);
			regularTemperatura();
			htmlResponse = "Parametro/s POST: " + requestParamValue + " -> ACEPTADO por el servidor";	
			httpExchange.sendResponseHeaders(200, htmlResponse.length());
		} else {
			htmlResponse = "Parametro/s POST: " + requestParamValue + " -> RECHAZADO por el servidor";	
			httpExchange.sendResponseHeaders(400, htmlResponse.length());
		}		
		outputStream.write(htmlResponse.getBytes());
		outputStream.flush();
		outputStream.close();
		System.out.println("Devuelve respuesta HTML: " + htmlResponse);		
	}

	
	/**Método: regularTemperatura
	 * Descripción: varia la variable temperaturaActual un grado cada 5 segundos hasta alcanzar
	 * 				la temperatura establecida en la variable temperaturaTermostato.
	 * Parámetros de entrada: no
	 * Parámetros de salida: no
	 */	
	private void regularTemperatura() throws InterruptedException {
		if(temperaturaTermostato > temperaturaActual) {
			do {
				temperaturaActual++;
				Thread.sleep(5000);
			} while(temperaturaTermostato > temperaturaActual);
		} else if (temperaturaTermostato < temperaturaActual) {
			do {
				temperaturaActual--;
				Thread.sleep(5000);
			} while(temperaturaTermostato < temperaturaActual);
		}		
	}

}
