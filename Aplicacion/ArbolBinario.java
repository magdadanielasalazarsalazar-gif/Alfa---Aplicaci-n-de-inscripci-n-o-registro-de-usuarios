package Aplicacion_de_inscripcion_de_usuario;

public class ArbolBinario {
private NodoArbol raiz;
    
    public ArbolBinario() {
        this.raiz = null;
    }
    
    // Insertar desde cero
    public void insertar(Usuario usuario) {
        raiz = insertarRecursivo(raiz, usuario);
    }
    
    private NodoArbol insertarRecursivo(NodoArbol nodo, Usuario usuario) {
        if (nodo == null) {
            return new NodoArbol(usuario);
        }
        if (usuario.nombre.compareTo(nodo.usuario.nombre) < 0) {
            nodo.izquierdo = insertarRecursivo(nodo.izquierdo, usuario);
        } else {
            nodo.derecho = insertarRecursivo(nodo.derecho, usuario);
        }
        return nodo;
    }
    
    // Buscar por nombre
    public Usuario buscar(String nombre) {
        return buscarRecursivo(raiz, nombre);
    }
    
    private Usuario buscarRecursivo(NodoArbol nodo, String nombre) {
        if (nodo == null) return null;
        if (nodo.usuario.nombre.equals(nombre)) return nodo.usuario;
        if (nombre.compareTo(nodo.usuario.nombre) < 0) {
            return buscarRecursivo(nodo.izquierdo, nombre);
        } else {
            return buscarRecursivo(nodo.derecho, nombre);
        }
    }
    
    // Recorrido inorden
    public void inOrden() {
        inOrdenRecursivo(raiz);
    }
    
    private void inOrdenRecursivo(NodoArbol nodo) {
        if (nodo != null) {
            inOrdenRecursivo(nodo.izquierdo);
            System.out.println("ID: " + nodo.usuario.id + ", Nombre: " + nodo.usuario.nombre);
            inOrdenRecursivo(nodo.derecho);
        }
    }

}
