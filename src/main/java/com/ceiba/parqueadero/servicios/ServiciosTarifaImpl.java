package com.ceiba.parqueadero.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ceiba.parqueadero.modelo.Tarifa;
import com.ceiba.parqueadero.repositorio.RepositorioTarifas;

@RequestMapping(value="/parqueadero/tarifas")
@RestController
public class ServiciosTarifaImpl implements ServiciosTarifa {
	
	@Autowired
	RepositorioTarifas repositorioTarifas;

	@Override
	@RequestMapping(value="/listar-tarifas", method = RequestMethod.GET)
	public List<Tarifa> listarTarifas() {
		List<Tarifa> tarifas = new ArrayList<>();
		repositorioTarifas.findAll()
		.forEach(tarifas::add);
		return tarifas;
	}

	@Override
	@RequestMapping(value="/ingresar-tarifa", method = RequestMethod.POST)
	public void ingresarTarifa(Tarifa tarifa) {
		repositorioTarifas.save(tarifa);
	}

}
