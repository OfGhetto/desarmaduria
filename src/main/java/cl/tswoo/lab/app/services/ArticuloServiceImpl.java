package cl.tswoo.lab.app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.tswoo.lab.app.models.Articulo;
import cl.tswoo.lab.app.models.Cliente;
import cl.tswoo.lab.app.models.Vehiculo;
import cl.tswoo.lab.app.repositories.ArticuloRepository;

@Service
public class ArticuloServiceImpl implements ArticuloService {
	
	@Autowired
	private ArticuloRepository articuloRepository;

	@Override
	public List<Articulo> findAllArticulo() {
		// TODO Auto-generated method stub
		List<Articulo> lista=articuloRepository.findAll();
		
		List<Articulo> listaVacia=new ArrayList<Articulo>();
		
		List<Articulo> buscados=new ArrayList<Articulo>();
		for(Articulo a: lista) {
			if(a.getVenta()==null) {
				buscados.add(a);
			}
		}
		
		return buscados.isEmpty()?listaVacia:buscados;
	}

	
	
	@Override
	public Articulo findArticuloById(int idArticulo) {
		Optional<Articulo> articulo = articuloRepository.findById(idArticulo);
		if (articulo.isPresent()) {
			return articulo.get();
		} else {
			return null;
		}
	}

	@Override
	public void save(Articulo articulo) {
		// TODO Auto-generated method stub
		articuloRepository.save(articulo);
		
	}

	@Override
	public void deleteArticuloById(int idArticulo) {
		// TODO Auto-generated method stub
		articuloRepository.deleteById(idArticulo);
		
	}
	@Override
	public ArrayList<Articulo> findArticuloByNombre(String nombre) {
		ArrayList<Articulo> articulos=new ArrayList<>(articuloRepository.findAll());
		
		
		ArrayList<Articulo> buscados=new ArrayList<>();
		
		for(Articulo c: articulos) {
			if(c.getNombre().equals(nombre)) {
				buscados.add(c);
			}
		}
		return buscados.isEmpty()?null:buscados;
	}
	@Override
	public ArrayList<Articulo> findArticuloByBodega(String nombre) {
		ArrayList<Articulo> articulos=new ArrayList<>(articuloRepository.findAll());
		
		
		ArrayList<Articulo> buscados=new ArrayList<>();
		
		for(Articulo c: articulos) {
			if(c.getBodega().getNombre().equals(nombre)) {
				buscados.add(c);
			}
		}
		return buscados.isEmpty()?null:buscados;
	}
	

}
