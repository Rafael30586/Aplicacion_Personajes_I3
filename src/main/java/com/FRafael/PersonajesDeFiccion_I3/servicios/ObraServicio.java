package com.FRafael.PersonajesDeFiccion_I3.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FRafael.PersonajesDeFiccion_I3.entidades.Obra;
import com.FRafael.PersonajesDeFiccion_I3.repositorios.ObraRepositorio;

@Service
public class ObraServicio {
	
	@Autowired
	private ObraRepositorio obraRepositorio;
	
	public void guardar(Obra obra) {
		obraRepositorio.save(obra);
	}
	
	public List<Obra> devolverTodas(){
		return obraRepositorio.findAll();
	}
	
	public void borrasTodas() {
		obraRepositorio.deleteAll();
	}
	
	public Obra devolverPorId(Long id) {
		return obraRepositorio.getReferenceById(id);
	}
	
	public List<Obra> devolverPorTitulo(){
		return obraRepositorio.devolverPorTitulo();
	}
	
	public List<Obra> devolverFiltroTitulo(String letras){
		return obraRepositorio.devolverFiltroTitulo(letras);
	}
	
	public List<Obra> devolverFiltroLanzamiento(int minimo, int maximo){
		return obraRepositorio.devolverFiltroLanzamiento(minimo, maximo);
	}
	
	public List<Obra> devolverFiltroClasificacion(String clasificacion){
		return obraRepositorio.devolverFiltroClasificacion(clasificacion);
	}

}
