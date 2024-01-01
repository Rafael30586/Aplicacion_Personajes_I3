package com.FRafael.PersonajesDeFiccion_I3.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.FRafael.PersonajesDeFiccion_I3.servicios.ObraServicio;

@RequestMapping("/obras")
@RestController
public class ObraControlador {
	
	@Autowired
	private ObraServicio obraServicio;

}
