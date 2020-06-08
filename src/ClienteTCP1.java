
import java.net.*;
import java.io.*;

public class ClienteTCP1 {
  public static void main(String[] args)  throws IOException {
    Socket socketCliente = null;
    BufferedReader entrada = null;
    PrintWriter salida = null;

    // Creamos un socket en el lado cliente, enlazado con un
    // servidor que está en la misma máquina que el cliente
    // y que escucha en el puerto 4444
    try {
      socketCliente = new Socket("localhost", 8888);
      // Obtenemos el canal de entrada
      
      entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
      
      // Obtenemos el canal de salida
      salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketCliente.getOutputStream())),true);
    } catch (IOException e) {
	System.err.println("No puede establer canales de E/S para la conexion");
        System.exit(-1);
    }
    BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
    String linea;
    
    try {
    	boolean sw = true;
    	while (sw) {
    	System.out.println("MENU:"+"\n-Opcion 1 \n-Opcion 2 \n-Opcion 3 \n-salir");
        linea = stdIn.readLine();
        
        salida.println(linea);
        
        if (linea.equals("salir")) sw = false ;
        else {
        linea = entrada.readLine();
        System.out.println("Respuesta servidor: " + linea);
        // Si es "salir" es que finaliza la comunicacion
        }
        
      }
    } catch (IOException e) {
	System.out.println("IOException: " + e.getMessage());
    }
 
    // Libera recursos
    salida.close();
    entrada.close();
    stdIn.close();
    socketCliente.close();
  }
}