package org.iesalandalus.programacion.matriculacion.vista;

import org.iesalandalus.programacion.matriculacion.Controlador.Controlador;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;
import java.util.*;

public class Vista{

    private static Controlador controller;
    private static Vista vista;

    // Constructor de la clase
    public Vista() {
        Opcion.setVista(vista);
    }

    public void setController(Controlador controller)
    {
        if(controller != null) {
            this.controller = controller;
        }
    }

    public void comenzar() throws OperationNotSupportedException {

        // Esto es para que tenga que presionar el usuario por cada opcion
        Scanner continuar = new Scanner(System.in);

        Opcion opcionElegida;
        do {
            Consola.mostrarMenu();
            opcionElegida = Consola.elegirOpcion();
            opcionElegida.ejecutar(); // Ejecutar opcion

            System.out.println("\n\t\tPRESIONE ENTER PARA CONTINUAR..."); //Mensaje en pantalla
            continuar.nextLine();
        } while (!opcionElegida.equals(Opcion.SALIR));
    }

    public void terminar()
    {
        controller.terminar();
        System.out.println("Cerramos la vista");
    }

    /*---------------------------------------------------------------------------------------------------------------*/
    /*------------------------------------------------ ALUMNOS ------------------------------------------------------*/
    /*---------------------------------------------------------------------------------------------------------------*/

    public static void insertarAlumno(){

        Alumno alumno = new Alumno(Consola.leerAlumno());

        controller.insertar(alumno);
        System.out.println("Alumno insertado correctamente");

    }

    public static void buscarAlumno() {
        Alumno alumno = new Alumno(Consola.getAlumnoPorDni());
        Alumno alumnoBuscado = null;

        alumnoBuscado = controller.buscar(alumno);
        if (alumnoBuscado != null){
            System.out.print(alumnoBuscado.imprimir());
        }
        else{
            throw new IllegalArgumentException("No existe ningun alumno con ese DNI");
        }
    }

    public static void borrarAlumno() throws OperationNotSupportedException {

        controller.borrar(Consola.getAlumnoPorDni());
    }

    public static void mostarAlumnos(){

        ArrayList<Alumno> alumnosMostar = controller.getAlumnos();

        alumnosMostar.sort(Comparator.comparing(Alumno::getNombre)); // Ordenacion por nombre

        if (alumnosMostar.isEmpty()) {
            throw new IllegalArgumentException("ERROR: No existen alumnos para mostrar.");
        }

        for (Alumno alumno : alumnosMostar) {
            System.out.println(alumno.imprimir());
        }
    }

    /*---------------------------------------------------------------------------------------------------------------*/
    /*---------------------------------------------- ASIGNATURAS ----------------------------------------------------*/
    /*---------------------------------------------------------------------------------------------------------------*/

    public static void insertarAsignatura(){
        CicloFormativo cicloFormativoAsignatura = Consola.getCicloFormativoPorCodigo();
        CicloFormativo ciclosExistentes = controller.buscar(cicloFormativoAsignatura);

        if (ciclosExistentes != null) {
            Asignatura asignatura = new Asignatura(Consola.leerAsignatura(ciclosExistentes));
            controller.insertar(asignatura);
            System.out.println("Asignatura insertada correctamente");
        }else{
            throw new IllegalArgumentException("ERROR: No se ha podido ingresar la asignatura");
        }
    }

    public static void buscarAsignatura(){

        Asignatura asignatura = new Asignatura(Consola.getAsignaturaPorCodigo());
        Asignatura asigBuscado = null;

        asigBuscado = controller.buscar(asignatura);
        if (asigBuscado != null){
            System.out.println(asigBuscado.imprimir());
        }
        else{
            throw new IllegalArgumentException("ERROR:: No existe ninguna asignatura con ese codigo");
        }
    }

    public static void borrarAsignatura() throws OperationNotSupportedException {
        controller.borrar(Consola.getAsignaturaPorCodigo());
    }

    public static void mostrarAsignaturas(){

        Consola.mostrarAsignautras(controller.getAsignaturas());
    }

    /*---------------------------------------------------------------------------------------------------------------*/
    /*----------------------------------------- CICLOS FORMATIVOS ---------------------------------------------------*/
    /*---------------------------------------------------------------------------------------------------------------*/
    public static void insertarCicloFormativo(){

        CicloFormativo cicloFormativo = new CicloFormativo(Consola.leerCicloFormativo());

        controller.insertar(cicloFormativo);
        System.out.println("Ciclo Formativo insertado correctamente");

    }

    public static void buscarCicloFormativo (){

        CicloFormativo cicloFormativo = new CicloFormativo(Consola.getCicloFormativoPorCodigo());
        CicloFormativo cicloBuscado = null;

        cicloBuscado = controller.buscar(cicloFormativo);
        if (cicloBuscado != null){
            System.out.println(cicloBuscado.imprimir());
        }
        else{
            throw new IllegalArgumentException("ERROR: No existe ningun ciclo formativo con ese codigo");
        }
    }

    public static void borrarCicloFormativo() throws OperationNotSupportedException {
        controller.borrar(Consola.getCicloFormativoPorCodigo());
    }

    public static void mostarCiclosFormativos(){

        Consola.mostrarCiclosFormativos(controller.getCicloformativos());

    }

    /*---------------------------------------------------------------------------------------------------------------*/
    /*---------------------------------------------- MATRICULAS -----------------------------------------------------*/
    /*---------------------------------------------------------------------------------------------------------------*/

    public static void insertarMatricula() throws OperationNotSupportedException {

        // Conseguir el usuario
        Alumno alumnoMatriculado = Consola.getAlumnoPorDni();
        Alumno alumnoExistente = controller.buscar(alumnoMatriculado);

        // Conseguir las asignaturas
        ArrayList<Asignatura> asignaturasRegistradas = controller.getAsignaturas();
        if (asignaturasRegistradas == null){
            throw new IllegalArgumentException("ERROR: No existen asignaturas registradas");
        }

        ArrayList<Asignatura> asignaturasMatricula = Consola.elegirAsignaturasMatricula(asignaturasRegistradas);

        Matricula matricula = new Matricula(Consola.leerMatricula(alumnoExistente, asignaturasMatricula));

        controller.insertar(matricula);
        System.out.println("Matricula insertado correctamente");
    }

    public static void buscarMatricula(){

        Matricula matricula = new Matricula(Consola.getMatriculaPorIdentificador());
        Matricula matriculaBuscado = null;

        matriculaBuscado = controller.buscar(matricula);
        if (matriculaBuscado != null){
            System.out.println(matriculaBuscado.imprimir());
        }
        else{
            throw new IllegalArgumentException("ERROR: No existe ninguna matricula con ese codigo");
        }
    }

    public static void anularMatricula() throws OperationNotSupportedException {

        controller.borrar(Consola.getMatriculaPorIdentificador());

    }

    public static void mostrarMatriculas() {

        ArrayList<Matricula> matriculaMostar =  controller.getMatriculas();

        if (matriculaMostar.isEmpty()) {
            throw new IllegalArgumentException("ERROR: No existen datos");
        }

        for (Matricula matricula : matriculaMostar) {
            System.out.println(matricula.imprimir());
        }
    }

    public static void mostrarMatriculasPorAlumno(){

        Alumno alumnoBuscado = Consola.getAlumnoPorDni();
        Alumno nuevoAlumno = controller.buscar(alumnoBuscado);

        if (nuevoAlumno == null) {
            throw new IllegalArgumentException("ERROR: No existen matricula de ese alumnos para mostrar.");
        }else{
            ArrayList<Matricula> matriculaMostrar = controller.getMatriculas(nuevoAlumno);
            for (Matricula matricula : matriculaMostrar) {
                System.out.println(matricula.imprimir());
            }
        }
    }

    public static void mostrarMatriculasPorCicloFormativo(){

        CicloFormativo cicloFormativoMostrar = Consola.getCicloFormativoPorCodigo();
        ArrayList<Matricula> matriculaMostrar = controller.getMatriculas(cicloFormativoMostrar);

        if (matriculaMostrar.isEmpty()) {
            throw new IllegalArgumentException("ERROR: No existen matriculas con ese ciclo formativo para mostrar.");
        }

        for (Matricula matricula : matriculaMostrar) {
            System.out.println(matricula.imprimir());
        }
    }

    public static void mostrarMatriculasPorCursoAcademico(){

        System.out.print("Introduzca el curso academicio:");
        String cursoAcademico = Entrada.cadena();


        ArrayList<Matricula> matriculaMostrar = controller.getMatriculas(cursoAcademico);

        if (matriculaMostrar.isEmpty()) {
            throw new IllegalArgumentException("ERROR: No existen matriculas con ese curso academico para mostrar.");
        }

        for (Matricula matricula : matriculaMostrar) {
            System.out.println(matricula.imprimir());
        }
    }
}
