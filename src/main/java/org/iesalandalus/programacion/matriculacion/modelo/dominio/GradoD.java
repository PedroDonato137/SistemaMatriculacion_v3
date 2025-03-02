package org.iesalandalus.programacion.matriculacion.modelo.dominio;

public class GradoD extends Grado {

    //Atributos de la clase
    private Modalidad modalidad;


    public GradoD(String nombre, int numAnios, Modalidad modalidad) {
        super(nombre);
        setNumAnios(numAnios);
        setModalidad(modalidad);
    }

    public Modalidad getModalidad() {
        return modalidad;
    }

    public void setModalidad(Modalidad modalidad) {
        this.modalidad = modalidad;
    }

    @Override
    public void setNumAnios(int numAnios) {
        if(numAnios < 2 || numAnios > 3){
            System.out.println("ERROR: Los años del grado de este tipo son 2 o 3 años");
        }else{
            this.numAnios = numAnios;
        }
    }

    @Override
    public String toString() {
        return "GradoD{" +
                "modalidad=" + modalidad +
                ", nombre='" + nombre + '\'' +
                ", iniciales='" + iniciales + '\'' +
                ", numAnios=" + numAnios +
                '}';
    }
}
