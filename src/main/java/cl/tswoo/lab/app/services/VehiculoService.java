package cl.tswoo.lab.app.services;
import java.util.List;

import cl.tswoo.lab.app.models.Vehiculo;

public interface VehiculoService  {
	
	List<Vehiculo> findAllVehiculo();

	Vehiculo findVehiculoById(int idVehiculo);
	
	List<Vehiculo> findVehiculosPorMarca(String marca);
	void save(Vehiculo vehiculo);

	void deleteVehiculoById(int idVehiculo);
}
