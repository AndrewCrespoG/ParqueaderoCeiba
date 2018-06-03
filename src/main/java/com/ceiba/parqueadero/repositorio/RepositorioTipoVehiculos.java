package com.ceiba.parqueadero.repositorio;

import org.springframework.data.repository.CrudRepository;
import com.ceiba.parqueadero.modelo.TipoVehiculo;

public interface RepositorioTipoVehiculos extends CrudRepository<TipoVehiculo, Integer>{

}
