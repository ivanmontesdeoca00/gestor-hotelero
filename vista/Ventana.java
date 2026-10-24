package vista;

import controlador.ControladorHotel; 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class Ventana extends Frame {

    // Componentes de la interfaz gráfica
    private Label lblTitulo, lblCodigo, lblNombre, lblTipoHabitacion, lblNumeroHabitacion, lblDiasHospedaje;
    private TextField txtCodigo, txtNombre, txtTipoHabitacion, txtNumeroHabitacion, txtDiasHospedaje;
    private Button btnAgregar, btnConsultar, btnModificar, btnEliminar;
    private DefaultTableModel modeloTabla;
    private JScrollPane scrollPane;
    private JTable tablaRegistroHotel;


    // Controlador
    ControladorHotel controlador = new ControladorHotel();

    // Constructor de la ventana
    public Ventana() {
        setLayout(null);
        setSize(800, 400);
        setTitle("Registro Hotelero - MVC");
        setBackground(Color.LIGHT_GRAY);
        setLocationRelativeTo(null);
        configurarCamposDeEntradas();
        configurarBotonesDeEventos();
        configurarTablaDeDatos();
    }

    // Método de configuración para los campos de entrada
    private void configurarCamposDeEntradas() {
        lblTitulo = new Label("Sistema de Registro Hotelero");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setBounds(50, 40, 500, 30);
        add(lblTitulo);

        lblCodigo = new Label("Código:");
        lblCodigo.setBounds(50, 100, 80, 20);
        add(lblCodigo);

        txtCodigo = new TextField();
        txtCodigo.setBounds(180, 100, 150, 20);
        add(txtCodigo);

        lblNombre = new Label("Nombre:");
        lblNombre.setBounds(50, 130, 80, 20);
        add(lblNombre);

        txtNombre = new TextField();
        txtNombre.setBounds(180, 130, 150, 20);
        add(txtNombre);

        lblTipoHabitacion = new Label("Tipo Habitación:");
        lblTipoHabitacion.setBounds(50, 160, 100, 20);
        add(lblTipoHabitacion);

        txtTipoHabitacion = new TextField();
        txtTipoHabitacion.setBounds(180, 160, 150, 20);
        add(txtTipoHabitacion);

        lblNumeroHabitacion = new Label("Número Habitación:");
        lblNumeroHabitacion.setBounds(50, 190, 120, 20);
        add(lblNumeroHabitacion);

        txtNumeroHabitacion = new TextField();
        txtNumeroHabitacion.setBounds(180, 190, 150, 20);
        add(txtNumeroHabitacion);

        lblDiasHospedaje = new Label("Días de Hospedaje:");
        lblDiasHospedaje.setBounds(50, 220, 120, 20);
        add(lblDiasHospedaje);

        txtDiasHospedaje = new TextField();
        txtDiasHospedaje.setBounds(180, 220, 150, 20);
        add(txtDiasHospedaje);
    }

    public void configurarTablaDeDatos(){
        String[] columnas = {"Código", "Nombre", "Tipo Habitación", "Número Habitación", "Días Hospedaje"};
        modeloTabla = new DefaultTableModel(columnas, 0);

        tablaRegistroHotel = new JTable(modeloTabla);
        scrollPane = new JScrollPane(tablaRegistroHotel);
        scrollPane.setBounds(350, 100, 400, 200);
        add(scrollPane);
    }


    // Método para configurar los botones
    private void configurarBotonesDeEventos() {
        btnAgregar = new Button("Agregar");
        btnAgregar.setBounds(50, 260, 100, 30);
        add(btnAgregar);

        btnConsultar = new Button("Consultar");
        btnConsultar.setBounds(50, 300, 100, 30);
        add(btnConsultar);

        btnModificar = new Button("Modificar");
        btnModificar.setBounds(180, 300, 100, 30);
        add(btnModificar);

        btnEliminar = new Button("Eliminar");
        btnEliminar.setBounds(180, 260, 100, 30);
        add(btnEliminar);

        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(validarCampos()){
                    String tipoHabitacionNormalizado = txtTipoHabitacion.getText().trim().toUpperCase();
                    String Nombre = txtNombre.getText().trim().toUpperCase();

                    controlador.agregarRegistro(
                        txtCodigo.getText().trim(),
                        Nombre,
                        tipoHabitacionNormalizado,
                        txtNumeroHabitacion.getText().trim(),
                        txtDiasHospedaje.getText().trim(),
                        modeloTabla
                    );
                    limpiarCampos();
                }               
            }
        });

    


       btnConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                int selectedRow = tablaRegistroHotel.getSelectedRow();
                if (selectedRow >= 0) { // Verifica que haya una fila seleccionada
                    controlador.consultarRegistro(selectedRow, modeloTabla, txtCodigo, txtNombre, txtTipoHabitacion, txtNumeroHabitacion, txtDiasHospedaje);
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
                        String tipoHabitacionNormalizado = txtTipoHabitacion.getText().trim().toUpperCase();
                        String Nombre = txtNombre.getText().trim().toUpperCase();

                        controlador.modificarRegistro(
                            selectedRow,
                            txtCodigo.getText().trim(),
                            Nombre,
                            tipoHabitacionNormalizado,
                            txtNumeroHabitacion.getText().trim(),
                            txtDiasHospedaje.getText().trim(),
                            modeloTabla
                        );
                        limpiarCampos();
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
                    mostrarPopup("Registro eliminado ");
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
        if(txtCodigo.getText().trim().isEmpty()){
            mostrarPopup("Campo obligatorio");
        }
        else if(txtNombre.getText().trim().isEmpty()){
            mostrarPopup("Campo  obligatorio");
        }
        else if(txtTipoHabitacion.getText().trim().isEmpty()){
            mostrarPopup("Campo obligatorio");
        }
        else if(txtNumeroHabitacion.getText().trim().isEmpty()){
            mostrarPopup("Campo obligatorio");
        }
        else if(txtDiasHospedaje.getText().trim().isEmpty()){
            mostrarPopup("Campo obligatorio");
        }

        if (txtCodigo.getText().trim().length() != 3) {
            mostrarPopup("El codigo debe tener 3 caracteres OBLIGATORIO");
            return false;
        }
        //validar números
        try{
            Integer.parseInt(txtNumeroHabitacion.getText().trim());
        }
        catch(Exception e){
            System.err.println("Error: " + e);
            mostrarPopup("Campo debe ser un número entero");
            return false;
        }
        try{
            Integer.parseInt(txtDiasHospedaje.getText().trim());
        }
        catch(Exception e){
            System.err.println("Error: " + e);
            mostrarPopup("Campo debe ser un número entero");
            return false;
        }
        return true;
    }

    public void mostrarPopup(String mensaje){
        Dialog popup = new Dialog(this, "Validacion", true);
        popup.setLayout(new FlowLayout());
        popup.add(new Label(mensaje));
        Button btnOk = new Button("Ok");
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                popup.setVisible(false);
            }
        });
        popup.add(btnOk);
        popup.setSize(350, 100);
        popup.setLocationRelativeTo(this);
        popup.setVisible(true);
    }

    public void limpiarCampos(){
        txtCodigo.setText("");
        txtNombre.setText("");
        txtTipoHabitacion.setText("");
        txtNumeroHabitacion.setText("");
        txtDiasHospedaje.setText("");
    }
    
}
