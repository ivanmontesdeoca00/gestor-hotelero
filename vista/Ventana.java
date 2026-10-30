package vista;

import controlador.ControladorHotel;
import modelo.Huesped;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Ventana extends Frame {

    private Label lblTitulo, lblCodigo, lblNombre, lblTipoHabitacion, lblNumeroHabitacion, lblDiasHospedaje, lblBuscar;
    private TextField txtCodigo, txtNombre, txtNumeroHabitacion, txtDiasHospedaje, txtBuscar;
    // NUEVO: JComboBox para la lista de opciones
    private JComboBox<String> cmbTipoHabitacion;
    private Button btnAgregar, btnConsultar, btnModificar, btnEliminar, btnBuscar;
    private DefaultTableModel modeloTabla;
    private JScrollPane scrollPane;
    private JTable tablaRegistroHotel;

    ControladorHotel controlador = new ControladorHotel();

    public Ventana() {
        setLayout(null);
    
        setSize(1000, 480); 
        setTitle("Registro Hotelero - MVC");
        setBackground(Color.LIGHT_GRAY);
        setLocationRelativeTo(null);
        
        configurarCamposDeEntradas();
        configurarBuscador(); 
        configurarBotonesDeEventos();
        configurarTablaDeDatos();
    }

    private void configurarCamposDeEntradas() {
        lblTitulo = new Label("Sistema de Registro Hotelero");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setBounds(30, 40, 300, 30);
        add(lblTitulo);

        lblCodigo = new Label("Código (Autogenerado):");
        lblCodigo.setBounds(30, 100, 160, 20);
        add(lblCodigo);

        txtCodigo = new TextField();
        txtCodigo.setBounds(200, 100, 150, 20);
        txtCodigo.setEditable(false); 
        add(txtCodigo);

        lblNombre = new Label("Nombre:");
        lblNombre.setBounds(30, 130, 160, 20);
        add(lblNombre);

        txtNombre = new TextField();
        txtNombre.setBounds(200, 130, 150, 20);
        add(txtNombre);

        lblTipoHabitacion = new Label("Tipo Habitación:");
        lblTipoHabitacion.setBounds(30, 160, 160, 20);
        add(lblTipoHabitacion);

        // CONFIGURACIÓN DEL COMBOBOX CON LAS OPCIONES
        String[] opcionesHabitacion = {"Básica", "Suite", "Suite Presidencial"};
        cmbTipoHabitacion = new JComboBox<>(opcionesHabitacion);
        cmbTipoHabitacion.setBounds(200, 160, 150, 20);
        add(cmbTipoHabitacion);

        lblNumeroHabitacion = new Label("Número Habitación:");
        lblNumeroHabitacion.setBounds(30, 190, 160, 20);
        add(lblNumeroHabitacion);

        txtNumeroHabitacion = new TextField();
        txtNumeroHabitacion.setBounds(200, 190, 150, 20);
        add(txtNumeroHabitacion);

        lblDiasHospedaje = new Label("Días de Hospedaje:");
        lblDiasHospedaje.setBounds(30, 220, 160, 20);
        add(lblDiasHospedaje);

        txtDiasHospedaje = new TextField();
        txtDiasHospedaje.setBounds(200, 220, 150, 20);
        add(txtDiasHospedaje);
    }
    
    private void configurarBuscador() {
        lblBuscar = new Label("Buscar (Nombre/Hab/Cód):");
        lblBuscar.setBounds(400, 60, 180, 20);
        add(lblBuscar);
        
        txtBuscar = new TextField();
        txtBuscar.setBounds(590, 60, 200, 20);
        add(txtBuscar);
        
        btnBuscar = new Button("Buscar");
        btnBuscar.setBounds(800, 60, 100, 20);
        add(btnBuscar);
    }

    public void configurarTablaDeDatos(){
        String[] columnas = {"Código", "Nombre", "Tipo Hab", "N° Hab", "Días", "Costo Total"};
        modeloTabla = new DefaultTableModel(columnas, 0);

        tablaRegistroHotel = new JTable(modeloTabla);
        scrollPane = new JScrollPane(tablaRegistroHotel);
        // Amplié la tabla para ocupar el nuevo espacio
        scrollPane.setBounds(400, 100, 550, 200);
        add(scrollPane);
    }

    private void configurarBotonesDeEventos() {
        btnAgregar = new Button("Agregar");
        btnAgregar.setBounds(50, 270, 100, 30);
        add(btnAgregar);

        btnConsultar = new Button("Consultar");
        btnConsultar.setBounds(50, 310, 100, 30);
        add(btnConsultar);

        btnModificar = new Button("Modificar");
        btnModificar.setBounds(180, 310, 100, 30);
        add(btnModificar);

        btnEliminar = new Button("Eliminar");
        btnEliminar.setBounds(180, 270, 100, 30);
        add(btnEliminar);

        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(validarCampos()){
                    String tipoSeleccionado = cmbTipoHabitacion.getSelectedItem().toString();
                    String nombre = txtNombre.getText().trim().toUpperCase();
                    int dias = Integer.parseInt(txtDiasHospedaje.getText().trim());

                    controlador.agregarRegistro(
                        nombre,
                        tipoSeleccionado,
                        txtNumeroHabitacion.getText().trim(),
                        dias,
                        modeloTabla
                    );
                    limpiarCampos();
                    mostrarPopup("Registro agregado exitosamente");
                }               
            }
        });

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String criterio = txtBuscar.getText().trim();
                if(criterio.isEmpty()){
                    mostrarPopup("Ingrese un dato para buscar.");
                    return;
                }
                
                Huesped encontrado = controlador.buscarCliente(criterio);
                if(encontrado != null) {
                    txtCodigo.setText(encontrado.getCodigo());
                    txtNombre.setText(encontrado.getNombre());
                    cmbTipoHabitacion.setSelectedItem(encontrado.getTipoHabitacion());
                    txtNumeroHabitacion.setText(encontrado.getNumeroHabitacion());
                    txtDiasHospedaje.setText(String.valueOf(encontrado.getDiasHospedaje()));
                    mostrarPopup("Cliente encontrado.");
                } else {
                    mostrarPopup("No se encontró ningún registro.");
                }
            }
        });

        btnConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                int selectedRow = tablaRegistroHotel.getSelectedRow();
                if (selectedRow >= 0) { 
                    txtCodigo.setText(modeloTabla.getValueAt(selectedRow, 0).toString());
                    txtNombre.setText(modeloTabla.getValueAt(selectedRow, 1).toString());
                    cmbTipoHabitacion.setSelectedItem(modeloTabla.getValueAt(selectedRow, 2).toString());
                    txtNumeroHabitacion.setText(modeloTabla.getValueAt(selectedRow, 3).toString());
                    txtDiasHospedaje.setText(modeloTabla.getValueAt(selectedRow, 4).toString());
                } else {
                    mostrarPopup("Error: Seleccione un registro de la tabla para consultar.");
                }
            }
        });

        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                int selectedRow = tablaRegistroHotel.getSelectedRow();
                if (selectedRow >= 0) { 
                    if(validarCampos()){
                        String tipoSeleccionado = cmbTipoHabitacion.getSelectedItem().toString();
                        String nombre = txtNombre.getText().trim().toUpperCase();
                        int dias = Integer.parseInt(txtDiasHospedaje.getText().trim());

                        controlador.modificarRegistro(
                            selectedRow,
                            txtCodigo.getText().trim(),
                            nombre,
                            tipoSeleccionado,
                            txtNumeroHabitacion.getText().trim(),
                            dias, 
                            modeloTabla
                        );
                        limpiarCampos();
                        mostrarPopup("Registro modificado correctamente.");
                    }
                } else {
                    mostrarPopup("Error: Seleccione un registro de la tabla para modificar.");
                }
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tablaRegistroHotel.getSelectedRow();
                if (selectedRow >= 0) {
                    controlador.eliminarRegistro(modeloTabla, selectedRow);
                    limpiarCampos();
                    mostrarPopup("Registro eliminado correctamente.");
                } else {
                    mostrarPopup("Error: Seleccione un registro a eliminar.");
                }
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });       
    } 
    
    public boolean validarCampos(){
        if(txtNombre.getText().trim().isEmpty()){
            mostrarPopup("Campo Nombre es obligatorio");
            return false;
        }
        
        if(txtNumeroHabitacion.getText().trim().isEmpty()){
            mostrarPopup("Campo Número Habitación es obligatorio");
            return false;
        }
        if(txtDiasHospedaje.getText().trim().isEmpty()){
            mostrarPopup("Campo Días Hospedaje es obligatorio");
            return false;
        }

        try{
            Integer.parseInt(txtNumeroHabitacion.getText().trim());
        } catch(Exception e){
            mostrarPopup("El Número de Habitación debe ser un número entero");
            return false;
        }
        try{
            Integer.parseInt(txtDiasHospedaje.getText().trim());
        } catch(Exception e){
            mostrarPopup("Los Días de Hospedaje deben ser un número entero");
            return false;
        }
        return true;
    }

    public void mostrarPopup(String mensaje){
        JOptionPane.showMessageDialog(this, mensaje, "Aviso del Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    public void limpiarCampos(){
        txtCodigo.setText("");
        txtNombre.setText("");
        cmbTipoHabitacion.setSelectedIndex(0); // Vuelve a seleccionar "Básica" por defecto
        txtNumeroHabitacion.setText("");
        txtDiasHospedaje.setText("");
        txtBuscar.setText("");
    }
}
