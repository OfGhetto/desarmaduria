package cl.tswoo.lab.app.models;

import java.sql.Date;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="venta")
public class Venta {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String codigo;
	
	@ManyToOne()
	@JoinColumn(name = "vendedor_id")
	private Vendedor vendedor;
	
	Date fecha;
	
	@OneToMany(mappedBy="venta")
	private List<Articulo> articulos=new ArrayList<Articulo>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public List<Articulo> getArticulos() {
		return articulos;
	}

	public void setArticulos(List<Articulo> articulos) {
		this.articulos = articulos;
	}

	public Venta(int id, String codigo, Vendedor vendedor, Date fecha, List<Articulo> articulos) {
		this.id = id;
		this.codigo = codigo;
		this.vendedor = vendedor;
		this.fecha = fecha;
		this.articulos = articulos;
	}

	public Venta() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}
