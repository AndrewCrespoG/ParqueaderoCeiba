package com.ceiba.parqueadero.servicios;

import java.util.List;

import com.ceiba.parqueadero.modelo.Factura;
import com.ceiba.parqueadero.modelo.Vehiculo;

public interface ServiciosParquedero {

	Factura ingresarVehiculo(String vehiculo) throws Exception;
	
	Factura calcularValorFactura(String placa);
	
	List<Vehiculo> consultarVehiculosEnParqueadero (String parqueadero);
}
