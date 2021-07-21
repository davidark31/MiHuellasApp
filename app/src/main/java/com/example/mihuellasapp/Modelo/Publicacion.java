package com.example.mihuellasapp.Modelo;

public class Publicacion implements Comparable<Publicacion> {

    private String Id;
    private String Animal;
    private String Tamano;
    private String Raza;
    private String Color;
    private String Color2;
    private String Sexo;
    private String Descripcion;
    private String Edad;
    private String IdPublicador;
    private String ImageUrl;
    private Double Latitud;
    private Double Longitud;
    private String Fecha;
    private int similutid;
    private String Contacto;

    public Publicacion() {
    }

    public Publicacion(String id, String animal, String tamano, String raza, String color, String color2, String sexo, String descripcion, String edad, String idPublicador, String imageUrl, Double latitud, Double longitud, String fecha,String contacto) {
        Id = id;
        Animal = animal;
        Tamano = tamano;
        Raza = raza;
        Color = color;
        Color2 = color2;
        Sexo = sexo;
        Descripcion = descripcion;
        Edad = edad;
        IdPublicador = idPublicador;
        ImageUrl = imageUrl;
        Latitud = latitud;
        Longitud = longitud;
        Fecha = fecha;
        Contacto = contacto;
    }

    public Publicacion(String id, String animal, String tamano, String raza, String color, String color2, String sexo, String descripcion, String edad, String idPublicador, String imageUrl, Double latitud, Double longitud, String fecha, int similutid,String contacto) {
        Id = id;
        Animal = animal;
        Tamano = tamano;
        Raza = raza;
        Color = color;
        Color2 = color2;
        Sexo = sexo;
        Descripcion = descripcion;
        Edad = edad;
        IdPublicador = idPublicador;
        ImageUrl = imageUrl;
        Latitud = latitud;
        Longitud = longitud;
        Fecha = fecha;
        this.similutid = similutid;
        Contacto=contacto;
    }

    public int getSimilutid() {
        return similutid;
    }

    public void setSimilutid(int similutid) {
        this.similutid = similutid;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getAnimal() {
        return Animal;
    }

    public void setAnimal(String animal) {
        Animal = animal;
    }

    public String getTamano() {
        return Tamano;
    }

    public void setTamano(String tamano) {
        Tamano = tamano;
    }

    public String getRaza() {
        return Raza;
    }

    public void setRaza(String raza) {
        Raza = raza;
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

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String sexo) {
        Sexo = sexo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getEdad() {
        return Edad;
    }

    public void setEdad(String edad) {
        Edad = edad;
    }

    public String getIdPublicador() {
        return IdPublicador;
    }

    public void setIdPublicador(String idPublicador) {
        IdPublicador = idPublicador;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public Double getLatitud() {
        return Latitud;
    }

    public void setLatitud(Double latitud) {
        Latitud = latitud;
    }

    public Double getLongitud() {
        return Longitud;
    }

    public void setLongitud(Double longitud) {
        Longitud = longitud;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }


    public String getContacto() {
        return Contacto;
    }

    public void setContacto(String contacto) {
        Contacto = contacto;
    }

    @Override
    public int compareTo(Publicacion o) {
        if (o.getSimilutid() > similutid) {
            return -1;
        } else {
            if (o.getSimilutid() < similutid) {
                return 1;
            }
        }
        return 0;
    }


}
