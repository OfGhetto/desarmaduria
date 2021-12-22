package cl.tswoo.lab.app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.tswoo.lab.app.models.Cliente;
import cl.tswoo.lab.app.models.Vendedor;
import cl.tswoo.lab.app.repositories.VendedorRepository;

@Service
public class VendedorServiceImpl implements VendedorService {
	
	@Autowired
	private VendedorRepository vendedorRepository;

	@Override
	public List<Vendedor> findAllVendedores() {
		return vendedorRepository.findAll();
	}

	@Override
	public Vendedor findVendedorById(int idVendedor) {
		Optional<Vendedor> vendedor = vendedorRepository.findById(idVendedor);
		if (vendedor.isPresent()) {
			return vendedor.get();
		} else {
			return null;
		}
	}

	@Override
	public Vendedor findVendedorByNombre(String nombre) {
		ArrayList<Vendedor> vendedores=new ArrayList<>(vendedorRepository.findAll());
		Vendedor buscado=null;
		for(Vendedor c: vendedores) {
			if(c.getNombre().equals(nombre)) {
				buscado=c;
			}
		}
		return buscado;
	}

	@Override
	public Vendedor save(Vendedor vendedor) {
		if (vendedor == null) {
			return null;
		}

		return vendedorRepository.save(vendedor);
	}

	@Override
	public void deleteVendedorById(int idVendedor) {
		// TODO Auto-generated method stub
		vendedorRepository.deleteById(idVendedor);	
	}

}
