package com.FRafael.PersonajesDeFiccion_I3.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

import com.FRafael.PersonajesDeFiccion_I3.entidades.ClasificacionObra;
import com.FRafael.PersonajesDeFiccion_I3.entidades.Obra;
import com.FRafael.PersonajesDeFiccion_I3.servicios.ObraServicio;

@RequestMapping("/obras")
@RestController
public class ObraControlador {
	
	@Autowired
	private ObraServicio obraServicio;
	
	@GetMapping("/formulario")
	public ModelAndView mostrarFormulario() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("formulario_obra.html");
		
		return modelAndView;
	}
	
	@PostMapping("/guardada") //usar condicionales para evitar valores  muy grandes
	public ModelAndView guardar(String titulo, int anioLanzamiento, String clasificacion, 
			Model mensaje,Model mensaje2) { //si el anioLanzamiento no se le coloca nada, te lo toma...
		                                    //...como String, hay que arreglarlo
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("formulario_obra.html");
		String mensajeDeAviso="";
		
		
		if(!titulo.equals("")&&!clasificacion.equals(null)) {
			Obra obra = new Obra();
			ClasificacionObra clasificacionObra;
			obra.setTitulo(titulo);
			obra.setAnioLanzamiento(anioLanzamiento);
			
			if(clasificacion.equals("Video Juego")) {
				clasificacionObra = ClasificacionObra.VIDEO_JUEGO;
				obra.setClasificacion(clasificacionObra);
				obraServicio.guardar(obra);
				mensajeDeAviso = "La obra se ha guardado correctamente";
			}else if(clasificacion.equals("Comic")) {
				clasificacionObra = ClasificacionObra.COMIC;
				obra.setClasificacion(clasificacionObra);
				obraServicio.guardar(obra);
				mensajeDeAviso = "La obra se ha guardado correctamente";
			}else if(clasificacion.equals("Pelicula")) {
				clasificacionObra = ClasificacionObra.PELICULA;
				obra.setClasificacion(clasificacionObra);
				obraServicio.guardar(obra);
				mensajeDeAviso = "La obra se ha guardado correctamente";
			}else if(clasificacion.equals("Serie")) {
				clasificacionObra = ClasificacionObra.SERIE;
				obra.setClasificacion(clasificacionObra);
				obraServicio.guardar(obra);
				mensajeDeAviso = "La obra se ha guardado correctamente";
			}else if(clasificacion.equals("libro")) {
				clasificacionObra = ClasificacionObra.LIBRO;
				obra.setClasificacion(clasificacionObra);
				obraServicio.guardar(obra);
				mensajeDeAviso = "La obra se ha guardado correctamente";
			}
			mensaje.addAttribute("mensaje", mensajeDeAviso);
			
		}else {
			mensajeDeAviso = "Ha ocurrido un error";
			mensaje2.addAttribute("mensaje2", mensajeDeAviso);
		}
		
		return modelAndView;
	}
	
	@GetMapping("/lista")
	public ModelAndView listarObras(Model model,Model modelCantidad) {
			
		List<Obra> obras = obraServicio.devolverPorTitulo();
		
		long cantidadObras = obras.size();
		
		if(cantidadObras!=0) {
        	model.addAttribute("obras",obras);
        }
		
		modelCantidad.addAttribute("cantidad",cantidadObras);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("lista_obras.html");
		
		return modelAndView;
	}
	
	@GetMapping("/filtro-por-titulo")
	public ModelAndView filtrarPorTitulo(String letras,Model model,Model modelCantidad){
			 
		List<Obra> obras = obraServicio.devolverFiltroTitulo(letras);
		
        long cantidadObras = obras.size();
        
        if(cantidadObras!=0) {
        	model.addAttribute("obras",obras);
        }
		
		modelCantidad.addAttribute("cantidad",cantidadObras);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("lista_obras.html");
		
		return modelAndView;
	}
	
	@GetMapping("/filtro-por-lanzamiento")
	public ModelAndView filtrarPorLanzamiento(int minimo, int maximo,Model model,Model modelCantidad){
			 
		List<Obra> obras = obraServicio.devolverFiltroLanzamiento(minimo, maximo);
		
        long cantidadObras = obras.size();
        
        modelCantidad.addAttribute("cantidad",cantidadObras);
        
        if(cantidadObras!=0) {
        	model.addAttribute("obras",obras);
        }
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("lista_obras.html");
		
		return modelAndView;
	}

}
