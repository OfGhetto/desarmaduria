package cl.tswoo.lab.app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.tswoo.lab.app.models.Cliente;
import cl.tswoo.lab.app.repositories.ClienteRepository;
import cl.tswoo.lab.app.repositories.VehiculoRepository;
import cl.tswoo.lab.app.models.Vehiculo;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private VehiculoRepository vehiculoRepository;
	
	@Override
	public List<Cliente> findAllClientes() {
		return clienteRepository.findAll();
	}
	
	@Override
	public boolean tieneVehiculo(int idCliente) {
		
		ArrayList<Vehiculo> vehiculos=new ArrayList<>(vehiculoRepository.findAll());
		boolean flag=false;
		for(Vehiculo v: vehiculos) {
			flag=(v.getCliente().getId()==idCliente) ? true:false;
		}
		return flag;
		
	}
	
	@Override
	public Cliente findClienteById(int idCliente) {
		Optional<Cliente> cliente = clienteRepository.findById(idCliente);
		if (cliente.isPresent()) {
			return cliente.get();
		} else {
			return null;
		}
		
	}
	@Override
	public Cliente findClienteByNombre(String nombre) {
		ArrayList<Cliente> clientes=new ArrayList<>(clienteRepository.findAll());
		Cliente buscado=null;
		for(Cliente c: clientes) {
			if(c.getNombre().equals(nombre)) {
				buscado=c;
			}
		}
		return buscado;
	}

	@Transactional
	@Override
	public Cliente save(Cliente cliente) {

		if (cliente == null) {
			return null;
		}

		return clienteRepository.save(cliente);

	}

	@Transactional
	@Override
	public void deleteClienteById(int idCliente){
		
			clienteRepository.deleteById(idCliente);
		

	}

}
