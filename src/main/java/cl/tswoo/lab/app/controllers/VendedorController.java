package cl.tswoo.lab.app.controllers;

import java.util.ArrayList;

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

import cl.tswoo.lab.app.models.Cliente;
import cl.tswoo.lab.app.models.Vehiculo;
import cl.tswoo.lab.app.models.Vendedor;
import cl.tswoo.lab.app.models.Venta;
import cl.tswoo.lab.app.services.VendedorService;
import cl.tswoo.lab.app.services.VentaService;

@Controller
@RequestMapping("/vendedores")
public class VendedorController {
	
	@Autowired
	private VendedorService vendedorService;
	
	@Autowired
	private VentaService ventaService;
	
	@GetMapping("/gestiona")
	public String gestionaVendedores(Model model) {
		
		model.addAttribute("vendedor", new Vendedor());
		model.addAttribute("titulo", "Buscar Cliente");
		ArrayList<Vendedor> lista = new ArrayList<>(vendedorService.findAllVendedores());
		model.addAttribute("vendedores", lista);
		model.addAttribute("listTab", "active");
		
		
		//model.addAttribute("flag", true);
		return "vendedores/vendedor-view";
	}
	
	@GetMapping("/nombre")
	public String buscarPorNombre(@RequestParam String nombre,Model model,@ModelAttribute("vendedor") Vendedor vendedor) {
		  model.addAttribute("listTab", "active");
		if(vendedorService.findVendedorByNombre(nombre)!=null) {
			
		  model.addAttribute("vendedores",vendedorService.findVendedorByNombre(nombre));
		  return "vendedores/vendedor-view";
		}
		  model.addAttribute("vendedores",vendedorService.findAllVendedores());
		  return "vendedores/vendedor-view";
		  
		//model.addAttribute("flag", true);
		
	}
	@PostMapping({"/","crear"})
	public String anadirVendedores( Model model,@Valid Vendedor vendedor, BindingResult result) { 
			if(result.hasErrors()) {
				model.addAttribute("vendedores",new ArrayList<>(vendedorService.findAllVendedores()));
				model.addAttribute("formTab", "active");
				return "vendedores/vendedor-view";
				
			}
			vendedorService.save(vendedor);
			model.addAttribute("vendedor", new Vendedor());
			model.addAttribute("vendedores",new ArrayList<>(vendedorService.findAllVendedores()));
			model.addAttribute("titulo", "Creacion de cliente");
			model.addAttribute("flag", true);
			model.addAttribute("listTab", "active");
			return "vendedores/vendedor-view";
	
	}
	@GetMapping("/editarVendedor/{id}")
	public String updateVendedor(Model model, @PathVariable("id") int idVendedor){
			Vendedor vendedor= vendedorService.findVendedorById(idVendedor);
			if (vendedor == null) {
				model.addAttribute("noCliente", "No existen clientes");
				return "error";
			}
			model.addAttribute("vendedor", vendedor);
			model.addAttribute("vendedores",new ArrayList<>(vendedorService.findAllVendedores()));
			model.addAttribute("flag", false);
			model.addAttribute("titulo", "Modificacion de cliente");
			model.addAttribute("formTab","active");
			return "vendedores/vendedor-view";
	}
	@GetMapping("eliminarVendedor/{id}")
	public String deleteVendedorById(Model model ,@PathVariable("id") int idVendedor) {
		
		ArrayList<Venta> listaVentas=new ArrayList<>(ventaService.findAllVentas());
		vendedorService.deleteVendedorById(idVendedor);
		model.addAttribute("vendedor", new Vendedor());
		model.addAttribute("vendedores", vendedorService.findAllVendedores());
		model.addAttribute("listTab", "active");
		return "vendedores/vendedor-view";
	}

}
