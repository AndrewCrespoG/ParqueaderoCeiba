package ejemplo.pruebas;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;
import ejemplo.ejemplo.*;

public class FacturaTest {
	
	@Test
	public void calcularValorFacturaTest() {
		//Assert
		//Propiedades de vehiculo 
		
		int cilindraje = 650;
		String placa = "ABC-123";
		String propietario = "Andrew Crespo";
		int tipoVehiculo = 0;
		
		Vehiculo vehiculo = new Vehiculo(cilindraje, placa, propietario, tipoVehiculo);
		
		Tarifa tarifa = new Tarifa (vehiculo);
		
		//La factura
		Calendar fechaIngreso = Calendar.getInstance();
		//fechaIngreso.add(Calendar.DATE, -1);//Ayer
		Calendar fechaSalida = Calendar.getInstance();
		fechaSalida.add(Calendar.HOUR, 3);//Se cobra un dia
		fechaSalida.add(Calendar.DATE, 1);//Se cobra un dia
		
		double esperado = 11000;
		
		Factura factura = new Factura(vehiculo, fechaIngreso, fechaSalida, tarifa);
		//Act
		double actual = factura.calcularValorFactura();
		System.out.println(actual);
		//Assert
		Assert.assertEquals(esperado, actual, 0);
	}
	
	@Test
	public void calcularNumeroDeHorasEntreDosFechasTest(){
		//Assert
		Calendar hoy = Calendar.getInstance();
		Calendar manana = Calendar.getInstance();
		manana.add(Calendar.DAY_OF_MONTH, 2);
		Factura factura = new Factura(null, hoy, manana, null);
		int esperado = 48;
		
		//Act
		int actual = factura.calcularNumeroDeHorasEntreDosFechas(hoy, manana);
		
		//Assert
		Assert.assertEquals(esperado, actual);
	}
}
