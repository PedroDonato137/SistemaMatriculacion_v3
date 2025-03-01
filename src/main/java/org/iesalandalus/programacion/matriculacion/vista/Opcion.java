package org.iesalandalus.programacion.matriculacion.vista;

import javax.naming.OperationNotSupportedException;

public enum Opcion {

    //-------------------------------  Alumnos  ------------------------------------//
    INSERTAR_ALUMNO("Insertar alumno") {
        @Override
        public void ejecutar() {
            vista.insertarAlumno();
        }
    },
    BUSCAR_ALUMNO("Buscar alumno") {
        @Override
        public void ejecutar() {
            vista.buscarAlumno();
        }
    },
    BORRAR_ALUMNO("Borrar alumno") {
        @Override
        public void ejecutar() throws OperationNotSupportedException {
            vista.borrarAlumno();
        }
    },
    MOSTRAR_ALUMNOS("Mostrar alumnos") {
        @Override
        public void ejecutar() {
            vista. mostarAlumnos();
        }
    },

    //-------------------------------  Ciclos formativos  ------------------------------------//
    INSERTAR_CICLO_FORMATIVO("Insertar ciclo formativo") {
        @Override
        public void ejecutar() {
            vista.insertarCicloFormativo();
        }
    },
    BUSCAR_CICLO_FORMATIVO("Buscar ciclo formativo") {
        @Override
        public void ejecutar() {
            vista.buscarCicloFormativo();
        }
    },
    BORRAR_CICLO_FORMATIVO("Borrar ciclo formativo") {
        @Override
        public void ejecutar() throws OperationNotSupportedException {
            vista.borrarCicloFormativo();
        }
    },
    MOSTRAR_CICLOS_FORMATIVOS("Mostrar ciclos formativos") {
        @Override
        public void ejecutar() {
            vista.mostarCiclosFormativos();
        }
    },

    //-------------------------------  Asignaturas  ------------------------------------//
    INSERTAR_ASIGNATURA("Insertar asignatura") {
        @Override
        public void ejecutar() {
            vista.insertarAsignatura();
        }
    },
    BUSCAR_ASIGNATURA("Buscar asignatura") {
        @Override
        public void ejecutar() {
            vista.buscarAsignatura();
        }
    },
    BORRAR_ASIGNATURA("Borrar asignatura") {
        @Override
        public void ejecutar() throws OperationNotSupportedException {
            vista.borrarAsignatura();
        }
    },
    MOSTRAR_ASIGNATURAS("Mostrar asignaturas") {
        @Override
        public void ejecutar() {
            vista.mostrarAsignaturas();
        }
    },

    //-------------------------------  Matriculas  ------------------------------------//
    INSERTAR_MATRICULA("Insertar matrícula") {
        @Override
        public void ejecutar() throws OperationNotSupportedException {
            vista.insertarMatricula();
        }
    },
    BUSCAR_MATRICULA("Buscar matrícula") {
        @Override
        public void ejecutar() {
            vista.buscarMatricula();
        }
    },
    ANULAR_MATRICULA("Anular matrícula") {
        @Override
        public void ejecutar() throws OperationNotSupportedException {
            vista.anularMatricula();
        }
    },
    MOSTRAR_MATRICULAS("Mostrar matrículas") {
        @Override
        public void ejecutar() {
            vista.mostrarMatriculas();
        }
    },
    MOSTRAR_MATRICULAS_POR_ALUMNO("Mostrar matrículas por alumno") {
        @Override
        public void ejecutar() {
            vista.mostrarMatriculasPorAlumno();
        }
    },
    MOSTRAR_MATRICULAS_POR_CICLO_FORMATIVO("Mostrar matrículas por ciclo formativo") {
        @Override
        public void ejecutar() {
            vista.mostrarMatriculasPorCicloFormativo();
        }
    },
    MOSTRAR_MATRICULAS_POR_CURSO_ACADEMICO("Mostrar matrículas por curso académico") {
        @Override
        public void ejecutar() {
            vista.mostrarMatriculasPorCursoAcademico();
        }
    },
    SALIR("Salir") {
        @Override
        public void ejecutar() {
            System.out.println("Hasta luego lucas");
        }
    };

    // Atributos del ENUM
    private final String mensajeAMostrar;
    private static Vista vista;

    Opcion(String mensajeAMostrar){
        this.mensajeAMostrar = mensajeAMostrar;
    }

    public static void setVista(Vista vista) {
        Opcion.vista = vista;
    }

    public abstract void ejecutar() throws OperationNotSupportedException;

    @Override
    public String toString() {
        return this.ordinal() + " .- " + mensajeAMostrar;
    }
}
