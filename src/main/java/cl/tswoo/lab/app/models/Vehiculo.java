package cl.tswoo.lab.app.models;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="vehiculo")
public class Vehiculo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JoinColumn(name="id_vehiculo")
	private int id;
	
	@NotEmpty
	private String marca;
	@NotEmpty
	private String modelo;
	@NotNull(message="el año es obligatorio")
	@Min(1900)
	@Max(2022)
	private int anio;
	
	@ManyToOne()
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	
	
	
	
	
	public Vehiculo(int id, String marca, String modelo, int anio, Cliente cliente) {
		super();
		this.id = id;
		this.marca = marca;
		this.modelo = modelo;
		this.anio = anio;
		this.cliente = cliente;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getMarca() {
		return marca;
	}



	public void setMarca(String marca) {
		this.marca = marca;
	}



	public String getModelo() {
		return modelo;
	}



	public void setModelo(String modelo) {
		this.modelo = modelo;
	}



	public int getAnio() {
		return anio;
	}



	public void setAnio(int anio) {
		this.anio = anio;
	}



	public Cliente getCliente() {
		return cliente;
	}



	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}



	public Vehiculo() {
		this.cliente=new Cliente();
	}

	
	
	

}
