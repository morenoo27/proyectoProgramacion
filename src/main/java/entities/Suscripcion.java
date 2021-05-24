package entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the suscripcion database table.
 * 
 */
@Entity
@Table(name="suscripcion")
@NamedQuery(name="Suscripcion.findAll", query="SELECT s FROM Suscripcion s")
public class Suscripcion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codSuscripcion;

	private double precioMensual;

	//bi-directional one-to-one association to Cliente
	// mapped hace referencia al nombre del atributo de la clase
	@OneToOne(mappedBy="suscripcion", fetch=FetchType.LAZY)
	private Cliente cliente;

	//bi-directional one-to-one association to Dispensadora
	@OneToOne(fetch=FetchType.LAZY)
	//name hace referencia al cnombre en al base de datos
	@JoinColumn(name="codDispensadora", referencedColumnName = "codDispensadora", insertable = false, updatable = false)
	private Dispensadora dispensadora;

	public Suscripcion() {
	}

	public int getCodSuscripcion() {
		return this.codSuscripcion;
	}

	public void setCodSuscripcion(int codSuscripcion) {
		this.codSuscripcion = codSuscripcion;
	}

	public double getPrecioMensual() {
		return this.precioMensual;
	}

	public void setPrecioMensual(double precioMensual) {
		this.precioMensual = precioMensual;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Dispensadora getDispensadora() {
		return this.dispensadora;
	}

	public void setDispensadora(Dispensadora dispensadora) {
		this.dispensadora = dispensadora;
	}

	@Override
	public String toString() {
		return "Suscripcion [codSuscripcion=" + codSuscripcion + ", precioMensual=" + precioMensual + ", cliente="
				+ cliente.getCodCliente() + ", dispensadora=" + dispensadora.getCodDispensadora() + "]";
	}

}