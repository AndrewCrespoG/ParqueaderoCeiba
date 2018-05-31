package com.ceiba.parqueadero.logica;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

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
		vehiculo.setPlaca(vehiculo.getPlaca().toUpperCase());//Placa en mayusculas
		validarPlacaVehiculo(vehiculo);
		
		if(repositorioVehiculos.existsById(vehiculo.getPlaca())) {
			throw new Exception ("El vehiculo ya esta registrado en el parqueadero");
		}else{
			
			repositorioVehiculos.save(vehiculo);
			System.out.println("Vehiculo guardado");
			factura.setVehiculo(vehiculo);
			factura.setFechaIngreso(Calendar.getInstance());
			factura.setValor(0);
			factura.setFechaSalida(null);
			factura.setHoras(0);

			repositorioFacturas.save(factura);
			System.out.println("Factura guardada");
		}
		
		return factura;
	}
	
	public void validarPlacaVehiculo(Vehiculo vehiculo) throws Exception{
		
		if(vehiculo.getPlaca().startsWith("A") && (!(Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || 
				Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY))) {
			//No entra
			throw new Exception("El vehiculo no esta autorizado para ingresar (hoy)");
		}
		
	}

	@Override
	public Factura calcularValorFactura(String placa) throws Exception {
		
		Optional <Factura> factura = repositorioFacturas.findByVehiculoPlaca(placa);
		Optional <Vehiculo> vehiculo = repositorioVehiculos.findById(placa);
		Optional <Tarifa> tarifa = repositorioTarifas.findByTipoVehiculo(vehiculo.get().getTipoVehiculo());
		
		if(!factura.isPresent()) {
			throw new Exception("Esta placa no coincide con ninguna factura");
		}
		
		if (!vehiculo.isPresent()) {
			throw new Exception("Vehiculo no encontrado");
		}
		
		if(!tarifa.isPresent()) {
			throw new Exception("Hubo un problema consultando las tarifas, verificar los tipos de tarifa");
		}
		
		factura = obtenerTotalPorPagar(factura, tarifa, vehiculo);
		return factura.get();
	}
	
	public Optional<Factura> obtenerTotalPorPagar(Optional<Factura> factura, Optional<Tarifa> tarifa, Optional<Vehiculo> vehiculo) {
		int numeroDeHoras = ManejoDeFechas.calcularHorasEntreDosFechas(factura.get().getFechaIngreso(), Calendar.getInstance());
		int diasPorFacturar = calcularDiasPorFacturar(numeroDeHoras);
		int horasPorFacturar = calcularHorasPorFacturar(numeroDeHoras);
		
		Double valorFactura = (tarifa.get().getValorPorHora() * horasPorFacturar) + (tarifa.get().getValorPorDia() * diasPorFacturar);
		valorFactura += calcularAdicionales(vehiculo); 
		
		factura.get().setValor(valorFactura);
		factura.get().setHoras(numeroDeHoras);
		factura.get().setFechaSalida(Calendar.getInstance());
		return factura;
	}
	
	public double calcularAdicionales(Optional<Vehiculo> vehiculo) {
		if(vehiculo.get().getTipoVehiculo().getNombre().equals("Motocicleta") && vehiculo.get().getCilindraje() > 500) {
			return 2000D;
		}
		return 0;
	}
	
	public Tarifa consultarTarifaPorTipoVehiculo(TipoVehiculo tipoVehiculo) throws Exception {
		Optional<Tarifa> tarifa = repositorioTarifas.findByTipoVehiculo(tipoVehiculo);
		if (! tarifa.isPresent() && tarifa.get() == null) {
			throw new Exception("No se pudo recuperar la tarifa");
		}
		return tarifa.get();
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
			} else if(pilaDeHoras < 9) {
				pilaDeHoras= 0;
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
