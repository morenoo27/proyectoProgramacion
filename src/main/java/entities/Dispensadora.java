package entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the dispensadora database table.
 * 
 */
@Entity
@Table(name="dispensadora")
@NamedQuery(name = "Dispensadora.findAll", query = "SELECT d FROM Dispensadora d")
public class Dispensadora implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codDispensadora;

	private String tamanio;

	// bi-directional many-to-one association to Pedido
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codDispensadora", referencedColumnName = "codDispensadora", insertable = false, updatable = false)
	private Pedido pedido;

	// bi-directional one-to-one association to Suscripcion
	@OneToOne(mappedBy = "dispensadora", fetch = FetchType.LAZY)
	private Suscripcion suscripcion;

	public Dispensadora() {
	}

	public int getCodDispensadora() {
		return this.codDispensadora;
	}

	public void setCodDispensadora(int codDispensadora) {
		this.codDispensadora = codDispensadora;
	}

	public String getTamanio() {
		return this.tamanio;
	}

	public void setTamanio(String tamanio) {
		this.tamanio = tamanio;
	}

	public Pedido getPedido() {
		return this.pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Suscripcion getSuscripcion() {
		return this.suscripcion;
	}

	public void setSuscripcion(Suscripcion suscripcion) {
		this.suscripcion = suscripcion;
	}

	@Override
	public String toString() {
		return "Dispensadora [codDispensadora=" + codDispensadora + ", tamanio=" + tamanio + ", pedido="
				+ pedido.getCodPedido() + ", suscripcion=" + suscripcion.getCodSuscripcion() + "]";
	}

}