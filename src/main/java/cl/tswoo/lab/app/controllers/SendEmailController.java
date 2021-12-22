package cl.tswoo.lab.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cl.tswoo.lab.app.models.Cliente;
import cl.tswoo.lab.app.services.ClienteService;
import cl.tswoo.lab.app.services.MailService;

@Controller
public class SendEmailController {

    @Autowired
    private MailService mailService;

    @Autowired 
    private ClienteService clienteService;
    
    @GetMapping("/sendEmailTo/{id}")
    public String sendEmailTo(@PathVariable(value="id") int idCliente, Model model){
    	Cliente cliente = clienteService.findClienteById(idCliente);
    	
    	mailService.sendMail("desarmaduria.confirmacion@gmail.com", cliente.getEmail(), "Aceptacion de Vehiculo", "Se le notifica que su"
    			+ " vehículo ha sido aceptado!, puede pasar a retirar su pago a la tienda.");
        return "redirect:/vehiculos/gestiona";
    }
}

