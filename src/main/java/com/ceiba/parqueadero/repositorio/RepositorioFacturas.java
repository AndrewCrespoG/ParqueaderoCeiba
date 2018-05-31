package com.ceiba.parqueadero.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.ceiba.parqueadero.modelo.Factura;
import com.ceiba.parqueadero.modelo.Tarifa;

public interface RepositorioFacturas extends CrudRepository <Factura, String>{
	
	Optional<Factura> findByVehiculoPlaca(String placa);

}
