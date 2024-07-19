package com.grupo4.proyecto.controller;

import com.grupo4.proyecto.data.EmpleadosResponse;
import com.grupo4.proyecto.repository.DatabaseRepositoryImpl;
import com.grupo4.proyecto.views.formacion.FormacionViewModel;
import com.grupo4.proyecto.views.informaciongeneral.EmpleadosViewModel;

public class EmpleadosInteractorImpl implements EmpleadosInteractor {
private DatabaseRepositoryImpl modelo;
private EmpleadosViewModel vista;

public EmpleadosInteractorImpl(EmpleadosViewModel vista) {
	super();
	this.vista = vista;
	this.modelo = DatabaseRepositoryImpl.getInstance("https://apex.oracle.com", 30000L);
   }

	@Override
	public void consultarEmpleados() {
            try {
            	
            EmpleadosResponse respuesta=this.modelo.consultarEmpleados();
            if(respuesta == null || respuesta.getCount()==0|| respuesta.getItems()==null) {
            	this.vista.mostrarMensajeError("No hay empleados que mostrar");
            }else {
            	this.vista.mostrarEmpleadosEnGrid(respuesta.getItems());
            }
            }catch(Exception error){
            	error.printStackTrace();
            	this.vista.mostrarMensajeExito("Ha ocurrido un problema, revisa tu conexion a internet");
            }
	}

}
