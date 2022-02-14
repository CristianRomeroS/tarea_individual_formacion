package com.formacionspring.app.apirestindividual.service;

import java.util.List;

import com.formacionspring.app.apirestindividual.entity.Comunidad;

public interface ComunidadService {
	public List<Comunidad> findAll();
	public Comunidad findById(long id);
	public Comunidad save(Comunidad comunidad);
	public void delete(long id);
}
