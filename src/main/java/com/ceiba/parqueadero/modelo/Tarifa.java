package com.ceiba.parqueadero.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Tarifa {

	@Id
	private Long id;
	private double valorPorHora;
	private int tipoVehiculo;
	private int cilindraje;
	
	public Tarifa() {
	}
	
	public double getValorPorHora() {
		return valorPorHora;
	}
	
	public void setValorPorHora(double valorPorHora) {
		this.valorPorHora = valorPorHora;
	}
	
	public int getTipoVehiculo() {
		return tipoVehiculo;
	}
	
	public void setTipoVehiculo(int tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}
	
	public int getCilindraje() {
		return cilindraje;
	}
	
	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
