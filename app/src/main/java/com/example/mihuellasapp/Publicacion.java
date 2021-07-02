package com.example.mihuellasapp;

public class Publicacion {

    private String animal;
    private String tamano;
    private String raza;
    private String color;
    private String sexo;
    private String lugar;
    private String edad;

    public Publicacion() {
    }

    public Publicacion(String animal, String tamano, String raza, String color, String sexo, String lugar, String edad) {
        this.animal = animal;
        this.tamano = tamano;
        this.raza = raza;
        this.color = color;
        this.sexo = sexo;
        this.lugar = lugar;
        this.edad = edad;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }
}
