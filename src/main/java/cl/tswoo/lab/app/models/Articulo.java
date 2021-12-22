package cl.tswoo.lab.app.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="articulo")
public class Articulo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty
	private String nombre;
	@NotNull
	@Min(1)
	private int precioCompra;
	@NotNull
	@Min(1)
	private int precioVenta;
	@NotEmpty
	private String marca;
	@NotEmpty
	private String modelo;
	@NotNull
	private int anio;
	

	private String imagen;
	
	@ManyToOne()
	@JoinColumn(name="venta_id")
	private Venta venta;
	
	@ManyToOne()
	@JoinColumn(name = "bodega_id")
	private Bodega bodega;
	
	public Venta getVenta() {
		return venta;
	}
	public Bodega getBodega() {
		return bodega;
	}
	public void setBodega(Bodega bodega) {
		this.bodega = bodega;
	}
	public void setVenta(Venta venta) {
		this.venta = venta;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getPrecioCompra() {
		return precioCompra;
	}
	public void setPrecioCompra(int precioCompra) {
		this.precioCompra = precioCompra;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public int getPrecioVenta() {
		return precioVenta;
	}
	public void setPrecioVenta(int precioVenta) {
		this.precioVenta = precioVenta;
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
	public Articulo(int id, String nombre, int precioCompra, int precioVenta, Bodega bodega, String marca, String modelo, int anio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precioCompra = precioCompra;
		this.precioVenta=precioVenta;
		this.bodega = bodega;
		this.marca=marca;
		this.modelo=modelo;
		this.anio=anio;
	}
	public Articulo() {
		// TODO Auto-generated constructor stub
		this.bodega=new Bodega();
	}
	
	
	
}
