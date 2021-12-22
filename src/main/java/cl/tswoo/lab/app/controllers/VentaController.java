package cl.tswoo.lab.app.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cl.tswoo.lab.app.models.Articulo;
import cl.tswoo.lab.app.models.Cliente;
import cl.tswoo.lab.app.models.Vendedor;
import cl.tswoo.lab.app.models.Venta;
import cl.tswoo.lab.app.services.ArticuloService;
import cl.tswoo.lab.app.services.VendedorService;
import cl.tswoo.lab.app.services.VentaService;

@Controller
@RequestMapping("/ventas")
public class VentaController {

	@Autowired
	VentaService ventaService;
	
	@Autowired
	ArticuloService articuloService;
	
	@Autowired
	VendedorService vendedorService;
	
	Map<Integer,ArrayList<Articulo>> map=new HashMap<Integer, ArrayList<Articulo>>();
	
	@GetMapping("/gestiona")
	public String gestionaVentas(Model model) {
		
		model.addAttribute("venta", new Venta());
		model.addAttribute("titulo", "Buscar Cliente");
		ArrayList<Venta> lista = new ArrayList<>(ventaService.findAllVentas());
		model.addAttribute("listaArticulos", articuloService.findAllArticulo());
		model.addAttribute("vendedores", vendedorService.findAllVendedores());
		model.addAttribute("ventas", lista);
		model.addAttribute("listTab", "active");
		model.addAttribute("vendedoresTop", ventaService.getVendedoresTop());
		
		//vendedores top.
		
		
		
		//model.addAttribute("flag", true);
		return "ventas/venta-view";
	}
	
	@GetMapping("/codigo")
	public String buscarPorCodigo(@RequestParam String nombre,Model model,@ModelAttribute("venta") Venta venta) {
		  model.addAttribute("listTab", "active");
		if(ventaService.findVentaByCodigo(nombre)!=null) {
			
		  model.addAttribute("ventas",ventaService.findVentaByCodigo(nombre));
		  return "ventas/venta-view";
		}
		  model.addAttribute("ventas",ventaService.findAllVentas());
		  return "ventas/venta-view";
		
		
		  
		//model.addAttribute("flag", true);
		
	}
	@PostMapping({"/","crear"})
	public String anadirVenta( Model model,@Valid Venta venta, BindingResult result) { 
			model.addAttribute("listaArticulos", articuloService.findAllArticulo());
			model.addAttribute("vendedores", vendedorService.findAllVendedores());
			
			/*if(result.hasErrors()) {
				model.addAttribute("ventas",new ArrayList<>(ventaService.findAllVentas()));
				model.addAttribute("formTab", "active");
				return "ventas/venta-view";
				
			}*/
			if(venta.getArticulos().isEmpty()) {
				
				return "error";
			}
			ventaService.relacionaArticulosConVenta(venta.getArticulos(), venta);
			ventaService.save(venta);
			model.addAttribute("venta", new Venta());
			model.addAttribute("listaArt", venta.getArticulos());
			
			//vendedores top
			model.addAttribute("vendedoresTop", ventaService.getVendedoresTop());
			
			model.addAttribute("ventas",new ArrayList<>(ventaService.findAllVentas()));
			model.addAttribute("titulo", "Creacion de cliente");
			model.addAttribute("flag", true);
			model.addAttribute("listTab", "active");
			//map.put(venta.getId(), (ArrayList<Articulo>) venta.getArticulos());
			
	
			return "ventas/venta-view";
	
	}
	
	@GetMapping("/editarVenta/{id}")
	public String updateVenta(Model model, @PathVariable("id") int idVenta){
			Venta venta= ventaService.findVentaById(idVenta);
			model.addAttribute("listaArticulos", articuloService.findAllArticulo());
			model.addAttribute("vendedores", vendedorService.findAllVendedores());
			model.addAttribute("venta", venta);
			model.addAttribute("ventas",new ArrayList<>(ventaService.findAllVentas()));
			model.addAttribute("flag", false);
			model.addAttribute("titulo", "Modificacion de cliente");
			model.addAttribute("vendedoresTop", ventaService.getVendedoresTop());
			model.addAttribute("formTab","active");
			return "ventas/venta-view";
	}
	@GetMapping("/detalle/{id}")
	public String datalleVenta(Model model, @PathVariable("id") int idVenta){
			Venta venta=ventaService.findVentaById(idVenta);
			model.addAttribute("ventasDetalle",venta);
			
			List<Articulo> articulos=ventaService.obtenerArticulos(idVenta);
			model.addAttribute("listaArt",articulos);
			model.addAttribute("listaArticulos", articuloService.findAllArticulo());
			model.addAttribute("vendedores", vendedorService.findAllVendedores());
			model.addAttribute("venta", new Venta());
			model.addAttribute("vendedoresTop", ventaService.getVendedoresTop());
			model.addAttribute("ventas",new ArrayList<>(ventaService.findAllVentas()));
			
			ArrayList<Articulo> articulos2=new ArrayList<>(venta.getArticulos());
			int acum=0;
			
			for(Articulo a: articulos) {
				acum+=a.getPrecioVenta();
			}
			
			model.addAttribute("precioTotal", acum);
			
			
			model.addAttribute("flag", false);
			//model.addAttribute("clientes", clienteService.findAllClientes());
			model.addAttribute("titulo", "Detalle del producto: "+venta.getCodigo());
			model.addAttribute("detalleTab","active");
			
			return "ventas/venta-view";
	}
	
	@GetMapping("/detalleVenta/{id}")
	public String datalleVenta2(Model model, @PathVariable("id") int idVenta){
			Venta venta=ventaService.findVentaById(idVenta);
			model.addAttribute("ventasDetalle",venta);
			model.addAttribute("listaArt",venta.getArticulos());
			model.addAttribute("vendedoresTop", ventaService.getVendedoresTop());
			
			ArrayList<Articulo> articulos=new ArrayList<>(venta.getArticulos());
			int acum=0;
			
			for(Articulo a: articulos) {
				acum+=a.getPrecioVenta();
			}
			
			model.addAttribute("precioTotal", acum);
			/*model.addAttribute("listaArticulos", articuloService.findAllArticulo());
			model.addAttribute("vendedores", vendedorService.findAllVendedores());
			model.addAttribute("venta", new Venta());
			model.addAttribute("ventas",new ArrayList<>(ventaService.findAllVentas()));
			
			model.addAttribute("flag", false);
			//model.addAttribute("clientes", clienteService.findAllClientes());*/
			model.addAttribute("titulo", "Detalle del producto: "+venta.getCodigo());
			model.addAttribute("detalleTab","active");
			
			return "ventas/venta-view";
	}
	
	@GetMapping("eliminarVenta/{id}")
	public String deleteVentaById(Model model ,@PathVariable("id") int idVenta) {
		
		model.addAttribute("listaArticulos", articuloService.findAllArticulo());
		model.addAttribute("vendedores", vendedorService.findAllVendedores());	
		ventaService.deleteVentaById(idVenta);
		model.addAttribute("venta", new Venta());
		model.addAttribute("vendedoresTop", ventaService.getVendedoresTop());
		model.addAttribute("ventas", ventaService.findAllVentas());
		model.addAttribute("listTab", "active");
		return "ventas/venta-view";
	}
	
	
	
	
}
