package Servicio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import Dominio.Libro;

public class ServicioArchivoLibros implements IServicioLibros{
	
	private final String NOMBRE_ARCHIVO= "Libros.txt";
	
	public ServicioArchivoLibros() {
		var archivo = new File(NOMBRE_ARCHIVO);
		try {
			//si ya exise el archivo no se vuelve a crear
			if(archivo.exists()) {
				System.out.println("El archivo ya existe");
				
			}else {
				//si no existe se crea 
				var salida = new PrintWriter(new FileWriter (archivo));
				salida.close();
				System.out.println("Se creo el archivo");
			}
			
		}catch(Exception e) {
			System.out.println("Ocurrio un error al abrir el archivo "+e.getMessage());
		}
	}

	@Override
	public void listarLibros() {
		//volvemos a abrir el archivo
		var archivo = new File(NOMBRE_ARCHIVO);
		try {
			System.out.println("LISTADO DE LIBROS");
			var entrada = new BufferedReader(new FileReader(archivo));
			//Leemos linea a linea el archivo
			String linea;
			linea=entrada.readLine();
			//leemos todas la lineas
			while(linea !=null) {
				var libro = new Libro(linea);
				System.out.println(libro);
				//antes de finalizar el ciclo se vuelve a leer la siguiente linea
				linea= entrada.readLine();
			}
			//cerramos el archivo
			entrada.close();
			
		}catch(Exception e) {
			System.out.println("Ocurrio un error al leer el archivo "+e.getMessage());
		}
		
		
	}

	@Override
	public void agregarLibro(Libro libro) {
		boolean anexar= false;
		var archivo = new File(NOMBRE_ARCHIVO);
		try {
			//Revisamos si existe el archivo
			anexar=archivo.exists();
			var salida= new PrintWriter(new FileWriter(archivo, anexar));
			
			//agregamos el libro con el metodo toString
			salida.println(libro);
			salida.close();
			System.out.println("Se agrego al archivo el libro: "+libro);
			
		}catch(Exception e) {
			System.out.println("Ocurrio un error al agregar el libro");
		}
		
		
	}

	@Override
	public void buscarLibro(Libro libro) {
		var archivo = new File(NOMBRE_ARCHIVO);
		
		try {
			//abrimos el archivo para lectura linea a linea
			var entrada = new BufferedReader(new FileReader(archivo));
			String lineaTexto;
			lineaTexto= entrada.readLine();
			var indice =1;
			var encontrado=false;
			var buscarLibro= libro.getNombre();
			while(lineaTexto !=null) {
				//buscamos sin importar mayusculas o minusculas
				if(buscarLibro != null && buscarLibro.equalsIgnoreCase(lineaTexto)) {
					encontrado=true;
					break;
				}
				//Si no encontramos el libro, leemos antes de la siguiente iteracion
				lineaTexto= entrada.readLine();
				indice++;
				
			}//fin del ciclo
			//se imprime la busqueda
			if(encontrado==true) {
				System.out.println("Libro "+lineaTexto+" encontrado en la seccion "+indice);
			}else {
				System.out.println("No se encontro el libro "+libro.getNombre());
			}
			entrada.close();
			
		}catch(Exception e) {
			System.out.println("Ocurrio un error buscando el libro "+e.getMessage());
		}
		
		
	}

}
