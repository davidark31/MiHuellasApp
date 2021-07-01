package com.example.mihuellasapp;

public class Mascota {

    private String id;
    private String nombre;
    private String animal;
    private String sexo;
    private String color;
    private String color2;
    private String raza;
    private String tamaño;
    private String edad;
    private String descripcion;
    private String estado;
    private String IdDueño;
    private String ImageUrl;

    public Mascota(String nombre, String animal, String sexo, String color, String raza, String tamaño, String edad, String descripcion, String estado, String idDueño,String ImageUrl) {
        this.nombre = nombre;
        this.animal = animal;
        this.sexo = sexo;
        this.color = color;
        this.raza = raza;
        this.tamaño = tamaño;
        this.edad = edad;
        this.descripcion = descripcion;
        this.estado = estado;
        this.IdDueño = idDueño;
        this.ImageUrl = ImageUrl;

    }


    public void Mascota() {

    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor2() {
        return color2;
    }

    public void setColor2(String color2) {
        this.color2 = color2;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getTamaño() {
        return tamaño;
    }

    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getIdDueño() {
        return IdDueño;
    }

    public void setIdDueño(String idDueño) {
        IdDueño = idDueño;
    }


}
