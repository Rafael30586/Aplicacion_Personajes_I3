package com.FRafael.PersonajesDeFiccion_I3.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FRafael.PersonajesDeFiccion_I3.entidades.Personaje;
import com.FRafael.PersonajesDeFiccion_I3.repositorios.PersonajeRepositorio;

@Service
public class PersonajeServicio {
	
	@Autowired
	private PersonajeRepositorio personajeRepositorio;
	
	public void guardar(Personaje personaje) {
		personajeRepositorio.save(personaje);
	}
	
	public List<Personaje> devolverTodos(){
		return personajeRepositorio.findAll();
	}
	
	public List<Personaje> devolverFiltroNombre(String letras){
		return personajeRepositorio.devolverFiltroNombre(letras);
	}
	/*
	public List<Personaje> devolverFiltroLanzamiento(int maximo, int minimo){
		return personajeRepositorio.devolverFiltroLanzamiento(maximo, minimo);
	}
	
	public List<Personaje> devolverFiltroClasificacion(String clasificacion){
		return personajeRepositorio.devolverFiltroClasificacion(clasificacion);
	}*/
	
	public void borrarPorId(Long id) {
		personajeRepositorio.deleteById(id);
	}
	
	public Personaje devolverPorId(Long id) {
		return personajeRepositorio.getReferenceById(id);
	}

}
