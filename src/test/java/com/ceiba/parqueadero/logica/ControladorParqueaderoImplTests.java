package com.ceiba.parqueadero.logica;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Calendar;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.parqueadero.modelo.Factura;
import com.ceiba.parqueadero.modelo.Parqueadero;
import com.ceiba.parqueadero.modelo.TipoVehiculo;
import com.ceiba.parqueadero.modelo.Vehiculo;
import com.ceiba.parqueadero.repositorio.RepositorioFacturas;
import com.ceiba.parqueadero.repositorio.RepositorioParqueadero;
import com.ceiba.parqueadero.repositorio.RepositorioVehiculos;
import com.ceiba.parqueadero.comun.ManejoDeFechas;
import com.ceiba.parqueadero.logica.ControladorParquederoImpl;

public class ControladorParqueaderoImplTests {

	ControladorParquederoImpl controladorParqueadero;
	ManejoDeFechas manejoDeFechas;
	RepositorioVehiculos repositorioVehiculos;
	RepositorioFacturas repositorioFacturas;
	RepositorioParqueadero repositorioParqueadero;
	
	@Before
	public void antesDeCadaPrueba() {
		this.controladorParqueadero = new ControladorParquederoImpl();
		this.manejoDeFechas = Mockito.mock(ManejoDeFechas.class);
		this.repositorioVehiculos = Mockito.mock(RepositorioVehiculos.class);
		this.repositorioFacturas = Mockito.mock(RepositorioFacturas.class);
		this.repositorioParqueadero = Mockito.mock(RepositorioParqueadero.class);
		
		controladorParqueadero.setManejoFechas(manejoDeFechas);
		controladorParqueadero.setRepositorioFacturas(repositorioFacturas);
		controladorParqueadero.setRepositorioVehiculos(repositorioVehiculos);
		controladorParqueadero.setRepositorioParqueaderos(repositorioParqueadero);
	}
	
	@Test
	public void ingresarVehiculoTest() throws Exception {
		//Arrange
		
		Calendar fechaIngreso = Calendar.getInstance();
		
		TipoVehiculo tipoVehiculo = new TipoVehiculo();
		tipoVehiculo.setId(0);
		tipoVehiculo.setNombre("Automovil");
		
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setTipoVehiculo(tipoVehiculo);
		vehiculo.setCilindraje(600);
		vehiculo.setPropietario("Andrew Crespo G");
		vehiculo.setPlaca("ZZZ-990");
		
		Parqueadero parqueadero = new Parqueadero();
		parqueadero.setId("1");
		parqueadero.setNombreParqueadero("Ceiba parking");
		parqueadero.setNumLugaresAutomovilesDisponibles(20);
		parqueadero.setNumLugaresMotocicletasDisponibles(10);
		
		Mockito.when(manejoDeFechas.obtenerFechaActual()).thenReturn(fechaIngreso);
		Mockito.when(repositorioVehiculos.save(vehiculo)).thenReturn(vehiculo);
		Mockito.when(repositorioVehiculos.existsById(vehiculo.getPlaca())).thenReturn(false);
		Mockito.when(repositorioParqueadero.findById("1")).thenReturn( Optional.of(parqueadero));
		
		//Act
		Factura facturaActual = controladorParqueadero.ingresarVehiculo(vehiculo);		
		
		//Assert
		Assert.assertTrue(0d == facturaActual.getValor());
		Assert.assertEquals(fechaIngreso, facturaActual.getFechaIngreso());
		Assert.assertEquals(null, facturaActual.getFechaSalida());
		Assert.assertEquals(0, facturaActual.getHoras());
		Assert.assertEquals(vehiculo, facturaActual.getVehiculo());
	}
	
	// @Test
	public Factura vehiculoYaExisteTest() throws Exception{
		//Arrange
		Vehiculo vehiculo = new Vehiculo();
		TipoVehiculo tipoVehiculo = new TipoVehiculo();
		tipoVehiculo.setId(1);
		tipoVehiculo.setNombre("Motocicleta");
		vehiculo.setPlaca("ERT-123");
		vehiculo.setCilindraje(500);
		vehiculo.setPropietario("Andres");
		vehiculo.setTipoVehiculo(tipoVehiculo);
		Factura factura = new Factura();
		//factura.set
		RepositorioFacturas repositorioFacturas = Mockito.mock(RepositorioFacturas.class);
		RepositorioVehiculos repositorioVehiculos = Mockito.mock(RepositorioVehiculos.class);
		
		ControladorParquederoImpl controladorParqueadero = new ControladorParquederoImpl();
		Mockito.doNothing().when(controladorParqueadero).validarPlacaVehiculo(vehiculo);
		Mockito.doNothing().when(controladorParqueadero).verificarEspaciosDisponibles(vehiculo);
		Mockito.doNothing().when(controladorParqueadero).recalcularEspaciosParqueadero(vehiculo);
		Mockito.when(repositorioVehiculos.save(vehiculo)).thenReturn(vehiculo);
		Mockito.when(repositorioVehiculos.existsById(vehiculo.getPlaca())).thenReturn(false);

		Mockito.when(repositorioFacturas.save(factura)).thenReturn(factura);
		
		//Act
		try {
			factura = controladorParqueadero.ingresarVehiculo(vehiculo);
		} catch (Exception e) {
			fail();
		}
		
		//Assert
		
		
		return null;
	}
	
	// @Test
	public void vehiculoConsultadoPorID() {
		//Arrange
		RepositorioVehiculos repoVehiculos = Mockito.mock(RepositorioVehiculos.class);
		
		//Act
		
		//Assert
		
	}
	
}
