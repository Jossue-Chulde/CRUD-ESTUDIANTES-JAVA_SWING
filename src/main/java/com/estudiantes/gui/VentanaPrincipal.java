package com.estudiantes.gui;

import com.estudiantes.database.EstudianteDAO;
import com.estudiantes.model.Estudiante;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VentanaPrincipal extends JFrame {
    // Componentes de la interfaz
    private JTextField txtId, txtNombres, txtApellidos, txtEdad, txtCarrera, txtSemestre, txtCorreo;
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private JButton btnGuardar, btnActualizar, btnEliminar, btnMostrar;
    private EstudianteDAO estudianteDAO;

    public VentanaPrincipal() {
        estudianteDAO = new EstudianteDAO();
        inicializarComponentes();
        setTitle("Gestión de Estudiantes");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void inicializarComponentes() {
        // Panel principal
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout(10, 10));
        panelPrincipal.setBackground(Color.WHITE);

        // Panel superior con el título CRUD
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(236, 72, 153)); // Color rosado similar a la imagen
        JLabel lblTitulo = new JLabel("CRUD");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setForeground(Color.WHITE);
        panelTitulo.add(lblTitulo);

        // Panel de formulario
        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(new GridBagLayout());
        panelFormulario.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Campos del formulario
        // ID
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        panelFormulario.add(new JLabel("ID:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        txtId = new JTextField();
        txtId.setEnabled(false);
        panelFormulario.add(txtId, gbc);

        // Nombres
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        panelFormulario.add(new JLabel("Nombres:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        txtNombres = new JTextField();
        panelFormulario.add(txtNombres, gbc);

        // Apellidos
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        panelFormulario.add(new JLabel("Apellidos:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        txtApellidos = new JTextField();
        panelFormulario.add(txtApellidos, gbc);

        // Edad
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;
        panelFormulario.add(new JLabel("Edad:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        txtEdad = new JTextField();
        panelFormulario.add(txtEdad, gbc);

        // Carrera
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0;
        panelFormulario.add(new JLabel("Carrera:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        txtCarrera = new JTextField();
        panelFormulario.add(txtCarrera, gbc);

        // Semestre
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 0;
        panelFormulario.add(new JLabel("Semestre:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        txtSemestre = new JTextField();
        panelFormulario.add(txtSemestre, gbc);

        // Correo
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.weightx = 0;
        panelFormulario.add(new JLabel("Correo:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        txtCorreo = new JTextField();
        panelFormulario.add(txtCorreo, gbc);

        // Panel de la tabla
        String[] columnas = {"ID", "Nombres", "Apellidos", "Edad", "Carrera", "Semestre", "Correo"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer la tabla no editable
            }
        };
        tabla = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setPreferredSize(new Dimension(750, 200));

        // Evento para cargar datos al seleccionar una fila
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cargarDatosDeTabla();
            }
        });

        // Panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBotones.setBackground(Color.WHITE);

        btnGuardar = new JButton("Guardar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnMostrar = new JButton("Mostrar");

        // Estilo de botones
        Color colorBoton = new Color(220, 220, 220);
        btnGuardar.setBackground(colorBoton);
        btnActualizar.setBackground(colorBoton);
        btnEliminar.setBackground(colorBoton);
        btnMostrar.setBackground(colorBoton);

        panelBotones.add(btnGuardar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnMostrar);

        // Eventos de los botones
        btnGuardar.addActionListener(e -> guardarEstudiante());
        btnActualizar.addActionListener(e -> actualizarEstudiante());
        btnEliminar.addActionListener(e -> eliminarEstudiante());
        btnMostrar.addActionListener(e -> mostrarEstudiantes());

        // Panel central que contiene formulario, tabla y botones
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setBackground(Color.WHITE);
        panelCentral.add(panelFormulario);
        panelCentral.add(Box.createRigidArea(new Dimension(0, 10)));
        panelCentral.add(scrollPane);
        panelCentral.add(Box.createRigidArea(new Dimension(0, 10)));
        panelCentral.add(panelBotones);

        // Agregar componentes al panel principal
        panelPrincipal.add(panelTitulo, BorderLayout.NORTH);
        panelPrincipal.add(panelCentral, BorderLayout.CENTER);

        add(panelPrincipal);
    }

    // Método para guardar un estudiante
    private void guardarEstudiante() {
        try {
            if (!validarCampos()) {
                return;
            }

            Estudiante estudiante = new Estudiante();
            estudiante.setNombres(txtNombres.getText().trim());
            estudiante.setApellidos(txtApellidos.getText().trim());
            estudiante.setEdad(Integer.parseInt(txtEdad.getText().trim()));
            estudiante.setCarrera(txtCarrera.getText().trim());
            estudiante.setSemestre(Integer.parseInt(txtSemestre.getText().trim()));
            estudiante.setCorreo(txtCorreo.getText().trim());

            if (estudianteDAO.guardar(estudiante)) {
                JOptionPane.showMessageDialog(this, "Estudiante guardado exitosamente");
                limpiarCampos();
                mostrarEstudiantes();
            } else {
                JOptionPane.showMessageDialog(this, "Error al guardar el estudiante", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Edad y Semestre deben ser números", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para mostrar todos los estudiantes
    private void mostrarEstudiantes() {
        modeloTabla.setRowCount(0); // Limpiar la tabla
        List<Estudiante> estudiantes = estudianteDAO.listar();

        for (Estudiante estudiante : estudiantes) {
            Object[] fila = {
                    estudiante.getId(),
                    estudiante.getNombres(),
                    estudiante.getApellidos(),
                    estudiante.getEdad(),
                    estudiante.getCarrera(),
                    estudiante.getSemestre(),
                    estudiante.getCorreo()
            };
            modeloTabla.addRow(fila);
        }
    }

    // Método para actualizar un estudiante
    private void actualizarEstudiante() {
        try {
            if (txtId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Seleccione un estudiante de la tabla", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!validarCampos()) {
                return;
            }

            Estudiante estudiante = new Estudiante();
            estudiante.setId(Integer.parseInt(txtId.getText().trim()));
            estudiante.setNombres(txtNombres.getText().trim());
            estudiante.setApellidos(txtApellidos.getText().trim());
            estudiante.setEdad(Integer.parseInt(txtEdad.getText().trim()));
            estudiante.setCarrera(txtCarrera.getText().trim());
            estudiante.setSemestre(Integer.parseInt(txtSemestre.getText().trim()));
            estudiante.setCorreo(txtCorreo.getText().trim());

            if (estudianteDAO.actualizar(estudiante)) {
                JOptionPane.showMessageDialog(this, "Estudiante actualizado exitosamente");
                limpiarCampos();
                mostrarEstudiantes();
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar el estudiante", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Edad y Semestre deben ser números", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para eliminar un estudiante
    private void eliminarEstudiante() {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un estudiante de la tabla", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro de eliminar este estudiante?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            int id = Integer.parseInt(txtId.getText().trim());

            if (estudianteDAO.eliminar(id)) {
                JOptionPane.showMessageDialog(this, "Estudiante eliminado exitosamente");
                limpiarCampos();
                mostrarEstudiantes();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar el estudiante", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método para cargar datos de la tabla a los campos
    private void cargarDatosDeTabla() {
        int fila = tabla.getSelectedRow();
        if (fila != -1) {
            txtId.setText(tabla.getValueAt(fila, 0).toString());
            txtNombres.setText(tabla.getValueAt(fila, 1).toString());
            txtApellidos.setText(tabla.getValueAt(fila, 2).toString());
            txtEdad.setText(tabla.getValueAt(fila, 3).toString());
            txtCarrera.setText(tabla.getValueAt(fila, 4).toString());
            txtSemestre.setText(tabla.getValueAt(fila, 5).toString());
            txtCorreo.setText(tabla.getValueAt(fila, 6).toString());
        }
    }

    // Método para validar que los campos no estén vacíos
    private boolean validarCampos() {
        if (txtNombres.getText().trim().isEmpty() ||
                txtApellidos.getText().trim().isEmpty() ||
                txtEdad.getText().trim().isEmpty() ||
                txtCarrera.getText().trim().isEmpty() ||
                txtSemestre.getText().trim().isEmpty() ||
                txtCorreo.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    // Método para limpiar los campos
    private void limpiarCampos() {
        txtId.setText("");
        txtNombres.setText("");
        txtApellidos.setText("");
        txtEdad.setText("");
        txtCarrera.setText("");
        txtSemestre.setText("");
        txtCorreo.setText("");
    }
}
