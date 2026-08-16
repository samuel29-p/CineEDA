package cine;

public class BoletaPreferencial extends Boleta {

    public BoletaPreferencial(String codigo, Funcion funcion, Asiento[] asientos, Cliente cliente)
            throws DatoInvalidoException, ClasificacionNoPermitidaException {
        super(codigo, funcion, asientos, cliente);
    }

    @Override
    public double calcularPrecio() {
        double total = 0;
        int i = 0;
        while (i < asientos.length) {
            total = total + funcion.getPrecio() * 1.5;
            i++;
        }
        return total;
    }

    @Override
    public String getTipo() { return "PREFERENCIAL"; }
}

