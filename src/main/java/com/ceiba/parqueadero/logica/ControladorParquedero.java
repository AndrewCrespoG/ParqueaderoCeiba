package com.ceiba.parqueadero.logica;

import java.util.List;

import com.ceiba.parqueadero.modelo.Factura;
import com.ceiba.parqueadero.modelo.Vehiculo;

public interface ControladorParquedero {

	Factura ingresarVehiculo(Vehiculo v) throws Exception;
	
	Factura calcularValorFactura(String placa);
	
	List<Vehiculo> consultarVehiculosEnParqueadero (String parqueadero);
}
