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

import cl.tswoo.lab.app.models.Bodega;
import cl.tswoo.lab.app.models.Vendedor;
import cl.tswoo.lab.app.models.Venta;
import cl.tswoo.lab.app.services.BodegaService;
import cl.tswoo.lab.app.services.VendedorService;

@Controller
@RequestMapping("/bodegas")
public class BodegaController {
	
	@Autowired
	private BodegaService bodegaService;
	
	@GetMapping("/gestiona")
	public String gestionaBodegas(Model model) {
		
		model.addAttribute("bodega", new Bodega());
		model.addAttribute("titulo", "Buscar Bodega");
		ArrayList<Bodega> lista = new ArrayList<>(bodegaService.findAllBodegas());
		model.addAttribute("bodegas", lista);
		model.addAttribute("listTab", "active");
		
		
		//model.addAttribute("flag", true);
		return "bodegas/bodega-view";
	}
	
	@GetMapping("/nombre")
	public String buscarPorNombre(@RequestParam String nombre,Model model,@ModelAttribute("bodega") Bodega bodega) {
		  model.addAttribute("listTab", "active");
		if(bodegaService.findBodegaByNombre(nombre)!=null) {
			
		  model.addAttribute("bodegas",bodegaService.findBodegaByNombre(nombre));
		  return "bodegas/bodega-view";
		}
		  model.addAttribute("bodegas",bodegaService.findAllBodegas());
		  return "bodegas/bodega-view";
		  
		//model.addAttribute("flag", true);
		
	}
	
	@PostMapping({"/","crear"})
	public String anadirBodegas( Model model,@Valid Bodega bodega, BindingResult result) { 
			if(result.hasErrors()) {
				model.addAttribute("bodegas",new ArrayList<>(bodegaService.findAllBodegas()));
				model.addAttribute("formTab", "active");
				return "bodegas/bodega-view";
				
			}
			bodegaService.save(bodega);
			model.addAttribute("bodega", new Bodega());
			model.addAttribute("bodegas",new ArrayList<>(bodegaService.findAllBodegas()));
			model.addAttribute("titulo", "Creacion de Bodega");
			model.addAttribute("flag", true);
			model.addAttribute("listTab", "active");
			return "bodegas/bodega-view";
	
	}
	
	@GetMapping("/editarBodega/{id}")
	public String updateBodega(Model model, @PathVariable("id") int idBodega){
			Bodega bodega= bodegaService.findBodegaById(idBodega);
			if (bodega == null) {
				model.addAttribute("noCliente", "No existen Bodegas");
				return "error";
			}
			model.addAttribute("bodega", bodega);
			model.addAttribute("bodegas",new ArrayList<>(bodegaService.findAllBodegas()));
			model.addAttribute("flag", false);
			model.addAttribute("titulo", "Modificacion de Bodega");
			model.addAttribute("formTab","active");
			return "bodegas/bodega-view";
	}
	
	@GetMapping("eliminarBodega/{id}")
	public String deleteBodegaById(Model model ,@PathVariable("id") int idBodega) {
		
		bodegaService.deleteBodegaById(idBodega);
		model.addAttribute("bodega", new Bodega());
		model.addAttribute("bodegas", bodegaService.findAllBodegas());
		model.addAttribute("listTab", "active");
		return "bodegas/bodega-view";
	}


}
