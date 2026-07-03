package Aplicacion_de_inscripcion_de_usuario;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public class VentanaRegistro extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField txtId, txtNombre, txtEmail;
    private JButton btnRegistrar, btnDeshacer, btnEliminar, btnAdmin;
    private JTextArea areaLog;
    private GestorUsuarios gestor;
    private Stack<String> pilaUI; // Pila para acciones en la interfaz

    // CORRECCIÓN: El constructor ahora puede recibir el gestor compartido del sistema
    public VentanaRegistro(GestorUsuarios gestorCompartido) {
        this.gestor = gestorCompartido;
        pilaUI = new Stack<>();
        
        setTitle("Registro de Usuarios - Panel Avanzado");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Evita cerrar toda la app si es secundaria
        setLayout(new BorderLayout());

        // Panel superior para entrada de datos y botones
        JPanel panelEntrada = new JPanel(new GridLayout(5, 2, 5, 5));

        panelEntrada.add(new JLabel("ID:"));
        txtId = new JTextField();
        panelEntrada.add(txtId);

        panelEntrada.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelEntrada.add(txtNombre);

        panelEntrada.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        panelEntrada.add(txtEmail);

        // Botón para registrar
        btnRegistrar = new JButton("Registrar");
        panelEntrada.add(btnRegistrar);

        // Botón para eliminar
        btnEliminar = new JButton("Eliminar Registro");
        panelEntrada.add(btnEliminar);

        // Botón para acceso de administrador
        btnAdmin = new JButton("Acceso de Administrador");
        panelEntrada.add(btnAdmin);

        // Botón para deshacer
        btnDeshacer = new JButton("Deshacer");
        panelEntrada.add(btnDeshacer);

        add(panelEntrada, BorderLayout.NORTH);

        // Área de log
        areaLog = new JTextArea();
        areaLog.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaLog);
        add(scrollPane, BorderLayout.CENTER);

        // Asignación de acciones con expresiones Lambda
        btnRegistrar.addActionListener(e -> registrarUsuario());
        btnEliminar.addActionListener(e -> eliminarUsuario());
        btnAdmin.addActionListener(e -> accesoAdmin());
        btnDeshacer.addActionListener(e -> deshacerAccion());
        
        this.revalidate();
        this.repaint();
    }

    // Constructor por defecto alternativo por si lo necesitas iniciar aislado
    public VentanaRegistro() {
        this(new GestorUsuarios());
    }

    private void registrarUsuario() {
        String id = txtId.getText().trim();
        String nombre = txtNombre.getText().trim();
        String email = txtEmail.getText().trim();

        // Llama al método correcto de tu gestor avanzado
        if (gestor.registrarUsuario(id, nombre, email)) {
            areaLog.append("Usuario registrado con éxito: " + id + "\n");
            pilaUI.push("Registrar " + id);
            limpiarCampos();
        } else {
            areaLog.append("Error al registrar usuario. Verifique duplicados o formato de email.\n");
        }
    }

    private void eliminarUsuario() {
        String id = txtId.getText().trim();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el ID del usuario a eliminar en el campo de texto superior.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // CORRECCIÓN CRÍTICA: Se cambió 'eliminarUsuarioPorId' por el nombre real 'eliminarUsuario'
        boolean eliminado = gestor.eliminarUsuario(id);
        
        if (eliminado) {
            areaLog.append("Usuario eliminado del sistema: " + id + "\n");
            pilaUI.push("Eliminar " + id);
            limpiarCampos();
        } else {
            areaLog.append("No se encontró ningún usuario activo con el ID: " + id + "\n");
        }
    }

    private void accesoAdmin() {
        String clave = JOptionPane.showInputDialog(this, "Ingrese clave de administrador:", "Acceso Administrador", JOptionPane.PLAIN_MESSAGE);
        if (clave != null && clave.equalsIgnoreCase("alfaestructura")) {
            String usuarios = gestor.obtenerTodosUsuarios();
            JTextArea textArea = new JTextArea(20, 50);
            textArea.setText(usuarios);
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            JOptionPane.showMessageDialog(this, scrollPane, "Listado de Usuarios", JOptionPane.INFORMATION_MESSAGE);
        } else if (clave != null) {
            JOptionPane.showMessageDialog(this, "Clave incorrecta.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deshacerAccion() {
        if (!pilaUI.isEmpty()) {
            String accion = pilaUI.pop();
            String[] parts = accion.split(" ");
            String tipo = parts[0];
            String id = parts[1];
            if (tipo.equals("Registrar")) {
                // CORRECCIÓN CRÍTICA: Ajustado el método de eliminación al deshacer un registro
                boolean eliminado = gestor.eliminarUsuario(id);
                if (eliminado) {
                    areaLog.append("Deshecho registro: " + id + "\n");
                }
            } else if (tipo.equals("Eliminar")) {
                areaLog.append("No se puede deshacer la eliminación en esta versión estructurada.\n");
            }
        } else {
            areaLog.append("No hay acciones en la pila para deshacer.\n");
        }
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtEmail.setText("");
    }

    public String obtenerTodosUsuarios() {
        return gestor.obtenerTodosUsuarios();
    }
}

