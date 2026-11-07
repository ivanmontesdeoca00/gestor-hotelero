package test;

import controlador.ControladorHotel;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;


import javax.swing.table.DefaultTableModel;
import java.awt.TextField;

public class ControladorHotelTest {

    private ControladorHotel controladorHotel;
    private DefaultTableModel modeloTabla;

    @Before
    public void setUp() {
        controladorHotel = new ControladorHotel(); // Aca inicializo el controlador para cada cuadro del test .
        // el modeloTabla es un inicializador de la tabla que se va a usar para los test haciendo como 
        // si estuvieran los campos vacios y se van a ir llenando con los datos que agrego en cada test correspondiente.
        modeloTabla = new DefaultTableModel(new String[]{"Codigo", "Nombre", "Tipo Hab", "Num Hab", "Dias", "Costo Total"}, 0);
    }

    @Test
    public void testAgregarRegistro() { //Esto lo tuve que adaptar porque el metodo requiere un default para poder ser ejecutado bien y este es el que se encarga de la interfaz
        // aparte despues se compara con el assertEquals y si el test esta bien es porque es compatible con modeloTabla. 

        controladorHotel.agregarRegistro("ivan", "SIMPLE", "101", 3, modeloTabla);
        
        assertEquals(1, modeloTabla.getRowCount());
        assertEquals("ivan", modeloTabla.getValueAt(0, 1).toString());
    }

    @Test
    public void testConsultarRegistro() { //es el testeo del metodo consulta con un preseteo en texto plano para poder tomarlo valido 
        controladorHotel.agregarRegistro("ivan", "SIMPLE", "101", 3, modeloTabla);
        
        TextField txtCodigo = new TextField();
        TextField txtNombre = new TextField();
        TextField txtTipoHabitacion = new TextField();
        TextField txtNumeroHabitacion = new TextField();
        TextField txtDiasHospedaje = new TextField();
        
        
        controladorHotel.consultarRegistro(0, modeloTabla, txtCodigo, txtNombre, txtTipoHabitacion, txtNumeroHabitacion, txtDiasHospedaje);
        
        assertEquals("ivan", txtNombre.getText());
        assertEquals("SIMPLE", txtTipoHabitacion.getText());
        assertEquals("101", txtNumeroHabitacion.getText());
        assertEquals("3", txtDiasHospedaje.getText());
    }

    @Test
    public void testModificarRegistro() {
        controladorHotel.agregarRegistro("ivan", "SIMPLE", "101", 3, modeloTabla);
        
        // aca genero el codigo del primer registro 
        String codigoGenerado = modeloTabla.getValueAt(0, 0).toString();
        
        // aca es cuando lo modificado es enviado 
        controladorHotel.modificarRegistro(0, codigoGenerado, "Vanessa", "DOBLE", "102", 5, modeloTabla);
        
        assertEquals(codigoGenerado, modeloTabla.getValueAt(0, 0).toString());
        assertEquals("Vanessa", modeloTabla.getValueAt(0, 1).toString());
        assertEquals("DOBLE", modeloTabla.getValueAt(0, 2).toString());
        assertEquals("102", modeloTabla.getValueAt(0, 3).toString());
        assertEquals("5", modeloTabla.getValueAt(0, 4).toString());
    }

    @Test
    public void testEliminarRegistro() {
        controladorHotel.agregarRegistro("ivan", "SIMPLE", "101", 3, modeloTabla);
        controladorHotel.eliminarRegistro(modeloTabla, 0);
        
        assertEquals(0, modeloTabla.getRowCount());
    }
}
