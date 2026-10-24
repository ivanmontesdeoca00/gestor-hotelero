package controlador;

import java.awt.TextField;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import modelo.RegistroHotel;

public class ControladorHotel {
    private final ArrayList<RegistroHotel> listaRegistro = new ArrayList<>();

    public void agregarRegistro(String codigo, String nombre, String tipoHabitacion, String numeroHabitacion, String diasHospedaje, DefaultTableModel modeloTabla){
        RegistroHotel registro = new RegistroHotel(codigo, nombre, tipoHabitacion, Integer.parseInt(numeroHabitacion), Integer.parseInt(diasHospedaje));
        listaRegistro.add(registro);
        modeloTabla.addRow(new Object[]{codigo, nombre, tipoHabitacion, numeroHabitacion, diasHospedaje});
    } 

    public void consultarRegistro(int selectedRow, DefaultTableModel modeloTabla, TextField txtCodigo, TextField txtNombre, TextField txtTipoHabitacion, TextField txtNumeroHabitacion, TextField txtDiasHospedaje){
        txtCodigo.setText(modeloTabla.getValueAt(selectedRow, 0).toString());
        txtNombre.setText(modeloTabla.getValueAt(selectedRow, 1).toString());
        txtTipoHabitacion.setText(modeloTabla.getValueAt(selectedRow, 2).toString());
        txtNumeroHabitacion.setText(modeloTabla.getValueAt(selectedRow, 3).toString());
        txtDiasHospedaje.setText(modeloTabla.getValueAt(selectedRow, 4).toString());
    }

    public void modificarRegistro(int selectedRow, String codigo, String nombre, String tipoHabitacion, String numeroHabitacion, String diasHospedaje, DefaultTableModel modeloTabla){
        RegistroHotel registro = listaRegistro.get(selectedRow);
        registro.setCodigo(codigo);
        registro.setNombre(nombre);
        registro.setTipoHabitacion(tipoHabitacion);
        registro.setNumeroHabitacion(Integer.parseInt(numeroHabitacion));
        registro.setDiasHospedaje(Integer.parseInt(diasHospedaje));

        modeloTabla.setValueAt(codigo, selectedRow, 0);
        modeloTabla.setValueAt(nombre, selectedRow, 1);
        modeloTabla.setValueAt(tipoHabitacion, selectedRow, 2);
        modeloTabla.setValueAt(numeroHabitacion, selectedRow, 3);
        modeloTabla.setValueAt(diasHospedaje, selectedRow, 4);
    }

    public void eliminarRegistro(DefaultTableModel modeloTabla, int selectedRow){
        listaRegistro.remove(selectedRow);
        modeloTabla.removeRow(selectedRow);
    }
}