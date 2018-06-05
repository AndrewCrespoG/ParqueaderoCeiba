package ejemplo.controladores;

import ejemplo.ejemplo.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.Calendar;

@RestController
public class FacturaController {
	
	@RequestMapping("/factura")
	public Factura getFactura(){
		//Creando y mostrando una Factura en "/mostrarFactura"
		
		return null;
	}
	
}
