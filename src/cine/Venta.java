package cine;

import java.time.LocalDateTime;
import java.util.Arrays;

public class Venta {

    private String codigo;
    private Cliente cliente;
    private Boleta[] boletas;
    private Asiento[] asientos;
    private int numBoletas;
    private FormaPago metodoPago;
    private LocalDateTime fecha;

    public Venta(String codigo, Cliente cliente, Boleta[] boletas, Asiento[] asientos,
                 int numBoletas, FormaPago metodoPago, LocalDateTime fecha)
            throws DatoInvalidoException {

        if (codigo == null || codigo.trim().isEmpty()) {
            throw new DatoInvalidoException("El codigo de la venta no puede estar vacio");
        }
        if (cliente == null) {
            throw new DatoInvalidoException("La venta debe tener un cliente asociado");
        }
        if (metodoPago == null) {
            throw new DatoInvalidoException("Debe seleccionar la forma de pago");
        }
        if (fecha == null) {
            throw new DatoInvalidoException("La fecha de la venta no puede estar vacia");
        }

        // Si no llegan boletas o asientos, la venta arranca con arreglos vacios
        if (boletas == null) {
            this.boletas = new Boleta[0];
        } else {
            this.boletas = boletas;
        }
        if (asientos == null) {
            this.asientos = new Asiento[0];
        } else {
            this.asientos = asientos;
        }

        if (numBoletas != this.boletas.length) {
            throw new DatoInvalidoException("El numero de boletas no coincide con las boletas recibidas");
        }

        this.codigo = codigo;
        this.cliente = cliente;
        this.numBoletas = numBoletas;
        this.metodoPago = metodoPago;
        this.fecha = fecha;
    }


    public void agregarBoleta(Boleta boleta) throws DatoInvalidoException {
        if (boleta == null) {
            throw new DatoInvalidoException("No se puede agregar una boleta vacia");
        }
        if (buscarBoleta(boleta.getCodigo()) != null) {
            throw new DatoInvalidoException("Ya existe una boleta con el codigo " + boleta.getCodigo());
        }

        boletas = Arrays.copyOf(boletas, boletas.length + 1);
        boletas[boletas.length - 1] = boleta;
        numBoletas = boletas.length;
    }


    public double calcularTotal() {
        double total = 0;
        int i = 0;
        while (i < boletas.length) {
            total = total + boletas[i].calcularPrecio();
            i++;
        }
        return total;
    }

    public double calcularReembolsoTotal(int horasAntes) {
        double total = 0;
        int i = 0;
        while (i < boletas.length) {
            total = total + boletas[i].calcularReembolso(horasAntes);
            i++;
        }
        return total;
    }

    public Boleta buscarBoleta(String codigo) {
        Boleta boletaEncontrada = null;
        int i = 0;
        while (i < boletas.length && boletaEncontrada == null) {
            if (boletas[i].getCodigo().equalsIgnoreCase(codigo)) {
                boletaEncontrada = boletas[i];
            }
            i++;
        }
        return boletaEncontrada;
    }

    public Boleta getBoleta(int indice) {
        Boleta boleta = null;
        if (indice >= 0 && indice < boletas.length) {
            boleta = boletas[indice];
        }
        return boleta;
    }

    public int getNumBoletas() {
        return numBoletas;
    }

    public String getCodigo() {
        return codigo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public FormaPago getFormaPago() {
        return metodoPago;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }
}