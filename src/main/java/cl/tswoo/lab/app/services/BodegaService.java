package cl.tswoo.lab.app.services;

import java.util.ArrayList;
import java.util.List;

import cl.tswoo.lab.app.models.Articulo;
import cl.tswoo.lab.app.models.Bodega;

public interface BodegaService {
	
	List<Bodega> findAllBodegas();

	Bodega findBodegaById(int idBodega);
	
	void save(Bodega bodega);

	void deleteBodegaById(int idBodega);
	ArrayList<Bodega> findBodegaByNombre(String nombre);
	//ArrayList<Articulo> findArticuloByBodega(String nombre);


}
