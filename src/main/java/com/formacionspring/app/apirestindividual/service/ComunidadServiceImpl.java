package com.formacionspring.app.apirestindividual.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.formacionspring.app.apirestindividual.dao.ComunidadDao;
import com.formacionspring.app.apirestindividual.entity.Comunidad;
@Service
public class ComunidadServiceImpl implements ComunidadService  {
	@Autowired
	private ComunidadDao comunidadDao;
	@Override
	@Transactional(readOnly = true)
	public List<Comunidad> findAll() {
		// TODO Auto-generated method stub
		return (List<Comunidad>) comunidadDao.findAll();
	}

	@Override
	public Comunidad findById(long id) {
		// TODO Auto-generated method stub
		return comunidadDao.findById(id).orElse(null);
	}

	@Override
	public Comunidad save(Comunidad comunidad) {
		// TODO Auto-generated method stub
		return comunidadDao.save(comunidad);
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		comunidadDao.deleteById(id);
	}


}
