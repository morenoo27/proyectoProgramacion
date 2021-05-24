package entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the pedido database table.
 * 
 */
@Entity
@Table(name="pedido")
@NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM Pedido p")
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codPedido;

	// bi-directional many-to-one association to Cliente
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codCliente",  insertable = false, updatable = false)
	private Cliente cliente;

	// bi-directional many-to-one association to Trabajador
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codTrabajador", insertable = false, updatable = false)
	private Trabajador trabajador;

	// bi-directional many-to-one association to Dispensadora
	@OneToMany(mappedBy = "pedido")
	private List<Dispensadora> dispensadoras;

//	CONSTRUCTORES
	public Pedido() {
	}

//	GETTERS AND SETTERS
	public int getCodPedido() {
		return this.codPedido;
	}

	public void setCodPedido(int codPedido) {
		this.codPedido = codPedido;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Trabajador getTrabajador() {
		return this.trabajador;
	}

	public void setTrabajador(Trabajador trabajador) {
		this.trabajador = trabajador;
	}

	public List<Dispensadora> getDispensadoras() {
		return this.dispensadoras;
	}

	public void setDispensadoras(List<Dispensadora> dispensadoras) {
		this.dispensadoras = dispensadoras;
	}

	public Dispensadora addDispensadora(Dispensadora dispensadora) {
		getDispensadoras().add(dispensadora);
		dispensadora.setPedido(this);

		return dispensadora;
	}

	public Dispensadora removeDispensadora(Dispensadora dispensadora) {
		getDispensadoras().remove(dispensadora);
		dispensadora.setPedido(null);

		return dispensadora;
	}

	@Override
	public String toString() {
		return "Pedido [codPedido=" + codPedido + ", cliente=" + cliente.getCodCliente() + ", trabajador="
				+ trabajador.getCodTrabajadro() + ", dispensadoras=" + dispensadoras() + "]";
	}

	// METODO QUE DEVUELVE TODOS LOS CODDISPENSADORAS QUE HAY EN UN PEDIDO, SO HAY
	// DISPENSADORAS
	private String dispensadoras() {

		String texto = null;

		if (!this.dispensadoras.isEmpty()) {
			for (Dispensadora dispensadora : dispensadoras) {

				texto += String.valueOf(dispensadora.getCodDispensadora()) + ",";
			}

			return texto;
		}
		return "";
	}

}