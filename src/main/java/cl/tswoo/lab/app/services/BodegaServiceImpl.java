package cl.tswoo.lab.app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.tswoo.lab.app.models.Articulo;
import cl.tswoo.lab.app.models.Bodega;
import cl.tswoo.lab.app.models.Cliente;
import cl.tswoo.lab.app.repositories.ArticuloRepository;
import cl.tswoo.lab.app.repositories.BodegaRepository;

@Service
public class BodegaServiceImpl implements BodegaService{
	
	@Autowired
	private BodegaRepository bodegaRepository;

	@Override
	public List<Bodega> findAllBodegas() {
		return bodegaRepository.findAll();
	}

	@Override
	public Bodega findBodegaById(int idBodega) {
		Optional<Bodega> bodega = bodegaRepository.findById(idBodega);
		if (bodega.isPresent()) {
			return bodega.get();
		} else {
			return null;
		}
	}

	@Override
	public void save(Bodega bodega) {
		// TODO Auto-generated method stub
		bodegaRepository.save(bodega);
		
	}

	@Override
	public void deleteBodegaById(int idBodega) {
		// TODO Auto-generated method stub
		bodegaRepository.deleteById(idBodega);
		
	}

	@Override
	public ArrayList<Bodega> findBodegaByNombre(String nombre) {
	
		ArrayList<Bodega> bodegas=new ArrayList<>(bodegaRepository.findAll());
		
		
		ArrayList<Bodega> buscados=new ArrayList<Bodega>();
		
		for(Bodega c: bodegas) {
			if(c.getNombre().equals(nombre)) {
				buscados.add(c);
			}
		}
		return buscados.isEmpty()?null:buscados;
		
	}
	
	

}
