package com.ceiba.parqueadero.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Parqueadero {
	/*+ nombreParqueadero: string
	+ numLugaresMotocicletas: int
	+ numLugaresAutomoviles: int
	+ numLugaresAutomovilesOcu: int
	+ numLugaresMotocicletas: int*/
	
	@Id
	private int id;
	private String nombreParqueadero;
	private int numLugaresMotocicletas;
	private int numLugaresAutomoviles;
	private int numLugaresAutomovilesOcupados;
	private int numLugaresMotocicletasOcupados;
	
	public Parqueadero() {
	}

	public String getNombreParqueadero() {
		return nombreParqueadero;
	}

	public void setNombreParqueadero(String nombreParqueadero) {
		this.nombreParqueadero = nombreParqueadero;
	}

	public int getNumLugaresMotocicletas() {
		return numLugaresMotocicletas;
	}

	public void setNumLugaresMotocicletas(int numLugaresMotocicletas) {
		this.numLugaresMotocicletas = numLugaresMotocicletas;
	}

	public int getNumLugaresAutomoviles() {
		return numLugaresAutomoviles;
	}

	public void setNumLugaresAutomoviles(int numLugaresAutomoviles) {
		this.numLugaresAutomoviles = numLugaresAutomoviles;
	}

	public int getNumLugaresAutomovilesOcupados() {
		return numLugaresAutomovilesOcupados;
	}

	public void setNumLugaresAutomovilesOcupados(int numLugaresAutomovilesOcupados) {
		this.numLugaresAutomovilesOcupados = numLugaresAutomovilesOcupados;
	}

	public int getNumLugaresMotocicletasOcupados() {
		return numLugaresMotocicletasOcupados;
	}

	public void setNumLugaresMotocicletasOcupados(int numLugaresMotocicletasOcupados) {
		this.numLugaresMotocicletasOcupados = numLugaresMotocicletasOcupados;
	}
	
	
}
