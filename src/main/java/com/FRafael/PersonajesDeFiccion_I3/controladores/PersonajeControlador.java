package com.FRafael.PersonajesDeFiccion_I3.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public ModelAndView mostrarInicio(Model model,Model model2) { 
		boolean invisible;
		boolean invisiblePersonajes;
		
		if(obraServicio.devolverTodas().size()>0) { //tratar de achicar el codigo en una sola linea cada condicional
			invisible = false;
		}else {
			invisible = true;
		}
		
		if(personajeServicio.devolverTodos().size()>0) {
			invisiblePersonajes = false;
		}else {
			invisiblePersonajes = true;
		}
		
		model.addAttribute("invisible",invisible);
		model2.addAttribute("invisiblePersonajes", invisiblePersonajes);
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
	
	@GetMapping("/filtro-por-nombre") //validacion para no usar mas de cuatro caracteres
	public ModelAndView filtrarPorNombre(String nombre,Model model,Model modelCantidad) {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("lista_personajes_c.html");
		
		List<Personaje> personajes;
		
		if(nombre.length()<=4) {
			personajes = personajeServicio.devolverFiltroNombre(nombre);
		}else {
			personajes = personajeServicio.devolverTodos();
		}
		
		long cantidadPersonajes;
        cantidadPersonajes = personajes.size();
		
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
	
	@GetMapping("/formulario-edicion")
	public ModelAndView mostrarFormularioEdicion(Model model, Model model2) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("edicion_personajes.html");
		
		List<Personaje> personajes = personajeServicio.devolverTodos();
		model.addAttribute("personajes", personajes);
		
		List<Obra> obras = obraServicio.devolverTodas();
		model2.addAttribute("obras",obras);
		
		
		
		return modelAndView;
	}
	
	@PostMapping("/editado")
	public ModelAndView editarPersonaje(@RequestParam Long id, @RequestParam String nombre, 
			@RequestParam String apodo, @RequestParam String idObra, @RequestParam String urlFoto,
			Model model, Model model2,Model mensaje, Model mensaje2) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("edicion_personajes.html");
		Long idObraLong;
		String mensajeDeAviso="";
		
		if((!apodo.equals("")||!nombre.equals(""))&&!idObra.equals("")&&!urlFoto.equals("")) {
			if(apodo.length()<=25&&nombre.length()<=25) {
				
				idObraLong = Long.parseLong(idObra);
				Personaje personaje = personajeServicio.devolverPorId(id);
				personaje.setNombre(nombre);
				personaje.setApodo(apodo);
				Obra obra = obraServicio.devolverPorId(idObraLong);
				personaje.setObra(obra);
				personaje.setFotoUrl(urlFoto);
				personajeServicio.guardar(personaje);
				mensajeDeAviso = "Personaje guardado correctamente";
				mensaje.addAttribute("mensajeExitoso",mensajeDeAviso);
				
			}else {
				mensajeDeAviso = "El nombre o apodo del personaje es demasiado extenso";
				mensaje2.addAttribute("mensajeDeError",mensajeDeAviso);
			}
			
		}else {
			mensajeDeAviso = "Ha ocurrido un error";
			mensaje2.addAttribute("mensajeDeError",mensajeDeAviso);
		}

		List<Personaje> personajes = personajeServicio.devolverTodos();
		model.addAttribute("personajes", personajes);
		
		List<Obra> obras = obraServicio.devolverTodas();
		model2.addAttribute("obras",obras);
		
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
	
	@DeleteMapping("/borrado/{id}")
	public ModelAndView borrarPersonaje(@PathVariable Long id,Model model) {
		personajeServicio.borrarPorId(id);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("borrar_personaje.html");
		
        List<Personaje> personajes = personajeServicio.devolverTodos();
		
		model.addAttribute("personajes",personajes);
		
		return modelAndView;
	}

}
