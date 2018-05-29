package com.ceiba.parqueadero.modelo;

import java.util.Calendar;

public class Factura {
	
	private Calendar fechaIngreso;
	
	public Calendar getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Calendar fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Factura() {
		
	}
}
