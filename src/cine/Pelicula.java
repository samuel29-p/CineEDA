package cine;

public class Pelicula {

    private String titulo;
    private String codigo;
    private int duracionMin;
    private Genero genero;
    private Clasificacion clasificacion;

    public Pelicula(String titulo, String codigo, int duracionMin,
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

    public String getTitulo() { return titulo; }

    public int getDuracionMin() { return duracionMin; }

    public Genero getGenero() { return genero; }

    public Clasificacion getClasificacion() { return clasificacion; }

    @Override
    public String toString() {
        return "Pelicula{" +
                "titulo='" + titulo + '\'' +
                ", codigo='" + codigo + '\'' +
                ", duracionMin=" + duracionMin +
                ", genero=" + genero +
                ", clasificacion=" + clasificacion +
                '}';
    }
}
