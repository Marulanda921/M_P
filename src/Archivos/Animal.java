package Archivos;

import java.io.Serializable;

public class Animal implements Serializable {

    private static final long serialVersionUID = 1L;
    //public static final int TAMANO_REGISTRO = 8;
    private int codigo;
    private String nombreEspecie;

    public Animal() {
        codigo = 0;
        nombreEspecie = "";
    }

    public Animal(int codigo, String nombreEspecie) {
        this.codigo = codigo;
        this.nombreEspecie = nombreEspecie;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombreEspecie() {
        return nombreEspecie;
    }

    public void setNombreEspecie(String nombreEspecie) {
        this.nombreEspecie = nombreEspecie;
    }
}
