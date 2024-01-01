package com.FRafael.PersonajesDeFiccion_I3.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "obra")
public class Obra {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String titulo;
	private int anioLanzamiento;
	
	@Enumerated(EnumType.STRING)
	private ClasificacionObra clasificacion;

	public Obra() {
	}

	public Obra(Long id, String titulo, int anioLanzamiento, ClasificacionObra clasificacion) {
		this.id = id;
		this.titulo = titulo;
		this.anioLanzamiento = anioLanzamiento;
		this.clasificacion = clasificacion;
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public int getAnioLanzamiento() {
		return anioLanzamiento;
	}

	public ClasificacionObra getClasificacion() {
		return clasificacion;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setAnioLanzamiento(int anioLanzamiento) {
		this.anioLanzamiento = anioLanzamiento;
	}

	public void setClasificacion(ClasificacionObra clasificacion) {
		this.clasificacion = clasificacion;
	}

	@Override
	public String toString() {
		return "Obra [id=" + id + ", titulo=" + titulo + ", anioLanzamiento=" + anioLanzamiento + ", clasificacion="
				+ clasificacion + "]";
	}
	
}
