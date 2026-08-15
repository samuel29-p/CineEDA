package cine;

import java.time.LocalDateTime;

public class Funcion {

    private String codigo;
    private Pelicula pelicula;
    private Sala sala;
    private LocalDateTime fechaHora;
    private double precio;
    private Asiento[][] asientos;

    public Funcion(String codigo, Pelicula pelicula, Sala sala, LocalDateTime fechaHora, double precio) {
        this.codigo = codigo;
        this.pelicula = pelicula;
        this.sala = sala;
        this.fechaHora = fechaHora;
        this.precio = precio;

        int filas = sala.getFilas();
        int columnas = sala.getColumnas();
        this.asientos = new Asiento[filas][columnas];

        int i = 0;
        while (i < filas) {
            int j = 0;
            while (j < columnas) {
                this.asientos[i][j] = new Asiento(i, j);
                j++;
            }
            i++;
        }
    }

    public void ocuparAsiento(int fila, int columna) throws AsientoOcupadoException, FuncionLlenaException {
        if (estaLlena()) {
            throw new FuncionLlenaException("La funcion " + codigo + " ya no tiene asientos disponibles.");
        }

        Asiento asiento = asientos[fila][columna];
        if (asiento.isOcupado()) {
            throw new AsientoOcupadoException("El asiento " + asiento.getEtiqueta() + " ya esta ocupado.");
        }
        asiento.setOcupado(true);
    }

    public void liberarAsiento(int fila, int columna) {
        Asiento asiento = asientos[fila][columna];
        asiento.setOcupado(false);
    }

    public Asiento buscarAsiento(String etiqueta) {
        Asiento asientoEncontrado = null;
        int i = 0;
        while (i < asientos.length && asientoEncontrado == null) {
            int j = 0;
            while (j < asientos[i].length && asientoEncontrado == null) {
                if (asientos[i][j].getEtiqueta().equalsIgnoreCase(etiqueta)) {
                    asientoEncontrado = asientos[i][j];
                }
                j++;
            }
            i++;
        }
        return asientoEncontrado;
    }

    public int asientosDisponibles() {
        int contador = 0;
        int i = 0;
        while (i < asientos.length) {
            int j = 0;
            while (j < asientos[i].length) {
                if (!asientos[i][j].isOcupado()) {
                    contador++;
                }
                j++;
            }
            i++;
        }
        return contador;
    }

    public boolean estaLlena() {
        return asientosDisponibles() == 0;
    }

    public String getCodigo() {
        return codigo;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public Sala getSala() {
        return sala;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public double getPrecio() {
        return precio;
    }

    public int getFilas() {
        return sala.getFilas();
    }

    public int getColumnas() {
        return sala.getColumnas();
    }

    public Asiento getAsiento(int fila, int columna) {
        return asientos[fila][columna];
    }
}

