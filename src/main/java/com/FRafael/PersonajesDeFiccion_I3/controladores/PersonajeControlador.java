package com.FRafael.PersonajesDeFiccion_I3.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.FRafael.PersonajesDeFiccion_I3.entidades.Obra;
import com.FRafael.PersonajesDeFiccion_I3.entidades.Personaje;
import com.FRafael.PersonajesDeFiccion_I3.servicios.ObraServicio;
import com.FRafael.PersonajesDeFiccion_I3.servicios.PersonajeServicio;
import java.util.List;

@RequestMapping("/personajes")
@RestController
public class PersonajeControlador {
	
	@Autowired
	private PersonajeServicio personajeServicio;
	@Autowired
	private ObraServicio obraServicio;
	
	@GetMapping("/formulario")
	public ModelAndView mostrarFormulario(Model model) {
		
		List<Obra> obras = obraServicio.devolverTodas();
		model.addAttribute("obras",obras);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("formulario_personaje.html");
		
		return modelAndView;
	}
	
	@PostMapping("/personaje-guardado")
	public ModelAndView guardar(String nombre, String apodo, String idObra,
			String fotoUrl,Model model) {
		Long idObraLong = Long.parseLong(idObra);
		
		Obra obra = obraServicio.devolverPorId(idObraLong);
		
		List<Obra> obras = obraServicio.devolverTodas();
		model.addAttribute("obras",obras);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("formulario_personaje.html");
		
		Personaje personaje = new Personaje();
		personaje.setNombre(nombre);
		personaje.setApodo(apodo);
		personaje.setObra(obra);
		personaje.setFotoUrl(fotoUrl);
		
		personajeServicio.guardar(personaje);
		
		return modelAndView;
	}
	

	@GetMapping("/lista")
	public ModelAndView listarPersonajes(Model model) {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("lista_personajes.html");
		
		List<Personaje> personajes = personajeServicio.devolverTodos();
		model.addAttribute("personajes",personajes);
		
		return modelAndView;
	}

}
