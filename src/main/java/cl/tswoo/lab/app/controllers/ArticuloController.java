package cl.tswoo.lab.app.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.multipart.MultipartFile;

import cl.tswoo.lab.app.models.Articulo;
import cl.tswoo.lab.app.models.Cliente;
import cl.tswoo.lab.app.models.Vehiculo;
import cl.tswoo.lab.app.services.ArticuloService;
import cl.tswoo.lab.app.services.BodegaService;
import cl.tswoo.lab.app.services.VehiculoService;

@Controller
@RequestMapping("/articulos")
public class ArticuloController {


	@Autowired
	private ArticuloService articuloService;
	

	@Autowired
	private BodegaService bodegaService;
	
	@GetMapping("/gestiona")
	public String gestionaArticulos(Model model) {
		
		model.addAttribute("articulo", new Articulo());
		//model.addAttribute("titulo", "Buscar Cliente");
		ArrayList<Articulo> lista = new ArrayList<>(articuloService.findAllArticulo());
		model.addAttribute("articulos", lista);
		model.addAttribute("bodegas", new ArrayList<>(bodegaService.findAllBodegas()));
		model.addAttribute("listTab", "active");
		
		
		//model.addAttribute("flag", true);
		return "articulos/articulo-view";
	}
	
	@GetMapping({"/crear"})
	public String redirecciona(Model model) {
		
		model.addAttribute("articulo", new Articulo());
		model.addAttribute("titulo", "Registrar Articulo");
		model.addAttribute("bodegas", new ArrayList<>(bodegaService.findAllBodegas()));
		//model.addAttribute("clientes", clienteService.findAllClientes());
		//model.addAttribute("flag", true);
		return "crearArticulo";
	}
	
	
	@PostMapping({"/","articulo"})
	public String anadirArticulo(Model model,@Valid Articulo articulo, BindingResult result, @RequestParam("file") MultipartFile imagen) { 
			if(result.hasErrors()) {
				
				model.addAttribute("formTab", "active");
				model.addAttribute("bodegas", new ArrayList<>(bodegaService.findAllBodegas()));
				model.addAttribute("articulos",new ArrayList<>(articuloService.findAllArticulo()));
				return "articulos/articulo-view";
			}
			if(!imagen.isEmpty()) {
				Path directorioImagenes=Paths.get("src//main//resources//static/images");
				String rutaAbsoluta=directorioImagenes.toFile().getAbsolutePath();
				try {
					byte [] bytesImg=imagen.getBytes();
					Path rutaCompleta=Paths.get(rutaAbsoluta+"//"+imagen.getOriginalFilename());
					Files.write(rutaCompleta, bytesImg);
					articulo.setImagen(imagen.getOriginalFilename());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			articuloService.save(articulo);
			model.addAttribute("bodegas", new ArrayList<>(bodegaService.findAllBodegas()));
			model.addAttribute("articulo", new Articulo());
			model.addAttribute("articulos",new ArrayList<>(articuloService.findAllArticulo()));
			model.addAttribute("titulo", "Creacion de Articulo");
			model.addAttribute("listTab", "active");
			//model.addAttribute("clientes", clienteService.findAllClientes());
			//model.addAttribute("flag", true);
			return "articulos/articulo-view";
	
	}
	
	@GetMapping("/nombre")
	public String buscarPorNombreArticulo(@RequestParam String nombre,Model model,@ModelAttribute("articulo") Articulo articulo) {
		  model.addAttribute("listTab", "active");
		if(articuloService.findArticuloByNombre(nombre)!=null) {
			model.addAttribute("articulos",articuloService.findArticuloByNombre(nombre));
			model.addAttribute("nombre", nombre);
			model.addAttribute("articulo", new Articulo());
			model.addAttribute("stock", articuloService.findArticuloByNombre(nombre).size());
			model.addAttribute("flag", true);
			return "articulos/articulo-view";
		}
		else if(articuloService.findArticuloByBodega(nombre)!=null) {
			model.addAttribute("articulos",articuloService.findArticuloByBodega(nombre));
			model.addAttribute("articulo", new Articulo());
			return "articulos/articulo-view";
		}
		
		model.addAttribute("articulos",articuloService.findAllArticulo());
		//model.addAttribute("flag", true);
		return "articulos/articulo-view";
	}
	
	
	@GetMapping({"/","articulo"})
	public String getAllArticulos(Model model) {
		
		
		ArrayList<Articulo> lista = new ArrayList<>(articuloService.findAllArticulo());
		
		if(lista.size()==0) {
			model.addAttribute("noCliente", "No existen Articulos");
			return "Error"; //retorna a pagina de error.
		}
		model.addAttribute("bodegas", new ArrayList<>(bodegaService.findAllBodegas()));
		model.addAttribute("articulos", lista);
		return "articulos";
	
	}
	
	@GetMapping("/detalle/{id}")
	public String datalleArticulo(Model model, @PathVariable("id") int idArticulo){
			Articulo articulo=articuloService.findArticuloById(idArticulo);
			model.addAttribute("articulo", articulo);
			model.addAttribute("articulos",new ArrayList<>(articuloService.findAllArticulo()));
			model.addAttribute("bodegas", new ArrayList<>(bodegaService.findAllBodegas()));
			model.addAttribute("flag", false);
			//model.addAttribute("clientes", clienteService.findAllClientes());
			model.addAttribute("titulo", "Detalle del producto: "+articulo.getNombre());
			model.addAttribute("detalleTab","active");
			
			return "articulos/articulo-view";
	}
	@GetMapping("/editarArticulo/{id}")
	public String updateArticulo(Model model, @PathVariable("id") int idArticulo){
			Articulo articulo=articuloService.findArticuloById(idArticulo);
			model.addAttribute("articulo", articulo);
			model.addAttribute("articulos",new ArrayList<>(articuloService.findAllArticulo()));
			model.addAttribute("bodegas", new ArrayList<>(bodegaService.findAllBodegas()));
			model.addAttribute("flag", false);
			//model.addAttribute("clientes", clienteService.findAllClientes());
			model.addAttribute("titulo", "Modificacion de articulo");
			model.addAttribute("formTab","active");
			return "articulos/articulo-view";
	}
	@GetMapping("eliminarArticulo/{id}")
	public String deleteArticuloById(Model model ,@PathVariable("id") int idArticulo) {
		
		Articulo articulo=articuloService.findArticuloById(idArticulo);
		if(articulo==null ) {
			model.addAttribute("noCliente", "No existen articulos");
			return "error";
		}
		/*if(clienteService.tieneVehiculo(idCliente)) {
			model.addAttribute("noDelete", "No se puede eliminar porque el cliente tiene vehiculos registrados.");
			return "error";
		}*/
			
		articuloService.deleteArticuloById(idArticulo);
		model.addAttribute("bodegas", new ArrayList<>(bodegaService.findAllBodegas()));
		model.addAttribute("articulo", new Articulo());
		model.addAttribute("articulos", new ArrayList<>(articuloService.findAllArticulo()));
		model.addAttribute("listTab","active");
		return "articulos/articulo-view";
	}
	
	
}
