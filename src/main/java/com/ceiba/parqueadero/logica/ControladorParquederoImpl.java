package com.ceiba.parqueadero.logica;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.parqueadero.comun.ManejoDeFechas;
import com.ceiba.parqueadero.modelo.Factura;
import com.ceiba.parqueadero.modelo.Tarifa;
import com.ceiba.parqueadero.modelo.TipoVehiculo;
import com.ceiba.parqueadero.modelo.Vehiculo;
import com.ceiba.parqueadero.repositorio.RepositorioFacturas;
import com.ceiba.parqueadero.repositorio.RepositorioTarifas;
import com.ceiba.parqueadero.repositorio.RepositorioVehiculos;

@Service
public class ControladorParquederoImpl implements ControladorParquedero {

	@Autowired
	RepositorioVehiculos repositorioVehiculos;
	
	@Autowired
	RepositorioFacturas repositorioFacturas; 
	
	@Autowired
	RepositorioTarifas repositorioTarifas;
	
	@Override
	public Factura ingresarVehiculo(Vehiculo vehiculo) throws Exception {
Factura factura = new Factura();
		
		try {

			if(repositorioVehiculos.existsById(vehiculo.getPlaca())) {
				System.out.println("El vehiculo ya esta registrado en el parqueadero");
			}else{
				System.out.println("Ingresando vehiculo");
				repositorioVehiculos.save(vehiculo);
				
				
				factura.setVehiculo(vehiculo);
				factura.setFechaIngreso(Calendar.getInstance());
				factura.setValor(0);
				factura.setFechaSalida(null);
				factura.setHoras(0);

				repositorioFacturas.save(factura);
			}
			
		}catch(Exception e) {
			e.getMessage();
		}
		return factura;
	}

	@Override
	public Factura calcularValorFactura(String placa) throws Exception {
		
		Optional <List<Factura>> factura = repositorioFacturas.findByVehiculoPlaca(placa);
		if (!factura.isPresent()) {
			throw new Exception("No se ha encotnrado el vehiculo en la bd factura");
		}
		int pilaDeHoras = ManejoDeFechas.calcularHorasEntreDosFechas(factura.get().get(0).getFechaIngreso(), Calendar.getInstance());
		int diasPorFacturar = calcularDiasPorFacturar(pilaDeHoras);
		int horasPorFacturar = calcularHorasPorFacturar(pilaDeHoras);
		//Obtener valores de tarifa, y multiplicar por las horas a facturar
		Optional<Vehiculo> vehiculo = repositorioVehiculos.findById(placa);
		if (!vehiculo.isPresent()) {
			throw new Exception("No se ha encontrado vehiculo");
		}
		Tarifa tarifa = consultarTarifaPorTipoVehiculo(vehiculo.get().getTipoVehiculo());
		
		
		return null;
	}
	
	public Factura calcularValorFactura2(Factura factura, Tarifa tarifa, Vehiculo vehiculo) {
		int numeroDeHoras = ManejoDeFechas.calcularHorasEntreDosFechas(factura.getFechaIngreso(), Calendar.getInstance());
		int diasPorFacturar = calcularDiasPorFacturar(numeroDeHoras);
		int horasPorFacturar = calcularHorasPorFacturar(numeroDeHoras);
		
		Double valorFactura = (tarifa.getValorPorHora() * horasPorFacturar ) + (tarifa.getValorPorDia() * diasPorFacturar);
		valorFactura += calcularAdicionales(vehiculo); 
		
		factura.setValor(valorFactura);
		return factura;
	}
	
	public Double calcularAdicionales(Vehiculo vehiculo) {
		//
		return 0D;
	}
	
	public Tarifa consultarTarifaPorTipoVehiculo(TipoVehiculo tipoVehiculo) throws Exception {
		Optional<List<Tarifa>> tarifa = repositorioTarifas.findByTipoVehiculo(tipoVehiculo.getId());
		if (! tarifa.isPresent() && tarifa.get().isEmpty()) {
			throw new Exception("No se pudo recuperar la tarifa");
		}
		return tarifa.get().get(0);
	}
	
	public int calcularDiasPorFacturar(int pilaDeHoras) {
		int diasPorFacturar = 0;
		while(pilaDeHoras > 0) {
			if (pilaDeHoras >= 9 && pilaDeHoras > 24) {
				//Resta 24 hrs a la pila y suma un dia
				pilaDeHoras = pilaDeHoras - 24;
				diasPorFacturar = diasPorFacturar + 1;
			} else if(pilaDeHoras >= 9 && pilaDeHoras <= 24) {
				//Hace que la pila de horas sea cero y agrega un dia
				pilaDeHoras = 0;
				diasPorFacturar = diasPorFacturar + 1;
			}
		}
		return  diasPorFacturar;
	}

	public int calcularHorasPorFacturar(int pilaDeHoras) {
		int horasPorFacturar = 0;
		while(pilaDeHoras > 0) {
			if (pilaDeHoras >= 9 && pilaDeHoras > 24) {
				pilaDeHoras = pilaDeHoras - 24;
			} else if(pilaDeHoras >= 9 && pilaDeHoras <= 24) {
				horasPorFacturar = 0;
				pilaDeHoras = 0;
			} else if (pilaDeHoras < 9) {
				horasPorFacturar = pilaDeHoras;
				pilaDeHoras = 0;
			}
		}
		return horasPorFacturar;
	}
	
	@Override
	public List<Vehiculo> consultarVehiculosEnParqueadero(String parqueadero) {
		
		return null;
	}

}
