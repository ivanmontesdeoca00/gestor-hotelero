package modelo;

public class RegistroHotel {
    private String codigo;
    private String nombre;
    private String tipoHabitacion;
    private int numeroHabitacion;
    private int diasHospedaje;

    public RegistroHotel() {
    }

    public RegistroHotel(String codigo, String nombre, String tipoHabitacion, int numeroHabitacion, int diasHospedaje) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipoHabitacion = tipoHabitacion;
        this.numeroHabitacion = numeroHabitacion;
        this.diasHospedaje = diasHospedaje;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(String tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public int getNumeroHabitacion() {
        return numeroHabitacion;
    }

    public void setNumeroHabitacion(int numeroHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
    }

    public int getDiasHospedaje() {
        return diasHospedaje;
    }

    public void setDiasHospedaje(int diasHospedaje) {
        this.diasHospedaje = diasHospedaje;
    }
}