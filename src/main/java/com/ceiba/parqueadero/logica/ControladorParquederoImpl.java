package com.ceiba.parqueadero.logica;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.parqueadero.comun.ManejoDeFechas;
import com.ceiba.parqueadero.modelo.Factura;
import com.ceiba.parqueadero.modelo.Parqueadero;
import com.ceiba.parqueadero.modelo.Tarifa;
import com.ceiba.parqueadero.modelo.TipoVehiculo;
import com.ceiba.parqueadero.modelo.Vehiculo;
import com.ceiba.parqueadero.repositorio.RepositorioFacturas;
import com.ceiba.parqueadero.repositorio.RepositorioParqueadero;
import com.ceiba.parqueadero.repositorio.RepositorioTarifas;
import com.ceiba.parqueadero.repositorio.RepositorioTipoVehiculos;
import com.ceiba.parqueadero.repositorio.RepositorioVehiculos;

@Service
public class ControladorParquederoImpl implements ControladorParquedero {

	@Autowired
	private RepositorioVehiculos repositorioVehiculos;
	
	@Autowired
	private RepositorioFacturas repositorioFacturas; 
	
	@Autowired
	private RepositorioTarifas repositorioTarifas;
	
	@Autowired
	private RepositorioParqueadero repositorioParqueaderos;
	
	@Autowired
	private RepositorioTipoVehiculos repositorioTipoVehiculos;
	
	@Autowired
	private ManejoDeFechas manejoFechas;
	
	
	
	@Override
	public Factura ingresarVehiculo(Vehiculo vehiculo) throws Exception {
			
		Factura factura = new Factura();
		vehiculo.setPlaca(vehiculo.getPlaca().toUpperCase());//Placa en mayusculas, por si acaso
		validarPlacaVehiculo(vehiculo);
		
		//Mirar el tipo de vehiculo para calcular si hay espacio donde se pueda entrar
		verificarEspaciosDisponibles(vehiculo);
		
		if(repositorioVehiculos.existsById(vehiculo.getPlaca())) {
			throw new Exception ("El vehiculo ya esta registrado en el parqueadero");
		}else{
			
			repositorioVehiculos.save(vehiculo);
			System.out.println("Vehiculo guardado");
			                                                                                                                                                                                                                                                  
			recalcularEspaciosParqueadero(vehiculo);
			
			factura.setVehiculo(vehiculo);
			factura.setFechaIngreso(manejoFechas.obtenerFechaActual());
			factura.setValor(0);
			factura.setFechaSalida(null);
			factura.setHoras(0);

			repositorioFacturas.save(factura); 	
			System.out.println("Factura guardada");
		}
		
		return factura;
	}
	
	public void verificarEspaciosDisponibles(Vehiculo vehiculo) throws Exception {
		//Mira para ver si hay espacios en el parqueadero donde este vehiculo se pueda guardar
		Optional <Parqueadero> parqueadero = repositorioParqueaderos.findById("1");
		if (!parqueadero.isPresent()) {
			throw new Exception ("Parqueadero no encontrado");
		}
		if(vehiculo.getTipoVehiculo().getNombre().equals("Automovil")) {
			//Preguntar si hay espacios para autos
			if(parqueadero.get().getNumLugaresAutomovilesDisponibles() <= 0) {
				throw new Exception("No quedan espacios disponibles para guardar automoviles");
			}
		}else if(vehiculo.getTipoVehiculo().getNombre().equals("Motocicleta") &&
				parqueadero.get().getNumLugaresMotocicletasDisponibles() <= 0) {
				throw new Exception ("No quedan espacios disponibles para guardar motocicletas");
		}
	}
	
	public Parqueadero recalcularEspaciosParqueadero(Vehiculo vehiculo) throws Exception {
		Optional <Parqueadero> parqueadero = repositorioParqueaderos.findById("1");
		if(!parqueadero.isPresent()) {
			throw new Exception("Parqueadero no encontrado, debe crearse");
		}
				
		if(vehiculo.getTipoVehiculo().getNombre().equals("Automovil")) {//Reduce automovil
			parqueadero.get().setNumLugaresAutomovilesDisponibles(
					parqueadero.get().getNumLugaresAutomovilesDisponibles() - 1);
		}else if(vehiculo.getTipoVehiculo().getNombre().equals("Motocicleta")) {//Reduce motocicleta
			parqueadero.get().setNumLugaresMotocicletasDisponibles(
					parqueadero.get().getNumLugaresMotocicletasDisponibles() - 1);
		}
		repositorioParqueaderos.save(parqueadero.get());
		System.out.println("Espacios disponibles recalculados");
		return parqueadero.get();
	}
	
	public void validarPlacaVehiculo(Vehiculo vehiculo) throws Exception{
		//Consulta si se puede ingresar el vehiculo de acuerdo a la restriccion de placa
		if(vehiculo.getPlaca().startsWith("A") && (!(manejoFechas.obtenerFechaActual().get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || 
				manejoFechas.obtenerFechaActual().get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY))) {
			//No entra
			throw new Exception("El vehiculo no esta autorizado para ingresar (hoy)");
		}
	}

	@Override
	public Factura calcularValorFactura(String placa) throws Exception {
		
		Optional <Factura> factura = repositorioFacturas.findByVehiculoPlaca(placa);
		if(!factura.isPresent()) {
			throw new Exception("Esta placa no coincide con ninguna factura");
		}
		Optional <Vehiculo> vehiculo = repositorioVehiculos.findById(placa);
		if (!vehiculo.isPresent()) {
			throw new Exception("Vehiculo no encontrado");
		}
		Optional <Tarifa> tarifa = repositorioTarifas.findByTipoVehiculo(vehiculo.get().getTipoVehiculo());
		if(!tarifa.isPresent()) {
			throw new Exception("Hubo un problema consultando las tarifas, verificar los tipos de tarifa");
		}
		factura = obtenerTotalPorPagar(factura, tarifa, vehiculo);
		
		
		return factura.get();
	}
	
	public Optional<Factura> obtenerTotalPorPagar(Optional<Factura> factura, Optional<Tarifa> tarifa, Optional<Vehiculo> vehiculo) {
		int numeroDeHoras = manejoFechas.calcularHorasEntreDosFechas(factura.get().getFechaIngreso(),
				manejoFechas.obtenerFechaActual());
		int diasPorFacturar = calcularDiasPorFacturar(numeroDeHoras);
		int horasPorFacturar = calcularHorasPorFacturar(numeroDeHoras);
		
		double valorFactura = (tarifa.get().getValorPorHora() * horasPorFacturar) + (tarifa.get().getValorPorDia() * diasPorFacturar);
		valorFactura += calcularAdicionales(vehiculo); 
		
		factura.get().setValor(valorFactura);
		factura.get().setHoras(numeroDeHoras);
		factura.get().setFechaSalida(manejoFechas.obtenerFechaActual());
		return factura;
	}
	
	public double calcularAdicionales(Optional<Vehiculo> vehiculo) {
		if(vehiculo.get().getTipoVehiculo().getNombre().equals("Motocicleta") && vehiculo.get().getCilindraje() > 500) {
			return 2000;
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
	
	@Override
	public Factura retirarVehiculoDelParqueadero(Vehiculo vehiculo) throws Exception {
		Optional <Parqueadero> parqueadero = repositorioParqueaderos.findById("1");
		if(!parqueadero.isPresent()) {
			throw new Exception("Parqueadero no encontrado, debe crearse");
		}
		
		if(vehiculo.getTipoVehiculo().getNombre().equals("Automovil")) {//Aumenta los espacios para Autos
			parqueadero.get().setNumLugaresAutomovilesDisponibles(
					parqueadero.get().getNumLugaresAutomovilesDisponibles() + 1);
		}else if(vehiculo.getTipoVehiculo().getNombre().equals("Motocicleta")) {//Aumenta los espacios para motos
			parqueadero.get().setNumLugaresMotocicletasDisponibles(
					parqueadero.get().getNumLugaresMotocicletasDisponibles() + 1);
		}
		
		repositorioParqueaderos.save(parqueadero.get());
		System.out.println("Espacios disponibles recalculados");
		Factura facturaParaMostrar = calcularValorFactura(vehiculo.getPlaca());
		
		
		retirarFacturaDelVehiculo(vehiculo);
		eliminarVehiculoDelParqueadero(vehiculo);
		
		return facturaParaMostrar;
	}
	
	public Factura retirarFacturaDelVehiculo(Vehiculo vehiculo) throws Exception {
		Optional <Factura> factura = repositorioFacturas.findByVehiculoPlaca(vehiculo.getPlaca());
		if (! factura.isPresent()) {
			throw new Exception("Factura no encontrada");
		}
		factura.get().setFechaSalida(manejoFechas.obtenerFechaActual());
		repositorioFacturas.delete(factura.get());
		return factura.get();
	}
	
	public void eliminarVehiculoDelParqueadero(Vehiculo vehiculo) {
		repositorioVehiculos.deleteById(vehiculo.getPlaca());
	}

	public RepositorioVehiculos getRepositorioVehiculos() {
		return repositorioVehiculos;
	}

	public void setRepositorioVehiculos(RepositorioVehiculos repositorioVehiculos) {
		this.repositorioVehiculos = repositorioVehiculos;
	}

	public RepositorioFacturas getRepositorioFacturas() {
		return repositorioFacturas;
	}

	public void setRepositorioFacturas(RepositorioFacturas repositorioFacturas) {
		this.repositorioFacturas = repositorioFacturas;
	}

	public RepositorioTarifas getRepositorioTarifas() {
		return repositorioTarifas;
	}

	public void setRepositorioTarifas(RepositorioTarifas repositorioTarifas) {
		this.repositorioTarifas = repositorioTarifas;
	}

	public RepositorioParqueadero getRepositorioParqueaderos() {
		return repositorioParqueaderos;
	}

	public void setRepositorioParqueaderos(RepositorioParqueadero repositorioParqueaderos) {
		this.repositorioParqueaderos = repositorioParqueaderos;
	}

	public RepositorioTipoVehiculos getRepositorioTipoVehiculos() {
		return repositorioTipoVehiculos;
	}

	public void setRepositorioTipoVehiculos(RepositorioTipoVehiculos repositorioTipoVehiculos) {
		this.repositorioTipoVehiculos = repositorioTipoVehiculos;
	}

	public ManejoDeFechas getManejoFechas() {
		return manejoFechas;
	}

	public void setManejoFechas(ManejoDeFechas manejoFechas) {
		this.manejoFechas = manejoFechas;
	}

}
