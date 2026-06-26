package Aplicacion_de_inscripcion_de_usuario;

/**
 * Lista enlazada personalizada para gestionar usuarios antes de almacenarlos en una estructura más avanzada.
 * Utiliza nodos (`NodoUsuario`) para enlazar los usuarios en orden de inserción.
 */
public class ListaEnlazadaUsuarios {
    NodoUsuario cabeza;

    public ListaEnlazadaUsuarios() {
        this.cabeza = null;
    }

    /**
     * Agrega un usuario al final de la lista.
     */
    public void agregar(Usuario usuario) {
        NodoUsuario nuevo = new NodoUsuario(usuario);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            NodoUsuario temp = cabeza;
            while (temp.siguiente != null) {
                temp = temp.siguiente;
            }
            temp.siguiente = nuevo;
        }
    }

    /**
     * Busca un usuario por su ID.
     * @return Usuario si lo encuentra, null si no.
     */
    public Usuario buscarPorId(String id) {
        NodoUsuario temp = cabeza;
        while (temp != null) {
            if (temp.usuario.id.equals(id)) {
                return temp.usuario;
            }
            temp = temp.siguiente;
        }
        return null;
    }

    /**
     * Elimina un usuario por su ID.
     * @return true si eliminó, false si no encontró.
     */
    public boolean eliminarPorId(String id) {
        if (cabeza == null) return false;
        if (cabeza.usuario.id.equals(id)) {
            cabeza = cabeza.siguiente;
            return true;
        }
        NodoUsuario temp = cabeza;
        while (temp.siguiente != null) {
            if (temp.siguiente.usuario.id.equals(id)) {
                temp.siguiente = temp.siguiente.siguiente;
                return true;
            }
            temp = temp.siguiente;
        }
        return false;
    }

    /**
     * NUEVO: Genera una cadena de texto estructurada con todos los usuarios registrados.
     * Resuelve el error de Eclipse en GestorUsuarios y alimenta la interfaz gráfica.
     */
    public String obtenerListadoString() {
        if (cabeza == null) {
            return "No hay usuarios registrados en el sistema actualmente.";
        }
        
        StringBuilder sb = new StringBuilder();
        NodoUsuario temp = cabeza;
        int contador = 1;
        
        while (temp != null) {
            sb.append(contador).append(". ")
              .append("ID: ").append(temp.usuario.id)
              .append(" | Nombre: ").append(temp.usuario.nombre)
              .append(" | Email: ").append(temp.usuario.email) // Muestra el correo electrónico añadido
              .append("\n--------------------------------------------------\n");
            temp = temp.siguiente;
            contador++;
        }
        return sb.toString();
    }

    /**
     * Muestra todos los usuarios en consola.
     */
    public void mostrarUsuarios() {
        NodoUsuario temp = cabeza;
        while (temp != null) {
            System.out.println("ID: " + temp.usuario.id + ", Nombre: " + temp.usuario.nombre + ", Email: " + temp.usuario.email);
            temp = temp.siguiente;
        }
    }
}
