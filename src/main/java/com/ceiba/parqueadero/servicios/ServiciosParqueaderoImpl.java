package com.ceiba.parqueadero.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.parqueadero.logica.ControladorParquedero;
import com.ceiba.parqueadero.modelo.Factura;
import com.ceiba.parqueadero.modelo.Vehiculo;

@RestController
public class ServiciosParqueaderoImpl implements ServiciosParquedero{

	@Autowired
	ControladorParquedero controladorParquedero;
	
	@Override
	@CrossOrigin(origins="*")
    @RequestMapping( path="/parqueadero/vehiculos", method = RequestMethod.POST)
	public Factura ingresarVehiculo(@ RequestBody Vehiculo v) throws Exception {
		System.out.println(v.getPlaca() + v.id);
		return this.controladorParquedero.ingresarVehiculo(new Vehiculo());
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
