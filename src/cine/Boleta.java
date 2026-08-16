package cine;

public abstract class Boleta implements Reembolsable {

    protected String codigo;
    protected Funcion funcion;
    protected Asiento[] asientos;
    protected Cliente cliente;

    public Boleta(String codigo, Funcion funcion, Asiento[] asientos, Cliente cliente)
            throws DatoInvalidoException, ClasificacionNoPermitidaException {

        if (codigo == null || codigo.trim().isEmpty()) {
            throw new DatoInvalidoException("El codigo de la boleta no puede estar vacio");
        }
        if (funcion == null) {
            throw new DatoInvalidoException("La boleta debe estar asociada a una funcion");
        }
        if (cliente == null) {
            throw new DatoInvalidoException("La boleta debe estar asociada a un cliente");
        }
        if (asientos == null || asientos.length == 0) {
            throw new DatoInvalidoException("La boleta debe tener al menos un asiento");
        }

        // Revisamos que no venga ningun asiento nulo dentro del arreglo
        boolean hayAsientoNulo = false;
        int i = 0;
        while (i < asientos.length && !hayAsientoNulo) {
            if (asientos[i] == null) {
                hayAsientoNulo = true;
            }
            i++;
        }
        if (hayAsientoNulo) {
            throw new DatoInvalidoException("La boleta tiene asientos invalidos");
        }

        // El cliente debe cumplir la clasificacion de la pelicula de esa funcion
        if (!funcion.getPelicula().esApta(cliente.getEdad())) {
            throw new ClasificacionNoPermitidaException("El cliente " + cliente.getNombre()
                    + " no cumple la edad minima para la pelicula "
                    + funcion.getPelicula().getCodigo());
        }

        this.codigo = codigo;
        this.funcion = funcion;
        this.asientos = asientos;
        this.cliente = cliente;
    }

    public abstract double calcularPrecio();


    public abstract String getTipo();


    @Override
    public boolean esReembolsable(int horasAntes) {
        return horasAntes >= 3;
    }


    @Override
    public double calcularReembolso(int horasAntes) {
        double reembolso = 0;
        if (esReembolsable(horasAntes)) {
            if (horasAntes >= 24) {
                reembolso = calcularPrecio();
            } else {
                reembolso = calcularPrecio() * 0.5;
            }
        }
        return reembolso;
    }

    public String getCodigo() {
        return codigo;
    }

    public Funcion getFuncion() {
        return funcion;
    }

    public int getNumAsientos() {
        return asientos.length;
    }

    public Asiento getAsiento(int indice) {
        Asiento asiento = null;
        if (indice >= 0 && indice < asientos.length) {
            asiento = asientos[indice];
        }
        return asiento;
    }
}