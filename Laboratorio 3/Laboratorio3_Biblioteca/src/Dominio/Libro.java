package Dominio;

import java.util.Objects;

public class Libro {
	
	private String nombre, autor, editorial;
	
	
	public Libro() { //constructor vacio
		
	}
	
	public Libro(String nombre, String autor, String editorial) { //constructor con parametros
		this.nombre=nombre;
		this.autor=autor;
		this.editorial=editorial;
	}
	
	// Constructor que solo toma el nombre
    public Libro(String nombre) {
        this.nombre = nombre;
        this.autor = "Autor desconocido"; // Valor predeterminado
        this.editorial = "Editorial desconocida"; // Valor predeterminado
    }
	
	//metodos GET y SET
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	@Override
	public int hashCode() {
		return Objects.hash(autor, editorial, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		return Objects.equals(autor, other.autor) && Objects.equals(editorial, other.editorial)
				&& Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return this.nombre;
	}
	
	
	
	public static void main(String[] args) {
		var libro1= new Libro("Calculo de una variable","Leithold","McPherson");
		var libro2 = new Libro("Domina Java","Ernst V.","McPherson");
		System.out.println(libro1);
		System.out.println(libro2);
	}

}
