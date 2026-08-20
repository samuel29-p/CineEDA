package cine;

import guardado.SistemaGuardadoCine;
import java.time.LocalDateTime;

public class PruebaTerminal {

    public static void main(String[] args) throws Exception {

        SistemaGuardadoCine gestor = new SistemaGuardadoCine("datos");
        Cine cine = new Cine("CineEIA", "Envigado");

        // Salas y los seis catalogos
        DatosCine.cargarSalas(cine);
        cargarCatalogo(cine, "accion");
        cargarCatalogo(cine, "animacion");
        cargarCatalogo(cine, "terror");
        cargarCatalogo(cine, "drama");
        cargarCatalogo(cine, "suspenso");
        cargarCatalogo(cine, "comedia");

        System.out.println();
        System.out.println("Salas: " + cine.getNumSalas() + " (esperado 6)");
        System.out.println("Peliculas: " + cine.getNumPeliculas() + " (esperado 17)");
        System.out.println("Funciones: " + cine.getNumFunciones() + " (esperado 17)");

        // Clientes
        cine.addCliente("1001", "Ana Gomez", 25, "ana@correo.com");
        cine.addCliente("1002", "Luis Perez", 10, "luis@correo.com");
        cine.addCliente("1003", "Sara Diaz", 16, "sara@correo.com");
        System.out.println("Clientes: " + cine.getNumClientes());

        // Precios por tipo de sala
        System.out.println();
        int t = 0;
        while (t < TipoSala.values().length) {
            TipoSala tipo = TipoSala.values()[t];
            System.out.println(tipo + " cuesta " + tipo.getPrecioSala());
            t++;
        }

        // Edad minima por clasificacion
        System.out.println();
        int c = 0;
        while (c < Clasificacion.values().length) {
            Clasificacion clas = Clasificacion.values()[c];
            System.out.println(clas + " exige " + clas.getEdadMinima() + " anos");
            c++;
        }

        // Restriccion de edad
        System.out.println();
        Pelicula odisea = cine.buscarPelicula("A01");
        Pelicula spider = cine.buscarPelicula("I01");
        System.out.println("The Odyssey apta para 25: " + odisea.esApta(25));
        System.out.println("The Odyssey apta para 16: " + odisea.esApta(16));
        System.out.println("Spider-Verse apta para 10: " + spider.esApta(10));

        // Dimensiones que la funcion toma de su sala
        System.out.println();
        Funcion fa1 = cine.buscarFuncion("FA1");
        System.out.println("FA1 tiene " + fa1.getFilas() + " filas y " + fa1.getColumnas() + " columnas");
        System.out.println("Capacidad de la sala: " + fa1.getSala().capacidadSala());
        System.out.println("Asientos disponibles: " + fa1.asientosDisponibles());

        // Cambio de correo
        System.out.println();
        Cliente ana = cine.buscarCliente("1001");
        System.out.println("Correo de Ana: " + ana.getCorreo());
        ana.setCorreo("ana.gomez@nuevo.com");
        System.out.println("Correo actualizado: " + ana.getCorreo());

        // Formas de pago
        System.out.println();
        int p = 0;
        while (p < FormaPago.values().length) {
            FormaPago forma = FormaPago.values()[p];
            Venta v = new Venta("VP" + p, ana, null, null, 0, forma, LocalDateTime.now());
            cine.registrarVenta(v);
            System.out.println("Venta VP" + p + " registrada con " + v.getFormaPago());
            p++;
        }

        // Casos que deben ser rechazados
        System.out.println();
        probarError(cine, "codigo repetido");
        probarError(cine, "sala invalida");
        probarError(cine, "horario ocupado");
        probarError(cine, "pelicula inexistente");
        probarError(cine, "correo sin arroba");
        probarError(cine, "cedula vacia");
        probarError(cine, "edad negativa");
        probarError(cine, "precio cero");
        probarError(cine, "venta duplicada");
        probarError(cine, "eliminar pelicula con funciones");
        probarError(cine, "eliminar funcion inexistente");

        // Busquedas
        System.out.println();
        System.out.println("buscarPelicula(A01): " + cine.buscarPelicula("A01").getTitulo());
        System.out.println("buscarPelicula(X99): " + cine.buscarPelicula("X99"));
        System.out.println("buscarPorTitulo('the'): "
                + cine.buscarPeliculasPorTitulo("the").length + " resultado(s)");
        System.out.println("buscarPorTitulo('zzz'): "
                + cine.buscarPeliculasPorTitulo("zzz").length + " resultado(s)");
        System.out.println("buscarSala(SP01): " + cine.buscarSala("SP01").getTipoSala());

        // Venta con los tres tipos de boleta
        System.out.println();
        Venta venta = new Venta("V001", ana, null, null, 0,
                FormaPago.TARJETA_DEBITO, LocalDateTime.now());
        cine.registrarVenta(venta);

        vender(venta, fa1, ana, "B001", "GENERAL", 2, 3);
        vender(venta, fa1, ana, "B002", "PREFERENCIAL", 2, 4);
        vender(venta, cine.buscarFuncion("FT2"), ana, "B003", "3D", 1, 1);

        // Estos dos deben fallar
        vender(venta, fa1, cine.buscarCliente("1002"), "B004", "GENERAL", 5, 5);
        vender(venta, fa1, ana, "B005", "GENERAL", 2, 3);

        // Prueba de liberar asiento
        System.out.println();
        System.out.println("Ocupado (2,3) antes de liberar: " + fa1.getAsiento(2, 3).isOcupado());
        System.out.println("Disponibles antes: " + fa1.asientosDisponibles());
        fa1.liberarAsiento(2, 3);
        System.out.println("Ocupado (2,3) tras liberar: " + fa1.getAsiento(2, 3).isOcupado());
        System.out.println("Disponibles tras liberar: " + fa1.asientosDisponibles());
        fa1.ocuparAsiento(2, 3);
        System.out.println("Disponibles tras volver a ocupar: " + fa1.asientosDisponibles());

        // Prueba de buscar asiento
        System.out.println();
        Asiento a1 = fa1.getAsiento(2, 3);
        System.out.println("Etiqueta del asiento (2,3): " + a1.getEtiqueta());
        Asiento porEtiqueta = fa1.buscarAsiento(a1.getEtiqueta());
        System.out.println("buscarAsiento lo encontro: " + (porEtiqueta != null));
        System.out.println("Coincide con getAsiento(2,3): " + (porEtiqueta == a1));
        System.out.println("buscarAsiento(ZZ99): " + fa1.buscarAsiento("ZZ99"));

        //Prueba de preferencial
        System.out.println();
        System.out.println("Asiento (0,0) preferencial: " + fa1.getAsiento(0, 0).isPreferencial());
        fa1.getAsiento(0, 0).setPreferencial(true);
        System.out.println("Tras marcarlo preferencial: " + fa1.getAsiento(0, 0).isPreferencial());
        fa1.getAsiento(0, 0).setPreferencial(false);
        System.out.println("Tras desmarcarlo: " + fa1.getAsiento(0, 0).isPreferencial());

        // Prueba de filas y columnas fuera de rango
        System.out.println();
        System.out.println("getAsiento(-1, 0): " + fa1.getAsiento(-1, 0));
        System.out.println("getAsiento(99, 99): " + fa1.getAsiento(99, 99));
        vender(venta, fa1, ana, "B020", "GENERAL", 99, 99);

        // Prueba de buscar boleta
        System.out.println();
        System.out.println("buscarBoleta(B001) la encontro: " + (venta.buscarBoleta("B001") != null));
        System.out.println("buscarBoleta(B999): " + venta.buscarBoleta("B999"));
        System.out.println("getBoleta(0): " + venta.getBoleta(0).getCodigo());
        System.out.println("getBoleta(99): " + venta.getBoleta(99));

        System.out.println("Boletas en la venta: " + venta.getNumBoletas());
        System.out.println("Total: " + venta.calcularTotal());
        System.out.println("Reembolso con 30 horas: " + venta.calcularReembolsoTotal(30));
        System.out.println("Reembolso con 5 horas: " + venta.calcularReembolsoTotal(5));
        System.out.println("Reembolso con 1 hora: " + venta.calcularReembolsoTotal(1));
        System.out.println("Disponibles en FA1: " + fa1.asientosDisponibles());

        // Prueba de funcion llena
        System.out.println();
        cine.addSala("STEST", 1, 2, TipoSala.ESTANDAR);
        cine.addFuncion("FTEST", "A02", "STEST",
                LocalDateTime.of(2026, 10, 1, 15, 0), 10000);

        Funcion ftest = cine.buscarFuncion("FTEST");

        Venta ventaTest = new Venta("V010", ana, null, null, 0,
                FormaPago.EFECTIVO, LocalDateTime.now());
        cine.registrarVenta(ventaTest);

        System.out.println("FTEST llena al inicio: " + ftest.estaLlena());

        vender(ventaTest, ftest, ana, "B010", "GENERAL", 0, 0);
        vender(ventaTest, ftest, ana, "B011", "GENERAL", 0, 1);

        System.out.println("FTEST llena tras vender las dos: " + ftest.estaLlena());
        System.out.println("Disponibles en FTEST: " + ftest.asientosDisponibles());

        vender(ventaTest, ftest, ana, "B012", "GENERAL", 0, 0);

        // Eliminaciones validas
        System.out.println();
        cine.eliminarFuncion("FC3");
        System.out.println("Funciones tras eliminar FC3: " + cine.getNumFunciones());
        cine.eliminarPelicula("C03");
        System.out.println("Peliculas tras eliminar C03: " + cine.getNumPeliculas());
        cine.eliminarCliente("1003");
        System.out.println("Clientes tras eliminar 1003: " + cine.getNumClientes());

        // Guardado y recarga en un cine vacio
        System.out.println();
        gestor.guardarTodo(cine);
        Cine cine2 = new Cine("CineEIA", "Envigado");
        gestor.cargarTodo(cine2);

        System.out.println("Salas:     " + cine.getNumSalas()     + " -> " + cine2.getNumSalas());
        System.out.println("Peliculas: " + cine.getNumPeliculas() + " -> " + cine2.getNumPeliculas());
        System.out.println("Clientes:  " + cine.getNumClientes()  + " -> " + cine2.getNumClientes());
        System.out.println("Funciones: " + cine.getNumFunciones() + " -> " + cine2.getNumFunciones());
        System.out.println("Ventas:    " + cine.getNumVentas()    + " -> " + cine2.getNumVentas());


        System.out.println();
        Funcion fa1Cargada = cine2.buscarFuncion("FA1");
        System.out.println("Disponibles en FA1 antes: " + fa1.asientosDisponibles());
        System.out.println("Disponibles en FA1 despues: " + fa1Cargada.asientosDisponibles());
        System.out.println("Boletas de V001 tras cargar: " + cine2.buscarVenta("V001").getNumBoletas());
        System.out.println("Total de V001 tras cargar: " + cine2.buscarVenta("V001").calcularTotal());
        System.out.println("Correo de Ana tras cargar: " + cine2.buscarCliente("1001").getCorreo());
        System.out.println("Sala SP01 disponible tras cargar: " + cine2.buscarSala("SP01").isDisponible());
        System.out.println("Fecha de FA1 tras cargar: " + fa1Cargada.getFechaHora());
    }

    private static void cargarCatalogo(Cine cine, String nombre) {
        try {
            if (nombre.equals("accion")) {
                DatosCine.cargarCatalogoAccion(cine);
            } else if (nombre.equals("animacion")) {
                DatosCine.cargarCatalogoAnimacion(cine);
            } else if (nombre.equals("terror")) {
                DatosCine.cargarCatalogoTerror(cine);
            } else if (nombre.equals("drama")) {
                DatosCine.cargarCatalogoDrama(cine);
            } else if (nombre.equals("suspenso")) {
                DatosCine.cargarCatalogoSuspenso(cine);
            } else if (nombre.equals("comedia")) {
                DatosCine.cargarCatalogoComedia(cine);
            }
            System.out.println("Catalogo " + nombre + " cargado");
        } catch (Exception e) {
            System.out.println("Fallo el catalogo " + nombre + ": " + e.getMessage());
        }
    }

    private static void vender(Venta venta, Funcion funcion, Cliente cliente,
                               String codigoBoleta, String tipo, int fila, int columna) {
        try {
            Asiento[] asientos = new Asiento[1];
            asientos[0] = funcion.getAsiento(fila, columna);

            Boleta b;
            if (tipo.equals("PREFERENCIAL")) {
                b = new BoletaPreferencial(codigoBoleta, funcion, asientos, cliente);
            } else if (tipo.equals("3D")) {
                b = new Boleta3D(codigoBoleta, funcion, asientos, cliente);
            } else {
                b = new BoletaGeneral(codigoBoleta, funcion, asientos, cliente);
            }

            venta.agregarBoleta(b);
            funcion.ocuparAsiento(fila, columna);
            System.out.println("Vendida " + codigoBoleta + " (" + tipo + ") en "
                    + asientos[0].getEtiqueta() + " por " + b.calcularPrecio());
        } catch (Exception e) {
            System.out.println("No se vendio " + codigoBoleta + ": " + e.getMessage());
        }
    }

    private static void probarError(Cine cine, String caso) {
        try {
            if (caso.equals("codigo repetido")) {
                cine.addPelicula("A01", "Otra", 100, Genero.DRAMA, Clasificacion.MAYORES_15);

            } else if (caso.equals("sala invalida")) {
                cine.addSala("SX01", 0, 5, TipoSala.ESTANDAR);

            } else if (caso.equals("horario ocupado")) {
                cine.addFuncion("FX1", "A02", "SE01",
                        LocalDateTime.of(2026, 9, 10, 20, 30), 12000);

            } else if (caso.equals("pelicula inexistente")) {
                cine.addFuncion("FX2", "X99", "SE01",
                        LocalDateTime.of(2026, 9, 20, 10, 0), 12000);

            } else if (caso.equals("correo sin arroba")) {
                cine.addCliente("1004", "Pedro", 30, "pedro.correo.com");

            } else if (caso.equals("cedula vacia")) {
                cine.addCliente("   ", "Fantasma", 30, "fantasma@correo.com");

            } else if (caso.equals("edad negativa")) {
                cine.addCliente("2001", "Raro", -5, "raro@correo.com");

            } else if (caso.equals("precio cero")) {
                cine.addFuncion("FX3", "A02", "SE02",
                        LocalDateTime.of(2026, 11, 1, 10, 0), 0);

            } else if (caso.equals("venta duplicada")) {
                cine.registrarVenta(new Venta("V001", cine.buscarCliente("1001"), null, null, 0,
                        FormaPago.EFECTIVO, LocalDateTime.now()));

            } else if (caso.equals("eliminar pelicula con funciones")) {
                cine.eliminarPelicula("A01");

            } else if (caso.equals("eliminar funcion inexistente")) {
                cine.eliminarFuncion("FZZ");
            }

            System.out.println("Fallo: " + caso + " deberia haber sido rechazado");

        } catch (Exception e) {
            System.out.println("Rechazado (" + caso + "): " + e.getMessage());
        }
    }}
