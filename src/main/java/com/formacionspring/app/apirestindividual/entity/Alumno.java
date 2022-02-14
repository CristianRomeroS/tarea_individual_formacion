package com.formacionspring.app.apirestindividual.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="alumnos")
public class Alumno implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(nullable=false)
	private String nombre;
	
	@Column(nullable=false)
	private String apellido;
	
	@Column(nullable=false,unique=true)
	private String email;
	private String dni;
	private int telefono;
	private int codigoPostal;
	private String direccion;
	private String imagenAvatar;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="comunidad_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Comunidad comunidad;
	
	
	
	public String getDni() {
		return dni;
	}


	public void setDni(String dni) {
		this.dni = dni;
	}


	public long getId() {
		return id;
	}


	public String getNombre() {
		return nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public String getEmail() {
		return email;
	}


	public int getTelefono() {
		return telefono;
	}


	public int getCodigoPostal() {
		return codigoPostal;
	}


	public String getDireccion() {
		return direccion;
	}


	public String getImagenAvatar() {
		return imagenAvatar;
	}


	public Comunidad getComunidad() {
		return comunidad;
	}


	public void setId(long id) {
		this.id = id;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}


	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public void setImagenAvatar(String imagenAvatar) {
		this.imagenAvatar = imagenAvatar;
	}


	public void setComunidad(Comunidad comunidad) {
		this.comunidad = comunidad;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
