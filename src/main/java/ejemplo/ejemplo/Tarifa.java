package ejemplo.ejemplo;

public class Tarifa {
	
	//Tabla de tarifas
	private static final double VALOR_DIA_AUTOMOVIL = 8000;
	private static final double VALOR_HORA_AUTOMOVIL = 1000;
	private static final double VALOR_DIA_MOTO = 4000;
	private static final double VALOR_HORA_MOTO = 500;
	private static final double VALOR_ADICIONAL_MOTO_POR_CILINDRAJE = 2000;
	private static final int CILINDRAJE_MINIMO_PAGO_ADICIONAL = 500;
	private static final double SIN_VALOR_ADICIONAL = 0;
	private static final int ES_MOTO = 1;
	private static final int ES_AUTOMOVIL = 0;
	
	//Fin tabla tarifas
	
	private double valorPorHora;
	private double valorPorDia;
	private double valorAdicionalAMotoPorCilindraje;
	
	public Tarifa () {
		
	}
	
	public Tarifa(Vehiculo vehiculo) {
		//Calcular valor por hora
		valorPorHora = calcularValorPorHora(vehiculo);
		valorPorDia = calcularValorPorDia(vehiculo);
		valorAdicionalAMotoPorCilindraje = calcularValorAdicionalPorCilindraje(vehiculo);
	}
	
	public double calcularValorAdicionalPorCilindraje(Vehiculo vehiculo) {
		return ((vehiculo.getTipoVehiculo() == ES_MOTO) &&
				(vehiculo.getCilindraje() >= CILINDRAJE_MINIMO_PAGO_ADICIONAL)) ?
						VALOR_ADICIONAL_MOTO_POR_CILINDRAJE : SIN_VALOR_ADICIONAL;
	}
	
	public double calcularValorPorDia(Vehiculo vehiculo) {
		return (vehiculo.getTipoVehiculo() == ES_AUTOMOVIL) ? VALOR_DIA_AUTOMOVIL: VALOR_DIA_MOTO;
	}
	
	public double calcularValorPorHora(Vehiculo vehiculo) {
		return (vehiculo.getTipoVehiculo() == ES_AUTOMOVIL) ? VALOR_HORA_AUTOMOVIL : VALOR_HORA_MOTO ;
	}

	public double getValorPorHora() {
		return valorPorHora;
	}

	public void setValorPorHora(double valorPorHora) {
		this.valorPorHora = valorPorHora;
	}

	public double getValorPorDia() {
		return valorPorDia;
	}

	public void setValorPorDia(double valorPorDia) {
		this.valorPorDia = valorPorDia;
	}

	public double getValorAdicionalAMotoPorCilindraje() {
		return valorAdicionalAMotoPorCilindraje;
	}

	public void setValorAdicionalAMotoPorCilindraje(double valorAdicionalAMotoPorCilindraje) {
		this.valorAdicionalAMotoPorCilindraje = valorAdicionalAMotoPorCilindraje;
	}
	
}