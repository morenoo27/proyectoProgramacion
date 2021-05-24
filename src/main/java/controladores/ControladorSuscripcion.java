package controladores;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entities.Suscripcion;

public class ControladorSuscripcion {

	// Factoria para obtener objetos EntityManager
	private static EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory("ProyectoBaseDeDatos");
	private EntityManager em;
	private Query consulta;

	public void deleteSus(Suscripcion cli) {

		this.em = entityManagerFactory.createEntityManager();

		Suscripcion aux = null;

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

		List<Suscripcion> registros = findAll();

		for (Suscripcion aux : registros) {
			deleteSus(aux);
			numFilas++;
		}

		em2.getTransaction().begin();

		em2.createNativeQuery("alter table suscripcion auto_increment=1;").executeUpdate();

		em2.getTransaction().commit();

		em2.close();

		return numFilas;
	}

	public void modifySus(Suscripcion user) {

		this.em = entityManagerFactory.createEntityManager();

		this.em.getTransaction().begin();

		this.em.merge(user);

		this.em.getTransaction().commit();

		this.em.close();
	}

	public void insertSus(Suscripcion user) {

		this.em = entityManagerFactory.createEntityManager();

		this.em.getTransaction().begin();

		this.em.persist(user);

		this.em.getTransaction().commit();

		this.em.close();
	}

	public int insertSuss(ArrayList<Suscripcion> suscripciones) {

		int numFilas = 0;
		for (Suscripcion aux : suscripciones) {

			insertSus(aux);
			numFilas++;
		}

		return numFilas;
	}

	public List<Suscripcion> findAll() {

		this.em = entityManagerFactory.createEntityManager();

		this.consulta = em.createNamedQuery("Suscripcion.findAll");

		List<Suscripcion> listaTrabajadores = consulta.getResultList();

		this.em.close();

		return listaTrabajadores;
	}

	public Suscripcion findByPK(int pk) {

		try {
			this.em = entityManagerFactory.createEntityManager();

			Suscripcion aux = null;

			this.consulta = em.createNativeQuery("Select * from suscripcion where codSuscripcion = ?", Suscripcion.class);

			this.consulta.setParameter(1, pk);// intercambiar primera ? por pk

			aux = (Suscripcion) consulta.getSingleResult();

			this.em.close();

			return aux;
		} catch (NoResultException ex) {
			System.out.println("No se encuentra el dato que se queire buscar");
			return null;
		}

	}
}
