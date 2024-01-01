package com.FRafael.PersonajesDeFiccion_I3.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.FRafael.PersonajesDeFiccion_I3.entidades.Personaje;

@Repository
public interface PersonajeRepositorio extends JpaRepository<Personaje,Long>{
	
	@Query(value = "SELECT p FROM personaje p WHERE p.nombre LIKE :letras% OR p.apodo LIKE :letras%")
	public List<Personaje> devolverFiltroNombre(@Param("letras") String letras); //funciona
	/*
	@Query("SELECT p FROM personaje p WHERE p.obra.anio_lanzamiento BETWEEN minimo :minimo AND maximo :maximo")
	public List<Personaje> devolverFiltroLanzamiento(@Param("minimo") int minimo,@Param("maximo") int maximo);//hacer un join
	
	@Query(value = "SELECT p FROM personaje p WHERE p.obra.clasificacion = :clasificacion")
	public List<Personaje> devolverFiltroClasificacion(@Param("clasificacion") String clasificacion);*/

}
