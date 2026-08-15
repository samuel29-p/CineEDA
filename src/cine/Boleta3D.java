package cine;

public class Boleta3D extends Boleta {

    private double precioGafas;

    public Boleta3D(String codigo, Funcion funcion, Asiento[] asientos, Cliente cliente, double precioGafas)
            throws DatoInvalidoException, ClasificacionNoPermitidaException {
        super(codigo, funcion, asientos, cliente);

        if (precioGafas <= 0) {
            throw new DatoInvalidoException("El precio de las gafas no puede ser negativo");
        }
        this.precioGafas = precioGafas;
    }

    @Override
    public double calcularPrecio() {
        double total = 0;
        int i = 0;
        while (i < asientos.length) {
            total = total + funcion.getPrecio() + precioGafas * 1.5;
            i++;
        }
        return total;
    }
}