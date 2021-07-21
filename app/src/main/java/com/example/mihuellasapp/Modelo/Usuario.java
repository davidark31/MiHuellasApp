package com.example.mihuellasapp.Modelo;

public class Usuario {

    private String Nombre;
    private String Correo;
    private String Telefono;
    private String Contraseña;
    private String ID;


    public Usuario() {
    }

    public Usuario(String nombre, String correo, String telefono, String contraseña, String ID) {
        this.Nombre = nombre;
        this.Correo = correo;
        this.Telefono = telefono;
        this.Contraseña = contraseña;
        this.ID = ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String contraseña) {
        Contraseña = contraseña;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
