package Aplicacion_de_inscripcion_de_usuario;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

// 1. CLASE PRINCIPAL (Debe llamarse igual que el archivo)
public class Aplicacion_de_inscripcion_de_usuario {
    // Mantenemos el gestor estático aquí para asegurar una única base de datos en memoria viva
    private static final GestorUsuarios GESTOR_UNICO = new GestorUsuarios();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Pasamos el almacenamiento compartido a la ventana
            MainFrame frame = new MainFrame(GESTOR_UNICO);
            frame.setVisible(true);
            System.out.println("¡Bienvenido a la aplicación de inscripción de usuarios!");
        });
    }
}

// 2. VENTANA PRINCIPAL
class MainFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private GestorUsuarios gestor; // Instancia del gestor recibida

    // El constructor ahora recibe el gestor para no resetear los datos registrados
    public MainFrame(GestorUsuarios gestorCompartido) {
        this.gestor = gestorCompartido;

        // Configuración básica de la ventana
        setTitle("Sistema de Gestión de Usuarios");
        setSize(550, 200); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER, 15, 45)); 
        setLocationRelativeTo(null); // Centra la ventana en pantalla

        // =================== BOTÓN 1: REGISTRAR USUARIO (ID, NOMBRE, EMAIL) ===================
        JButton btnRegistrar = new JButton("Registrar Usuario");
        add(btnRegistrar);
        
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 1. Pedir ID
                String id = JOptionPane.showInputDialog(MainFrame.this, "Ingrese el ID del usuario:", "Registrar Usuario", JOptionPane.QUESTION_MESSAGE);
                if (id != null && !id.trim().isEmpty()) {
                    
                    // 2. Pedir Nombre
                    String nombre = JOptionPane.showInputDialog(MainFrame.this, "Ingrese el Nombre del usuario:", "Registrar Usuario", JOptionPane.QUESTION_MESSAGE);
                    if (nombre != null && !nombre.trim().isEmpty()) {
                        
                        // 3. Pedir Email
                        String email = JOptionPane.showInputDialog(MainFrame.this, "Ingrese el Email del usuario:", "Registrar Usuario", JOptionPane.QUESTION_MESSAGE);
                        if (email != null && !email.trim().isEmpty()) {
                            
                            // Se crea el objeto Usuario incluyendo el nuevo campo Email
                            Usuario nuevoUsuario = new Usuario(id, nombre, email);
                            
                            // SE CORRIGIÓ AQUÍ: Ejecutamos la inserción directa sin asignarla a un booleano
                            gestor.insertarUsuario(nuevoUsuario); 
                            
                            JOptionPane.showMessageDialog(MainFrame.this, "Usuario registrado con éxito.", "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            }
        });

        // =================== BOTÓN 2: ELIMINAR REGISTRO ===================
        JButton btnEliminar = new JButton("Eliminar Registro");
        add(btnEliminar);
        
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = JOptionPane.showInputDialog(MainFrame.this, "Ingrese el ID del usuario a eliminar:", "Eliminar Usuario", JOptionPane.WARNING_MESSAGE);
                if (id != null && !id.trim().isEmpty()) {
                    
                    // SE CORRIGIÓ AQUÍ: Ejecutamos la eliminación directa asumiendo que tu método gestor es void
                    gestor.eliminarUsuario(id); 
                    
                    JOptionPane.showMessageDialog(MainFrame.this, "Proceso de eliminación ejecutado para el ID: " + id, "Eliminar Usuario", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        // =================== BOTÓN 3: ACCESO DE ADMINISTRADOR (VER LISTADO) ===================
        JButton btnAdmin = new JButton("Acceso de Administrador");
        add(btnAdmin);
        
        btnAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
                String clave = JOptionPane.showInputDialog(
                    MainFrame.this, 
                    "Ingrese clave de administrador:", 
                    "Acceso Administrador", 
                    JOptionPane.PLAIN_MESSAGE
                );
                
                if (clave != null && clave.equalsIgnoreCase("alfaestructura")) {
                    // Recupera la cadena con el listado desde el gestor
                    String usuarios = gestor.obtenerTodosUsuarios();
                    
                    // Si el listado viene vacío o nulo, mostramos un aviso amigable
                    if (usuarios == null || usuarios.trim().isEmpty() || usuarios.contains("No hay usuarios registrados")) {
                        usuarios = "No hay usuarios registrados en el sistema actualmente.";
                    }
                    
                    JTextArea textArea = new JTextArea(15, 35);
                    textArea.setText(usuarios);
                    textArea.setEditable(false);
                    JScrollPane scrollPane = new JScrollPane(textArea);
                    
                    JOptionPane.showMessageDialog(
                        MainFrame.this, 
                        scrollPane, 
                        "Listado de Usuarios", 
                        JOptionPane.INFORMATION_MESSAGE
                    );
                } else if (clave != null) {
                    JOptionPane.showMessageDialog(
                        MainFrame.this, 
                        "Clave incorrecta.", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
        // ===================================================================
        
        // Asegura que los componentes añadidos se dibujen de inmediato
        this.revalidate();
        this.repaint();
    }
}

