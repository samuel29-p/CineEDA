package cine;

public class Sala {

    private TipoSala tipoSala;
    private String codigo;
    private boolean disponibilidad;
    private Asiento[][] asientos;

    public Sala(TipoSala tipoSala, String codigo, boolean disponibilidad,
                int filas, int columnas) {

        this.tipoSala = tipoSala;
        this.codigo = codigo;
        this.disponibilidad = disponibilidad;

        asientos = new Asiento[filas][columnas];
    }

    public int capacidadSala() {
        return asientos.length * asientos[0].length;
    }

    public int getFilas() {
        return asientos.length;
    }

    public int getColumnas() {
        return asientos[0].length;
    }

    public Asiento getAsiento(int fila, int col) {
        return asientos[fila][col];
    }

    public String getCodigo() {
        return codigo;
    }
}
