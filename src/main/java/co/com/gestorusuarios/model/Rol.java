package co.com.gestorusuarios.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Rol {
	
	@Id
	private int id;
	private String nombre;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

}
