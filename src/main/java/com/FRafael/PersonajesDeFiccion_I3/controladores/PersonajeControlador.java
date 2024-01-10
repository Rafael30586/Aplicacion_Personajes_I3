package com.FRafael.PersonajesDeFiccion_I3.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping("/inicio")
	public ModelAndView mostrarInicio() {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index.html");
		
		return modelAndView;
	}
	
	@GetMapping("/formulario")
	public ModelAndView mostrarFormulario(Model model) {
		
		List<Obra> obras = obraServicio.devolverTodas();
		model.addAttribute("obras",obras);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("formulario_personaje.html");
		
		return modelAndView;
	}
	
	@PostMapping("/personaje-guardado") //usar condicionales para evitar valores  muy grandes
	public ModelAndView guardar(String nombre, String apodo, String idObra,
			String fotoUrl,Model model,Model mensaje, Model mensaje2) {
		Long idObraLong;  
		String mensajeDeAviso="";
		List<Obra> obras = obraServicio.devolverTodas();
		model.addAttribute("obras",obras);
		
		if((!apodo.equals("")||!nombre.equals(""))&&!idObra.equals("")&&!fotoUrl.equals("")) {
			if(apodo.length()<=25&&nombre.length()<=25) {
				idObraLong = Long.parseLong(idObra);
				Obra obra = obraServicio.devolverPorId(idObraLong);
				
				Personaje personaje = new Personaje();
				personaje.setNombre(nombre);
				personaje.setApodo(apodo);
				personaje.setObra(obra);
				personaje.setFotoUrl(fotoUrl);
				
				personajeServicio.guardar(personaje);
				
				mensajeDeAviso = "Personaje guardado correctamente";
				mensaje.addAttribute("mensajeExitoso",mensajeDeAviso);
			}else {
				mensajeDeAviso = "El nombre o apodo del personaje es demasiado extenso";
				mensaje2.addAttribute("mensajeDeError",mensajeDeAviso);
			}
			
		}else {
			mensajeDeAviso = "Ha ocurrido un error";
			mensaje2.addAttribute("mensajeDeError",mensajeDeAviso); //Funciona pero tratare de usar condicionales para...
		}                                          //...usar un Model solo para ambos mensajes
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("formulario_personaje.html");
		
		return modelAndView;
	}
	

	@GetMapping("/lista")
	public ModelAndView listarPersonajes(Model model,Model modelCantidad) {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("lista_personajes_c.html");
		
		List<Personaje> personajes = personajeServicio.devolverTodos();
		long cantidadPersonajes = personajes.size();
		
		model.addAttribute("personajes",personajes);
		modelCantidad.addAttribute("cantidad", cantidadPersonajes);
		
		return modelAndView;
	}
	
	@GetMapping("/filtro-por-nombre")
	public ModelAndView filtrarPorNombre(String nombre,Model model,Model modelCantidad) {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("lista_personajes_c.html");
		
		List<Personaje> personajes = personajeServicio.devolverFiltroNombre(nombre);
		
        long cantidadPersonajes = personajes.size();
		
		model.addAttribute("personajes",personajes);
		modelCantidad.addAttribute("cantidad", cantidadPersonajes);
		
		return modelAndView;
	}
	
	@GetMapping("/individual")
	public ModelAndView mostrarPersonaje(@RequestParam Long id, Model model, Model modelNombre) {
		Personaje personaje = personajeServicio.devolverPorId(id);
		String nombreOApodo;
		
		if(personaje.getApodo().equals(null)||personaje.getApodo().equals("")) {
			nombreOApodo = personaje.getNombre();
		}else {
			nombreOApodo = personaje.getApodo();
		}
		
		modelNombre.addAttribute("nombreOApodo",nombreOApodo);
		
		model.addAttribute("personaje",personaje);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("personaje_individual.html");
		return modelAndView;
	}
	
	@GetMapping("/formulario-borrado-personaje")
	public ModelAndView borrarPersonaje(Model model) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("borrar_personaje.html");
		
		List<Personaje> personajes = personajeServicio.devolverTodos();
		
		model.addAttribute("personajes",personajes);
		
		
		return modelAndView;
	}
	
	@DeleteMapping("/borrado")
	public ModelAndView borrarPersonaje(@RequestParam Long id,Model model) {
		personajeServicio.borrarPorId(id);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("borrar_personaje.html");
		
        List<Personaje> personajes = personajeServicio.devolverTodos();
		
		model.addAttribute("personajes",personajes);
		
		return modelAndView;
	}

}
