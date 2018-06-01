package com.ceiba.parqueadero.modelo;

import org.junit.Assert;
import org.junit.Test;

public class ParqueaderoTests {

	@Test
	public void crearParqueaderoTest() {
		//Arrange
		
		//Act
		Parqueadero parqueadero = new Parqueadero();
		
		//Assert
		Assert.assertNotNull(parqueadero);
	}
}
