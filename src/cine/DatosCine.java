package cine;

import java.time.LocalDateTime;

public class DatosCine {

    public static void cargarSalas(Cine cine){
        try {
            cine.addSala("SE01", 8, 12, TipoSala.ESTANDAR);    // 96
            cine.addSala("SE02", 8, 12, TipoSala.ESTANDAR);    // 96
            cine.addSala("SE03", 7, 10, TipoSala.ESTANDAR);    // 70
            cine.addSala("SP01", 5, 8, TipoSala.PREMIUM);      // 40
            cine.addSala("SP02", 5, 8, TipoSala.PREMIUM);      // 40
            cine.addSala("S3D01", 7, 10, TipoSala.SALA_3D);    // 70
        }catch(DatoInvalidoException e){
            System.out.println("Error al cargar salas" + e.getMessage());
        }
    }

    public static void cargarCatalogoAccion(Cine cine) throws DatoInvalidoException {
        cine.addPelicula("A01", "The Odyssey", 172, Genero.CIENCIA_FICCION, Clasificacion.MAYORES_18);
        cine.addPelicula("A02", "F1", 156, Genero.ACCION, Clasificacion.MAYORES_12);
        cine.addPelicula("A03", "TENET", 150, Genero.CIENCIA_FICCION, Clasificacion.MAYORES_15);

        cine.addFuncion("FA1", "A01", "SE01", LocalDateTime.of(2026, 9, 10, 19, 0), 18000);
        cine.addFuncion("FA2", "A02", "SE02", LocalDateTime.of(2026, 9, 10, 20, 0), 16000);
        cine.addFuncion("FA3", "A03", "SE03", LocalDateTime.of(2026, 9, 10, 21, 0), 22000);
    }

    public static void cargarCatalogoAnimacion(Cine cine) throws DatoInvalidoException {
        cine.addPelicula("I01", "Spider-Man: Across the Spider-Verse", 140, Genero.ANIMACION, Clasificacion.TODO_PUBLICO);
        cine.addPelicula("I02", "PINOCCHIO", 117, Genero.ANIMACION, Clasificacion.TODO_PUBLICO);

        cine.addFuncion("FI1", "I01", "SE01", LocalDateTime.of(2026, 9, 11, 14, 0), 12000);
        cine.addFuncion("FI2", "I02", "SE02", LocalDateTime.of(2026, 9, 11, 15, 0), 12000);
    }

    public static void cargarCatalogoTerror(Cine cine) throws DatoInvalidoException {
        cine.addPelicula("T01", "The Sixth Sense", 107, Genero.TERROR, Clasificacion.MAYORES_15);
        cine.addPelicula("T02", "Hereditary", 127, Genero.TERROR, Clasificacion.MAYORES_18);

        cine.addFuncion("FT1", "T01", "SE03", LocalDateTime.of(2026, 9, 12, 22, 0), 20000);
        cine.addFuncion("FT2", "T02", "S3D01", LocalDateTime.of(2026, 9, 12, 22, 30), 25000);
    }

    public static void cargarCatalogoDrama(Cine cine) throws DatoInvalidoException {
        cine.addPelicula("D01", "Django Unchained", 165, Genero.DRAMA, Clasificacion.MAYORES_18);
        cine.addPelicula("D02", "Whiplash", 106, Genero.DRAMA, Clasificacion.MAYORES_15);
        cine.addPelicula("D03", "La La Land", 128, Genero.DRAMA, Clasificacion.TODO_PUBLICO);

        cine.addFuncion("FD1", "D01", "SE01", LocalDateTime.of(2026, 9, 13, 17, 0), 15000);
        cine.addFuncion("FD2", "D02", "SE02", LocalDateTime.of(2026, 9, 13, 18, 0), 15000);
        cine.addFuncion("FD3", "D03", "SP01", LocalDateTime.of(2026, 9, 13, 20, 0), 19000);
    }

    public static void cargarCatalogoSuspenso(Cine cine) throws DatoInvalidoException {
        cine.addPelicula("SU1", "The Prestige", 130, Genero.SUSPENSO, Clasificacion.MAYORES_15);
        cine.addPelicula("SU2", "Gone Girl", 149, Genero.SUSPENSO, Clasificacion.MAYORES_18);
        cine.addPelicula("SU3", "Nocturnal Animals", 116, Genero.SUSPENSO, Clasificacion.MAYORES_18);

        cine.addFuncion("FS1", "SU1", "SE01", LocalDateTime.of(2026, 9, 14, 19, 0), 17000);
        cine.addFuncion("FS2", "SU2", "SE02", LocalDateTime.of(2026, 9, 14, 20, 30), 17000);
        cine.addFuncion("FS3", "SU3", "S3D01", LocalDateTime.of(2026, 9, 14, 21, 0), 24000);
    }

    public static void cargarCatalogoComedia(Cine cine) throws DatoInvalidoException {
        cine.addPelicula("C01", "Barbie", 114, Genero.COMEDIA, Clasificacion.MAYORES_12);
        cine.addPelicula("C02", "Superbad", 113, Genero.COMEDIA, Clasificacion.MAYORES_18);
        cine.addPelicula("C03", "The Grand Budapest Hotel", 99, Genero.COMEDIA, Clasificacion.MAYORES_12);

        cine.addFuncion("FC1", "C01", "SE01", LocalDateTime.of(2026, 9, 15, 16, 0), 14000);
        cine.addFuncion("FC2", "C02", "SP01", LocalDateTime.of(2026, 9, 15, 21, 0), 19000);
        cine.addFuncion("FC3", "C03", "SP02", LocalDateTime.of(2026, 9, 15, 18, 0), 19000);
    }
}
