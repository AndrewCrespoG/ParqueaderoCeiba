package com.ceiba.parqueadero.modelo;

import org.junit.Assert;
import org.junit.Test;

public class TarifaTests {

	@Test
	public void crearTarifaTest() {
		//Arrange
		
		//Act
		Tarifa tarifa = new Tarifa();
		
		//Assert
		Assert.assertNotNull(tarifa);
	}
}
