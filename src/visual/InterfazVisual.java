package visual;

import cine.*;
import guardado.SistemaGuardadoCine;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import java.time.LocalDateTime;

public class InterfazVisual extends JFrame {

    private Cine cine;
    private SistemaGuardadoCine gestor;

    public InterfazVisual(Cine cine, SistemaGuardadoCine gestor) {

        this.cine = cine;
        this.gestor = gestor;

        setTitle("Interfaz Visual - " + cine.getNombre());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 650);
        setLayout(new GridLayout(0, 1, 5, 5));

        agregarBotones();
    }

    private void agregarBotones() {
        JButton btnAgregarPelicula = new JButton("Agregar Pelicula");
        btnAgregarPelicula.addActionListener(e -> agregarPelicula());
        add(btnAgregarPelicula);

        JButton btnAgregarSala = new JButton("Agregar Sala");
        btnAgregarSala.addActionListener(e -> agregarSala());
        add(btnAgregarSala);

        JButton btnAgregarFuncion = new JButton("Agregar Funcion");
        btnAgregarFuncion.addActionListener(e -> agregarFuncion());
        add(btnAgregarFuncion);

        JButton btnAgregarCliente = new JButton("Agregar Cliente");
        btnAgregarCliente.addActionListener(e -> agregarCliente());
        add(btnAgregarCliente);

        JButton btnBuscarPelicula = new JButton("Buscar Pelicula");
        btnBuscarPelicula.addActionListener(e -> buscarPelicula());
        add(btnBuscarPelicula);

        JButton btnBuscarPeliculasPorTitulo = new JButton("Buscar Pelicula por Titulo");
        btnBuscarPeliculasPorTitulo.addActionListener(e -> buscarPorTitulo());
        add(btnBuscarPeliculasPorTitulo);

        JButton btnBuscarSala = new JButton("Buscar Sala");
        btnBuscarSala.addActionListener(e -> buscarSala());
        add(btnBuscarSala);

        JButton btnBuscarFuncion = new JButton("Buscar Funcion");
        btnBuscarFuncion.addActionListener(e -> buscarFuncion());
        add(btnBuscarFuncion);

        JButton btnBuscarCliente = new JButton("Buscar Cliente");
        btnBuscarCliente.addActionListener(e -> buscarCliente());
        add(btnBuscarCliente);

        JButton btnEliminarPelicula = new JButton("Eliminar Pelicula");
        btnEliminarPelicula.addActionListener(e -> eliminarPelicula());
        add(btnEliminarPelicula);

        JButton btnEliminarFuncion = new JButton("Eliminar Funcion");
        btnEliminarFuncion.addActionListener(e -> eliminarFuncion());
        add(btnEliminarFuncion);

        JButton btnEliminarCliente = new JButton("Eliminar Cliente");
        btnEliminarCliente.addActionListener(e -> eliminarCliente());
        add(btnEliminarCliente);

        JButton btnVerResumen = new JButton("Ver Resumen del Cine");
        btnVerResumen.addActionListener(e -> verResumen());
        add(btnVerResumen);

        JButton btnVenderBoleta = new JButton("Vender Boleta");
        btnVenderBoleta.addActionListener(e -> venderBoleta());
        add(btnVenderBoleta);
    }

    private void agregarPelicula() {
        try {
            String codigo = JOptionPane.showInputDialog(this, "Codigo de la pelicula:");
            String titulo = JOptionPane.showInputDialog(this, "Titulo:");
            int duracionMin = Integer.parseInt(JOptionPane.showInputDialog(this, "Duracion en minutos:"));

            Genero genero = (Genero) JOptionPane.showInputDialog(this, "Genero:", "Genero",
                    JOptionPane.QUESTION_MESSAGE, null, Genero.values(), Genero.values()[0]);
            Clasificacion clasificacion = (Clasificacion) JOptionPane.showInputDialog(this, "Clasificacion:",
                    "Clasificacion", JOptionPane.QUESTION_MESSAGE, null, Clasificacion.values(), Clasificacion.values()[0]);

            cine.addPelicula(codigo, titulo, duracionMin, genero, clasificacion);
            gestor.guardarTodo(cine);
            JOptionPane.showMessageDialog(this, "Pelicula agregada correctamente.");
        } catch (DatoInvalidoException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "La duracion debe ser un numero.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void agregarSala() {
        try {
            String codigo = JOptionPane.showInputDialog(this, "Codigo de la sala:");
            int filas = Integer.parseInt(JOptionPane.showInputDialog(this, "Numero de filas:"));
            int columnas = Integer.parseInt(JOptionPane.showInputDialog(this, "Numero de columnas:"));

            TipoSala tipoSala = (TipoSala) JOptionPane.showInputDialog(this, "Tipo de sala:", "Tipo de sala",
                    JOptionPane.QUESTION_MESSAGE, null, TipoSala.values(), TipoSala.values()[0]);

            cine.addSala(codigo, filas, columnas, tipoSala);
            gestor.guardarTodo(cine);
            JOptionPane.showMessageDialog(this, "Sala agregada correctamente.");
        } catch (DatoInvalidoException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Filas y columnas deben ser numeros.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void agregarFuncion() {
        try {
            String codigo = JOptionPane.showInputDialog(this, "Codigo de la funcion:");
            String codigoPelicula = JOptionPane.showInputDialog(this, "Codigo de la pelicula:");
            String codigoSala = JOptionPane.showInputDialog(this, "Codigo de la sala:");
            double precioBase = Double.parseDouble(JOptionPane.showInputDialog(this, "Precio base:"));

            int anio = Integer.parseInt(JOptionPane.showInputDialog(this, "Anio (ej: 2026):"));
            int mes = Integer.parseInt(JOptionPane.showInputDialog(this, "Mes (1-12):"));
            int dia = Integer.parseInt(JOptionPane.showInputDialog(this, "Dia:"));
            int hora = Integer.parseInt(JOptionPane.showInputDialog(this, "Hora (0-23):"));
            int minuto = Integer.parseInt(JOptionPane.showInputDialog(this, "Minuto:"));

            LocalDateTime fechaHora = LocalDateTime.of(anio, mes, dia, hora, minuto);

            cine.addFuncion(codigo, codigoPelicula, codigoSala, fechaHora, precioBase);
            gestor.guardarTodo(cine);
            JOptionPane.showMessageDialog(this, "Funcion agregada correctamente.");
        } catch (DatoInvalidoException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Revisa que los numeros esten bien escritos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void agregarCliente() {
        try {
            String cedula = JOptionPane.showInputDialog(this, "Cedula:");
            String nombre = JOptionPane.showInputDialog(this, "Nombre:");
            int edad = Integer.parseInt(JOptionPane.showInputDialog(this, "Edad:"));
            String correo = JOptionPane.showInputDialog(this, "Correo:");

            cine.addCliente(cedula, nombre, edad, correo);
            gestor.guardarTodo(cine);
            JOptionPane.showMessageDialog(this, "Cliente agregado correctamente.");
        } catch (DatoInvalidoException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "La edad debe ser un numero.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarPelicula() {
        String codigo = JOptionPane.showInputDialog(this, "Codigo de la pelicula a buscar:");
        Pelicula pelicula = cine.buscarPelicula(codigo);
        if (pelicula == null) {
            JOptionPane.showMessageDialog(this, "No se encontro ninguna pelicula con ese codigo.");
        } else {
            JOptionPane.showMessageDialog(this,
                    "Codigo: " + pelicula.getCodigo()
                            + "\nTitulo: " + pelicula.getTitulo()
                            + "\nDuracion: " + pelicula.getDuracionMin() + " min"
                            + "\nGenero: " + pelicula.getGenero()
                            + "\nClasificacion: " + pelicula.getClasificacion()
                            + " (desde " + pelicula.getClasificacion().getEdadMinima() + " años)");
        }
    }

    private void buscarPorTitulo() {
        String texto = JOptionPane.showInputDialog(this, "Escriba parte del titulo:");
        if (texto == null) return;

        Pelicula[] encontradas = cine.buscarPeliculasPorTitulo(texto);
        if (encontradas.length == 0) {
            JOptionPane.showMessageDialog(this, "Ninguna pelicula coincide con '" + texto + "'.");
            return;
        }

        String lista = "Se encontraron " + encontradas.length + " pelicula(s):\n\n";
        int i = 0;
        while (i < encontradas.length) {
            lista = lista + encontradas[i].getCodigo() + " - " + encontradas[i].getTitulo()
                    + " (" + encontradas[i].getGenero() + ", "
                    + encontradas[i].getDuracionMin() + " min)\n";
            i++;
        }
        JOptionPane.showMessageDialog(this, lista);
    }

    private void buscarSala() {
        String codigo = JOptionPane.showInputDialog(this, "Codigo de la sala a buscar:");
        Sala sala = cine.buscarSala(codigo);
        if (sala == null) {
            JOptionPane.showMessageDialog(this, "No se encontro ninguna sala con ese codigo.");
        } else {
            JOptionPane.showMessageDialog(this,
                    "Codigo: " + sala.getCodigo()
                            + "\nTipo: " + sala.getTipoSala()
                            + "\nDistribucion: " + sala.getFilas() + " filas x " + sala.getColumnas() + " columnas"
                            + "\nCapacidad: " + sala.capacidadSala() + " puestos"
                            + "\nRecargo por sala: " + sala.getTipoSala().getPrecioSala()
                            + "\nDisponible: " + sala.isDisponible());
        }
    }

    private void buscarFuncion() {
        String codigo = JOptionPane.showInputDialog(this, "Codigo de la funcion a buscar:");
        if (codigo == null) return;

        Funcion funcion = cine.buscarFuncion(codigo);
        if (funcion == null) {
            JOptionPane.showMessageDialog(this, "No se encontro ninguna funcion con ese codigo.");
        } else {
            int ocupados = funcion.getSala().capacidadSala() - funcion.asientosDisponibles();

            JOptionPane.showMessageDialog(this,
                    "Codigo: " + funcion.getCodigo()
                            + "\nPelicula: " + funcion.getPelicula().getTitulo()
                            + " (" + funcion.getPelicula().getDuracionMin() + " min)"
                            + "\nSala: " + funcion.getSala().getCodigo()
                            + " (" + funcion.getSala().getTipoSala() + ")"
                            + "\nFecha y hora: " + funcion.getFechaHora()
                            + "\nPrecio base: " + funcion.getPrecio()
                            + "\nOcupados: " + ocupados + " de " + funcion.getSala().capacidadSala()
                            + "\nDisponibles: " + funcion.asientosDisponibles());
        }
    }

    private void buscarCliente() {
        String cedula = JOptionPane.showInputDialog(this, "Cedula del cliente a buscar:");
        Cliente cliente = cine.buscarCliente(cedula);
        if (cliente == null) {
            JOptionPane.showMessageDialog(this, "No se encontro ningun cliente con esa cedula.");
        } else {
            JOptionPane.showMessageDialog(this,
                    "Cedula: " + cliente.getCedula()
                            + "\nNombre: " + cliente.getNombre()
                            + "\nEdad: " + cliente.getEdad()
                            + "\nCorreo: " + cliente.getCorreo());
        }
    }

    private void eliminarPelicula() {
        try {
            String codigo = JOptionPane.showInputDialog(this, "Codigo de la pelicula a eliminar:");
            cine.eliminarPelicula(codigo);
            gestor.guardarTodo(cine);
            JOptionPane.showMessageDialog(this, "Pelicula eliminada correctamente.");
        } catch (DatoInvalidoException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarFuncion() {
        try {
            String codigo = JOptionPane.showInputDialog(this, "Codigo de la funcion a eliminar:");
            cine.eliminarFuncion(codigo);
            gestor.guardarTodo(cine);
            JOptionPane.showMessageDialog(this, "Funcion eliminada correctamente.");
        } catch (DatoInvalidoException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarCliente() {
        try {
            String cedula = JOptionPane.showInputDialog(this, "Cedula del cliente a eliminar:");
            cine.eliminarCliente(cedula);
            gestor.guardarTodo(cine);
            JOptionPane.showMessageDialog(this, "Cliente eliminado correctamente.");
        } catch (DatoInvalidoException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void verResumen() {
        String resumen = "Peliculas: " + cine.getNumPeliculas()
                + "\nSalas: " + cine.getNumSalas()
                + "\nFunciones: " + cine.getNumFunciones()
                + "\nClientes: " + cine.getNumClientes()
                + "\nVentas: " + cine.getNumVentas();
        JOptionPane.showMessageDialog(this, resumen);
    }

    private void venderBoleta() {
        try {
            // Cliente
            String cedula = JOptionPane.showInputDialog(this, "Cedula del cliente:");
            if (cedula == null) return;

            Cliente cliente = cine.buscarCliente(cedula);
            if (cliente == null) {
                JOptionPane.showMessageDialog(this, "No existe un cliente con esa cedula.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Funcion
            String codigoFuncion = JOptionPane.showInputDialog(this, "Codigo de la funcion:");
            if (codigoFuncion == null) return;

            Funcion funcion = cine.buscarFuncion(codigoFuncion);
            if (funcion == null) {
                JOptionPane.showMessageDialog(this, "No existe una funcion con ese codigo.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (funcion.estaLlena()) {
                JOptionPane.showMessageDialog(this, "Esa funcion ya no tiene asientos disponibles.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Asiento
            String etiqueta = JOptionPane.showInputDialog(this, funcion + "\n\nAsiento (ejemplo: C4):");
            if (etiqueta == null) return;

            Asiento asiento = funcion.buscarAsiento(etiqueta);
            if (asiento == null) {
                JOptionPane.showMessageDialog(this, "Ese asiento no existe en la sala.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (asiento.isOcupado()) {
                JOptionPane.showMessageDialog(this, "El asiento " + asiento.getEtiqueta() + " ya esta ocupado.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Tipo de boleta y forma de pago
            String[] tipos = {"GENERAL", "PREFERENCIAL", "3D"};
            String tipo = (String) JOptionPane.showInputDialog(this, "Tipo de boleta:", "Tipo de boleta",
                    JOptionPane.QUESTION_MESSAGE, null, tipos, tipos[0]);
            if (tipo == null) return;

            FormaPago formaPago = (FormaPago) JOptionPane.showInputDialog(this, "Forma de pago:", "Forma de pago",
                    JOptionPane.QUESTION_MESSAGE, null, FormaPago.values(), FormaPago.values()[0]);
            if (formaPago == null) return;

            // Se arma la boleta
            Asiento[] asientos = new Asiento[1];
            asientos[0] = asiento;

            String codigoVenta = "V" + System.currentTimeMillis();
            String codigoBoleta = "B" + System.currentTimeMillis();

            Boleta boleta;
            if (tipo.equals("PREFERENCIAL")) {
                boleta = new BoletaPreferencial(codigoBoleta, funcion, asientos, cliente);
            } else if (tipo.equals("3D")) {
                boleta = new Boleta3D(codigoBoleta, funcion, asientos, cliente);
            } else {
                boleta = new BoletaGeneral(codigoBoleta, funcion, asientos, cliente);
            }

            Boleta[] boletas = new Boleta[1];
            boletas[0] = boleta;

            //Se registra la venta en el cine
            Venta venta = new Venta(codigoVenta, cliente, boletas, asientos, 1, formaPago, LocalDateTime.now());
            cine.registrarVenta(venta);

            // Se ocupa el asiento y se guarda
            funcion.ocuparAsiento(asiento.getFila(), asiento.getColumna());
            gestor.guardarTodo(cine);

            JOptionPane.showMessageDialog(this,
                    "Venta registrada correctamente.\n\n"
                            + funcion + "\n"
                            + "Cliente: " + cliente.getNombre() + "\n"
                            + "Asiento: " + asiento.getEtiqueta() + "\n"
                            + "Tipo: " + boleta.getTipo() +"\n"
                            + "Pago: " + formaPago + "\n"
                            + "Total: " + venta.calcularTotal());

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "No se pudo registrar la venta", JOptionPane.ERROR_MESSAGE);
        }
    }
}