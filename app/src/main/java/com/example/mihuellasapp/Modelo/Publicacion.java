package com.example.mihuellasapp.Modelo;

public class Publicacion {

    private String Id;
    private String AnimalPublicacion;
    private String TamañoPublicacion;
    private String RazaPublicacion;
    private String ColorPublicacion;
    private String Color2Publicacion;
    private String SexoPublicacion;
    private String DescripcionPublicacion;
    private String EdadPublicacion;
    private String IdDueño;
    private String ImageUrl;

    public Publicacion() {
    }

    public Publicacion(String id, String animalPublicacion, String tamañoPublicacion, String razaPublicacion, String colorPublicacion, String color2Publicacion, String sexoPublicacion, String descripcionPublicacion, String edadPublicacion, String idDueño, String imageUrl) {
        Id = id;
        AnimalPublicacion = animalPublicacion;
        TamañoPublicacion = tamañoPublicacion;
        RazaPublicacion = razaPublicacion;
        ColorPublicacion = colorPublicacion;
        Color2Publicacion = color2Publicacion;
        SexoPublicacion = sexoPublicacion;
        DescripcionPublicacion = descripcionPublicacion;
        EdadPublicacion = edadPublicacion;
        IdDueño = idDueño;
        ImageUrl = imageUrl;
    }

    public String getIdPublicacion() {
        return Id;
    }

    public void setIdPublicacion(String id) {
        Id = id;
    }

    public String getAnimalPublicacion() {
        return AnimalPublicacion;
    }

    public void setAnimalPublicacion(String animalPublicacion) {
        AnimalPublicacion = animalPublicacion;
    }

    public String getTamañoPublicacion() {
        return TamañoPublicacion;
    }

    public void setTamañoPublicacion(String tamañoPublicacion) {
        TamañoPublicacion = tamañoPublicacion;
    }

    public String getRazaPublicacion() {
        return RazaPublicacion;
    }

    public void setRazaPublicacion(String razaPublicacion) {
        RazaPublicacion = razaPublicacion;
    }

    public String getColorPublicacion() {
        return ColorPublicacion;
    }

    public void setColorPublicacion(String colorPublicacion) {
        ColorPublicacion = colorPublicacion;
    }

    public String getColor2Publicacion() {
        return Color2Publicacion;
    }

    public void setColor2Publicacion(String color2Publicacion) {
        Color2Publicacion = color2Publicacion;
    }

    public String getSexoPublicacion() {
        return SexoPublicacion;
    }

    public void setSexoPublicacion(String sexoPublicacion) {
        SexoPublicacion = sexoPublicacion;
    }

    public String getDescripcionPublicacion() {
        return DescripcionPublicacion;
    }

    public void setDescripcionPublicacion(String descripcionPublicacion) {
        DescripcionPublicacion = descripcionPublicacion;
    }

    public String getEdadPublicacion() {
        return EdadPublicacion;
    }

    public void setEdadPublicacion(String edadPublicacion) {
        EdadPublicacion = edadPublicacion;
    }

    public String getIdDueñoPublicacion() {
        return IdDueño;
    }

    public void setIdDueñoPublicacion(String idDueño) {
        IdDueño = idDueño;
    }

    public String getImageUrlPublicacion() {
        return ImageUrl;
    }

    public void setImageUrlPublicacion(String imageUrl) {
        ImageUrl = imageUrl;
    }
}
