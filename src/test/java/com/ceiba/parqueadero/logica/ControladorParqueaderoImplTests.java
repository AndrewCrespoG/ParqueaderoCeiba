package com.ceiba.parqueadero.logica;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import com.ceiba.parqueadero.modelo.Factura;
import com.ceiba.parqueadero.modelo.TipoVehiculo;
import com.ceiba.parqueadero.modelo.Vehiculo;
import com.ceiba.parqueadero.logica.ControladorParquederoImpl;

public class ControladorParqueaderoImplTests {

	@Test
	public void ingresarVehiculoTest() throws Exception {
		//Arrange
		ControladorParquederoImpl controladorParqueadero = new ControladorParquederoImpl();
		
		TipoVehiculo tipoVehiculo = new TipoVehiculo();
		tipoVehiculo.setId(0);
		tipoVehiculo.setNombre("Automovil");
		
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setTipoVehiculo(tipoVehiculo);
		vehiculo.setCilindraje(600);
		vehiculo.setPropietario("Andrew Crespo G");
		vehiculo.setPlaca("ZZZ-990");
		
		Factura facturaEsperada = new Factura();
		Calendar fechaEntrada = null;
		facturaEsperada.setFechaIngreso(fechaEntrada);
		facturaEsperada.setFechaSalida(null);
		facturaEsperada.setHoras(0);
		facturaEsperada.setValor(0);
		facturaEsperada.setVehiculo(vehiculo);
		
		//Act
		Factura facturaActual = controladorParqueadero.ingresarVehiculo(vehiculo);
		facturaActual.setFechaIngreso(fechaEntrada);
		
		//Assert
		Assert.assertEquals(facturaEsperada, facturaActual);
	}
}
