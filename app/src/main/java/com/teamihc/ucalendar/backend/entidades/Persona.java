package com.teamihc.ucalendar.backend.entidades;

import com.teamihc.ucalendar.backend.entidades.enums.Sexo;

import java.util.Calendar;
import java.util.Date;

public class Persona
{
    protected String correo;
    protected Date fechaNacimiento;
    protected Sexo sexo;
    protected String rol;
    protected String nombres;
    protected String apellidos;
    
    public Persona(String nombres, String apellidos, String correo, Date fechaNacimiento, Sexo sexo, String rol)
    {
        this.correo = correo;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.rol = rol;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }
    
    public Persona()
    {
        this.correo = "";
        this.fechaNacimiento = Calendar.getInstance().getTime();
        this.sexo = Sexo.Hombre;
        this.rol = "";
        this.nombres = "";
        this.apellidos = "";
    }
    
    public String getCorreo()
    {
        return correo;
    }
    public void setCorreo(String correo)
    {
        this.correo = correo;
    }
    public Date getFechaNacimiento()
    {
        return fechaNacimiento;
    }
    public void setFechaNacimiento(Date fechaNacimiento)
    {
        this.fechaNacimiento = fechaNacimiento;
    }
    public Sexo getSexo()
    {
        return sexo;
    }
    public void setSexo(Sexo sexo)
    {
        this.sexo = sexo;
    }
    public String getRol()
    {
        return rol;
    }
    public void setRol(String rol)
    {
        this.rol = rol;
    }
    public String getNombres()
    {
        return nombres;
    }
    public void setNombres(String nombres)
    {
        this.nombres = nombres;
    }
    public String getApellidos()
    {
        return apellidos;
    }
    public void setApellidos(String apellidos)
    {
        this.apellidos = apellidos;
    }
}
