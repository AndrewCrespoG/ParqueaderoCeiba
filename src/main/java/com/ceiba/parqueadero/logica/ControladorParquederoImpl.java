package com.ceiba.parqueadero.logica;

import java.util.List;
import java.util.Optional;

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
		Optional<Vehiculo> vehiculo = repositorioVehiculos.findById(v.getPlaca());
		repositorioVehiculos.save(v);
		if(vehiculo.isPresent()) {
			// 
			repositorioVehiculos.save(v);
			Factura factura = new Factura();
			
			return factura;
		}
		
		throw new Exception("el vehucilo ya ha ingresadio");
	}

	@Override
	public Factura calcularValorFactura(String placa) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vehiculo> consultarVehiculosEnParqueadero(String parqueadero) {
		// TODO Auto-generated method stub
		return null;
	}

}
