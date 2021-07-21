package com.example.mihuellasapp.Modelo;

public class Mascota {

    private String Id;
    private String Nombre;
    private String Animal;
    private String Sexo;
    private String Color;
    private String Color2;
    private String Raza;
    private String Tamaño;
    private String Edad;
    private String Descripcion;
    private String Estado;
    private String IdDueño;
    private String ImageUrl;
    private String Contacto;


    public Mascota() {

    }

    public Mascota(String id, String nombre, String animal, String sexo, String color, String color2, String raza, String tamaño, String edad, String descripcion, String estado, String idDueño, String imageUrl, String contacto) {
        Id = id;
        Nombre = nombre;
        Animal = animal;
        Sexo = sexo;
        Color = color;
        Color2 = color2;
        Raza = raza;
        Tamaño = tamaño;
        Edad = edad;
        Descripcion = descripcion;
        Estado = estado;
        IdDueño = idDueño;
        ImageUrl = imageUrl;
        Contacto = contacto;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getAnimal() {
        return Animal;
    }

    public void setAnimal(String animal) {
        Animal = animal;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String sexo) {
        Sexo = sexo;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public String getColor2() {
        return Color2;
    }

    public void setColor2(String color2) {
        Color2 = color2;
    }

    public String getRaza() {
        return Raza;
    }

    public void setRaza(String raza) {
        Raza = raza;
    }

    public String getTamaño() {
        return Tamaño;
    }

    public void setTamaño(String tamaño) {
        Tamaño = tamaño;
    }

    public String getEdad() {
        return Edad;
    }

    public void setEdad(String edad) {
        Edad = edad;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public String getIdDueño() {
        return IdDueño;
    }

    public void setIdDueño(String idDueño) {
        IdDueño = idDueño;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getContacto() {
        return Contacto;
    }

    public void setContacto(String contacto) {
        Contacto = contacto;
    }
}
