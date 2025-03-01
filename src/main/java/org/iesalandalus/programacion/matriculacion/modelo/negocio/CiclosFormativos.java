package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;

public class CiclosFormativos {

    private ArrayList<CicloFormativo> coleccionCiclosFormativos;

    //Constructor
    public CiclosFormativos(){
        coleccionCiclosFormativos = new ArrayList<>();
    }

    // Constructor copia
    public ArrayList<CicloFormativo> get() {
        return copiaProfundaCicloFormativo();
    }

    private ArrayList<CicloFormativo> copiaProfundaCicloFormativo() {
        return new ArrayList<>(coleccionCiclosFormativos);
    }

    public int getTamano() {
        return coleccionCiclosFormativos.size();
    }

    public void insertar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {

        CicloFormativo cicloEncontrado = null;
        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: No se puede insertar un ciclo formativo nulo.");
        }

        for (int i = 0; i < getTamano(); i++){
            if(coleccionCiclosFormativos.get(i).getCodigo() == cicloFormativo.getCodigo()){
                cicloEncontrado = coleccionCiclosFormativos.get(i);
                break;
            }
        }

        if (cicloEncontrado != null) {
            throw new IllegalArgumentException("ERROR: Ya existe un ciclo formativo con ese codigo.");
        } else {
            coleccionCiclosFormativos.add(new CicloFormativo(cicloFormativo));
        }
    }

    public CicloFormativo buscar(CicloFormativo cicloFormativo) {

        CicloFormativo cicloEncontrado = null;

        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: No se puede buscar un ciclo formativo nulo.");
        }

        for (int i = 0; i < getTamano(); i++){
            if(coleccionCiclosFormativos.get(i).getCodigo() == cicloFormativo.getCodigo()){
                cicloEncontrado = coleccionCiclosFormativos.get(i);
            }
        }

        if(cicloEncontrado == null){
            throw new IllegalArgumentException("ERROR: El alumno no existe");
        }else{
            return new CicloFormativo(cicloEncontrado);
        }

    }

    public void borrar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {

        CicloFormativo cicloEncontrado = null;

        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: No se puede borrar un alumno nulo.");
        }

        cicloEncontrado = buscar(cicloFormativo);

        if (cicloEncontrado == null) {
            throw new OperationNotSupportedException("ERROR: No existe ningún ciclo formativo como el codigo indicado.");
        } else {
            coleccionCiclosFormativos.remove(cicloEncontrado);
        }
    }

}
