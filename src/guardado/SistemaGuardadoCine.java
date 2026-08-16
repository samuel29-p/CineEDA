package guardado;
import cine.*;

import java.io.*;
import java.time.LocalDateTime;


public class SistemaGuardadoCine {

    private static final String SEPARADOR = ";";
    private String ruta;

    public SistemaGuardadoCine(String ruta) {
        this.ruta = ruta;
        File carpeta = new File(ruta);
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }
    }
    //no referencia a nadie, osea que debe existir de primeros
    public void guardarPeliculas(Cine cine){
        FileWriter fw = null;
        BufferedWriter bw = null;

        try {
            fw = new FileWriter(ruta + File.separator + "peliculas.txt");
            bw = new BufferedWriter(fw);

            int i = 0;
            while (i < cine.getNumPeliculas()) {
                Pelicula p =  cine.getPelicula(i);
                bw.write(p.getCodigo() + SEPARADOR + p.getTitulo() + SEPARADOR
                        + p.getDuracionMin() + SEPARADOR + p.getGenero()
                        + SEPARADOR + p.getClasificacion());
                bw.newLine();
                i++;
            }
        } catch (IOException e) {
            System.out.println("No se puede escribir el archivo peliculas.txt");
        } finally {
            if (fw != null) {
                try {
                    bw.close();
                    fw.close();
                } catch (IOException e) {
                    System.out.println("No se puede cerrar el archivo peliculas.txt");
                }
            }
        }

    }
    //no referencia a nadie
    public void guardarSalas(Cine cine){
        FileWriter fw = null;
        BufferedWriter bw = null;

        try{
            fw = new FileWriter(ruta + File.separator + "salas.txt");
            bw = new BufferedWriter(fw);

            int i = 0;
            while (i < cine.getNumSalas()) {
                Sala s =  cine.getSala(i);
                bw.write(s.getCodigo() + SEPARADOR
                        + s.getFilas() + SEPARADOR
                        + s.getColumnas() + SEPARADOR
                        + s.getTipoSala() + SEPARADOR
                        + s.isDisponible());
                bw.newLine();
                i++;
            }
        } catch (IOException e) {
            System.out.println("No se puede escribir el archivo salas.txt");
        } finally {
            if (fw != null) {
                try {
                    bw.close();
                    fw.close();
                } catch (IOException e) {
                    System.out.println("No se puede cerrar el archivo salas.txt");
                }
            }
        }
    }
    //no referencia a nadie
    public void guardarClientes(Cine cine){
        FileWriter fw = null;
        BufferedWriter bw = null;

        try {
            fw = new FileWriter(ruta + File.separator + "clientes.txt");
            bw = new BufferedWriter(fw);

            int i = 0;
            while (i < cine.getNumClientes()) {
                Cliente c =  cine.getCliente(i);
                bw.write(c.getCedula() + SEPARADOR + c.getNombre() + SEPARADOR
                        + c.getEdad() + SEPARADOR + c.getCorreo());
                bw.newLine();
                i++;
            }
        } catch (IOException e) {
            System.out.println("No se puede escribir el archivo clientes.txt");
        } finally {
            if (fw != null) {
                try {
                    bw.close();
                    fw.close();
                } catch (IOException e) {
                    System.out.println("No se puede cerrar el archivo clientes.txt");
                }
            }
        }
    }
    //referencia pelicula y sala
    public void guardarFunciones(Cine cine){
        FileWriter fw = null;
        BufferedWriter bw = null;

        try {
            fw = new FileWriter(ruta + File.separator + "funciones.txt");
            bw = new BufferedWriter(fw);

            int i = 0;
            while (i < cine.getNumFunciones()) {
                Funcion f =  cine.getFuncion(i);
                bw.write(f.getCodigo() + SEPARADOR
                        + f.getPelicula().getCodigo() + SEPARADOR
                        + f.getSala().getCodigo() + SEPARADOR
                        + f.getFechaHora() + SEPARADOR
                        + f.getPrecio());
                bw.newLine();
                i++;
            }
        } catch (IOException e) {
            System.out.println("No se puede crear el archivo funciones.txt");
        } finally {
            if (fw != null) {
                try {
                    bw.close();
                    fw.close();
                } catch (IOException e) {
                    System.out.println("No se puede cerrar el archivo funciones.txt");
                }
            }
        }
    }
    //referencia cliente
    public void guardarVentas(Cine cine){
        FileWriter fw = null;
        BufferedWriter bw = null;

        try {
            fw = new FileWriter(ruta + File.separator + "ventas.txt");
            bw = new BufferedWriter(fw);

            int i = 0;
            while (i < cine.getNumVentas()) {
                Venta v =  cine.getVenta(i);
                bw.write(v.getCodigo() + SEPARADOR +
                        v.getCliente().getCedula() + SEPARADOR +
                        v.getFecha() + SEPARADOR +
                        v.getFormaPago());
                bw.newLine();
                i++;
            }
        } catch (IOException e) {
            System.out.println("No se puede crear el archivo ventas.txt");
        } finally {
            if (fw != null) {
                try {
                    bw.close();
                    fw.close();
                } catch (IOException e) {
                    System.out.println("No se puede cerrar el archivo ventas.txt");
                }
            }
        }
    }
    //referencia venta y funcion
    public void guardarBoletas(Cine cine) {
        FileWriter fw = null;
        BufferedWriter bw = null;

        try {
            fw = new FileWriter(ruta + File.separator + "boletas.txt");
            bw = new BufferedWriter(fw);

            int i = 0;
            while (i < cine.getNumVentas()) {
                Venta v = cine.getVenta(i);

                int k = 0;
                while (k < v.getNumBoletas()) {
                    Boleta b = v.getBoleta(k);
                    //el campo de asientos: "F5,F6,F7"
                    String campoAsientos = "";
                    int a = 0;
                    while (a < b.getNumAsientos()) {
                        if (a > 0) {
                            campoAsientos = campoAsientos + ",";
                        }
                        campoAsientos = campoAsientos + b.getAsiento(a).getEtiqueta();
                        a++;
                    }

                    bw.write(v.getCodigo() + SEPARADOR
                            + b.getCodigo() + SEPARADOR
                            + b.getFuncion().getCodigo() + SEPARADOR
                            + b.getTipo() + SEPARADOR
                            + campoAsientos);
                    bw.newLine();
                    k++;
                }
                i++;
            }
        } catch (IOException e) {
            System.out.println("No se puede crear el archivo boletas.txt");
        } finally {
            if (fw != null) {
                try {
                    bw.close();
                    fw.close();
                } catch (IOException e) {
                    System.out.println("No se puede cerrar el archivo boletas.txt");
                }
            }
        }
    }

    public void guardarTodo(Cine cine) {
        guardarPeliculas(cine);
        guardarSalas(cine);
        guardarClientes(cine);
        guardarFunciones(cine);
        guardarVentas(cine);
        guardarBoletas(cine);
    }

    public void cargarPeliculas(Cine cine) {
        File archivo = new File(ruta + File.separator + "peliculas.txt");
        if (!archivo.exists()) {
            return;
        }

        FileReader fr = null;
        BufferedReader br = null;

        try {
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            String linea;
            while ((linea = br.readLine()) != null) {
                try {
                    String[] datos = linea.split(SEPARADOR);

                    String codigo = datos[0].trim();
                    String titulo = datos[1].trim();
                    int duracionMin = Integer.parseInt(datos[2].trim());//Del archivo tdo sale como texto: "120" es un String, no un número. parseInt lo convierte.
                    Genero genero = Genero.valueOf(datos[3].trim());//valueOf busca la constante del enum
                    Clasificacion clasificacion = Clasificacion.valueOf(datos[4].trim());

                    cine.addPelicula(codigo, titulo, duracionMin, genero, clasificacion);

                } catch (Exception e) {
                    System.out.println("Linea invalida en peliculas.txt: " + linea);//atrapa ArrayIndexOutOfBoundsException si faltan campos, NumberFormatException si la duración no es número, IllegalArgumentException si el enum no coincide, y DatoInvalidoException si el dato no pasa validación.
                }
            }
        } catch (IOException e) {
            System.out.println("No se puede leer el archivo peliculas.txt");
        } finally {
            if (fr != null) {
                try {
                    br.close();
                    fr.close();
                } catch (IOException e) {
                    System.out.println("No se puede cerrar el archivo peliculas.txt");
                }
            }
        }
    }

    public void cargarSalas(Cine cine) {
        File archivo = new File(ruta + File.separator + "salas.txt");
        if (!archivo.exists()) {
            return;
        }

        FileReader fr = null;
        BufferedReader br = null;

        try {
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            String linea;
            while ((linea = br.readLine()) != null){
                try {
                    String[] datos = linea.split(SEPARADOR);

                    String codigo = datos[0].trim();
                    int filas = Integer.parseInt(datos[1].trim());
                    int columnas = Integer.parseInt(datos[2].trim());
                    TipoSala tipoSala = TipoSala.valueOf(datos[3].trim());
                    boolean disponibilidad = Boolean.parseBoolean(datos[4].trim());

                    cine.addSala(codigo, filas, columnas, tipoSala);       // disponibilidad nace disponible
                    cine.buscarSala(codigo).setDisponibilidad(disponibilidad);  // se ajusta al estado guardado
                }catch (Exception e) {
                    System.out.println("Linea invalida en salas.txt: " + linea);
                }
            }
        }catch (IOException e) {
            System.out.println("No se puede leer el archivo salas.txt");
        }finally {
            if (fr != null) {
                try {
                    br.close();
                    fr.close();
                } catch (IOException e) {
                    System.out.println("No se puede cerrar el archivo salas.txt");
                }
            }
        }
    }

    public void cargarClientes(Cine cine) {
        File archivo = new File(ruta + File.separator + "clientes.txt");
        if (!archivo.exists()) {
            return;
        }

        FileReader fr = null;
        BufferedReader br = null;

        try {
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            String linea;
            while ((linea = br.readLine()) != null){
                try {
                    String[] datos = linea.split(SEPARADOR);

                    String cedula = datos[0].trim();
                    String nombre = datos[1].trim();
                    int edad = Integer.parseInt(datos[2].trim());
                    String correo = datos[3].trim();

                    cine.addCliente(cedula, nombre, edad, correo);
                } catch (Exception e) {
                    System.out.println("Linea invalida en clientes.txt: " + linea);
                }
            }
        }catch (IOException e){
            System.out.println("No se puede leer el archivo clientes.txt");
        }finally {
            if (fr != null) {
                try {
                    br.close();
                    fr.close();
                } catch (IOException e) {
                    System.out.println("No se puede cerrar el archivo clientes.txt");
                }
            }
        }
    }

    public void cargarFunciones(Cine cine) {
        File archivo = new File(ruta + File.separator + "funciones.txt");
        if (!archivo.exists()) {
            return;
        }

        FileReader fr = null;
        BufferedReader br = null;

        try {
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            String linea;
            while ((linea = br.readLine()) != null) {
                try {
                    String[] datos = linea.split(SEPARADOR);

                    String codigo = datos[0].trim();
                    String codigoPelicula = datos[1].trim();
                    String codigoSala =  datos[2].trim();
                    LocalDateTime fechaHora =  LocalDateTime.parse(datos[3].trim());
                    double precioBase =  Double.parseDouble(datos[4].trim());

                    cine.addFuncion(codigo, codigoPelicula, codigoSala, fechaHora, precioBase);
                }catch (Exception e) {
                    System.out.println("Linea invalida en funciones.txt: " + linea);
                }
            }
        }catch (IOException e) {
            System.out.println("No se puede leer el archivo funciones.txt");
        }finally {
            if (fr != null) {
                try {
                    br.close();
                    fr.close();
                } catch (IOException e) {
                    System.out.println("No se puede cerrar el archivo funciones.txt");
                }
            }
        }
    }

    public void cargarVentas(Cine cine) {
        File archivo = new File(ruta + File.separator + "ventas.txt");
        if (!archivo.exists()) {
            return;
        }

        FileReader fr = null;
        BufferedReader br = null;

        try {
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            String linea;
            while ((linea = br.readLine()) != null){
                try {
                    String[] datos = linea.split(SEPARADOR);

                    String codigo = datos[0].trim();
                    Cliente cliente = cine.buscarCliente(datos[1].trim());
                    LocalDateTime fecha = LocalDateTime.parse(datos[2].trim());
                    FormaPago formaPago = FormaPago.valueOf(datos[3].trim());

                    Venta v = new Venta(codigo, cliente, new Boleta[0], new Asiento[0], 0, formaPago, fecha);
                    cine.registrarVenta(v);
                }catch (Exception e){
                    System.out.println("Linea invalida en ventas.txt: " + linea);
                }
            }
        }catch (IOException e){
            System.out.println("No se puede leer el archivo ventas.txt");
        }finally {
            if (fr != null) {
                try {
                    br.close();
                    fr.close();
                } catch (IOException e) {
                    System.out.println("No se puede cerrar el archivo ventas.txt");
                }
            }
        }
    }

    public void cargarBoletas(Cine cine) {
        File archivo = new File(ruta + File.separator + "boletas.txt");
        if (!archivo.exists()) {
            return;
        }

        FileReader fr = null;
        BufferedReader br = null;

        try {
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            String linea;
            while((linea = br.readLine()) != null){
                try {
                    String[] datos = linea.split(SEPARADOR);

                    Venta venta = cine.buscarVenta(datos[0].trim());
                    String codigoBoleta = datos[1].trim();
                    Funcion funcion = cine.buscarFuncion(datos[2].trim());
                    String tipoBoleta = datos[3].trim();
                    String[] etiquetas = datos[4].split(",");
                    Asiento[] asientos = new Asiento[etiquetas.length];
                    int j = 0;
                    while (j < etiquetas.length) {
                        asientos[j] = funcion.buscarAsiento(etiquetas[j].trim());
                        j++;
                    }
                    Boleta boleta;
                    if (tipoBoleta.equalsIgnoreCase("GENERAL")) {
                        boleta = new BoletaGeneral(codigoBoleta, funcion, asientos, venta.getCliente());
                    }
                    else if (tipoBoleta.equalsIgnoreCase("PREFERENCIAL")){
                        boleta = new BoletaPreferencial(codigoBoleta, funcion, asientos, venta.getCliente());
                    }
                    else if (tipoBoleta.equalsIgnoreCase("3D")){
                        boleta = new Boleta3D(codigoBoleta, funcion, asientos, venta.getCliente());
                    }
                    else {
                        throw new DatoInvalidoException("Tipo de boleta desconocido: " + tipoBoleta);
                    }

                    venta.agregarBoleta(boleta);

                    int a = 0;
                    while (a < asientos.length) {
                        funcion.ocuparAsiento(asientos[a].getFila(), asientos[a].getColumna());
                        a++;
                    }

                }catch (Exception e) {
                    System.out.println("Linea invalida en boletas.txt: " + linea);
                }
            }
        }catch (IOException e){
            System.out.println("No se puede leer el archivo boletas.txt");
        }finally {
            if (fr != null) {
                try {
                    br.close();
                    fr.close();
                } catch (IOException e) {
                    System.out.println("No se puede cerrar el archivo boletas.txt");
                }
            }
        }
    }

    public void cargarTodo(Cine cine) {
        cargarPeliculas(cine);
        cargarSalas(cine);
        cargarClientes(cine);
        cargarFunciones(cine);
        cargarVentas(cine);
        cargarBoletas(cine);
    }
}
