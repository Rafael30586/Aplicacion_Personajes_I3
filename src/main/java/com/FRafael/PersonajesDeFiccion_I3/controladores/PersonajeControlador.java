package com.FRafael.PersonajesDeFiccion_I3.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.FRafael.PersonajesDeFiccion_I3.entidades.Obra;
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
		modelAndView.setViewName("");
		
		return modelAndView;
	}

}
