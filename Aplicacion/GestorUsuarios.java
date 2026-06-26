package Aplicacion_de_inscripcion_de_usuario;

import java.util.LinkedList;
import java.util.Stack;

public class GestorUsuarios {
    // Estructuras
    private HashMapUsuarios hashUsuarios;        // Hashing
    private ListaEnlazadaUsuarios listaUsuarios;   // Lista enlazada
    private Stack<String> pilaAcciones;          // Pila
    private LinkedList<Usuario> colaEspera;      // Cola
    private ArbolBinario arbolBinario;            // Árbol binario
    private ArbolAVL arbolAVL;                    // Árbol AVL

    public GestorUsuarios() {
        hashUsuarios = new HashMapUsuarios();
        listaUsuarios = new ListaEnlazadaUsuarios();
        pilaAcciones = new Stack<>();
        colaEspera = new LinkedList<>();
        arbolBinario = new ArbolBinario();
        arbolAVL = new ArbolAVL();
    }
    
    // Validación sencilla
    public boolean validarUsuario(String id, String nombre, String email) {
        if (id == null || nombre == null || email == null) return false;
        if (id.isEmpty() || nombre.isEmpty() || email.isEmpty()) return false;
        if (hashUsuarios.buscar(id) != null) return false;
        if (!email.contains("@")) return false;
        return true;
    }
    
    /**
     * PUENTE CON LA INTERFAZ:
     * Tu interfaz gráfica llama a 'insertarUsuario'. Modificamos este método para que 
     * use internamente toda tu lógica de estructuras avanzadas.
     */
    public void insertarUsuario(Usuario usuario) {
        // Ejecutamos tu registro completo pasándole las variables del objeto
        registrarUsuario(usuario.id, usuario.nombre, usuario.email);
    }
    
    // Registrar usuario en todas las estructuras
    public boolean registrarUsuario(String id, String nombre, String email) {
        if (!validarUsuario(id, nombre, email)) return false;
        Usuario nuevo = new Usuario(id, nombre, email);
        
        // Guarda en tu lista enlazada (de aquí lee el administrador)
        listaUsuarios.agregar(nuevo);
        // HashMap
        hashUsuarios.insertar(nuevo);
        // Pila
        pilaAcciones.push("Agregar " + id);
        // Cola
        colaEspera.add(nuevo);
        // Árbol binario
        arbolBinario.insertar(nuevo);
        // Árbol AVL
        arbolAVL.insertar(nuevo);
        
        System.out.println("DEBUG: Usuario registrado con éxito en todas las estructuras.");
        return true;
    }
    
    // Buscar por ID en Hashing
    public Usuario buscarPorId(String id) {
        return hashUsuarios.buscar(id);
    }
    
    // Eliminar usuario de las estructuras
    public boolean eliminarUsuario(String id) {
        Usuario u = hashUsuarios.buscar(id);
        if (u != null) {
            hashUsuarios.eliminar(id);
            listaUsuarios.eliminarPorId(id);
            // Registrar en pila
            pilaAcciones.push("Eliminar " + id);
            // Eliminar de la cola
            colaEspera.remove(u);
            return true;
        }
        return false;
    }
    
    // Deshacer la última acción
    public void deshacer() {
        if (pilaAcciones.isEmpty()) return;
        String accion = pilaAcciones.pop();
        String[] parts = accion.split(" ");
        String tipo = parts[0];
        String id = parts[1];
        if (tipo.equals("Agregar")) {
            eliminarUsuario(id);
        }
    }
    
    // Métodos adicionales para mostrar la cola en consola
    public void mostrarCola() {
        System.out.println("Cola de espera:");
        for (Usuario u : colaEspera) {
            System.out.println("ID: " + u.id + ", Nombre: " + u.nombre);
        }
    }

    /**
     * CONEXIÓN DE ADMINISTRADOR:
     * Llama al método 'obtenerListadoString()' de la lista enlazada que corregimos antes
     * para transferirle los datos acumulados a la interfaz gráfica.
     */
    public String obtenerTodosUsuarios() {
        if (listaUsuarios == null) {
            return "No hay usuarios registrados en el sistema actualmente.";
        }
        return listaUsuarios.obtenerListadoString();
    }
}
