package com.grupo4.proyecto.views.informaciongeneral;

import java.util.List;

import com.grupo4.proyecto.data.Empleados;

public interface EmpleadosViewModel {
	
void mostrarEmpleadosEnGrid(List<Empleados> items);
void mostrarMensajeExito(String mensaje);
void mostrarMensajeError(String mensaje);

}