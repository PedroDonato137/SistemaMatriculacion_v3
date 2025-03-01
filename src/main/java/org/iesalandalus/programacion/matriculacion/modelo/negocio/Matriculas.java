package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;
import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;

public class Matriculas {

    private ArrayList<Matricula> coleccionMatriculas;

    //Constructor
    public Matriculas(){
        coleccionMatriculas = new ArrayList<>();
    }

    // Constructor copia
    public ArrayList<Matricula> get() {
        return copiaProfundaMatriculas();
    }

    public ArrayList<Matricula> get(Alumno alumno) {

        ArrayList<Matricula> matriculaAlumno = new ArrayList<>();

        if (alumno == null) {
            throw new NullPointerException("ERROR: No se pueden buscar un alumno nulo.");
        }

        for (Matricula matricula : coleccionMatriculas) {
            if (matricula.getAlumno().getDni().equals(alumno.getDni())) {
                matriculaAlumno.add(matricula);
            }
        }

        return matriculaAlumno;
    }

    public ArrayList<Matricula> get(String cursoAcademico) {

        if (cursoAcademico == null) {
            throw new NullPointerException("ERROR: No se pueden buscar un curso nulo.");
        }

        ArrayList<Matricula> cursoPermanencia = new ArrayList<>();

        for (Matricula matricula : coleccionMatriculas) {
            if (matricula.getCursoAcademico().equals(cursoAcademico)) {
                cursoPermanencia.add(matricula);
            }
        }

        return cursoPermanencia;
    }

    public ArrayList<Matricula> get(CicloFormativo cicloFormativo) {

        ArrayList<Matricula> matriculasConCiclo = new ArrayList<>(); // Array a devolver
        int contadorMatriculas = 0;

        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: No se pueden buscar un ciclo formativo nulo.");
        }else{
            for (int i = 0; i < coleccionMatriculas.size(); i++) { // For para sacar las asignaturas
                ArrayList<Asignatura> asignaturasMatricula = new ArrayList<>(); // Creo un array temporal por cada matricula sacar las asignaturas
                Matricula matricula = coleccionMatriculas.get(i); // consigo solo una matricula
                asignaturasMatricula = matricula.getColeccionAsignaturas(); // Saco las asignaturas de esa matricula
                for (int j = 0; j < asignaturasMatricula.size(); j++) { // Este for es para recorrer las asignaturas
                    if (asignaturasMatricula.get(j).getCicloFormativo().getCodigo() == cicloFormativo.getCodigo()) { // comparar el ciclo formativo de cada asignatura con el pasado por parametro
                        matriculasConCiclo.add(coleccionMatriculas.get(i)); // si coincide lo mete en un array independiente
                        contadorMatriculas++;
                    }
                }
            }
        }

        return matriculasConCiclo;
    }

    public int getTamano() {
        return coleccionMatriculas.size();
    }

    private ArrayList<Matricula> copiaProfundaMatriculas() {
            return new ArrayList<>(coleccionMatriculas);
    }


    public Matricula buscar(Matricula matricula) {

        Matricula matriculaEncontrada = null;
        if (matricula == null) {
            throw new NullPointerException("ERROR: No se puede buscar una matricula nula.");
        }

        for (int i = 0; i < getTamano(); i++){
            if(coleccionMatriculas.get(i).getIdMatricula() == matricula.getIdMatricula()){
                matriculaEncontrada = coleccionMatriculas.get(i);
                break;
            }
        }

        if(matriculaEncontrada == null){
            throw new IllegalArgumentException("ERROR: La matricula no existe");
        }else{
            return new Matricula(matriculaEncontrada);
        }
    }

    public void borrar(Matricula matricula) throws OperationNotSupportedException {
        if (matricula == null) {
            throw new NullPointerException("ERROR: No se puede borrar una matricula nula.");
        }

        Matricula matriculaEncontrado = buscar(matricula);

        if (matriculaEncontrado == null) {
            throw new OperationNotSupportedException("ERROR: No existe ninguna matricula como el codigo indicado.");
        } else {
            coleccionMatriculas.remove(matriculaEncontrado);
        }
    }

    public void insertar(Matricula matricula) throws OperationNotSupportedException {

        Matricula matriculaEncontrada = null;

        if (matricula == null) {
            throw new NullPointerException("ERROR: No se puede insertar una matricula nulo.");
        }

        //Comprobacion de que existe el alumno
        for (int i = 0; i < getTamano(); i++){
            if(coleccionMatriculas.get(i).getIdMatricula() == matricula.getIdMatricula()){
                matriculaEncontrada = coleccionMatriculas.get(i);
                break;
            }
        }

        if (matriculaEncontrada != null) {
            throw new IllegalArgumentException("ERROR: Ya existe esa matricula.");
        } else {
            coleccionMatriculas.add(new Matricula(matricula));
        }
    }

}


