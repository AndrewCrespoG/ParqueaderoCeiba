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
import com.ceiba.parqueadero.modelo.TipoVehiculo;
import com.ceiba.parqueadero.modelo.Vehiculo;
import com.ceiba.parqueadero.repositorio.RepositorioVehiculos;

@RestController
public class ServiciosParqueaderoImpl implements ServiciosParquedero{

	//@Autowired
	ControladorParquedero controladorParquedero;
	
	@Autowired
	RepositorioVehiculos repositorioVehiculos;
	
	@Override
	@CrossOrigin(origins="*")
    @RequestMapping(value="/parqueadero/vehiculos", method = RequestMethod.POST)
	public Factura ingresarVehiculo(@RequestBody Vehiculo vehiculo) throws Exception {
		Vehiculo v = new Vehiculo();
		try {
			v.setPlaca(vehiculo.getPlaca());
			//v.setTipoVehiculo(vehiculo.getTipoVehiculo());
			v.setPropietario(vehiculo.getPropietario());
			v.setCilindraje(vehiculo.getCilindraje());
			repositorioVehiculos.save(v);
		}catch(Exception e) {
			e.getMessage();
		}
		
		Factura factura = new Factura();
		return factura;
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
