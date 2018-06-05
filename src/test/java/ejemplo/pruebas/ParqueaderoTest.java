package ejemplo.pruebas;

import ejemplo.ejemplo.Parqueadero;
import ejemplo.ejemplo.Vehiculo;

import org.junit.Test;

import java.util.Calendar;

import org.junit.Assert;

public class ParqueaderoTest {
	@Test
	public void primeraLetraDeCadenaTest() {
		//Arrange
		String cadena = "ABC";
		String esperado = "A";
		Parqueadero parqueadero = new Parqueadero();
		//Act
		String actual = parqueadero.primeraLetraDeCadena(cadena);
		//Assert
		Assert.assertEquals(esperado, actual);
	}
	
	@Test
	public void intentarIngresarVehiculoAlParqueaderoTest(){
		Parqueadero parqueadero = new Parqueadero();
		
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setPlaca("ABC-123");
		
		parqueadero.intentarIngresarVehiculoAlParqueadero(vehiculo, null);
		
	}
}
