package Aplicacion_de_inscripcion_de_usuario;

@SuppressWarnings("unused") // Esto le dice a Eclipse que ignore el error de variable no usada
public class ArbolAVL {
	 private NodoAVL raiz;
	    
	    // Insertar
	    public void insertar(Usuario usuario) {
	        raiz = insertarRecursivo(raiz, usuario);
	    }
	    
	    private NodoAVL insertarRecursivo(NodoAVL nodo, Usuario usuario) {
	        if (nodo == null) return new NodoAVL(usuario);
	        if (usuario.id.compareTo(nodo.usuario.id) < 0)
	            nodo.izquierdo = insertarRecursivo(nodo.izquierdo, usuario);
	        else
	            nodo.derecho = insertarRecursivo(nodo.derecho, usuario);
	        // balancear
	        nodo.altura = 1 + Math.max(altura(nodo.izquierdo), altura(nodo.derecho));
	        int balance = getBalance(nodo);
	        // Rotaciones (implementar si se desea, aquí se omite por simplicidad)
	        return nodo;
	    }
	    
	    public Usuario buscar(String id) {
	        return buscarRecursivo(raiz, id);
	    }
	    
	    private Usuario buscarRecursivo(NodoAVL nodo, String id) {
	        if (nodo == null) return null;
	        if (nodo.usuario.id.equals(id)) return nodo.usuario;
	        if (id.compareTo(nodo.usuario.id) < 0)
	            return buscarRecursivo(nodo.izquierdo, id);
	        else
	            return buscarRecursivo(nodo.derecho, id);
	    }
	    
	    private int altura(NodoAVL n) {
	        if (n == null) return 0;
	        return n.altura;
	    }
	    
	    private int getBalance(NodoAVL n) {
	        if (n == null) return 0;
	        return altura(n.izquierdo) - altura(n.derecho);
	    }
}
