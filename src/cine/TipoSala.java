package cine;

public enum TipoSala {

    ESTANDAR(10),
    PREMIUM(15),
    _3D(20);

    private double precioSala;

    private TipoSala(double precioSala) {
        this.precioSala = precioSala;
    }

    public double getPrecioSala() {
        return precioSala;
    }
}
