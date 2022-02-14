package com.formacionspring.app.apirestindividual.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacionspring.app.apirestindividual.dao.AlumnoDao;
import com.formacionspring.app.apirestindividual.entity.Alumno;
@Service
public class AlumnoServiceImpl implements AlumnoService  {
	@Autowired
	private AlumnoDao alumnoDao;
	@Override
	@Transactional(readOnly = true)
	public List<Alumno> findAll() {
		// TODO Auto-generated method stub
		return (List<Alumno>) alumnoDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Alumno findById(long id) {
		// TODO Auto-generated method stub
		return alumnoDao.findById(id).orElse(null);
	}

	@Override
	public Alumno save(Alumno alumno) {
		// TODO Auto-generated method stub
		return alumnoDao.save(alumno);
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		alumnoDao.deleteById(id);
	}

}
