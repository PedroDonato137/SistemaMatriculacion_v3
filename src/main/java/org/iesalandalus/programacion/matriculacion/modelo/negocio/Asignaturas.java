package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;

public class Asignaturas {
    private ArrayList<Asignatura> coleccionAsignaturas;

    //Constructor
    public Asignaturas(){
        coleccionAsignaturas = new ArrayList<>();
    }

    // Constructor copia
    public ArrayList<Asignatura> get() {
        return copiaProfundaAsignaturas();
    }

    private ArrayList<Asignatura> copiaProfundaAsignaturas() {
        return new ArrayList<>(coleccionAsignaturas);
    }

    public int getTamano() {
        return coleccionAsignaturas.size();
    }

    public Asignatura buscar(Asignatura asignatura) {

        Asignatura asignaturaEncontrado = null;

        if (asignatura == null) {
            throw new NullPointerException("ERROR: No se puede buscar una asignatura nula.");
        }

        for (int i = 0; i < getTamano(); i++){
            if(coleccionAsignaturas.get(i).getCodigo().equalsIgnoreCase(asignatura.getCodigo())){
                asignaturaEncontrado = coleccionAsignaturas.get(i);
            }
        }

        if(asignaturaEncontrado == null){
            throw new IllegalArgumentException("ERROR: La asignatura no existe");
        }else{
            return new Asignatura(asignaturaEncontrado);
        }
    }

    public void borrar(Asignatura asignatura) throws OperationNotSupportedException {

        if (asignatura == null) {
            throw new NullPointerException("ERROR: No se puede borrar una asignatura nula.");
        }

        Asignatura asignaturaEncontrado = buscar(asignatura);

        if (asignaturaEncontrado == null) {
            throw new OperationNotSupportedException("ERROR: No existe ninguna asignatura como el codigo indicado.");
        } else {
            coleccionAsignaturas.remove(asignaturaEncontrado);
        }
    }

    public void insertar(Asignatura asignatura) throws OperationNotSupportedException {

        Asignatura asignaturaEncontrado = null;

        if (asignatura == null) {
            throw new NullPointerException("ERROR: No se puede insertar una asignatura nula.");
        }

        for (int i = 0; i < getTamano(); i++){
            if(coleccionAsignaturas.get(i).getCodigo().equalsIgnoreCase(asignatura.getCodigo())){
                asignaturaEncontrado = coleccionAsignaturas.get(i);
                break;
            }
        }

        if (asignaturaEncontrado != null) {
            throw new IllegalArgumentException("ERROR: Ya existe una asignatura con ese codigo.");
        } else {
            coleccionAsignaturas.add(new Asignatura(asignatura));
        }
    }
}
