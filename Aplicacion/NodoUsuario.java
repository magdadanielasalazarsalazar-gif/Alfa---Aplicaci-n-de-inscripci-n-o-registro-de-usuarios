package Aplicacion_de_inscripcion_de_usuario;

// Nodo para la lista enlazada personalizada
public class NodoUsuario {
    Usuario usuario;
    NodoUsuario siguiente;

    public NodoUsuario(Usuario usuario) {
        this.usuario = usuario;
        this.siguiente = null;
    }
}

