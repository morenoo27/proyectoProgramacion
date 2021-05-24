package controladores;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entities.Cliente;

public class ControladorCliente {

	// Factoria para obtener objetos EntityManager
	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ProyectoBaseDeDatos");
	private EntityManager em;
	private Query consulta;

	public void deleteCli(Cliente cli) {

		this.em = entityManagerFactory.createEntityManager();

		Cliente aux = null;

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

		List<Cliente> registros = findAll();

		for (Cliente aux : registros) {
			deleteCli(aux);
			numFilas++;
		}

		em2.getTransaction().begin();

		em2.createNativeQuery("alter table cliente auto_increment=1;").executeUpdate();

		em2.getTransaction().commit();

		em2.close();

		return numFilas;
	}

	public void modifyCli(Cliente user) {

		this.em = entityManagerFactory.createEntityManager();

		this.em.getTransaction().begin();

		this.em.merge(user);

		this.em.getTransaction().commit();

		this.em.close();
	}

	public void insertCli(Cliente user) {

		this.em = entityManagerFactory.createEntityManager();

		this.em.getTransaction().begin();

		this.em.persist(user);

		this.em.getTransaction().commit();

		this.em.close();
	}

	public int insertClis(ArrayList<Cliente> clientes) {

		int numFilas = 0;
		for (Cliente aux : clientes) {

			insertCli(aux);
			numFilas++;
		}

		return numFilas;
	}

	public List<Cliente> findAll() {

		this.em = entityManagerFactory.createEntityManager();

		this.consulta = em.createNamedQuery("Cliente.findAll");

		List<Cliente> listaTrabajadores = consulta.getResultList();

		this.em.close();

		return listaTrabajadores;
	}
	
	public Cliente findByPK(int pk) {

		try {
			this.em = entityManagerFactory.createEntityManager();

			Cliente aux = null;

			this.consulta = em.createNativeQuery("Select * from cliente where codCliente = ?", Cliente.class);

			this.consulta.setParameter(1, pk);// intercambiar primera ? por pk

			aux = (Cliente) consulta.getSingleResult();

			this.em.close();

			return aux;
		} catch (NoResultException ex) {
			System.out.println("No se encuentra el dato que se queire buscar");
			return null;
		}

	}
}
