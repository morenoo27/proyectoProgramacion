package entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the cliente database table.
 * 
 */
@Entity
@Table(name="cliente")
@NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codCliente;

	// bi-directional many-to-one association to Pedido
	@OneToMany(mappedBy = "cliente")
	private List<Pedido> pedidos;

	// bi-directional one-to-one association to Usuario
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codUsuario", insertable = false, updatable = false)
	private Usuario usuario;

	// bi-directional one-to-one association to Suscripcion
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codCliente", referencedColumnName = "codCliente",  insertable = false, updatable = false)
	private Suscripcion suscripcion;

//	CONSTRUCTORES
	public Cliente() {
	}

//	GETTERS AND SETTERS
	public int getCodCliente() {
		return this.codCliente;
	}

	public void setCodCliente(int codCliente) {
		this.codCliente = codCliente;
	}

	public List<Pedido> getPedidos() {
		return this.pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public Pedido addPedido(Pedido pedido) {
		getPedidos().add(pedido);
		pedido.setCliente(this);

		return pedido;
	}

	public Pedido removePedido(Pedido pedido) {
		getPedidos().remove(pedido);
		pedido.setCliente(null);

		return pedido;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Suscripcion getSuscripcion() {
		return this.suscripcion;
	}

	public void setSuscripcion(Suscripcion suscripcion) {
		this.suscripcion = suscripcion;
	}

	@Override
	public String toString() {
		return "Cliente [codCliente=" + codCliente + ", pedidos=" + pedidos() + " usuario=" + usuario.getCodUsuario()
				+ ", suscripcion=" + suscripcion.getCodSuscripcion() + "]";
	}

	private String pedidos() {

		String texto = null;

		if (!this.pedidos.isEmpty()) {
			for (Pedido pedido : pedidos) {

				texto += String.valueOf(pedido.getCodPedido()) + ",";
			}

			return texto;
		}
		return " ,";
	}
}