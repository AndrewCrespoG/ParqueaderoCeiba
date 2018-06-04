package com.ceiba.parqueadero.servicios;

import java.util.List;

import com.ceiba.parqueadero.modelo.Factura;
import com.ceiba.parqueadero.modelo.Vehiculo;

public interface ServiciosParquedero {

	public Factura ingresarVehiculo(Vehiculo vehiculo) throws Exception;
	
	public Factura calcularValorFactura(String placa) throws Exception;
	
	public List<Vehiculo> consultarVehiculosEnParqueadero (String parqueadero);
	
	public Factura retirarVehiculoDelParqueadero(Vehiculo vehiculo) throws Exception;
	
}
