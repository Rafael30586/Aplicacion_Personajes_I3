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
	
	@PostMapping("/guardada")
	public ModelAndView guardar(String titulo, int anioLanzamiento, String clasificacion) {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("formulario_obra.html");
		
		Obra obra = new Obra();
		ClasificacionObra clasificacionObra;
		
		obra.setTitulo(titulo);
		obra.setAnioLanzamiento(anioLanzamiento);
		
		if(clasificacion.equals("Video Juego")) {
			clasificacionObra = ClasificacionObra.VIDEO_JUEGO;
			obra.setClasificacion(clasificacionObra);
			obraServicio.guardar(obra);
		}else if(clasificacion.equals("Comic")) {
			clasificacionObra = ClasificacionObra.COMIC;
			obra.setClasificacion(clasificacionObra);
			obraServicio.guardar(obra);
		}else if(clasificacion.equals("Pelicula")) {
			clasificacionObra = ClasificacionObra.PELICULA;
			obra.setClasificacion(clasificacionObra);
			obraServicio.guardar(obra);
		}else if(clasificacion.equals("Serie")) {
			clasificacionObra = ClasificacionObra.SERIE;
			obra.setClasificacion(clasificacionObra);
			obraServicio.guardar(obra);
		}else if(clasificacion.equals("libro")) {
			clasificacionObra = ClasificacionObra.LIBRO;
			obra.setClasificacion(clasificacionObra);
			obraServicio.guardar(obra);
		}
		
		return modelAndView;
	}
	
	@GetMapping("/lista")
	public ModelAndView listarObras(Model model) {
		List<Obra> obras = obraServicio.devolverPorTitulo();
		model.addAttribute("obras",obras);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("lista_obras.html");
		
		return modelAndView;
	}

}
