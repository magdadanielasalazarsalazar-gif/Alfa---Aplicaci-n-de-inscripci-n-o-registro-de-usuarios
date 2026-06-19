package Aplicacion_de_inscripcion_de_usuario;

import javax.swing.*;

// Clase que inicia la aplicación
public class Aplicacion_de_inscripcion_de_usuario {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaRegistro ventana = new VentanaRegistro();
            ventana.setVisible(true);
            System.out.println("¡Bienvenido a la aplicación de inscripción de usuarios!");
            // Aquí se puede agregar más lógica de inicialización en el futuro
        });
    }
}
