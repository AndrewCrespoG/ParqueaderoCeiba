package com.ceiba.parqueadero.servicios;

import java.util.List;
import com.ceiba.parqueadero.modelo.Tarifa;

public interface ServiciosTarifa {
	
	public List<Tarifa> listarTarifas();
	
	public void ingresarTarifa(Tarifa tarifa);
	
}
