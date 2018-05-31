package com.ceiba.parqueadero.repositorio;

import org.springframework.data.repository.CrudRepository;
import com.ceiba.parqueadero.modelo.Parqueadero;

public interface RepositorioParqueadero extends CrudRepository <Parqueadero, String> {


}
