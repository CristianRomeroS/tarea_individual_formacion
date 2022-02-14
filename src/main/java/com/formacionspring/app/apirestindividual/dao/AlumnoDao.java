package com.formacionspring.app.apirestindividual.dao;

import org.springframework.data.repository.CrudRepository;

import com.formacionspring.app.apirestindividual.entity.Alumno;



public interface AlumnoDao extends CrudRepository<Alumno, Long> {

}
