package com.ceiba.parqueadero.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="tipo_vehiculo")
public class TipoVehiculo {
	
	@Id
	private Long id;
	private String nombre;
	
	public TipoVehiculo() {
		//Consrtuctor sin parametros
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
