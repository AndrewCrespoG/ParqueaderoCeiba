package com.ceiba.parqueadero.modelo;

import org.junit.Assert;
import org.junit.Test;

public class VehiculoTests {

	@Test
	public void crearVehiculoTest() {
		//Arrange
		
		//Act
		Vehiculo vehiculo = new Vehiculo();
		
		//Assert
		Assert.assertNotNull(vehiculo);
	}
}
