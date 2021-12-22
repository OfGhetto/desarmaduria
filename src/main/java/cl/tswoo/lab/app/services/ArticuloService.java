package cl.tswoo.lab.app.services;

import java.util.ArrayList;
import java.util.List;

import cl.tswoo.lab.app.models.Articulo;
import cl.tswoo.lab.app.models.Cliente;
import cl.tswoo.lab.app.models.Vehiculo;

public interface ArticuloService {
	
	List<Articulo> findAllArticulo();

	Articulo findArticuloById(int idArticulo);
	
	void save(Articulo articulo);

	void deleteArticuloById(int idArticulo);
	ArrayList<Articulo> findArticuloByNombre(String nombre);
	ArrayList<Articulo> findArticuloByBodega(String nombre);


}
