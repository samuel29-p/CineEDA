package cine;

public class Pelicula {

    private String titulo;
    private String codigo;
    private String duracionMin;
    private Genero genero;
    private Clasificacion clasificacion;

    public Pelicula(String titulo, String codigo, String duracionMin,
                    Genero genero, Clasificacion clasificacion) {

        this.titulo = titulo;
        this.codigo = codigo;
        this.duracionMin = duracionMin;
        this.genero = genero;
        this.clasificacion = clasificacion;
    }

    public boolean esApta(int edad) {
        return edad >= clasificacion.getEdadMinima();
    }

    public String getCodigo() {
        return codigo;
    }
}
