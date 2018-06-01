package com.ceiba.parqueadero.repositorio;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.ceiba.parqueadero.modelo.Factura;


public interface RepositorioFacturas extends CrudRepository <Factura, String>{
	
	Optional<Factura> findByVehiculoPlaca(String placa);

}
