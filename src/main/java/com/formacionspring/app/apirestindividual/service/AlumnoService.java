package com.formacionspring.app.apirestindividual.service;

import java.util.List;

import com.formacionspring.app.apirestindividual.entity.Alumno;

public interface AlumnoService {
	public List<Alumno> findAll();
	public Alumno findById(long id);
	public Alumno save(Alumno alumno);
	public void delete(long id);
}
