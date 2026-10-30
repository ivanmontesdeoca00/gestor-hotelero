package controlador;

import modelo.Huesped;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.table.DefaultTableModel;

public class ControladorHotel {
    
    private ArrayList<Huesped> listaHuespedes = new ArrayList<>();

    // NUEVO MÉTODO: Define los precios de cada tipo de habitación
    public double obtenerTarifa(String tipoHabitacion) {
        switch (tipoHabitacion) {
            case "Básica": return 45000.0;
            case "Suite": return 80000.0;
            case "Suite Presidencial": return 150000.0;
            default: return 45000.0;
        }
    }

    public double calcularCostoTotal(int diasHospedaje, double tarifaPorDia) {
        double costoTotal = diasHospedaje * tarifaPorDia;
        return Math.round(costoTotal * 100.0) / 100.0;
    }

    public String generarCodigoUnico() {
        int randomNum = (int) (Math.random() * 10000); 
        return "H-" + randomNum + "-" + System.currentTimeMillis(); 
    }

    // MODIFICADO: Ahora calcula el costo basado en el tipo de habitación
    public void agregarRegistro(String nombre, String tipoHabitacion, String numHabitacion, int dias, DefaultTableModel modelo) {
        String codigo = generarCodigoUnico();
        
        // Obtenemos la tarifa dinámica
        double tarifa = obtenerTarifa(tipoHabitacion);
        double costo = calcularCostoTotal(dias, tarifa);
        
        Huesped nuevoHuesped = new Huesped(codigo, nombre, tipoHabitacion, numHabitacion, dias, costo);
        listaHuespedes.add(nuevoHuesped); 
        
        modelo.addRow(new Object[]{codigo, nombre, tipoHabitacion, numHabitacion, dias, costo});
    }

    // MODIFICADO: Recalcula el costo usando la tarifa correcta si se modifica
    public void modificarRegistro(int fila, String codigo, String nombre, String tipoHabitacion, String numHabitacion, int dias, DefaultTableModel modelo) {
        
        double tarifa = obtenerTarifa(tipoHabitacion);
        double nuevoCosto = calcularCostoTotal(dias, tarifa);
        
        if (fila >= 0 && fila < listaHuespedes.size()) {
            Huesped actualizado = new Huesped(codigo, nombre, tipoHabitacion, numHabitacion, dias, nuevoCosto);
            listaHuespedes.set(fila, actualizado); 
        }
        
        modelo.setValueAt(codigo, fila, 0);
        modelo.setValueAt(nombre, fila, 1);
        modelo.setValueAt(tipoHabitacion, fila, 2);
        modelo.setValueAt(numHabitacion, fila, 3);
        modelo.setValueAt(dias, fila, 4);
        modelo.setValueAt(nuevoCosto, fila, 5); 
    }

    public void eliminarRegistro(DefaultTableModel modelo, int fila) {
        if (fila >= 0 && fila < listaHuespedes.size()) {
            listaHuespedes.remove(fila); 
            modelo.removeRow(fila);      
        }
    }

    public Huesped buscarCliente(String criterio) {
        Iterator<Huesped> iterador = listaHuespedes.iterator();
        while (iterador.hasNext()) {
            Huesped h = iterador.next();
            if (h.getNombre().equalsIgnoreCase(criterio) || 
                h.getNumeroHabitacion().equals(criterio) || 
                h.getCodigo().equals(criterio)) {
                return h;
            }
        }
        return null; 
    }
}
