package Aplicacion_de_inscripcion_de_usuario;

import java.util.HashMap;

public class HashMapUsuarios {

private HashMap<String, Usuario> mapa;
    
    public HashMapUsuarios() {
        mapa = new HashMap<>();
    }
    
    public void insertar(Usuario usuario) {
        mapa.put(usuario.id, usuario);
    }
    
    public Usuario buscar(String id) {
        return mapa.get(id);
    }
    
    public void eliminar(String id) {
        mapa.remove(id);
    }

}
