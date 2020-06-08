
import java.io.*;
import java.net.*;
import java.nio.channels.SocketChannel;

public class ServidorTCP1 {  
  public static final int PORT = 8888;
  public static void main(String[] args) throws IOException {
    // Establece el puerto en el que escucha peticiones
    ServerSocket socketServidor = null;
    try {
      socketServidor = new ServerSocket(PORT);
    } catch (IOException e) {
      System.out.println("No puede escuchar en el puerto: " + PORT);
      System.exit(-1);
    }

    Socket socketCliente = null;
    BufferedReader entrada = null;
    PrintWriter salida = null;

    System.out.println("Escuchando: " + socketServidor);
    try {
    	
    	while(true) {
	      // Se bloquea hasta que recibe alguna petición de un cliente
	      // abriendo un socket para el cliente
	      socketCliente = socketServidor.accept();
	      System.out.println("Connexion acceptada: "+ socketCliente);
	      // Establece canal de entrada
	      entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
	      // Establece canal de salida
	      salida = new PrintWriter(new BufferedWriter(new 
		  OutputStreamWriter(socketCliente.getOutputStream())),true);
	      
	      boolean sw = true;
	      // Hace eco de lo que le proporciona el cliente, hasta que recibe "Adios"
	      while (sw) {  
	        String str = entrada.readLine();
	        System.out.println("Entrada del cliente: "+str);
//	        String opc = "";
	        if(str.equals("salir")) sw = false;
	        if(str.equals("1")) salida.println("papel");
	        if(str.equals("2")) salida.println("piedra");
	        if(str.equals("3")) salida.println("tijera");
	    }
    }

    } catch (IOException e) {
      System.out.println("IOException: " + e.getMessage());
    }  
    salida.close();
    entrada.close();
    socketCliente.close();
    socketServidor.close();
  }
}