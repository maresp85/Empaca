package com.co.empaca.models;

public class Products {

    private String imagen;
    private String name;
    private String description;

    public Products(){ //(int imagen, String name, String description) {
        /*this.imagen = imagen;
        this.name = name;
        this.description = description;*/
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}