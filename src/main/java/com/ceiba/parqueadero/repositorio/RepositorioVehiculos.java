package com.ceiba.parqueadero.repositorio;
import org.springframework.data.repository.CrudRepository;

import com.ceiba.parqueadero.modelo.Vehiculo;


public interface RepositorioVehiculos extends CrudRepository <Vehiculo, String>{

	
}
