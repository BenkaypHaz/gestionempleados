package com.grupo4.proyecto.data;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class Formacion {

    @Id
    private int id_formacion;
    
    @NotNull
    private String nombre_empleado;
    
    @NotNull
    private String educacion;
    
    @NotNull
    private String institucion;
    
    @NotNull
    private String carrera_certificaciones;
    
    @NotNull
    private int puntuacion;


    public int getIdFormacion() {
        return id_formacion;
    }

    public void setIdFormacion(int idFormacion) {
        this.id_formacion = idFormacion;
    }

    public String getNombreEmpleado() {
        return nombre_empleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombre_empleado = nombreEmpleado;
    }

    public String getEducacion() {
        return educacion;
    }

    public void setEducacion(String educacion) {
        this.educacion = educacion;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getCarreraCertificaciones() {
        return carrera_certificaciones;
    }

    public void setCarreraCertificaciones(String carreraCertificaciones) {
        this.carrera_certificaciones = carreraCertificaciones;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
}
