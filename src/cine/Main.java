package cine;

import visual.InterfazVisual;
import guardado.SistemaGuardadoCine;

public class Main {

    static void main(String[] args) {
        Cine cine = new Cine("CineEIA", "Envigado");
        SistemaGuardadoCine gestor = new SistemaGuardadoCine("datos");

        gestor.cargarTodo(cine);

        if (cine.getNumSalas() == 0){
            DatosCine.cargarSalas(cine);
        }

        InterfazVisual ventana = new InterfazVisual(cine, gestor);
        ventana.setVisible(true);
    }
}