package com.ceiba.parqueadero.repositorio;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.ceiba.parqueadero.modelo.Tarifa;
import com.ceiba.parqueadero.modelo.TipoVehiculo;

public interface RepositorioTarifas extends CrudRepository<Tarifa, String> {
	
	Optional<Tarifa> findByTipoVehiculo(TipoVehiculo tipoVehiculo);
}
