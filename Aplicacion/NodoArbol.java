package Aplicacion_de_inscripcion_de_usuario;

public class NodoArbol {
	Usuario usuario;
    NodoArbol izquierdo, derecho;
    
    public NodoArbol(Usuario usuario) {
        this.usuario = usuario;
        this.izquierdo = null;
        this.derecho = null;
    }

}
