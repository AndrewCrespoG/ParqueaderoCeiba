package com.ceiba.parqueadero.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ceiba.parqueadero.modelo.Tarifa;

public interface RepositorioTarifas extends CrudRepository<Tarifa, String> {
	
	Optional<List<Tarifa>> findByTipoVehiculo(int tipoVehiculo);

}
