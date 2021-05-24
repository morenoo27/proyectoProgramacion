package controladores;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entities.Trabajador;

public class ControladorTrabajador {

	// Factoria para obtener objetos EntityManager
	private static EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory("ProyectoBaseDeDatos");
	private EntityManager em;
	private Query consulta;

	public void deleteTra(Trabajador cli) {

		this.em = entityManagerFactory.createEntityManager();

		Trabajador aux = null;

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

		List<Trabajador> registros = findAll();

		for (Trabajador aux : registros) {
			deleteTra(aux);
			numFilas++;
		}

		em2.getTransaction().begin();

		em2.createNativeQuery("alter table trabajador auto_increment=1;").executeUpdate();

		em2.getTransaction().commit();

		em2.close();

		return numFilas;
	}

	public void modifyTra(Trabajador user) {

		this.em = entityManagerFactory.createEntityManager();

		this.em.getTransaction().begin();

		this.em.merge(user);

		this.em.getTransaction().commit();

		this.em.close();
	}

	public void insertTra(Trabajador user) {

		this.em = entityManagerFactory.createEntityManager();

		this.em.getTransaction().begin();

		this.em.persist(user);

		this.em.getTransaction().commit();

		this.em.close();
	}

	public int insertTras(ArrayList<Trabajador> trabajadores) {

		int numFilas = 0;
		for (Trabajador aux : trabajadores) {

			insertTra(aux);
			numFilas++;
		}

		return numFilas;
	}

	public List<Trabajador> findAll() {

		this.em = entityManagerFactory.createEntityManager();

		this.consulta = em.createNamedQuery("Trabajador.findAll");

		List<Trabajador> listaTrabajadores = (List<Trabajador>) consulta.getResultList();

		this.em.close();

		return listaTrabajadores;
	}
	
	public Trabajador findByPK(int pk) {

		try {
			this.em = entityManagerFactory.createEntityManager();

			Trabajador aux = null;

			this.consulta = em.createNativeQuery("Select * from trabajador where codTrabajador = ?", Trabajador.class);

			this.consulta.setParameter(1, pk);// intercambiar primera ? por pk

			aux = (Trabajador) consulta.getSingleResult();

			this.em.close();

			return aux;
		} catch (NoResultException ex) {
			System.out.println("No se encuentra el dato que se queire buscar");
			return null;
		}

	}
}
