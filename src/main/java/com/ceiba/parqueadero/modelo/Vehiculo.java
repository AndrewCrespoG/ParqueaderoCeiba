package com.ceiba.parqueadero.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Vehiculo {

	@Id
	private String placa;
	//private TipoVehiculo tipoVehiculo;
	private int cilindraje;
	private String propietario;

	public Vehiculo() {
		//Constructor sin parametros
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getPropietario() {
		return propietario;
	}

	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}

	public int getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}

	/*public TipoVehiculo getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}*/
	
}
