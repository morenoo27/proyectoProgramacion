package controladores;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entities.Pedido;

public class ControladorPedido {

	// Factoria para obtener objetos EntityManager
	private static EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory("ProyectoBaseDeDatos");
	private EntityManager em;
	private Query consulta;

	public void deletePed(Pedido cli) {

		this.em = entityManagerFactory.createEntityManager();

		Pedido aux = null;

		this.em.getTransaction().begin();

		if (!this.em.contains(cli)) {
			aux = this.em.merge(cli);
		}

		this.em.remove(aux);

		this.em.getTransaction().commit();

		this.em.close();
	}

	public int deleteAll() {

		EntityManager em2 = entityManagerFactory.createEntityManager();

		int numFilas = 0;

		List<Pedido> registros = findAll();

		for (Pedido aux : registros) {
			deletePed(aux);
			numFilas++;
		}

		em2.getTransaction().begin();

		em2.createNativeQuery("alter table pedido auto_increment=1;").executeUpdate();

		em2.getTransaction().commit();

		em2.close();

		return numFilas;
	}

	public void modifyPed(Pedido user) {

		this.em = entityManagerFactory.createEntityManager();

		this.em.getTransaction().begin();

		this.em.merge(user);

		this.em.getTransaction().commit();

		this.em.close();
	}

	public void insertPed(Pedido user) {

		this.em = entityManagerFactory.createEntityManager();

		this.em.getTransaction().begin();

		this.em.persist(user);

		this.em.getTransaction().commit();

		this.em.close();
	}

	public int insertDiss(ArrayList<Pedido> suscripciones) {

		int numFilas = 0;
		for (Pedido aux : suscripciones) {

			insertPed(aux);
			numFilas++;
		}

		return numFilas;
	}

	public List<Pedido> findAll() {

		this.em = entityManagerFactory.createEntityManager();

		this.consulta = em.createNamedQuery("Pedido.findAll");

		List<Pedido> listaTrabajadores = consulta.getResultList();

		this.em.close();

		return listaTrabajadores;
	}

	public Pedido findByPK(int pk) {

		try {
			this.em = entityManagerFactory.createEntityManager();

			Pedido aux = null;

			this.consulta = em.createNativeQuery("Select * from pedido where codPedido = ?",
					Pedido.class);

			this.consulta.setParameter(1, pk);// intercambiar primera ? por pk

			aux = (Pedido) consulta.getSingleResult();

			this.em.close();

			return aux;
		} catch (NoResultException ex) {
			System.out.println("No se encuentra el dato que se queire buscar");
			return null;
		}

	}
}
