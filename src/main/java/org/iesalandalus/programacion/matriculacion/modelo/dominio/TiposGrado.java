package org.iesalandalus.programacion.matriculacion.modelo.dominio;

public enum TiposGrado {
    GRADOD("GRADOD"),
    GRADOE("GRADOE");

    private final String cadenaAMostrar;

    TiposGrado(String cadenaAMostrar) {
        this.cadenaAMostrar = cadenaAMostrar;
    }

    public String imprimir(){
        return this.ordinal() + ".-" + cadenaAMostrar;
    }

    @Override
    public String toString() {
        return "Tipos de Grado{" + "cadenaAMostrar='" + cadenaAMostrar + '\'' + '}';
    }
}
