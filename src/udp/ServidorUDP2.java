package udp;

import java.net.*;
import java.io.*;

public class ServidorUDP2 {

  public static void main (String args[]) {

    try {

      DatagramSocket socketUDP = new DatagramSocket(6789);
      byte[] bufer = new byte[10000];

      while (true) {
        // Construimos el DatagramPacket para recibir peticiones
        DatagramPacket peticion = new DatagramPacket(bufer, bufer.length);
        // Leemos una petición del DatagramSocket
        socketUDP.receive(peticion);
        System.out.print("Datagrama recibido del host: " + peticion.getAddress());
        System.out.println(" desde el puerto remoto: " + peticion.getPort());
        // Construimos el DatagramPacket para enviar la respuesta
        String cad = new String(peticion.getData());
        System.out.println(cad);
        int tam = peticion.getLength(), numP;
        String cadR= "";
    	for (int i = 0 ; i<tam; i++)
    		cadR += cad.charAt(i);
    	String [] str = cadR.split(" ");
    	numP = str.length;
    	cadR = Integer.toString(numP);
    	
    	byte [] mensaje = cadR.getBytes();
        DatagramPacket respuesta = new DatagramPacket(mensaje, cadR.length(), peticion.getAddress(), peticion.getPort());
        // Enviamos la respuesta, que es la cantidad de pal
        socketUDP.send(respuesta);
      }

    } catch (SocketException e) {
      System.out.println("Socket: " + e.getMessage());
    } catch (IOException e) {
      System.out.println("IO: " + e.getMessage());
    }
  }

}
