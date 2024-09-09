package Biblioteca;

//SocketWeb: Test de conexiòn al servidor Web sobre la maquina local (direcion IP: 201.185.236.220),
// puerto : 8080. Se envia una consulta HTTP de tipo GET para leer el codigo de la pagina home del servidor
//
//

import java.net.*;
import java.io.*;

public class SocketWeb {
	
	public static void main(String[] args) {
		
		Socket socketCliente;
		BufferedReader entrada;
		PrintWriter salida;
		
		
		try {
			//Se intancia un objeto Socket, acompañado de un envìo de una consulta de conexión. 
			// la cual es el envío de la dirección IP y el número de puerto de la aplicación
			// en este caso el puerto 8080 corresponde con el servidor JEE (GlassFish instalado localmente)
			socketCliente = new Socket ("localhost",8080);
			System.out.println("Conectado a: "+socketCliente.getInetAddress());
			
			try {
				//Apertura de un flujo de escritura para enviar las consultas al servidor
				salida = new PrintWriter (socketCliente.getOutputStream());
				
				//Apertura de un flujo de lectura para recibir las respuestas del servidor
				entrada= new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
				
				//Envio de consultas (simulacion HTTP)
				// Enviar solicitud HTTP GET
                salida.println("GET / HTTP/1.1");
                salida.println("Host: localhost");
                salida.println("Connection: Close");
                salida.println();
				//El flush () permite enviar el request...
				//Si no lo hacemos, se rellena un buffer de escritura, y espera a que se rellene 
				// para partir automáticamente
				
				salida.print("GET / HTTP/1.1\r\nHost: localhost\r\n\r\n");

				salida.flush();
				
				//Lectura de la respuesta del servidor: se trata de la pantalla home de GlassFish
				
				System.out.println("\nArchivo home :\n");
				
				String linea;
				boolean cuerpo =false;
				//fila=entrada.readLine();
				
				while((linea = entrada.readLine()) != null) {
					if(linea.isEmpty()) {
						//Fin de los headers, comenzar a leer el cuerpo
						cuerpo=true;
						continue;
					}
					if(cuerpo) {
						//imprime el cuerpo de la respuesta
						System.out.println(linea);
					}
					
				}
				
			}
			finally {
				socketCliente.close();
			}
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
