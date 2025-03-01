package org.iesalandalus.programacion.matriculacion.Controlador;

import org.iesalandalus.programacion.matriculacion.modelo.Modelo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.*;
import org.iesalandalus.programacion.matriculacion.vista.Vista;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;

public class Controlador {

    private Modelo modelo;
    private Vista vista;

    public Controlador(Modelo modelo, Vista vista)
    {
        if (modelo == null) {
            throw new IllegalArgumentException("ERROR: El modelo no puede ser nulo.");
        }
        if (vista == null) {
            throw new IllegalArgumentException("ERROR: La vista no puede ser nula.");
        }
        this.modelo = modelo;
        this.vista = vista;


        this.vista.setController(this);
    }

    public void comenzar() throws OperationNotSupportedException {
        System.out.println("Iniciamos el controlador");
        this.modelo.comenzar();
        this.vista.comenzar();

    }

    public void terminar()
    {
        this.modelo.terminar();
        this.vista.terminar();
        System.out.println("Cerramos el controlador");
    }

    /*---------------------------------------------------------------------------------------------------------------*/
    /*------------------------------------------------ ALUMNOS ------------------------------------------------------*/
    /*---------------------------------------------------------------------------------------------------------------*/

    public void insertar(Alumno alumno) {
        this.modelo.insertar(alumno);
    }

    public Alumno buscar(Alumno alumno) {
        return this.modelo.buscar(alumno);
    }

    public void borrar(Alumno alumno) throws OperationNotSupportedException {
        this.modelo.borrar(alumno);
    }

    public ArrayList<Alumno> getAlumnos(){
        return this.modelo.getAlumnos();
    }

    /*---------------------------------------------------------------------------------------------------------------*/
    /*---------------------------------------------- ASIGNATURAS ----------------------------------------------------*/
    /*---------------------------------------------------------------------------------------------------------------*/

    public void insertar(Asignatura asignatura) {
        this.modelo.insertar(asignatura);
    }

    public Asignatura buscar(Asignatura asignatura) {
        return this.modelo.buscar(asignatura);
    }

    public void borrar(Asignatura asignatura) throws OperationNotSupportedException {
        this.modelo.borrar(asignatura);
    }

    public ArrayList<Asignatura> getAsignaturas(){
        return this.modelo.getAsignaturas();
    }

    /*---------------------------------------------------------------------------------------------------------------*/
    /*----------------------------------------- CICLOS FORMATIVOS ---------------------------------------------------*/
    /*---------------------------------------------------------------------------------------------------------------*/

    public void insertar(CicloFormativo cicloFormativo) {
        this.modelo.insertar(cicloFormativo);
    }

    public CicloFormativo buscar(CicloFormativo cicloFormativo) {
        return this.modelo.buscar(cicloFormativo);
    }

    public void borrar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        this.modelo.borrar(cicloFormativo);
    }

    public ArrayList<CicloFormativo> getCicloformativos(){
        return this.modelo.getCiclosFormativos();
    }

    /*---------------------------------------------------------------------------------------------------------------*/
    /*---------------------------------------------- MATRICULAS -----------------------------------------------------*/
    /*---------------------------------------------------------------------------------------------------------------*/

    public void insertar(Matricula matricula) {
        this.modelo.insertar(matricula);
    }

    public Matricula buscar(Matricula matricula) {
        return this.modelo.buscar(matricula);
    }

    public void borrar(Matricula matricula) throws OperationNotSupportedException {
        this.modelo.borrar(matricula);
    }

    public ArrayList<Matricula> getMatriculas(){
        return this.modelo.getMatriculas();
    }
    public ArrayList<Matricula> getMatriculas(Alumno alumno){
        return this.modelo.getMatriculas(alumno);
    }
    public ArrayList<Matricula> getMatriculas(CicloFormativo cicloFormativo){
        return this.modelo.getMatriculas(cicloFormativo);
    }
    public ArrayList<Matricula> getMatriculas(String cursoAcademico){
        return this.modelo.getMatriculas(cursoAcademico);
    }
}
