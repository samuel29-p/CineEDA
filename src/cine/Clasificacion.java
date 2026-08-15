package cine;

public enum Clasificacion {

    TODO_PUBLICO(0),
    MAYORES_12(12),
    MAYORES_15(15),
    MAYORES_18(18);

    private int edadMinima;

    private Clasificacion(int edadMinima) {
        this.edadMinima = edadMinima;
    }

    public int getEdadMinima() {
        return edadMinima;
    }
}
