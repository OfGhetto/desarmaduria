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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import antlr.collections.List;
import cl.tswoo.lab.app.services.ClienteService;
import cl.tswoo.lab.app.services.VehiculoService;
import cl.tswoo.lab.app.validations.RUNValidation;
import cl.tswoo.lab.app.models.Cliente;
import cl.tswoo.lab.app.models.Vehiculo;

@Controller
@RequestMapping("/clientes")
public class ClienteController {


	@Autowired
	private ClienteService clienteService;
	@Autowired
	private VehiculoService vehiculoService;
	
	@Autowired
	private RUNValidation validador;
	
	
	@GetMapping("/gestiona")
	public String gestionaClientes(Model model) {
		
		model.addAttribute("cliente", new Cliente());
		model.addAttribute("titulo", "Buscar Cliente");
		ArrayList<Cliente> lista = new ArrayList<>(clienteService.findAllClientes());
		model.addAttribute("clientes", lista);
		model.addAttribute("listTab", "active");
		
		
		//model.addAttribute("flag", true);
		return "user-form/user-view";
	}
	
	@GetMapping("/buscar")
	public String redireccionABuscar(Model model) {
		
		model.addAttribute("cliente", new Cliente());
		model.addAttribute("titulo", "Buscar Cliente");
		//model.addAttribute("flag", true);
		return "buscarCliente";
	}
	
	@GetMapping("/nombre")
	public String buscarPorNombre(@RequestParam String nombre,Model model,@ModelAttribute("cliente") Cliente cliente) {
		  model.addAttribute("listTab", "active");
		if(clienteService.findClienteByNombre(nombre)!=null) {
			
		  model.addAttribute("clientes",clienteService.findClienteByNombre(nombre));
		  return "user-form/user-view";
		}
		  model.addAttribute("clientes",clienteService.findAllClientes());
		  return "user-form/user-view";
		
		  
		//model.addAttribute("flag", true);
		
	}
	
	
	
	
	
	@GetMapping({"/crear"})
	public String redirecciona(Model model) {
		
		model.addAttribute("cliente", new Cliente());
		model.addAttribute("titulo", "Creacion de cliente");
		model.addAttribute("flag", true);
		return "crearCliente";
	}
	
	@GetMapping({"/volver"})
	public String redirecciona2(Model model) {
		
		return "index";
	}
	
	@GetMapping({"/","client"})
	public String getAllClientes(Model model) {
		
		
		ArrayList<Cliente> lista = new ArrayList<>(clienteService.findAllClientes());
		
		if(lista.size()==0) {
			model.addAttribute("noCliente", "No existen clientes");
			return "Error"; //retorna a pagina de error.
		}
		model.addAttribute("clientes", lista);
		return "client";
	
	}
	@PostMapping({"/","client"})
	public String anadirClientes( Model model,@Valid Cliente cliente, BindingResult result) { 
			validador.validate(cliente, result);
			if(result.hasErrors()) {
				model.addAttribute("clientes",new ArrayList<>(clienteService.findAllClientes()));
				model.addAttribute("formTab", "active");
				return "user-form/user-view";
				
			}
			clienteService.save(cliente);
			model.addAttribute("cliente", new Cliente());
			model.addAttribute("clientes",new ArrayList<>(clienteService.findAllClientes()));
			model.addAttribute("titulo", "Creacion de cliente");
			model.addAttribute("flag", true);
			model.addAttribute("listTab", "active");
			return "user-form/user-view";
	
	}
	@GetMapping("/editarCliente/{id}")
	public String updateCliente(Model model, @PathVariable("id") int idCliente){
			Cliente cliente= clienteService.findClienteById(idCliente);
			if (cliente == null) {
				model.addAttribute("noCliente", "No existen clientes");
				return "error";
			}
			model.addAttribute("cliente", cliente);
			model.addAttribute("clientes",new ArrayList<>(clienteService.findAllClientes()));
			model.addAttribute("flag", false);
			model.addAttribute("titulo", "Modificacion de cliente");
			model.addAttribute("formTab","active");
			return "user-form/user-view";
	}
	
	@GetMapping("eliminarCliente/{id}")
	public String deleteClienteById(Model model ,@PathVariable("id") int idCliente) {
		
		ArrayList<Vehiculo> listaVehiculos=new ArrayList<>(vehiculoService.findAllVehiculo());
		
		for(Vehiculo v: listaVehiculos ) {
			if(v.getCliente().getId()==idCliente) {
				vehiculoService.deleteVehiculoById(v.getId());
			}
		}
		
			
		clienteService.deleteClienteById(idCliente);
		model.addAttribute("cliente", new Cliente());
		model.addAttribute("clientes", clienteService.findAllClientes());
		model.addAttribute("listTab", "active");
		return "user-form/user-view";
	}
	
}
