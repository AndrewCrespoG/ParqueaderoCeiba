package com.ceiba.parqueadero.logica;

import java.util.List;

import com.ceiba.parqueadero.modelo.Factura;
import com.ceiba.parqueadero.modelo.Vehiculo;

public interface ControladorParquedero {

	Factura ingresarVehiculo(Vehiculo vehiculo) throws Exception;
	
	Factura calcularValorFactura(String placa) throws Exception;
	
	List<Vehiculo> consultarVehiculosEnParqueadero (String parqueadero);
	
	Factura retirarVehiculoDelParqueadero(Vehiculo vehiculo) throws Exception;
}
