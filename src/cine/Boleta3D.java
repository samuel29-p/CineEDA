package cine;

public class Boleta3D extends Boleta {

    private static final double PRECIO_GAFAS = 4000;

    public Boleta3D(String codigo, Funcion funcion, Asiento[] asientos, Cliente cliente)
            throws DatoInvalidoException, ClasificacionNoPermitidaException {
        super(codigo, funcion, asientos, cliente);
    }

    @Override
    public double calcularPrecio() {
        double total = 0;
        int i = 0;
        while (i < asientos.length) {
            total = total + funcion.getPrecio() + PRECIO_GAFAS * 1.5;
            i++;
        }
        return total;
    }

    public String getTipo() { return "3D"; }
}