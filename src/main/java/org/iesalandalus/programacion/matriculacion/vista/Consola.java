package org.iesalandalus.programacion.matriculacion.vista;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.*;
import org.iesalandalus.programacion.utilidades.Entrada;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Consola {

    private Consola() {
        //Constructor privado para no poder instanciarlo
    }

    public static void mostrarMenu(){
        System.out.println("=== Opciones del menú ===");
        for (Opcion opcion : Opcion.values()) {
            System.out.println(opcion.toString());
        }
    }
    public static Opcion elegirOpcion(){

        int ordinalOpcion = -1;
        do {
            System.out.print("Selecciona que opción quiere realizar: ");
            ordinalOpcion = Entrada.entero();
        } while (ordinalOpcion < 0 || ordinalOpcion >= Opcion.values().length);

        return Opcion.values()[ordinalOpcion];
    }

    /*---------------------------------------------------------------------------------------------------------------*/
    /*------------------------------------------------ ALUMNOS ------------------------------------------------------*/
    /*---------------------------------------------------------------------------------------------------------------*/

    public static Alumno leerAlumno(){

        String nombreAlumno;
        String dniAlumno;
        String correoAlumno;
        String telefonoAlumno;

        do {
            System.out.print("Introduce el nombre del Alumno: ");
            nombreAlumno = Entrada.cadena();
        } while (nombreAlumno.isEmpty());

        do {
            System.out.print("Introduce el DNI del Alumno: ");
            dniAlumno = Entrada.cadena();
        } while (dniAlumno.isEmpty());

        do {
            System.out.print("Introduce el correo del Alumno: ");
            correoAlumno = Entrada.cadena();
        } while (correoAlumno.isEmpty());

        do {
            System.out.print("Introduce el telefono del Alumno: ");
            telefonoAlumno = Entrada.cadena();
        } while (telefonoAlumno.isEmpty());

        try {
            return new Alumno(nombreAlumno, dniAlumno, correoAlumno, telefonoAlumno, leerFecha("Introduce la fecha de nacimiento(dd/mm/yyyy): "));
        } catch (Exception e) {
            throw new IllegalArgumentException("ERROR: No se pudo crear el alumno con el DNI proporcionado.", e);
        }
    }

    public static Alumno getAlumnoPorDni() {

        String dniAlumno;
        // Crear datos ficticios para el alumno
        String nombreFicticio = "Ficticio";
        String telefonoFicticio = "609822699";
        String correoFicticio = "correo@ficticio.com";
        LocalDate fechaNacimientoFicticio = LocalDate.of(1990, 6, 9); // Fecha ficticia válida (+16)

        do {
            System.out.print("Introduce el DNI del Alumno: ");
            dniAlumno = Entrada.cadena();
        } while (dniAlumno.isEmpty());

        try {
            return new Alumno(nombreFicticio, dniAlumno, correoFicticio, telefonoFicticio, fechaNacimientoFicticio);
        } catch (Exception e) {
            throw new IllegalArgumentException("ERROR: No se pudo crear el alumno con el DNI proporcionado.", e);
        }
    }

    public static LocalDate leerFecha(String mensaje){
        LocalDate fecha = null;
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        while (fecha == null) {
            System.out.print(mensaje);
            String fechaLeida = Entrada.cadena();

            try {
                fecha = LocalDate.parse(fechaLeida, formatoFecha);
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("ERROR: Fecha incorrecta");
            }
        }

        return fecha;
    }

    public static TiposGrado leerTipoGrado(){

        int opcionTipo;

        System.out.println("Seleccione un grado de la lista:");
        for (TiposGrado tiposGrado : TiposGrado.values()) {
            System.out.println(tiposGrado.imprimir());
        }

        do {
            System.out.print("Introduce el número correspondiente: ");
            opcionTipo = Entrada.entero();
        } while (opcionTipo < 0 || opcionTipo > TiposGrado.values().length);

        return TiposGrado.values()[opcionTipo];
    }

    public static Modalidad leerModalidad(){

        int opcionModalidad;

        System.out.println("Seleccione un grado de la lista:");
        for (Modalidad modalidad : Modalidad.values()) {
            System.out.println(modalidad.imprimir());
        }

        do {
            System.out.print("Introduce el número correspondiente: ");
            opcionModalidad = Entrada.entero();
        } while (opcionModalidad < 0 || opcionModalidad > Modalidad.values().length);

        return Modalidad.values()[opcionModalidad];
    }

    public static Grado leerGrado(){

        TiposGrado tiposGrado;

        System.out.print("Introduzca el nombre del grado: ");
        String nombreGrado = Entrada.cadena();
        System.out.print("Introduzca numero de años: ");
        int numAnios = Entrada.entero();

        tiposGrado = leerTipoGrado();

        if(tiposGrado.equals(TiposGrado.GRADOD)){
            Modalidad modalidad = leerModalidad();
            return new GradoD(nombreGrado, numAnios, modalidad);
        }else{
            System.out.print("Introduzca numero de edición: ");
            int numEdiciones = Entrada.entero();
            return new GradoE(nombreGrado, numAnios, numEdiciones);
        }
    }

    /*---------------------------------------------------------------------------------------------------------------*/
    /*----------------------------------------- CICLOS FORMATIVOS ---------------------------------------------------*/
    /*---------------------------------------------------------------------------------------------------------------*/

    public static CicloFormativo leerCicloFormativo(){

        int codigoCiclo;
        String familiaProfesionalCiclo;
        Grado gradoCiclo;
        String nombreCiclo;
        int horasCiclo;

        CicloFormativo nuevoGrado = null;

        do {
            System.out.print("Introduce el código del ciclo formativo: ");
            codigoCiclo = Entrada.entero();
        } while (codigoCiclo < 1000 || codigoCiclo > 9999);

        do {
            System.out.print("Introduce la familia profesional: ");
            familiaProfesionalCiclo = Entrada.cadena();
        } while (familiaProfesionalCiclo.isEmpty());

        gradoCiclo = Consola.leerGrado();

        do {
            System.out.print("Introduce el nombre del ciclo formativo: ");
            nombreCiclo = Entrada.cadena();
        } while (nombreCiclo.isEmpty());

        do {
            System.out.print("Introduce el horas del ciclo formativo: ");
            horasCiclo = Entrada.entero();
        } while (horasCiclo < 0 || horasCiclo > 2000);

        nuevoGrado = new CicloFormativo(codigoCiclo, familiaProfesionalCiclo, gradoCiclo, nombreCiclo, horasCiclo);

        return new CicloFormativo(nuevoGrado);

    }

    public static void mostrarCiclosFormativos(ArrayList<CicloFormativo> ciclosFormativos){


        if (ciclosFormativos.isEmpty()) {
            throw new IllegalArgumentException("ERROR: No existen ciclos formativos para mostrar.");
        }

        for (CicloFormativo cicloFormativo : ciclosFormativos) {
            System.out.println(cicloFormativo.imprimir());
        }


    }

    public static CicloFormativo getCicloFormativoPorCodigo() {

        int codigoCiclo;
        // Crear datos ficticios para el ciclo formativo
        String familiaProfesionalCiclo = "Familia profesional Ficticia";
        Grado gradoCiclo = new GradoE("Grado Ficticio", 1, 1);
        String nombreCiclo = "Ficticio";
        int horasCiclo = 25;

        do {
            System.out.print("Introduce el Codigo del ciclo formativo: ");
            codigoCiclo = Entrada.entero();
        } while (codigoCiclo == 0);

        try {
            return new CicloFormativo(codigoCiclo, familiaProfesionalCiclo, gradoCiclo, nombreCiclo, horasCiclo );
        } catch (Exception e) {
            throw new IllegalArgumentException("ERROR: No se pudo crear el ciclo formativo con ese código", e);
        }
    }

    public static Curso leerCurso(){

        int opcionCurso;
        System.out.println("Seleccione un curso de la lista:");
        for (Curso curso : Curso.values()) {
            System.out.println(curso.imprimir());
        }

        do {
            System.out.print("Introduce el número correspondiente: ");
            opcionCurso = Entrada.entero();
        } while (opcionCurso < 0 || opcionCurso > Curso.values().length);

        return Curso.values()[opcionCurso];
    }

    public static EspecialidadProfesorado leerEspecialidadProfesorado(){

        int opcionEspecialidad;
        System.out.println("Seleccione una especialidad de la lista:");
        for (EspecialidadProfesorado especialidadProfesorado : EspecialidadProfesorado.values()) {
            System.out.println(especialidadProfesorado.imprimir());
        }

        do {
            System.out.print("Introduce el número correspondiente: ");
            opcionEspecialidad = Entrada.entero();
        } while (opcionEspecialidad < 0 || opcionEspecialidad > EspecialidadProfesorado.values().length);

        return EspecialidadProfesorado.values()[opcionEspecialidad];
    }


    /*---------------------------------------------------------------------------------------------------------------*/
    /*---------------------------------------------- ASIGNATURAS ----------------------------------------------------*/
    /*---------------------------------------------------------------------------------------------------------------*/

    public static Asignatura leerAsignatura(CicloFormativo cicloFormativo){

        String codigoAsignatura;
        String nombreAsignatura;
        int horasAnualesAsignatura;
        Curso cursoAsignatura;
        int horasDesdobleAsignatura;
        EspecialidadProfesorado especialidadProfesoradoAsignatura;

        Asignatura nuevaAsignatura = null;

        do {
            System.out.print("Introduce el Codigo de la asignatura: ");
            codigoAsignatura = Entrada.cadena();
        } while (codigoAsignatura.isEmpty());

        do {
            System.out.print("Introduce el nombre de la asignatura: ");
            nombreAsignatura = Entrada.cadena();
        } while (nombreAsignatura.isEmpty());

        do {
            System.out.print("Introduce las horas anuales de la asignatura: ");
            horasAnualesAsignatura = Entrada.entero();
        } while (horasAnualesAsignatura == 0);

        cursoAsignatura = Consola.leerCurso();

        do {
            System.out.print("Introduce las horas de desdoble de la asignatura: ");
            horasDesdobleAsignatura = Entrada.entero();
        } while (horasDesdobleAsignatura == 0);

        especialidadProfesoradoAsignatura = Consola.leerEspecialidadProfesorado();

        nuevaAsignatura = new Asignatura(codigoAsignatura, nombreAsignatura, horasAnualesAsignatura, cursoAsignatura, horasDesdobleAsignatura, especialidadProfesoradoAsignatura, cicloFormativo);
        return new Asignatura(nuevaAsignatura);

    }

    public static Asignatura getAsignaturaPorCodigo(){

        String codigoAsignatura;
        String nombreAsignatura = "Base de datos Ficticia";
        int horasAnualesAsignatura = 200;
        Curso cursoAsignatura = Curso.PRIMERO;
        int horasDesdobleAsignatura = 2;
        EspecialidadProfesorado especialidadProfesoradoAsignatura = EspecialidadProfesorado.INFORMATICA;
        Grado gradoCiclo = new GradoE("Grado Ficticio", 1, 1);
        CicloFormativo cicloFormativoAsignatura = new CicloFormativo(1001, "Informática y Comunicaciones", gradoCiclo, "DAW", 500 );

        do {
            System.out.print("Introduce el Codigo de la asignatura: ");
            codigoAsignatura = Entrada.cadena();
        } while (codigoAsignatura.isEmpty());

        try {
            return new Asignatura(codigoAsignatura, nombreAsignatura, horasAnualesAsignatura, cursoAsignatura, horasDesdobleAsignatura, especialidadProfesoradoAsignatura, cicloFormativoAsignatura);
        } catch (Exception e) {
            throw new IllegalArgumentException("ERROR: No se pudo crear la asignatura con ese código", e);
        }

    }

    public static void mostrarAsignautras(ArrayList<Asignatura> asignaturas){

        if (asignaturas.isEmpty()) {
            throw new IllegalArgumentException("ERROR: No existen asignaturas para mostrar.");
        }

        for (Asignatura asignatura : asignaturas) {
            System.out.println(asignatura.imprimir());
        }

    }

    public static ArrayList<Asignatura> elegirAsignaturasMatricula(ArrayList<Asignatura> asignaturasRegistradas){
        // Variables auxiliales
        Asignatura nuevaAsignaturas;
        ArrayList<Asignatura> asignaturasMatricula = new ArrayList<>();
        int i = 0; // Varible auxiliar para las asignaturas

        // Buscar Asignaturas
        do {
            nuevaAsignaturas = getAsignaturaPorCodigo();
            for(Asignatura asignatura : asignaturasRegistradas){
                if(nuevaAsignaturas.getCodigo().equalsIgnoreCase(asignatura.getCodigo())){
                    if (!asignaturaYaMatriculada(asignaturasMatricula, nuevaAsignaturas)) {
                        asignaturasMatricula.add(nuevaAsignaturas);
                        i++;
                    }
                }
            }
        } while (i < 3);

        return asignaturasMatricula;
    }

    /*---------------------------------------------------------------------------------------------------------------*/
    /*---------------------------------------------- MATRICULAS -----------------------------------------------------*/
    /*---------------------------------------------------------------------------------------------------------------*/

    private static boolean asignaturaYaMatriculada(ArrayList<Asignatura> asignaturasMatricula, Asignatura asignatura){

        if (asignaturasMatricula == null || asignatura == null) {
            throw new IllegalArgumentException("ERROR: Ni la lista ni la asignatura pueden ser nulas.");
        }

        for (Asignatura asignaturaEnLista : asignaturasMatricula) {
            if (asignaturaEnLista != null && asignaturaEnLista.getCodigo().equalsIgnoreCase(asignatura.getCodigo())){
                return true; // La asignatura ya está en la lista.
            }
        }

        return false; // La asignatura no está en la lista.
    }

    public static Matricula leerMatricula(Alumno alumno, ArrayList<Asignatura> Asignaturas){

        int idMatricula;
        String cursoAcademico;
        LocalDate fechaMatriculacion;

        Matricula nuevaMatricula = null;

        do {
            System.out.print("Introduce el ID de la matricula: ");
            idMatricula = Entrada.entero();
        } while (idMatricula == 0);

        do {
            System.out.print("Introduce el Curso academico: ");
            cursoAcademico = Entrada.cadena();
        } while (cursoAcademico.isEmpty());

        fechaMatriculacion = leerFecha("Introduce la fecha de matriculación: ");

        nuevaMatricula = new Matricula(idMatricula, cursoAcademico, fechaMatriculacion, null, alumno, Asignaturas );
        return new Matricula(nuevaMatricula);
    }

    public static Matricula getMatriculaPorIdentificador(){

        int idMatricula;
        // Datos ficticios
        String cursoAcademico = "24-25";
        //Fecha matricula
        String fechaMatriculacion = "21/01/2025";
        LocalDate fecha = null;
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        fecha = LocalDate.parse(fechaMatriculacion, formatoFecha);

        LocalDate fechaNacimientoFicticio = LocalDate.of(1990, 6, 9); // Fecha ficticia válida (+16)

        Alumno alumno = new Alumno("Pedro", "54119272L", "pedrodonatogarcia@gmail.com", "609822699", fechaNacimientoFicticio);
        ArrayList<Asignatura> coleccionAsignaturas = new ArrayList<>();

        Grado gradoCiclo = new GradoE("Grado Ficticio", 1, 1);
        CicloFormativo cicloFicticio = new CicloFormativo(1001, "Informatica", gradoCiclo, "Informatica", 2000);
        Asignatura asignaturaFicticia = new Asignatura("1001", "Base Datos", 100, Curso.PRIMERO, 2, EspecialidadProfesorado.INFORMATICA, cicloFicticio);
        coleccionAsignaturas.add(asignaturaFicticia);

        Matricula nuevaMatricula = null;

        do {
            System.out.print("Introduce el ID de la matrícula: ");
            idMatricula = Entrada.entero();
        } while (idMatricula == 0);

        nuevaMatricula = new Matricula(idMatricula, cursoAcademico, fecha, null, alumno, coleccionAsignaturas );
        return new Matricula(nuevaMatricula);
    }

}
