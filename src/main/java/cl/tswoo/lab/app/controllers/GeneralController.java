package cl.tswoo.lab.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import cl.tswoo.lab.app.models.Cliente;

@Controller
public class GeneralController {
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("cliente",new Cliente());
		model.addAttribute("texto", "cualquier");
		return "indexNuevo";
	}


}
