package Biblioteca;

import java.net.*;
import java.util.HashMap;
import java.io.*;

public class Servidor {
	
	//HashMap para almacenar coreo y claves
	private static final HashMap<String, String> usuarios = new HashMap<>();

	public static void main(String[] args) {
		
		//Inicializa el HashMap usuarios
		inicializarUsuarios();
		// Servidor: Objeto Servidor
		// Socket: referencia de un Socket del servidor hacia el cliente
		// salida : flujo hacia el cliente
		// entrada: flujo que proviene del cliente
		// linea: cadena de lectura y de escritur de flujos

		ServerSocket servidor;
		Socket socketServidor;
		PrintWriter salida;
		BufferedReader entrada;
		String linea;

		try {
			servidor = new ServerSocket(9090);

			try {
				// Aqui se utiliza el SO_TIMEOUT que permite limitar el tiempo
				// que espera el servidor. El servidor va a esperar 1 minuto para que el cliente
				// se conecte
				// Por defecto va a emitir un socketTimeoutException

				//servidor.setSoTimeout(100000);

				System.out
						.println("Un estudiante se debe conectar antes de " + servidor.getSoTimeout() / 10000 + " segundos");

				// El metodo accept() pide al programa esperar que un cliente se conecte.
				// La conexion del cliente se acompaña de la creaciòn de un objeto Socket que
				// permite dialogar con el
				// El metodo accept() devuelve la referencia de este "Socket"

				socketServidor = servidor.accept();
				try {
					salida = new PrintWriter(new OutputStreamWriter(socketServidor.getOutputStream(), "UTF-8"));
					entrada = new BufferedReader(new InputStreamReader(socketServidor.getInputStream(), "UTF-8"));

					// Envio del mensaje de bienvenida

					salida.println("Bienvenido, apreciado estudiante!");
					salida.flush();

					// Lectura del mensaje del cliente
					String email =entrada.readLine();
					String clave = entrada.readLine();
					if(validarCredenciales(email, clave)) {
						salida.println("Acceso permitido");
					}else {
						salida.print("Acceso denegado. Credenciales incorrectas");
					}
					salida.flush();

					
				} finally {
					socketServidor.close();
				}
			} catch (SocketTimeoutException e) {
				System.out.println("Tiempo de espera de la conexiòn con el cliente alcanzada !");

			} finally {
				servidor.close();
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//Inicializa el HashMap con datos quemados
	private static void inicializarUsuarios() {
		usuarios.put("correo1@uqvirtual.edu.co", "1010");
		usuarios.put("correo2@uqvirtual.edu.co", "2020");
		usuarios.put("correo3@uqvirtual.edu.co", "3030");
		usuarios.put("correo4@uqvirtual.edu.co", "4040");
		usuarios.put("correo5@uqvirtual.edu.co", "5050");
		usuarios.put("correo6@uqvirtual.edu.co", "6060");
		usuarios.put("correo7@uqvirtual.edu.co", "7070");
		usuarios.put("correo8@uqvirtual.edu.co", "8080");
		usuarios.put("correo9@uqvirtual.edu.co", "9090");
		usuarios.put("correo10@uqvirtual.edu.co", "100100");
		usuarios.put("correo11uqvirtual.edu.co", "200200");
		
        }
	
	//Valida las credenciales
	private static boolean validarCredenciales (String email, String clave) {
		return usuarios.containsKey(email)&& usuarios.get(email).equals(clave);
	}
}
