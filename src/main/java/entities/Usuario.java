package entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@Table(name="usuario")
@NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codUsuario;

	private String apellidosUsuario;

	private String dirUsuario;

	private String email;

	private String nombreUsuario;

	// bi-directional one-to-one association to Cliente
	@OneToOne(mappedBy = "usuario", fetch = FetchType.LAZY)
	private Cliente cliente;

	// bi-directional one-to-one association to Trabajador
	@OneToOne(mappedBy = "usuario", fetch = FetchType.LAZY)
	private Trabajador trabajador;

//	CONSTRUCTORES
	public Usuario() {
	}

//	GETTERS AND SETTERS
	public int getCodUsuario() {
		return this.codUsuario;
	}

	public void setCodUsuario(int codUsuario) {
		this.codUsuario = codUsuario;
	}

	public String getApellidosUsuario() {
		return this.apellidosUsuario;
	}

	public void setApellidosUsuario(String apellidosUsuario) {
		this.apellidosUsuario = apellidosUsuario;
	}

	public String getDirUsuario() {
		return this.dirUsuario;
	}

	public void setDirUsuario(String dirUsuario) {
		this.dirUsuario = dirUsuario;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombreUsuario() {
		return this.nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public Cliente getCliente() {

		try {
			return this.cliente;
		} catch (NullPointerException ex) {
			System.out.println("Este usuario no es un cliente");
			return null;
		}
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Trabajador getTrabajador() {

		try {
			return this.trabajador;
		} catch (NullPointerException ex) {
			System.out.println("Este usuario no es un trabajador");
			return null;
		}
	}

	public void setTrabajador(Trabajador trabajador) {
		this.trabajador = trabajador;
	}

//	TO STIRNG
	@Override
	public String toString() {
		return "Usuario [codUsuario=" + codUsuario + ", apellidosUsuario=" + apellidosUsuario + ", dirUsuario="
				+ dirUsuario + ", email=" + email + ", nombreUsuario=" + nombreUsuario + ", cliente=" + cliente()
				+ ", trabajador=" + trabajador() + "]";
	}

	/**
	 * Metodo que devuelve el numero del cliente de un usuario, si lo tiene
	 * 
	 * @return codCliente | ""
	 */
	private String cliente() {

		try {
			return String.valueOf(cliente.getCodCliente());
		} catch (NullPointerException ex) {
			return "";
		}
	}

	/**
	 * Metodo que devuelve el numero del trabajador de un usuario, si lo tiene
	 * 
	 * @return codTrabajadro | ""
	 */
	private String trabajador() {

		try {
			return String.valueOf(trabajador.getCodTrabajadro());
		} catch (NullPointerException ex) {
			return "";
		}
	}

}