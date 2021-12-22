package cl.tswoo.lab.app.services;

import java.util.List;

import cl.tswoo.lab.app.models.Cliente;

public interface ClienteService {
	

	List<Cliente> findAllClientes();

	Cliente findClienteById(int idCliente);
	
	Cliente findClienteByNombre(String nombre);

	Cliente save(Cliente cliente);

	void deleteClienteById(int idCliente);
	
	 boolean tieneVehiculo(int idCliente);

}
