
package com.mycompany.laboratorio_3.Biblioteca;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ServicioDeLibros  implements IServicioLibros{
    
    private final List<Libro> libros;
	
	public ServicioDeLibros() {
		this.libros= new ArrayList<>();
		
	}

	@Override
	public void listarLibros() {
		System.out.println("Listado de Libros: ");
		libros.forEach(System.out::println);//se aplica un metodo de referencia
		
		
	}

	@Override
	public void agregarLibro(Libro libro) {
		libros.add(libro);
		JOptionPane.showMessageDialog(null,"Se agrego libro: "+ libro);
		
		
	}

	@Override
	public void buscarLibro(Libro libro) {
		//regresamos el indice del libro
		var seccion = libros.indexOf(libro);
		if(seccion ==-1)
			System.out.println("No se encuentra el libro "+libro);
		System.out.println("Libro encontrado en la seccion: "+seccion+" de la biblioteca");
		JOptionPane.showMessageDialog(null, "Libro encontrado en la seccion: "+seccion+" de la biblioteca");
		
		
	}
	public static void main(String[] args) {
		//Creamos algunos libros
		var libro1 = new Libro("Algoritmos a fondo con implementacion en C y Java","Augusto Sznajdleder","Alfaomega");
		var libro2 = new Libro("Algoritmos y estructuras de datos en python","Walter Bel","Facultad UADER");
		var libro3 = new Libro("Administración de bases de datos","Michael Maninno","McGrawHill");
		var libro4 = new Libro("C# Guia del programador","Nicolas Landa","RedUser");
		var libro5 = new Libro("Desarrollo web con Java desde cero","RedUser","RedUser");
		
		//Creamos el servicio (patron de diseño service)
		IServicioLibros servicioLibros = new ServicioDeLibros();
		
		//agregamos los libros a la lista
		servicioLibros.agregarLibro(libro1);
		servicioLibros.agregarLibro(libro2);
		servicioLibros.agregarLibro(libro3);
		servicioLibros.agregarLibro(libro4);
		servicioLibros.agregarLibro(libro5);
		
		//ahora listamos los libros
		servicioLibros.listarLibros();
		
		//Buscamos un libro
		//implementamos el metodo equals y hashcode
		
		servicioLibros.buscarLibro(libro1);
		servicioLibros.buscarLibro(libro2);
		servicioLibros.buscarLibro(libro3);
		servicioLibros.buscarLibro(new Libro("Criptografía","Federico Pacheco","RedUser"));
		
	}
    
}
