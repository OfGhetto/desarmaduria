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
import cl.tswoo.lab.app.services.VehiculoService;
import cl.tswoo.lab.app.services.ClienteService;

@Controller
@RequestMapping("/vehiculos")
public class VehiculoController {
	
	@Autowired
	private VehiculoService vehiculoService;
	@Autowired
	private ClienteService clienteService;
	
	
	@GetMapping("/gestiona")
	public String gestionaClientes(Model model) {
		
		model.addAttribute("vehiculo", new Vehiculo());
		//model.addAttribute("titulo", "Buscar Cliente");
		ArrayList<Vehiculo> lista = new ArrayList<>(vehiculoService.findAllVehiculo());
		model.addAttribute("clientes", clienteService.findAllClientes());
		model.addAttribute("vehiculos", lista);
		model.addAttribute("cliente", new Cliente());
		model.addAttribute("listTab", "active");
		
		
		//model.addAttribute("flag", true);
		return "vehiculos/vehiculo-view";
	}
	
	@GetMapping("/buscar")
	public String redireccionABuscar2(Model model) {
		
		model.addAttribute("vehiculo", new Vehiculo());
		model.addAttribute("titulo", "Buscar Vehiculo");
		//model.addAttribute("flag", true);
		return "searchVehiculo";
	}
	
	@GetMapping("/marca")
	public String buscarPorMarca(@RequestParam String marca,Model model,@ModelAttribute("vehiculo") Vehiculo vehiculo) {
		  model.addAttribute("listTab", "active");
		if(vehiculoService.findVehiculosPorMarca(marca)!=null) {
			model.addAttribute("vehiculos",vehiculoService.findVehiculosPorMarca(marca));
			model.addAttribute("clientes", clienteService.findAllClientes());
			return "vehiculos/vehiculo-view";
		}
		
		model.addAttribute("vehiculos",vehiculoService.findAllVehiculo());
		//model.addAttribute("flag", true);
		return "vehiculos/vehiculo-view";
	}
	
	
	@GetMapping({"/crear"})
	public String redirecciona(Model model) {
		
		model.addAttribute("vehiculo", new Vehiculo());
		model.addAttribute("titulo", "Registrar vehiculo");
		model.addAttribute("clientes", clienteService.findAllClientes());
		//model.addAttribute("flag", true);
		return "crearVehiculo";
	}
	
	@GetMapping({"/","vehiculo"})
	public String getAllVehiculos(Model model) {
		
		
		ArrayList<Vehiculo> lista = new ArrayList<>(vehiculoService.findAllVehiculo());
		
		if(lista.size()==0) {
			model.addAttribute("noCliente", "No existen vehiculos");
			return "Error"; //retorna a pagina de error.
		}
		model.addAttribute("vehiculos", lista);
		return "vehiculos";
	
	}
	@PostMapping({"/","vehiculo"})
	public String anadirVehiculo(Model model,@Valid Vehiculo vehiculo, BindingResult result) { 
		
			if(result.hasErrors()) {
				model.addAttribute("clientes", clienteService.findAllClientes());
				model.addAttribute("vehiculos",new ArrayList<>(vehiculoService.findAllVehiculo()));
				model.addAttribute("cliente", new Cliente());
				model.addAttribute("formTab", "active");
				return "vehiculos/vehiculo-view";
			}		
			vehiculoService.save(vehiculo);
			model.addAttribute("vehiculo", new Vehiculo());
			model.addAttribute("vehiculos",new ArrayList<>(vehiculoService.findAllVehiculo()));
			model.addAttribute("titulo", "Creacion de Vehiculo");
			model.addAttribute("cliente", new Cliente());
			model.addAttribute("clientes", clienteService.findAllClientes());
			model.addAttribute("listTab", "active");
			//model.addAttribute("flag", true);
			return "vehiculos/vehiculo-view";
	
	}
	@GetMapping("/editarVehiculo/{id}")
	public String updateVehiculo(Model model, @PathVariable("id") int idVehiculo){
			Vehiculo vehiculo=vehiculoService.findVehiculoById(idVehiculo);
			if (vehiculo == null) {
				model.addAttribute("noCliente", "No existen vehiculos");
				return "error";
			}
			model.addAttribute("vehiculo", vehiculo);
			model.addAttribute("vehiculos",new ArrayList<>(vehiculoService.findAllVehiculo()));
			model.addAttribute("flag", false);
			model.addAttribute("clientes", clienteService.findAllClientes());
			model.addAttribute("titulo", "Modificacion de vehiculo");
			model.addAttribute("formTab","active");
			return "vehiculos/vehiculo-view";
	}
	@GetMapping("eliminarVehiculo/{id}")
	public String deleteClienteById(Model model ,@PathVariable("id") int idVehiculo) {
		
		Vehiculo vehiculo=vehiculoService.findVehiculoById(idVehiculo);
		if(vehiculo==null ) {
			model.addAttribute("noCliente", "No existen vehiculos");
			return "error";
		}
		/*if(clienteService.tieneVehiculo(idCliente)) {
			model.addAttribute("noDelete", "No se puede eliminar porque el cliente tiene vehiculos registrados.");
			return "error";
		}*/
			
		vehiculoService.deleteVehiculoById(idVehiculo);
		model.addAttribute("vehiculo", new Vehiculo());
		model.addAttribute("vehiculos", new ArrayList<>(vehiculoService.findAllVehiculo()));
		model.addAttribute("clientes", clienteService.findAllClientes());
		model.addAttribute("listTab", "active");
		return "vehiculos/vehiculo-view";
	}
}
