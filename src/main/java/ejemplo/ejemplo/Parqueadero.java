package ejemplo.ejemplo;

import java.util.Calendar;

public class Parqueadero {
	
	private String nombreParqueadero;
	private Factura factura;
	
	private static final boolean MENSAJE_INGRESO_INVALIDO = false;
	private static final boolean MENSAJE_INGRESO_EXITOSO = true;
	
	public Parqueadero(String nombreParqueadero) {
		this.nombreParqueadero = nombreParqueadero;
	}

	public Parqueadero() {
	}

	public boolean intentarIngresarVehiculoAlParqueadero(Vehiculo vehiculo, Calendar fechaIngreso) {
		//Si la placa comienza por A
		
		if (!vehiculo.getPlaca().startsWith("A")) {//Si la placa no comienza por A ingresa
			ingresarVehiculoAlParqueadero(vehiculo, fechaIngreso);
			System.out.println("Entra porque no comienza por A");
			return MENSAJE_INGRESO_EXITOSO;
		}else if(vehiculo.getPlaca().startsWith("A") &&
				(fechaIngreso.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ||
				fechaIngreso.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY)) { //Si comienza por A y es domingo o lunes ingresa
			ingresarVehiculoAlParqueadero(vehiculo, fechaIngreso);
			System.out.println("Entra porque comienza por A y es lunes o domingo");
			return MENSAJE_INGRESO_EXITOSO;
		}else if(vehiculo.getPlaca().startsWith("A") &&
				!(fechaIngreso.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ||
				fechaIngreso.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY)) {//Empieza por A pero no es lunes ni domingo no entra
			System.out.println("No entra porque comienza por A y no es lunes o domingo");
			return MENSAJE_INGRESO_INVALIDO;
		}
		
		return false;
	}
	
	public void ingresarVehiculoAlParqueadero(Vehiculo vehiculo, Calendar fechaIngreso) {
		//Consulta si se puede hacer la insercion (si el parqueadero no esta lleno)
		//Ejecuta la inserción en la base de datos
	}
	
	public String primeraLetraDeCadena(String cadena) {
		return Character.toString(cadena.charAt(0));
	}
	
	public String getNombreParqueadero() {
		return nombreParqueadero;
	}

	public void setNombreParqueadero(String nombreParqueadero) {
		this.nombreParqueadero = nombreParqueadero;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}
	
}
