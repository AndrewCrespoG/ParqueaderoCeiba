package com.ceiba.parqueadero.comun;

import java.util.Calendar;

import org.springframework.stereotype.Service;

@Service
public class ManejoDeFechas {
	public int calcularHorasEntreDosFechas(Calendar fechaAntigua, Calendar fechaFuturo) {
		int horasCalc = (int) ((fechaFuturo.getTimeInMillis() - fechaAntigua.getTimeInMillis())/1000/60/60);
		System.out.println("Siempre se le va a calcular una hora por lo menos");
		return (horasCalc <= 0) ? 1 : horasCalc;
	}
	
	public Calendar obtenerFechaActual() {
		return Calendar.getInstance();
	}
}
