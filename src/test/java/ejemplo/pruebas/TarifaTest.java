package ejemplo.pruebas;

import org.junit.Test;
import org.junit.Assert;

import ejemplo.ejemplo.*;

public class TarifaTest {
	@Test
	public void crearTarifaTest() {
		//Arrange
		int cilindraje = 200;
		String placa = "ABC-123";
		String propietario = "Andrew Crespo";
		int tipoVehiculo = 1;
		
		//Act
		Vehiculo vehiculo = new Vehiculo(cilindraje, placa, propietario, tipoVehiculo);
		
		//Assert
		Assert.assertNotNull(vehiculo);
	}
	
	@Test
	public void calcularValorAdicionalPorCilindrajeSiEsMotoTest() {
		//Arrange
		int cilindraje = 650;
		int tipoVehiculo = 1;
		Vehiculo vehiculo = new Vehiculo(cilindraje, null, null, tipoVehiculo);
		Tarifa tarifa = new Tarifa();
		double esperado = 2000;

		//Act
		double actual = tarifa.calcularValorAdicionalPorCilindraje(vehiculo);
		
		//Assert (Es moto)
		Assert.assertEquals(esperado, actual, 0);
	}
	
	@Test
	public void calcularValorPorDiaTest() {
		//Arrange
		int tipoVehiculo = 0;
		Vehiculo vehiculo = new Vehiculo(0, null, null, tipoVehiculo);
		Tarifa tarifa = new Tarifa();
		double esperado = 8000;
		
		//Act
		double actual = tarifa.calcularValorPorDia(vehiculo);
		
		//Assert
		Assert.assertEquals(esperado, actual, 0);
		
	}
	
	@Test
	public void calcularValorPorHoraTest() {
		//Arrange
		int tipoVehiculo = 1;
		Vehiculo vehiculo = new Vehiculo(0, null, null, tipoVehiculo);
		Tarifa tarifa = new Tarifa();
		double esperado = 500;
		
		//Act
		double actual = tarifa.calcularValorPorHora(vehiculo);
		
		//Assert
		Assert.assertEquals(esperado, actual, 0);
		
	}
}
