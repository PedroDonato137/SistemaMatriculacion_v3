package org.iesalandalus.programacion.matriculacion.modelo.negocio;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;

public class Alumnos {

    private ArrayList<Alumno> coleccionAlumnos;

    //Constructor
    public Alumnos() {
        coleccionAlumnos = new ArrayList<>();
    }

    // Constructor copia
    public ArrayList<Alumno> get() {
        return copiaProfundaAlumnos();
    }

    private ArrayList<Alumno> copiaProfundaAlumnos() {
        return new ArrayList<>(coleccionAlumnos);
    }

    public int getTamano() {
        return coleccionAlumnos.size();
    }

    public void insertar(Alumno alumno) throws OperationNotSupportedException {

        Alumno alumnoEncontrado = null;

        if (alumno == null) {
            throw new NullPointerException("ERROR: No se puede insertar un alumno nulo.");
        }

        //Comprobacion de que existe el alumno
        for (int i = 0; i < getTamano(); i++){
            if(coleccionAlumnos.get(i).getDni().equalsIgnoreCase(alumno.getDni())){
                alumnoEncontrado = coleccionAlumnos.get(i);
                break;
            }
        }

        if (alumnoEncontrado != null) {
            throw new IllegalArgumentException("ERROR: Ya existe un alumno con ese dni.");
        } else {
            coleccionAlumnos.add(new Alumno(alumno));
        }
    }


    public Alumno buscar(Alumno alumno)  {

        Alumno alumnoEncontrado = null;
        if (alumno == null) {
            throw new NullPointerException("ERROR: No se puede buscar un alumno nulo.");
        }

        for (int i = 0; i < getTamano(); i++){
            if(coleccionAlumnos.get(i).getDni().equalsIgnoreCase(alumno.getDni())){
                alumnoEncontrado = coleccionAlumnos.get(i);
                break;
            }
        }

        if(alumnoEncontrado == null){
            throw new IllegalArgumentException("ERROR: El alumno no existe");
        }else{
            return new Alumno(alumnoEncontrado);
        }
    }

    public void borrar(Alumno alumno) throws OperationNotSupportedException {

        if (alumno == null) {
            throw new NullPointerException("ERROR: No se puede borrar un alumno nulo.");
        }

        Alumno alumnoEncontrado = buscar(alumno);

        if (alumnoEncontrado == null) {
            throw new OperationNotSupportedException("ERROR: No existe ningún alumno como el dni indicado.");
        } else {
            coleccionAlumnos.remove(alumnoEncontrado);
        }
    }

}
