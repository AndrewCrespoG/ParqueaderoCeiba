package com.ceiba.parqueadero.servicios;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.parqueadero.logica.ControladorParquedero;
import com.ceiba.parqueadero.modelo.Factura;
import com.ceiba.parqueadero.modelo.Vehiculo;
import com.ceiba.parqueadero.repositorio.RepositorioVehiculos;

@RestController
public class ServiciosParqueaderoImpl implements ServiciosParquedero{

	//@Autowired
	//ControladorParquedero controladorParquedero;
	
	@Autowired
	RepositorioVehiculos repositorioVehiculos;
	
	@Override
	//@CrossOrigin(origins="*")
    @RequestMapping(value="/parqueadero/vehiculos", consumes = "application/json", method = RequestMethod.POST)
	//public Factura ingresarVehiculo(@RequestBody Vehiculo v) throws Exception {
	public Factura ingresarVehiculo(@RequestBody String vehiculo) throws Exception {
		Vehiculo v = new Vehiculo();
		try {
			JSONObject objVehiculo = new JSONObject(vehiculo);
			v.setId(objVehiculo.getLong("id"));
			v.setPlaca(objVehiculo.getString("placa"));
			repositorioVehiculos.save(v);
		}catch(JSONException e) {
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
