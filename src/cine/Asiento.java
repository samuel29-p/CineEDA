package cine;

public class Asiento {

    private String codigo;
    private boolean ocupado;
    private int fila;
    private int columna;
    private boolean preferencial;

    public Asiento(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.ocupado = false;
        this.preferencial = false;

        char letraFila = (char) ('A' + fila);
        this.codigo = letraFila + String.valueOf(columna + 1);
    }

    public String getEtiqueta() {
        return codigo;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public boolean isPreferencial() {
        return preferencial;
    }

    public void setPreferencial(boolean preferencial) {
        this.preferencial = preferencial;
    }
}
