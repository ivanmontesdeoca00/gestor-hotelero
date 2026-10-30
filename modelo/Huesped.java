package modelo;


public class Huesped {
    private String codigo;
    private String nombre;
    private String tipoHabitacion;
    private String numeroHabitacion;
    private int diasHospedaje;
    private double costoTotal;

    public Huesped(String codigo, String nombre, String tipoHabitacion, String numeroHabitacion, int diasHospedaje, double costoTotal) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipoHabitacion = tipoHabitacion;
        this.numeroHabitacion = numeroHabitacion;
        this.diasHospedaje = diasHospedaje;
        this.costoTotal = costoTotal;
    }

    // Getters necesarios para la búsqueda y visualización
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getNumeroHabitacion() { return numeroHabitacion; }
    public int getDiasHospedaje() { return diasHospedaje; }
    public double getCostoTotal() { return costoTotal; }
    public String getTipoHabitacion() { return tipoHabitacion; }
}