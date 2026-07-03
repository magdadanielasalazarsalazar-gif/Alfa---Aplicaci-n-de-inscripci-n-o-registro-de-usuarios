package Aplicacion_de_inscripcion_de_usuario;

//=================== Árbol AVL (simplificado, solo inserción y búsqueda) ===================
public class NodoAVL {

	Usuario usuario;
    NodoAVL izquierdo, derecho;
    int altura;
    
    public NodoAVL(Usuario usuario) {
        this.usuario = usuario;
        this.altura = 1;
    }
}
