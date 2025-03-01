package org.iesalandalus.programacion.matriculacion.modelo.dominio;

public abstract class Grado {

    // Atributos de la clase
    protected String nombre;
    protected String iniciales;
    protected int numAnios;

    public Grado(String nombre) {
        setNombre(nombre);
        setIniciales();
    }

    public String getNombre() {
        return nombre;
    }

    protected void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNumAnios(int numAnios) {
        this.numAnios = numAnios;
    }

    private void setIniciales() {

        if (nombre == null || nombre.isEmpty()) {
            throw new NullPointerException("ERROR: No es posible copiar un ciclo formativo nulo.");
        }

        String[] palabras = nombre.split("\\s+");
        StringBuilder resultado = new StringBuilder();

        for (String palabra : palabras) {
            resultado.append(palabra.toUpperCase().charAt(0));
        }

        this.iniciales = resultado.toString();
    }

    @Override
    public String toString() {
        return "(" + iniciales + ") - " + nombre;
    }
}
