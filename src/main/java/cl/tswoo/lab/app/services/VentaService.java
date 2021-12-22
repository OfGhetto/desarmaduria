package cl.tswoo.lab.app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cl.tswoo.lab.app.models.Articulo;
import cl.tswoo.lab.app.models.Vendedor;
import cl.tswoo.lab.app.models.Venta;



public interface VentaService {
	
	List<Venta> findAllVentas();

	Venta findVentaById(int idVenta);
	
	void save(Venta venta);
	
	List<Vendedor> getVendedoresTop();

	void deleteVentaById(int idVenta);
	ArrayList<Venta> findVentaByCodigo(String codigo);
	
	void relacionaArticulosConVenta(List<Articulo> lista, Venta venta);
	
	List<Articulo> obtenerArticulos(int idVenta);


}
