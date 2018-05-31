package com.ceiba.parqueadero.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Parqueadero {

	@Id
	private int id;
	private String nombreParqueadero;
	private int numLugaresMotocicletasDisponibles;
	private int numLugaresAutomovilesDisponibles;
	
	public Parqueadero() {
	}

	public String getNombreParqueadero() {
		return nombreParqueadero;
	}

	public void setNombreParqueadero(String nombreParqueadero) {
		this.nombreParqueadero = nombreParqueadero;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumLugaresMotocicletasDisponibles() {
		return numLugaresMotocicletasDisponibles;
	}

	public void setNumLugaresMotocicletasDisponibles(int numLugaresMotocicletasDisponibles) {
		this.numLugaresMotocicletasDisponibles = numLugaresMotocicletasDisponibles;
	}

	public int getNumLugaresAutomovilesDisponibles() {
		return numLugaresAutomovilesDisponibles;
	}

	public void setNumLugaresAutomovilesDisponibles(int numLugaresAutomovilesDisponibles) {
		this.numLugaresAutomovilesDisponibles = numLugaresAutomovilesDisponibles;
	}
}
