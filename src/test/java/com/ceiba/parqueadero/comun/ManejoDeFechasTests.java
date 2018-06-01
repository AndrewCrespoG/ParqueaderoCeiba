package com.ceiba.parqueadero.comun;

import java.util.Calendar;

import org.junit.Test;
import org.junit.Assert;

public class ManejoDeFechasTests {
	@Test
	public void calcularHorasEntreDosFechasUnDiaTest() {
		//Arrange
		Calendar fechaAntigua = Calendar.getInstance();
		fechaAntigua.add(Calendar.DATE, -1);
		Calendar fechaFuturo = Calendar.getInstance();
		int esperado = 24;
		//Act
		int actual = ManejoDeFechas.calcularHorasEntreDosFechas(fechaAntigua, fechaFuturo);
		//Assert
		Assert.assertEquals(esperado, actual);
	}
	@Test
	public void calcularHorasEntreDosFechasUnaHoraTest() {
		//Arrange
		Calendar fAntigua = Calendar.getInstance();
		fAntigua.add(Calendar.HOUR, -1);
		Calendar fFuturo = Calendar.getInstance();
		int esperado = 1;
		//Act
		int actual = ManejoDeFechas.calcularHorasEntreDosFechas(fAntigua, fFuturo);
		//Assert
		Assert.assertEquals(esperado, actual);
	} 
}
