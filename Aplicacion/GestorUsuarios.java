package Aplicacion_de_inscripcion_de_usuario;

import java.util.*;


/**
 * Clase que gestiona la lógica de negocio y estructuras de datos para los usuarios.
 * Incluye:
 * - Pila (Stack) para acciones de deshacer.
 * - Cola (LinkedList) para gestionar la cola de espera.
 * - Lista enlazada personalizada para gestionar usuarios en memoria.
 * - HashMap para búsquedas rápidas por ID.
 */
public class GestorUsuarios {
    // ================== Pila ==================
    // Pila para registrar acciones que permiten deshacer cambios (LIFO)
    private Stack<String> pilaAcciones;
    // ==========================================

    // ================== Cola ==================
    // Cola para gestionar la cola de espera de usuarios
    private LinkedList<Usuario> colaEspera;
    // ==========================================

    // ================== Lista enlazada ==================
    // Lista enlazada personalizada para gestionar usuarios antes de almacenarlos en un sistema más avanzado
    private ListaEnlazadaUsuarios listaUsuarios;
    // ====================================================

    // ================== Hashing ==================
    // HashMap para búsquedas rápidas y acceso en tiempo constante
    private HashMap<String, Usuario> hashUsuarios;
    // ==============================================

    public GestorUsuarios() {
        // Inicialización de las estructuras
        pilaAcciones = new Stack<>();
        colaEspera = new LinkedList<>();
        listaUsuarios = new ListaEnlazadaUsuarios();
        hashUsuarios = new HashMap<>();
    }

    /**
     * Valida los datos del usuario antes de registrarlo.
     */
    public boolean validarUsuario(String id, String nombre, String email) {
        if (id.isEmpty() || nombre.isEmpty() || email.isEmpty()) {
            return false;
        }
        // Verifica que no exista ya un usuario con ese ID
        if (hashUsuarios.containsKey(id)) {
            return false;
        }
        // Validación básica del email
        if (!email.contains("@")) {
            return false;
        }
        return true;
    }

    /**
     * Registra un usuario si los datos son válidos.
     */
    public boolean registrarUsuario(String id, String nombre, String email) {
        if (validarUsuario(id, nombre, email)) {
            Usuario nuevo = new Usuario(id, nombre, email);
            // Añade a la lista enlazada
            listaUsuarios.agregar(nuevo);
            // Añade al hash para búsquedas rápidas
            hashUsuarios.put(id, nuevo);
            // Registra la acción en la pila para poder deshacerla
            pilaAcciones.push("Agregar " + id);
            // Añade a la cola de espera
            colaEspera.add(nuevo);
            return true;
        }
        return false;
    }

    /**
     * Busca un usuario por su ID usando el HashMap para acceso rápido.
     */
    public Usuario buscarUsuario(String id) {
        return hashUsuarios.get(id);
    }

    /**
     * Elimina un usuario por su ID.
     * También registra la acción para poder deshacerla.
     */
    public boolean eliminarUsuario(String id) {
        Usuario usuario = hashUsuarios.get(id);
        if (usuario != null) {
            // Elimina de la estructura HashMap
            hashUsuarios.remove(id);
            // Elimina de la lista enlazada
            listaUsuarios.eliminarPorId(id);
            // Registra la acción en la pila
            pilaAcciones.push("Eliminar " + id);
            // Elimina de la cola de espera si está
            colaEspera.remove(usuario);
            return true;
        }
        return false;
    }

    /**
     * Deshace la última acción registrada en la pila.
     * Solo implementa deshacer agregados y eliminaciones.
     */
    public void deshacer() {
        if (!pilaAcciones.isEmpty()) {
            String accion = pilaAcciones.pop();
            String[] parts = accion.split(" ");
            String tipo = parts[0];
            String id = parts[1];
            if (tipo.equals("Agregar")) {
                // Deshacer una adición: eliminar el usuario
                eliminarUsuario(id);
            } else if (tipo.equals("Eliminar")) {
                // Para eliminar, se requerirían datos adicionales
                // Aquí se puede implementar lógica adicional
            }
        }
    }

    /**
     * Muestra el contenido actual de la cola de espera en consola.
     */
    public void mostrarCola() {
        System.out.println("Cola de espera:");
        for (Usuario u : colaEspera) {
            System.out.println("ID: " + u.id + ", Nombre: " + u.nombre);
        }
    }
}
