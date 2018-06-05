package ejemplo.ejemplo;

import java.util.Calendar;

public class Factura {
	
	private double valorFactura;
	private Calendar ingresoVehiculo;
	private Calendar salidaVehiculo;
	private Tarifa tarifa;
	private Vehiculo vehiculo;
	
	public Factura() {
	}
	
	public double calcularValorFactura() {
		int horasPorCalcular = calcularNumeroDeHorasEntreDosFechas(this.ingresoVehiculo, this.salidaVehiculo);
		
		int diasPorFacturar = 0;
		int horasPorFacturar = 0;
		
		while(horasPorCalcular > 0) {
			if (horasPorCalcular >= 9 && horasPorCalcular > 24) {
				//Resta 24 hrs a la pila y suma un dia
				horasPorCalcular = horasPorCalcular - 24;
				diasPorFacturar = diasPorFacturar + 1;
			} else if(horasPorCalcular >= 9 && horasPorCalcular <= 24) {
				//Hace que la pila de horas sea cero y agrega un dia
				horasPorCalcular = 0;
				diasPorFacturar = diasPorFacturar + 1;
			} else if (horasPorCalcular < 9) {
				//Sumar las horas restantes de la pila a las horas por facturar
				horasPorFacturar = horasPorCalcular;
				horasPorCalcular = 0;
			}
		}
		
		double total;
		total = (diasPorFacturar * this.tarifa.getValorPorDia()) +
				(horasPorFacturar * this.tarifa.getValorPorHora()) +
				this.tarifa.getValorAdicionalAMotoPorCilindraje();
		
		this.valorFactura = total;
		
		return total;
	}
	
	public int calcularNumeroDeHorasEntreDosFechas(Calendar antes, Calendar despues) {
		int horasCalc = (int) ((despues.getTimeInMillis() - antes.getTimeInMillis())/1000/60/60);
		return (horasCalc < 1) ? (1) : (horasCalc);
	}

	public Factura(Vehiculo vehiculo, Calendar ingresoVehiculo, Calendar salidaVehiculo, Tarifa tarifa) {
		this.vehiculo = vehiculo;
		this.ingresoVehiculo = ingresoVehiculo;
		this.salidaVehiculo = salidaVehiculo;
		this.tarifa = tarifa;	
	}

	public Calendar getIngresoVehiculo() {
		return ingresoVehiculo;
	}

	public void setIngresoVehiculo(Calendar ingresoVehiculo) {
		this.ingresoVehiculo = ingresoVehiculo;
	}

	public Calendar getSalidaVehiculo() {
		return salidaVehiculo;
	}

	public void setSalidaVehiculo(Calendar salidaVehiculo) {
		this.salidaVehiculo = salidaVehiculo;
	}
	
	
}
