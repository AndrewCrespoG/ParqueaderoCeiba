package com.ceiba.parqueadero.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Tarifa {

	@Id
	private Long id;
	private double valorPorHora;
	private double valorPorDia;
	@ManyToOne
	private TipoVehiculo tipoVehiculo;
	
	public Tarifa() {
	}
	
	public double getValorPorHora() {
		return valorPorHora;
	}
	
	public void setValorPorHora(double valorPorHora) {
		this.valorPorHora = valorPorHora;
	}
	
	public TipoVehiculo getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getValorPorDia() {
		return valorPorDia;
	}

	public void setValorPorDia(double valorPorDia) {
		this.valorPorDia = valorPorDia;
	}
	
	
}
