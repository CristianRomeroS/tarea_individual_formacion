package com.formacionspring.app.apirestindividual.controllers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.formacionspring.app.apirestindividual.entity.Alumno;
import com.formacionspring.app.apirestindividual.service.AlumnoService;

@RestController
@RequestMapping("/app")
public class AlumnoController {
	@Autowired
	private AlumnoService servicio;
	@GetMapping("/alumnos")
	public List<Alumno> alumnos(){
		return servicio.findAll();
	}
	
	@GetMapping("/alumnos/{id}")
	public ResponseEntity<?> alumno(@PathVariable Long id){
		Alumno alumno=null;
		Map<String,Object> response=new HashMap<>();
		
		try {
			alumno=servicio.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar consulta base de datos");
			response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(alumno==null) {
			response.put("mensaje", "El alumno ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);

		}
		
		return new ResponseEntity<Alumno>(alumno,HttpStatus.OK);
	}
	
	@PostMapping("/alumnos")
	public ResponseEntity<?> save(@RequestBody Alumno alumno) {
		Alumno alumnoNew=null;
		Map<String,Object> response=new HashMap<>();
		try {
			alumnoNew=servicio.save(alumno);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar insercion en la base de datos");
			response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El Alumno ha sido creado con exito");
		response.put("Alumno", alumnoNew);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		
	}
	
	@PutMapping("/alumnos/{id}")
	public ResponseEntity<?> updateAlumno( @RequestBody Alumno alumno, @PathVariable long id) {
		Alumno alumnoActual=servicio.findById(id);
		Map<String,Object> response=new HashMap<>();
		try {
			alumnoActual.setNombre(alumno.getNombre());
			alumnoActual.setApellido(alumno.getApellido());
			alumnoActual.setDni(alumno.getDni());
			alumnoActual.setEmail(alumno.getEmail());
			alumnoActual.setTelefono(alumno.getTelefono());
			alumnoActual.setCodigoPostal(alumno.getCodigoPostal());
			alumnoActual.setDireccion(alumno.getDireccion());
			alumnoActual.setImagenAvatar(alumno.getImagenAvatar());
			alumnoActual.setComunidad(alumno.getComunidad());
			servicio.save(alumnoActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar modificacion en el Alumno ");
			response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El Alumno ha sido modificado  con exito");
		response.put("Alumno", alumnoActual);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	@DeleteMapping("/alumnos/{id}")
	public ResponseEntity<?> deleteCleinte(@PathVariable Long id) {
		Alumno alumnoBorrado=null;
		
		Map<String,Object> response=new HashMap<>();
				
			alumnoBorrado=servicio.findById(id);
			if(alumnoBorrado==null) {
				response.put("mensaje", "El cliente ID: ".concat(id.toString().concat(" no existe en la base de datos")));
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);

			}else {
				try {
					servicio.delete(id);
					String nombreFotoAnterior = alumnoBorrado.getImagenAvatar();
					
					if(nombreFotoAnterior !=null && nombreFotoAnterior.length()>0 ) {
						
						Path rutaFotoAnterior= Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
						File archivoFotoanterior = rutaFotoAnterior.toFile();
						
						if(archivoFotoanterior.exists() && archivoFotoanterior.canRead() ) {
							
							archivoFotoanterior.delete();
						}
					}
				} catch (DataAccessException e) {
					response.put("mensaje", "Error al borrar el alumno ");
					response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));
					return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}

		response.put("mensaje", "El alumno ha sido borrado con exito");
		response.put("cliente", alumnoBorrado);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	
	@PostMapping("/alumnos/upload")
	public ResponseEntity<?> uploadImagen(@RequestParam("archivo")MultipartFile archivo, @RequestParam("id")Long id){
		Map<String,Object> response=new HashMap<>();
		Alumno alumno=servicio.findById(id);
		if(!archivo.isEmpty()) {
			String nombreArchivo= UUID.randomUUID().toString()+"_"+archivo.getOriginalFilename().replace(" ","");
			Path rutaArchivo=Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
			try {
				Files.copy(archivo.getInputStream(), rutaArchivo);
			} catch (IOException e) {
				response.put("mensaje", "Error al intentar subir la imagen ");
				response.put("error", e.getMessage().concat("_ ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			String nombreFotoAnterior = alumno.getImagenAvatar();
			
			if(nombreFotoAnterior !=null && nombreFotoAnterior.length()>0 ) {
				
				Path rutaFotoAnterior= Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
				File archivoFotoanterior = rutaFotoAnterior.toFile();
				
				if(archivoFotoanterior.exists() && archivoFotoanterior.canRead() ) {
					
					archivoFotoanterior.delete();
				
				}
			}

			alumno.setImagenAvatar(nombreArchivo);
			servicio.save(alumno);
			response.put("mensaje", "Se ha subido correctamente el archivo: " + nombreArchivo);
			response.put("Alumno", alumno);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		}else {
			response.put("Archivo", "Archivo vacio");
		}
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	@GetMapping("/alumnos/uploads/img/{nombreImagen:.+}")
	public ResponseEntity<Resource> verImagen(@PathVariable String nombreImagen){
		Path rutaArchivo=Paths.get("uploads").resolve(nombreImagen).toAbsolutePath();
		Resource recurso=null;
		try {
			recurso= new UrlResource(rutaArchivo.toUri());
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		if(!recurso.exists()&&!recurso.isReadable()) {
			throw new RuntimeException("Error no se puede cargar la imagen " +nombreImagen);
		}
		
		HttpHeaders cabecera=new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\""+recurso.getFilename()+"\"");
		return new ResponseEntity<Resource>(recurso,cabecera,HttpStatus.OK);
	}
}
