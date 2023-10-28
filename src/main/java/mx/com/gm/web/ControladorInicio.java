package mx.com.gm.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;
import mx.com.gm.domain.Persona;
import mx.com.gm.servicio.PersonaService;

@Controller
@Slf4j
public class ControladorInicio {
	
	@Autowired
	private PersonaService personaService;
	
	@GetMapping("/") // Responde a la ruta http://localhost:8080/
	public String inicio(Model model, @AuthenticationPrincipal User user) {
		log.info("Ejecutando el controlador Spring MVC");
		log.info("Usuario que hizo login: " + user);
		
		List<Persona> personas = (List<Persona>) personaService.listarPersonas();
		Double saldoTotal = 0D;
		
		for (Persona p : personas) {
			saldoTotal += p.getSaldo();
		}
		
		model.addAttribute("personas", personas);
		model.addAttribute("saldoTotal", saldoTotal);
		model.addAttribute("totalClientes", personas.size());
		
		return "index";
	}
	
	@GetMapping("/agregar")
	public String agregar(Persona persona) {
		return "modificar";
	}
	
	@PostMapping("/guardar")
	public String guardar(@Valid Persona persona, Errors errores) {
		if (errores.hasErrors()) {
			return "modificar";
		}
		
		personaService.guardar(persona);
		
		return "redirect:/"; // Para regresar a nuestra página de inicio
	}
	
	@GetMapping("/editar/{idPersona}")
	public String editar(Persona persona, Model model) {
		// Con solo añadir el parámetro 'persona' en el método, Spring lo inyectará automáticamente con el Path Variable 'idPersona'
		// y no hará falta ni 1) Crear dentro del método el objeto Persona ni 2) Setearle al objeto Persona el 'idPersona'
		persona = personaService.encontrarPersona(persona);
		model.addAttribute("persona", persona);
		
		return "modificar";
	}
	
	/**
	 * Método de 'eliminar' por Path Variable (en index.html)
	 * 
	@GetMapping("/eliminar/{idPersona}")
	public String eliminar(Persona persona) {
		// Con solo añadir el parámetro 'persona' en el método, Spring lo inyectará automáticamente con el Path Variable 'idPersona'
		// y no hará falta ni 1) Crear dentro del método el objeto Persona ni 2) Setearle al objeto Persona el 'idPersona'
		personaService.eliminar(persona);
		
		return "redirect:/"; // Para regresar a nuestra página de inicio
	}
	*/
	
	/**
	 * Método de 'eliminar' por Query Param (en index.html)
	 * @param persona
	 * @return
	 */
	@GetMapping("/eliminar")
	public String eliminar(Persona persona) {
		// Con solo añadir el parámetro 'persona' en el método, Spring lo inyectará automáticamente con el Path Variable 'idPersona'
		// y no hará falta ni 1) Crear dentro del método el objeto Persona ni 2) Setearle al objeto Persona el 'idPersona'
		personaService.eliminar(persona);
		
		return "redirect:/"; // Para regresar a nuestra página de inicio
	}
}
