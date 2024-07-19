package com.grupo4.proyecto.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Empleados {

    @Id
    private int idEmpleado;
    
    @NotNull
    private String nombre_completo;
    
    @NotNull
    private String identidad;
    
    @NotNull
    private int edad;
    
    @NotNull
    private String sexo;
    
    @NotNull
    private int areaId;
    
    @NotNull
    private String area_nombre;
    
    @NotNull
    private int cargoId;
    
    @NotNull
    private String cargo_nombre;

    @NotNull
    private Date fecha_ingreso;


    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombreCompleto() {
        return nombre_completo;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombre_completo = nombreCompleto;
    }

    public String getIdentidad() {
        return identidad;
    }

    public void setIdentidad(String identidad) {
        this.identidad = identidad;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getAreaNombre() {
        return area_nombre;
    }

    public void setAreaNombre(String areaNombre) {
        this.area_nombre = areaNombre;
    }

    public int getCargoId() {
        return cargoId;
    }

    public void setCargoId(int cargoId) {
        this.cargoId = cargoId;
    }

    public String getCargoNombre() {
        return cargo_nombre;
    }

    public void setCargoNombre(String cargoNombre) {
        this.cargo_nombre = cargoNombre;
    }

    public Date getFechaIngreso() {
        return fecha_ingreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fecha_ingreso = fechaIngreso;
    }
}
