package com.grupo4.proyecto.views.formacion;
import com.grupo4.proyecto.data.Formacion;
import java.util.List;

public interface FormacionViewModel {
    void mostrarFormacionEnGrid(List<Formacion> items);
    void mostrarMensajeExito(String mensaje);
    void mostrarMensajeError(String mensaje);
}
