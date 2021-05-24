package entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the trabajador database table.
 * 
 */
@Entity
@Table(name="trabajador")
@NamedQuery(name = "Trabajador.findAll", query = "SELECT t FROM Trabajador t")
public class Trabajador implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codTrabajadro;

	// bi-directional many-to-one association to Pedido
	@OneToMany(mappedBy = "trabajador")
	private List<Pedido> pedidosACargo;

	// bi-directional one-to-one association to Usuario
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codUsuario", referencedColumnName = "codUsuario", insertable = false, updatable = false)
	private Usuario usuario;

//	CONTRUCTORES
	public Trabajador() {
	}

//	GETTERS AND SETTERS
	public int getCodTrabajadro() {
		return this.codTrabajadro;
	}

	public void setCodTrabajadro(int codTrabajadro) {
		this.codTrabajadro = codTrabajadro;
	}

	public List<Pedido> getPedidosACargo() {
		return this.pedidosACargo;
	}

	public void setPedidosACargo(List<Pedido> pedidosACargo) {
		this.pedidosACargo = pedidosACargo;
	}

	public Pedido addPedidosACargo(Pedido pedidosACargo) {
		getPedidosACargo().add(pedidosACargo);
		pedidosACargo.setTrabajador(this);

		return pedidosACargo;
	}

	public Pedido removePedidosACargo(Pedido pedidosACargo) {
		getPedidosACargo().remove(pedidosACargo);
		pedidosACargo.setTrabajador(null);

		return pedidosACargo;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

//	TO STRING
	@Override
	public String toString() {
		return "Trabajador [codTrabajadro=" + codTrabajadro + ", pedidosACargo=" + pedidosACargo() + " usuario="
				+ usuario.getCodUsuario() + "]";
	}

//	METODO PARA OBTENER TODOS LOS CODIUGOS DE LOS PEDIDOS QUE TIENE A CARGO DICHO TRABAJADOR
	private String pedidosACargo() {

		String texto = null;

		if (!this.pedidosACargo.isEmpty()) {

			for (Pedido pedido : pedidosACargo) {
				texto += String.valueOf(pedido.getCodPedido()) + ",";
			}

			return texto;
		}

		return "";
	}

}