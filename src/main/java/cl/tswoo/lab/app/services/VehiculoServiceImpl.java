package cl.tswoo.lab.app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.tswoo.lab.app.models.Vehiculo;
import cl.tswoo.lab.app.repositories.VehiculoRepository;

@Service
public class VehiculoServiceImpl implements VehiculoService {
	
	@Autowired
	private VehiculoRepository vehiculoRepository;

	@Override
	public List<Vehiculo> findAllVehiculo() {
		return vehiculoRepository.findAll();
	}
	@Override
	public ArrayList<Vehiculo> findVehiculosPorMarca(String marca){
		ArrayList<Vehiculo> vehiculos=new ArrayList<Vehiculo>(vehiculoRepository.findAll());
		ArrayList<Vehiculo>vehiculosABuscar=new ArrayList<>();
		
		for(Vehiculo v: vehiculos) {
			if(v.getMarca().equals(marca)) {
				vehiculosABuscar.add(v);
			}
		}
		return vehiculosABuscar.isEmpty()?null:vehiculosABuscar;
		
	}

	@Override
	public Vehiculo findVehiculoById(int idVehiculo){
		Optional<Vehiculo> vehiculo = vehiculoRepository.findById(idVehiculo);
		if (vehiculo.isPresent()) {
			return vehiculo.get();
		} else {
			return null;
		}
	}
	
	@Transactional
	@Override
	public void save(Vehiculo vehiculo){
		
		vehiculoRepository.save(vehiculo);
		
	}
	
	@Transactional
	@Override
	public void deleteVehiculoById(int idVehiculo){

			vehiculoRepository.deleteById(idVehiculo);	
		
	}

}
