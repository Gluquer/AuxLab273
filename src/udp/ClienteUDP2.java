package udp;

import java.net.*;
import java.io.*;

public class ClienteUDP2{

  // Los argumentos proporcionan el mensaje y el nombre del servidor
  public static void main(String args[]) {

    try {
    	
      DatagramSocket socketUDP = new DatagramSocket();
      InetAddress hostServidor = InetAddress.getByName("localhost");
      int puertoServidor = 6789;
      BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
      
      String cad;
      cad = sc.readLine();
      byte[] mensaje = cad.getBytes();
      // Construimos un datagrama para enviar el mensaje al servidor
      DatagramPacket peticion = new DatagramPacket(mensaje, cad.length(), hostServidor, puertoServidor);
      // Enviamos el datagrama
      socketUDP.send(peticion);
      // Construimos el DatagramPacket que contendrá la respuesta
      byte[] bufer = new byte[10000];
      DatagramPacket respuesta = new DatagramPacket(bufer, bufer.length);
      socketUDP.receive(respuesta);
      // Enviamos la respuesta del servidor a la salida estandar
      System.out.println("Cantidad de Palabras : " + new String(respuesta.getData()));

      // Cerramos el socket
      socketUDP.close();

    } catch (SocketException e) {
      System.out.println("Socket: " + e.getMessage());
    } catch (IOException e) {
      System.out.println("IO: " + e.getMessage());
    }
  }
}
