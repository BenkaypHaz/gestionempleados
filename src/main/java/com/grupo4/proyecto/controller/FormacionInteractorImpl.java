package com.grupo4.proyecto.controller;


import com.grupo4.proyecto.data.FormacionResponse;
import com.grupo4.proyecto.repository.DatabaseRepositoryImpl;
import com.grupo4.proyecto.views.formacion.FormacionViewModel;

public class FormacionInteractorImpl {
    private DatabaseRepositoryImpl modelo;
    private FormacionViewModel vista;

    public FormacionInteractorImpl(FormacionViewModel vista) {
        this.vista = vista;
        this.modelo = DatabaseRepositoryImpl.getInstance("https://apex.oracle.com", 30000L);
    }

    public void consultarFormacion() {
        try {
            FormacionResponse respuesta = this.modelo.consultarFormacion();
            if (respuesta == null || respuesta.getCount() == 0 || respuesta.getItems() == null) {
                this.vista.mostrarMensajeError("No hay formaciones que mostrar");
            } else {
                this.vista.mostrarFormacionEnGrid(respuesta.getItems());
            }
        } catch (Exception error) {
            error.printStackTrace();
            this.vista.mostrarMensajeError("Ha ocurrido un problema, revisa tu conexión a internet");
        }
    }
}
