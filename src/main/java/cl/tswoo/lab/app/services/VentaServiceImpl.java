package cl.tswoo.lab.app.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.tswoo.lab.app.models.Articulo;
import cl.tswoo.lab.app.models.Vendedor;
import cl.tswoo.lab.app.models.Venta;
import cl.tswoo.lab.app.repositories.ArticuloRepository;
import cl.tswoo.lab.app.repositories.VendedorRepository;
import cl.tswoo.lab.app.repositories.VentaRepository;
@Service
public class VentaServiceImpl implements VentaService{
	
	@Autowired
	VentaRepository ventaRepository;
	
	@Autowired
	ArticuloRepository articuloRepository;
	
	@Autowired
	VendedorService vendedorService;
	
	
	@Autowired
	VendedorRepository vendedorRepository;

	@Override
	public List<Venta> findAllVentas() {
		// TODO Auto-generated method stub
		return ventaRepository.findAll();
	}

	@Override
	public Venta findVentaById(int idVenta) {
		Optional<Venta> venta = ventaRepository.findById(idVenta);
		if (venta.isPresent()) {
			return venta.get();
		} else {
			return null;
		}
	}

	@Override
	public void save(Venta venta) {
		// TODO Auto-generated method stub
		ventaRepository.save(venta);
		
	}

	@Override
	public void deleteVentaById(int idVenta) {
		// TODO Auto-generated method stub
		ventaRepository.deleteById(idVenta);
		
	}

	@Override
	public ArrayList<Venta> findVentaByCodigo(String codigo) {
		ArrayList<Venta> ventas=new ArrayList<>(ventaRepository.findAll());
		
		
		ArrayList<Venta> buscados=new ArrayList<>();
		
		for(Venta c: ventas) {
			if(c.getCodigo().equals(codigo)) {
				buscados.add(c);
			}
		}
		return buscados.isEmpty()?null:buscados;
	}

	@Override
	public void relacionaArticulosConVenta(List<Articulo> lista, Venta venta) {
		// TODO Auto-generated method stub
		
		for(Articulo a: lista) {
			a.setVenta(venta);
			
		}
		
	}

	@Override
	public List<Articulo> obtenerArticulos(int idVenta) {
		// TODO Auto-generated method stub
	   List<Articulo> articulos=articuloRepository.findAll();
	   
	   List<Articulo> buscados=new ArrayList<Articulo>();
	   
	   for (Articulo a: articulos) {
		   if(a.getVenta()!=null) {
		   
		   	if(a.getVenta().getId()==idVenta) {
		   		buscados.add(a);
		   	}
		   }
	   }
		
	   return buscados.isEmpty()?null:buscados;
	}

	@Override
	public  List<Vendedor>  getVendedoresTop(){
		List<Vendedor> vendedores= vendedorRepository.findAll();
		List<Vendedor> vendedorAux=new ArrayList<Vendedor>();
		List<Integer> lista=new ArrayList<Integer>();
		Map<Integer, Vendedor> map=new HashMap<Integer, Vendedor>();
		Set<Integer> claves= new HashSet<Integer>();
		
		
			for(Vendedor vendedor: vendedores) {
				if(vendedor.getVentas()!=null) {
					if(map.get(vendedor.getVentas())==null) {
					map.put(vendedor.getVentas().size(),vendedor);
					lista.add(vendedor.getVentas().size());
					}
				}
			}
			int val=0;
			for(int i=0;i<5; i++) {
				if(lista.size()!=0) {
					val=Collections.max(lista);
					lista.remove(val);
					vendedorAux.add(map.get(val));
				}
			}
			
		
		
		return vendedorAux.size()==0?null:vendedorAux;
	}
	
	private List<Vendedor> obtieneVendedoresConVentas(){
		List<Venta> ventas=ventaRepository.findAll();
		List<Vendedor> vendedores=new ArrayList<Vendedor>();
		
		
		
		for(Venta venta: ventas) {
			vendedores.add(vendedorService.findVendedorById(venta.getVendedor().getId()));
			
		}
		
		return vendedores.size()==0?null:vendedores;
	}
	

}
