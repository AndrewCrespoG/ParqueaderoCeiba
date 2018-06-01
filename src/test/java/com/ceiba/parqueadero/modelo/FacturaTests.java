package com.ceiba.parqueadero.modelo;

import org.junit.Test;
import org.junit.Assert;

public class FacturaTests {

	@Test
	public void crearVehiculoTest() {
		//Arrange
		
		//Act
		Factura factura = new Factura();
		//Assert
		Assert.assertNotNull(factura);
	}
}
