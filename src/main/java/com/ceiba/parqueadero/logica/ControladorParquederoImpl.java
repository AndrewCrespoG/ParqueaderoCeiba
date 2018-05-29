package com.ceiba.parqueadero.logica;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.parqueadero.modelo.Factura;
import com.ceiba.parqueadero.modelo.Vehiculo;
import com.ceiba.parqueadero.repositorio.RepositorioVehiculos;

@Service
public class ControladorParquederoImpl implements ControladorParquedero {

	@Autowired
	RepositorioVehiculos repositorioVehiculos;
	
	@Override
	public Factura ingresarVehiculo(Vehiculo v) throws Exception {
		
		return null;
	}

	@Override
	public Factura calcularValorFactura(String placa) {
		
		return null;
	}

	@Override
	public List<Vehiculo> consultarVehiculosEnParqueadero(String parqueadero) {
		
		return null;
	}

}
