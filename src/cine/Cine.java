package cine;

import java.time.LocalDateTime;
import java.util.Arrays;


public class Cine {
    private String nombre;
    private String direccion;
    private Pelicula[] peliculas;
    private Sala[] salas;
    private Funcion[] funciones;
    private Cliente[] clientes;
    private Venta[] ventas;


    public Cine(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.peliculas = new Pelicula[0];
        this.salas = new Sala[0];
        this.funciones = new Funcion[0];
        this.clientes = new Cliente[0];
        this.ventas = new Venta[0];
    }

    public String getNombre() {return nombre;}

    public String getDireccion() {return direccion;}

    public int getNumPeliculas() {return peliculas.length;}
    public int getNumSalas() {return salas.length;}
    public int getNumFunciones() {return funciones.length;}
    public int getNumClientes() {return clientes.length;}
    public int getNumVentas() {return ventas.length;}

    public Pelicula getPelicula(int indice) {
        Pelicula pelicula = null;
        if (indice >= 0 && indice < peliculas.length) {
            pelicula = peliculas[indice];
        }
        return pelicula;
    }

    public Sala getSala(int indice) {
        Sala sala = null;
        if (indice >= 0 && indice < salas.length) {
            sala = salas[indice];
        }
        return sala;
    }

    public Funcion getFuncion(int indice) {
        Funcion funcion = null;
        if (indice >= 0 && indice < funciones.length) {
            funcion = funciones[indice];
        }
        return funcion;
    }

    public Cliente getCliente(int indice) {
        Cliente cliente = null;
        if (indice >= 0 && indice < clientes.length) {
            cliente = clientes[indice];
        }
        return cliente;
    }

    public Venta getVenta(int indice) {
        Venta venta = null;
        if (indice >= 0 && indice < ventas.length) {
            venta = ventas[indice];
        }
        return venta;
    }

    public Pelicula buscarPelicula(String codigo) {
        Pelicula peliculaEncontrada = null;
        int i = 0;
        while(i < peliculas.length && peliculaEncontrada == null){
            if(peliculas[i].getCodigo().equalsIgnoreCase(codigo)){
                peliculaEncontrada = peliculas[i];
            }
            i++;
        }
        return peliculaEncontrada;
    }

    public Pelicula[] buscarPeliculasPorTitulo(String texto) {
        Pelicula[] resultado = new Pelicula[peliculas.length];
        int encontradas = 0;

        if (texto != null && !texto.trim().isEmpty()) {
            String busqueda = texto.toLowerCase().trim();
            int i = 0;
            while (i < peliculas.length) {
                if (peliculas[i].getTitulo().toLowerCase().contains(busqueda)) {
                    resultado[encontradas] = peliculas[i];
                    encontradas++;
                }
                i++;
            }
        }

        return Arrays.copyOf(resultado, encontradas);
    }

    public Cliente buscarCliente(String cedula){
        Cliente clienteEncontrado = null;
        int i = 0;
        while(i < clientes.length && clienteEncontrado == null){
            if(clientes[i].getCedula().equalsIgnoreCase(cedula)){
                clienteEncontrado = clientes[i];
            }
            i++;
        }
        return clienteEncontrado;
    }

    public Sala buscarSala(String codigo){
        Sala salaEncontrada = null;
        int i = 0;
        while(i < salas.length && salaEncontrada == null){
            if(salas[i].getCodigo().equalsIgnoreCase(codigo)){
                salaEncontrada = salas[i];
            }
            i++;
        }
        return salaEncontrada;
    }

    public Funcion buscarFuncion(String codigo){
        Funcion funcionEncontrada = null;
        int i = 0;
        while(i < funciones.length && funcionEncontrada == null){
            if(funciones[i].getCodigo().equalsIgnoreCase(codigo)){
                funcionEncontrada = funciones[i];
            }
            i++;
        }
        return funcionEncontrada;
    }

    public Venta buscarVenta(String codigo){
        Venta ventaEncontrada = null;
        int i = 0;
        while(i < ventas.length && ventaEncontrada == null){
            if(ventas[i].getCodigo().equalsIgnoreCase(codigo)){
                ventaEncontrada = ventas[i];
            }
            i++;
        }
        return ventaEncontrada;
    }

    public void addSala(String codigo, int filas, int columnas, TipoSala tipoSala)
            throws DatoInvalidoException {

        if (codigo == null || codigo.trim().isEmpty()) {
            throw new DatoInvalidoException("El código de la sala no puede estar vacío");
        }
        if (filas <= 0) {
            throw new DatoInvalidoException("La sala debe tener al menos una fila");
        }
        if (columnas <= 0) {
            throw new DatoInvalidoException("La sala debe tener al menos una columna");
        }
        if (tipoSala == null) {
            throw new DatoInvalidoException("Debe seleccionar el tipo de sala");
        }
        if (buscarSala(codigo) != null) {
            throw new DatoInvalidoException("Ya existe una sala con el código " + codigo);
        }

        salas = Arrays.copyOf(salas, salas.length + 1);
        salas[salas.length - 1] = new Sala(codigo, filas, columnas, tipoSala);
    }

    public void addPelicula(String codigo, String titulo, int duracionMin, Genero genero, Clasificacion clasificacion)
            throws DatoInvalidoException {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new DatoInvalidoException("El código de la película no puede estar vacío");
        }
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new DatoInvalidoException("El título de la película no puede estar vacío");
        }
        if (duracionMin <= 0) {
            throw new DatoInvalidoException("La duración de la película debe ser mayor a cero");
        }
        if (genero == null) {
            throw new DatoInvalidoException("Debe seleccionar el género de la película");
        }
        if (clasificacion == null) {
            throw new DatoInvalidoException("Debe seleccionar la clasificación de la película");
        }
        if (buscarPelicula(codigo) != null) {
            throw new DatoInvalidoException("Ya existe una película con el código " + codigo);
        }

        peliculas = Arrays.copyOf(peliculas, peliculas.length + 1);
        peliculas[peliculas.length - 1] = new Pelicula(codigo, titulo, duracionMin, genero, clasificacion);
    }

    public void addFuncion(String codigo, String codigoPelicula, String codigoSala, LocalDateTime fechaHora, double precioBase) throws DatoInvalidoException{
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new DatoInvalidoException("El código de la función no puede estar vacío");
        }
        if (codigoPelicula == null || codigoPelicula.trim().isEmpty()) {
            throw new DatoInvalidoException("El código de la película no puede estar vacío");
        }
        if (codigoSala == null || codigoSala.trim().isEmpty()) {
            throw new DatoInvalidoException("El código de la sala no puede estar vacío");
        }
        if (fechaHora == null) {
            throw new DatoInvalidoException("La fecha y hora no pueden estar vacías");
        }

        if (precioBase <= 0) {
            throw new DatoInvalidoException("El precio debe ser mayor a cero");
        }
        if (buscarFuncion(codigo) != null) {
            throw new DatoInvalidoException("Ya existe una función con el código " + codigo);
        }
        Pelicula pelicula = buscarPelicula(codigoPelicula);
        if (pelicula == null) {
            throw new DatoInvalidoException("No existe una película con el código " + codigoPelicula);
        }
        Sala sala = buscarSala(codigoSala);
        if (sala == null) {
            throw new DatoInvalidoException("No existe una sala con el código " + codigoSala);
        }

        // Horario que ocuparía la función nueva
        LocalDateTime inicioFuncionNueva = fechaHora;
        LocalDateTime finFuncionNueva = inicioFuncionNueva.plusMinutes(pelicula.getDuracionMin());

        boolean salaOcupadaEnEseHorario = false;
        int i = 0;
        while (i < funciones.length && !salaOcupadaEnEseHorario) {

            // miramos la sala a utilizar
            if (funciones[i].getSala().getCodigo().equalsIgnoreCase(codigoSala)) {

                // Horario que ya ocupa la función existente
                LocalDateTime inicioFuncionExistente = funciones[i].getFechaHora();
                LocalDateTime finFuncionExistente = inicioFuncionExistente.plusMinutes(
                        funciones[i].getPelicula().getDuracionMin());

                // Se cruzan si la nueva empieza antes de que termine la existente
                // Y la nueva termina despues de que la existente empiece
                if (inicioFuncionNueva.isBefore(finFuncionExistente)
                        && finFuncionNueva.isAfter(inicioFuncionExistente)) {
                    salaOcupadaEnEseHorario = true;
                }
            }
            i++;
        }

        if (salaOcupadaEnEseHorario) {
            throw new DatoInvalidoException("La sala " + codigoSala
                    + " ya tiene una función programada en ese horario");
        }

        funciones = Arrays.copyOf(funciones, funciones.length + 1);
        funciones[funciones.length - 1] = new Funcion(codigo, pelicula, sala, fechaHora, precioBase);
    }

    public void addCliente(String cedula, String nombre, int edad, String correo) throws DatoInvalidoException{
        if (cedula == null || cedula.trim().isEmpty()) {
            throw new DatoInvalidoException("El campo cedula es obligatorio.");
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new DatoInvalidoException("El campo nombre es obligatorio.");
        }
        if (edad <= 0 || edad > 110) {
            throw new DatoInvalidoException("Valor de edad invalido.");
        }
        if (correo == null || correo.trim().isEmpty() || !correo.contains("@")) {
            throw new DatoInvalidoException("Correo invalido.");
        }
        if (buscarCliente(cedula) != null) {
            throw new DatoInvalidoException("Ya hay un cliente registrado con esta cedula.");
        }
        clientes =  Arrays.copyOf(clientes, clientes.length + 1);
        clientes[clientes.length-1] = new Cliente(cedula, nombre, edad, correo);
    }
    public void registrarVenta(Venta venta) throws DatoInvalidoException{
        if (venta == null) {
            throw new DatoInvalidoException("No se puede registrar una venta vacia.");
        }
        if (venta.getCodigo() == null || venta.getCodigo().trim().isEmpty()) {
            throw new DatoInvalidoException("La venta debe tener un código válido.");
        }
        if (buscarVenta(venta.getCodigo()) != null) {
            throw new DatoInvalidoException("Ya existe una venta con el código " + venta.getCodigo());
        }
        if (venta.getCliente() == null) {
            throw new DatoInvalidoException("La venta debe tener un cliente asociado.");
        }
        if (buscarCliente(venta.getCliente().getCedula()) == null) {
            throw new DatoInvalidoException("El cliente de la venta no está registrado en el cine.");
        }
        ventas = Arrays.copyOf(ventas, ventas.length + 1);
        ventas[ventas.length-1] = venta;
    }
    public void eliminarPelicula(String codigo) throws DatoInvalidoException {
        if(buscarPelicula(codigo) == null) {
            throw new DatoInvalidoException("No existe una pelicula con el código " + codigo);
        }
        boolean tieneFunciones = false;
        int i = 0;
        while (i < funciones.length && !tieneFunciones) {
            if (funciones[i].getPelicula().getCodigo().equalsIgnoreCase(codigo)) {
                tieneFunciones = true;
            }
            i++;
        }
        if (tieneFunciones) {
            throw new DatoInvalidoException("La película con el código " + codigo + " tiene funciones asociadas.");
        }
        Pelicula[] nuevoArreglo = new Pelicula[peliculas.length - 1];
        int t = 0;
        int j = 0;
        while (t < peliculas.length) {
            if (!peliculas[t].getCodigo().equalsIgnoreCase(codigo)) {
                nuevoArreglo[j] = peliculas[t];
                j++;
            }
            t++;
        }
        this.peliculas = nuevoArreglo;
    }
    public void eliminarFuncion(String codigo) throws DatoInvalidoException {
        if(buscarFuncion(codigo) == null) {
            throw new DatoInvalidoException("No existe la funcion con el codigo " + codigo);
        }
        boolean tieneBoletas = false;
        int i = 0;
        while (i < ventas.length && !tieneBoletas) {
            int k = 0;
            while (k < ventas[i].getNumBoletas() && !tieneBoletas) {
                if (ventas[i].getBoleta(k).getFuncion().getCodigo().equalsIgnoreCase(codigo)) {
                    tieneBoletas = true;
                }
                k++;
            }
            i++;
        }
        if (tieneBoletas) {
            throw new DatoInvalidoException("La función con el código " + codigo + " tiene boletas vendidas y no se puede eliminar.");
        }
        Funcion[] nuevoArreglo = new Funcion[funciones.length - 1];
        int t = 0;
        int j = 0;
        while (t < funciones.length) {
            if (!funciones[t].getCodigo().equalsIgnoreCase(codigo)) {
                nuevoArreglo[j] = funciones[t];
                j++;
            }
            t++;
        }
        this.funciones = nuevoArreglo;
    }
    public void eliminarCliente(String cedula) throws DatoInvalidoException {
        if(buscarCliente(cedula) == null) {
            throw new DatoInvalidoException("No existe un cliente con la cédula " + cedula);
        }
        boolean tieneVentas = false;
        int i = 0;
        while (i < ventas.length && !tieneVentas) {
            if (ventas[i].getCliente().getCedula().equalsIgnoreCase(cedula)) {
                tieneVentas = true;
            }
            i++;
        }
        if (tieneVentas) {
            throw new DatoInvalidoException("El cliente con la cédula " + cedula + " tiene ventas y no se puede eliminar.");
        }
        Cliente[] nuevoArreglo = new Cliente[clientes.length - 1];
        int t = 0;
        int j = 0;
        while (t < clientes.length) {
            if(!clientes[t].getCedula().equalsIgnoreCase(cedula)){
                nuevoArreglo[j] = clientes[t];
                j++;
            }
            t++;
        }
        this.clientes = nuevoArreglo;
    }
}
