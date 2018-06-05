package ejemplo.controladores;

import java.util.Calendar;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ejemplo.ejemplo.Factura;
import ejemplo.ejemplo.Parqueadero;
import ejemplo.ejemplo.Tarifa;
import ejemplo.ejemplo.Vehiculo;

@RestController
public class ParqueaderoController {
	@RequestMapping("/mostrarFactura")
	public Factura mostrarFactura() {
		//Creando el parqueadero
		Parqueadero parqueadero = new Parqueadero("Parqueadero donde Andre");
		Vehiculo vehiculo = new Vehiculo(650, "ABC-123", "AndrewCrespo", 0);
		Tarifa tarifa = new Tarifa (vehiculo);
		Calendar salida = Calendar.getInstance();
		Calendar entrada = Calendar.getInstance();
		entrada.add(Calendar.DATE, -1);
		Factura factura = new Factura(vehiculo, entrada, salida, tarifa);
		factura.calcularValorFactura();
		return factura;
	}
}
