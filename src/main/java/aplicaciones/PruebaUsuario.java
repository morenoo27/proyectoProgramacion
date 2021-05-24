package aplicaciones;

import java.util.ArrayList;
import java.util.stream.Collectors;

import controladores.ControladorUsuario;
import entities.Usuario;

public class PruebaUsuario {

	static ControladorUsuario cu = new ControladorUsuario();

	public static void main(String[] args) {

//		Almacenamos los datos ya existentes		
		ArrayList<Usuario> usuarios = recogerDatosUsuario();

		System.out.println("------------------------------------------------");
		System.out.println("Usuarios antes de cualquier operacion");
		usuarios.forEach(System.out::println);

//		Volvemos a captar todos los datos
		usuarios = recogerDatosUsuario();

		System.out.println("------------------------------------------------");
		System.out.println("Usuarios despues de la operacion");
		usuarios.forEach(System.out::println);

		System.out.println("------------------------------------------------");
		System.out.println("Busqueda el usuario con pk 2 y 5");
		Usuario aux = new Usuario();
		aux = cu.findByPK(2);
		if (aux != null) {
			System.out.println(aux.toString());
		}
		aux = cu.findByPK(5);
		if (aux != null) {
			System.out.println(aux.toString());
		}

		System.out.println("------------------------------------------------");
		System.out.println("");
		// Creamos un trabajador (registro) y lo introducimos en la tabla y obtenemos de
		// nuevo
		// todos los registros, que son los trabajadores
		Usuario u = new Usuario();
		u.setNombreUsuario("Alejandro");
		u.setApellidosUsuario("Moreno");
		u.setEmail("privado@gmail.com");
		u.setDirUsuario("mi casa");
		cu.insertUser(u);// Si está creado lanzará una excepcións

//		Volvemos a captar todos los datos
		usuarios = recogerDatosUsuario();

		System.out.println("------------------------------------------------");
		System.out.println("Usuarios despues de la operacion");
		usuarios.forEach(System.out::println);

		System.out.println("------------------------------------------------");
		System.out.println("Vamos a modificar el usuario de pk 4");
		aux = cu.findByPK(4);
		if (aux != null) {
			System.out.println(aux.toString());
			Usuario usuarioModificado = cu.findByPK(4);
			usuarioModificado.setEmail("tuxulita33@gmail.com");
			cu.modifyuser(usuarioModificado);
			cu.findByPK(4).toString();
		}

		System.out.println("------------------------------------------------");
		System.out.println("----Todas los datos llamados Manuel----");
		ArrayList<Usuario> listaManuel = new ArrayList<>();
		listaManuel = cu.findByNombre("Manuel").stream().collect(Collectors.toCollection(ArrayList::new));// conversion
																											// a
																											// arraylist
		listaManuel.forEach(System.out::println);

		System.out.println("------------------------------------------------");
		System.out.println("----Borrar el usuario 2----");
		Usuario userDelete = cu.findByPK(2);
		if (userDelete != null) {
			cu.deleteUser(userDelete);
		}

//		Volvemos a captar todos los datos
		usuarios = recogerDatosUsuario();

		System.out.println("------------------------------------------------");
		System.out.println("Usuarios despues de la operacion");
		usuarios.forEach(System.out::println);

//		System.out.println("Se han borrado " + cu.deleteAll() + " registros");

//		Volvemos a captar todos los datos
		usuarios = recogerDatosUsuario();

		System.out.println("------------------------------------------------");
		System.out.println("Usuarios despues de la operacion");
		usuarios.forEach(System.out::println);
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
