package cine;

public class BoletaGeneral extends Boleta {

    public BoletaGeneral(String codigo, Funcion funcion, Asiento[] asientos, Cliente cliente)
            throws DatoInvalidoException, ClasificacionNoPermitidaException {
        super(codigo, funcion, asientos, cliente);
    }

    @Override
    public double calcularPrecio() {
        double total = 0;
        int i = 0;
        while (i < asientos.length) {
            total = total + funcion.getPrecio();
            i++;
        }
        return total;
    }

    public String getTipo() { return "GENERAL"; }
}
