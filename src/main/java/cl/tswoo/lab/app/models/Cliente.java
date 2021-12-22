package cl.tswoo.lab.app.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="cliente")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
 
	
	@JoinColumn(name="rut_Cliente")
	@NotEmpty
	@Pattern(regexp="^\\d{1,2}\\.{0,1}\\d{3}\\.{0,1}\\d{3}\\-(\\d|k|k)$", message="El rut ingresado no sigue el formato")
	private String rutCliente;
	
	@NotEmpty
	private String nombre;
	
	@NotEmpty
	private String apellido;
	
	@NotEmpty
	private String email;
	
	
	public Cliente(Integer id, String rutCliente, String nombre, String apellido, String email) {
		super();
		this.id = id;
		this.rutCliente = rutCliente;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email=email;
	}


	public Integer getId() {
		return id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getRutCliente() {
		return rutCliente;
	}


	public void setRutCliente(String rutCliente) {
		this.rutCliente = rutCliente;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public Cliente(){}
	
	
}
