package aplicaciones;

import java.util.ArrayList;
import java.util.stream.Collectors;

import controladores.ControladorCliente;
import controladores.ControladorUsuario;
import entities.Usuario;

public class PruebaCliente {

	static ControladorUsuario cu = new ControladorUsuario();
	static ControladorCliente cc = new ControladorCliente();

	public static void main(String[] args) {

//		Almacenamos los datos ya existentes		
		ArrayList<Usuario> usuarios = recogerDatosUsuario();

		Usuario m = new Usuario();
		m.setNombreUsuario("Miguel");
		m.setApellidosUsuario("Cruz");
		m.setEmail("micuo@gmail.com");
		m.setDirUsuario("av. pozito");
		cu.insertUser(m);// Si está creado lanzará una excepción
		
		ArrayList<Usuario> miguel = cu.findByNombre("Miguel").stream().collect(Collectors.toCollection(ArrayList::new));
		
		Usuario Miguel = miguel.stream()
				.filter(apellido -> .equalsIgnoreCase("Cruz"));
	}

	/**
	 * Metodo que ejecuta el metodo findAll() de la clase ControladorUsuario
	 * 
	 * @return Arraylist de objetos Usuario
	 */
	private static ArrayList<Usuario> recogerDatosUsuario() {

		return cu.findAll().stream().collect(Collectors.toCollection(ArrayList::new)); // conversion por stream a un
																						// arraylist
	}
}
