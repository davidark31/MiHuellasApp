package com.example.mihuellasapp.Modelo;

public class MascotaPerdida {


    private String Id;
    private String iDMascota;
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
    private String DescripcionSuceso;
    private String Fecha;
    private String Latitud;
    private String Longitud;
    private String Recompensa;
    private String Contacto;

    public MascotaPerdida() {
    }


    public MascotaPerdida(String id, String iDMascota, String nombre, String animal, String sexo, String color, String color2, String raza, String tamaño, String edad, String descripcion, String estado, String idDueño, String imageUrl, String descripcionSuceso, String fecha, String latitud, String longitud, String recompensa, String contacto) {
        Id = id;
        this.iDMascota = iDMascota;
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
        DescripcionSuceso = descripcionSuceso;
        Fecha = fecha;
        Latitud = latitud;
        Longitud = longitud;
        Recompensa = recompensa;
        Contacto = contacto;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getiDMascota() {
        return iDMascota;
    }

    public void setiDMascota(String iDMascota) {
        this.iDMascota = iDMascota;
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

    public String getDescripcionSuceso() {
        return DescripcionSuceso;
    }

    public void setDescripcionSuceso(String descripcionSuceso) {
        DescripcionSuceso = descripcionSuceso;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getLatitud() {
        return Latitud;
    }

    public void setLatitud(String latitud) {
        Latitud = latitud;
    }

    public String getLongitud() {
        return Longitud;
    }

    public void setLongitud(String longitud) {
        Longitud = longitud;
    }

    public String getRecompensa() {
        return Recompensa;
    }

    public void setRecompensa(String recompensa) {
        Recompensa = recompensa;
    }

    public String getContacto() {
        return Contacto;
    }

    public void setContacto(String contacto) {
        Contacto = contacto;
    }
}
