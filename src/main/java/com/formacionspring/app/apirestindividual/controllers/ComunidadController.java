package com.formacionspring.app.apirestindividual.controllers;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formacionspring.app.apirestindividual.entity.Comunidad;
import com.formacionspring.app.apirestindividual.service.ComunidadService;

@RestController
@RequestMapping("/app")
public class ComunidadController {
	
	@Autowired
	private ComunidadService servicio;
	
	@GetMapping("/comunidades")
	public List<Comunidad> comunidades(){
		return servicio.findAll();
	}
	
	@GetMapping("/comunidades/{id}")
	public ResponseEntity<?> clienteShow(@PathVariable Long id){
		Comunidad comunidad=null;
		Map<String,Object> response=new HashMap<>();
		
		try {
			comunidad=servicio.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar consulta base de datos");
			response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(comunidad==null) {
			response.put("mensaje", "La comunidad con el ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);

		}
		
		return new ResponseEntity<Comunidad>(comunidad,HttpStatus.OK);
	}
	
	@PostMapping("/comunidades")
	public ResponseEntity<?> save(@RequestBody Comunidad comunidad) {
		Comunidad comunidadNew=null;
		Map<String,Object> response=new HashMap<>();
		try {
			comunidadNew=servicio.save(comunidad);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar insercion en la base de datos");
			response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La comunidad ha sido creado con exito");
		response.put("Comunidad", comunidadNew);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		
	}
	
	@PutMapping("/comunidades/{id}")
	public ResponseEntity<?> updateCliente( @RequestBody Comunidad comunidad, @PathVariable long id) {
		Comunidad comunidadActual=servicio.findById(id);
		Map<String,Object> response=new HashMap<>();
		try {
			comunidadActual.setNombre(comunidad.getNombre());
			servicio.save(comunidadActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar modificacion en la comunidad ");
			response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La comuniad ha sido modificado  con exito");
		response.put("Comunidad", comunidadActual);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/comunidades/{id}")
	public ResponseEntity<?> deleteCleinte(@PathVariable Long id) {
		Comunidad comunidadBorrada=null;
		
		Map<String,Object> response=new HashMap<>();
				
			comunidadBorrada=servicio.findById(id);
			if(comunidadBorrada==null) {
				response.put("mensaje", "La comunidad con el ID: ".concat(id.toString().concat(" no existe en la base de datos")));
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);

			}else {
				try {
					servicio.delete(id);
				} catch (DataAccessException e) {
					response.put("mensaje", "Error al borrar La comunidad ");
					response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));
					return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}

		response.put("mensaje", "La comunidad ha sido borrado con exito");
		response.put("Comunidad", comunidadBorrada);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
}
