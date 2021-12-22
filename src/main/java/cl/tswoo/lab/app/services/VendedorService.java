package cl.tswoo.lab.app.services;

import java.util.List;

import cl.tswoo.lab.app.models.Vendedor;



public interface VendedorService {
	
	List<Vendedor> findAllVendedores();

	Vendedor findVendedorById(int idVendedor);
	
	Vendedor findVendedorByNombre(String nombre);

	Vendedor save(Vendedor cliente);

	void deleteVendedorById(int idVendedor);
	

}
