package com.FRafael.PersonajesDeFiccion_I3.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.FRafael.PersonajesDeFiccion_I3.entidades.Obra;

@Repository
public interface ObraRepositorio extends JpaRepository<Obra,Long>{
	
	@Query(value = "SELECT * FROM obra ORDER BY titulo",
			nativeQuery = true)
	public List<Obra> devolverPorTitulo();
	
	@Query(value = "SELECT o FROM obra o WHERE o.titulo LIKE :letras% ORDER BY o.titulo")
	public List<Obra> devolverFiltroTitulo(@Param("letras") String letras);
	
	@Query(value = "SELECT * FROM obra WHERE anio_lanzamiento BETWEEN :minimo AND :maximo ORDER BY anio_lanzamiento",
			nativeQuery = true)
	public List<Obra> devolverFiltroLanzamiento(@Param("minimo") int minimo,@Param("maximo") int maximo);
	
	@Query(value = "SELECT o FROM obra o WHERE o.clasificacion = :clasificacion ORDER BY o.clasificacion")
	public List<Obra> devolverFiltroClasificacion(@Param("clasificacion") String clasificacion);
}
