package com.FRafael.PersonajesDeFiccion_I3.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.FRafael.PersonajesDeFiccion_I3.servicios.PersonajeServicio;

@RequestMapping("/personajes")
@RestController
public class PersonajeControlador {
	
	@Autowired
	private PersonajeServicio personajeServicio;

}
