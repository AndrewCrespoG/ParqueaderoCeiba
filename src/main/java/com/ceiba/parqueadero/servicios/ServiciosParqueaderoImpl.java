package com.ceiba.parqueadero.servicios;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.parqueadero.logica.ControladorParquedero;
import com.ceiba.parqueadero.modelo.Factura;
import com.ceiba.parqueadero.modelo.Parqueadero;
import com.ceiba.parqueadero.modelo.Vehiculo;
import com.ceiba.parqueadero.repositorio.RepositorioFacturas;
import com.ceiba.parqueadero.repositorio.RepositorioParqueadero;
import com.ceiba.parqueadero.repositorio.RepositorioVehiculos;

@RequestMapping(value="/parqueadero/vehiculo")
@RestController
public class ServiciosParqueaderoImpl implements ServiciosParquedero{
	
	@Autowired
	RepositorioVehiculos repositorioVehiculos;
	
	@Autowired
	RepositorioFacturas repositorioFacturas;
	
	@Autowired
	RepositorioParqueadero repositorioParqueadero;
	
	@Autowired
	ControladorParquedero controladorParqueadero;
	
	@Override
    @RequestMapping(value="/ingresar-vehiculo", method = RequestMethod.POST)
	public Factura ingresarVehiculo(@RequestBody Vehiculo vehiculo) throws Exception {
		return controladorParqueadero.ingresarVehiculo(vehiculo);
	}

	@Override
	@RequestMapping(value = "/calcular-factura", method = RequestMethod.POST)
	public Factura calcularValorFactura(@RequestBody String placa) throws Exception{	
		
		return controladorParqueadero.calcularValorFactura(placa);
	}
	
	public int calcularHorasEntreDosFechas(Calendar fechaAntigua, Calendar fechaFuturo) {
		int horasCalc = (int) ((fechaFuturo.getTimeInMillis() - fechaAntigua.getTimeInMillis())/1000/60/60);
		System.out.println("Siempre se le va a calcular una hora por lo menos");
		return (horasCalc <= 0) ? (1) : (horasCalc);
	}

	@Override
	@RequestMapping(value="/listar-vehiculos", method = RequestMethod.GET)
	public List<Vehiculo> consultarVehiculosEnParqueadero (String parqueadero){
		List<Vehiculo> vehiculos = new ArrayList<>();
		repositorioVehiculos.findAll()
		.forEach(vehiculos::add);
		return vehiculos;
	}

}
