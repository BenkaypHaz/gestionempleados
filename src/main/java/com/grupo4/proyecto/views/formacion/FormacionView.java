package com.grupo4.proyecto.views.formacion;




import com.grupo4.proyecto.controller.FormacionInteractorImpl;
import com.grupo4.proyecto.data.Formacion;
import com.grupo4.proyecto.views.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import java.util.List;
import java.util.Optional;

@PageTitle("Informacion General")
@Route(value = "empleados-formacion", layout = MainLayout.class)
@Uses(Icon.class)
public class FormacionView extends Div implements BeforeEnterObserver, FormacionViewModel {

    private final String FORMACION_ID = "formacionID";
    private final String FORMACION_EDIT_ROUTE_TEMPLATE = "/formacion/%s/edit";

    private final Grid<Formacion> grid = new Grid<>(Formacion.class, false);

    private TextField nombreEmpleado;
    private TextField educacion;
    private TextField institucion;
    private TextField carreraCertificaciones;
    private TextField puntuacion;

    private final Button cancel = new Button("Cancel");
    private final Button save = new Button("Save");

    private FormacionInteractorImpl formacionInteractor;

    public FormacionView() {
        addClassNames("informacion-general-view");

        SplitLayout splitLayout = new SplitLayout();

        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);

        formacionInteractor = new FormacionInteractorImpl(this);
        add(splitLayout);

        grid.addColumn(Formacion::getNombreEmpleado).setHeader("Nombre Empleado").setAutoWidth(true).setId("nombreEmpleadoColumn");
        grid.addColumn(Formacion::getEducacion).setHeader("Educacion").setAutoWidth(true).setId("educacionColumn");
        grid.addColumn(Formacion::getInstitucion).setHeader("Institucion").setAutoWidth(true).setId("institucionColumn");
        grid.addColumn(Formacion::getCarreraCertificaciones).setHeader("Carrera/Certificaciones").setAutoWidth(true).setId("carreraCertificacionesColumn");
        grid.addColumn(Formacion::getPuntuacion).setHeader("Puntuacion").setAutoWidth(true).setId("puntuacionColumn");

        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);

        formacionInteractor.consultarFormacion();

        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                populateForm(event.getValue());
            } else {
                clearForm();
            }
        });

        save.addClickListener(e -> {
            Notification.show("Exito,se realizo correctamente", 3000, Notification.Position.TOP_CENTER);
        });
    }

    private void createEditorLayout(SplitLayout splitLayout) {
        Div editorLayoutDiv = new Div();
        editorLayoutDiv.setClassName("editor-layout");

        Div editorDiv = new Div();
        editorDiv.setClassName("editor");
        editorLayoutDiv.add(editorDiv);

        FormLayout formLayout = new FormLayout();
        nombreEmpleado = new TextField("Nombre Empleado");
        nombreEmpleado.setId("nombreEmpleadoField");
        educacion = new TextField("Educacion");
        educacion.setId("educacionField");
        institucion = new TextField("Institucion");
        institucion.setId("institucionField");
        carreraCertificaciones = new TextField("Carrera/Certificaciones");
        carreraCertificaciones.setId("carreraCertificacionesField");
        puntuacion = new TextField("Puntuacion");
        puntuacion.setId("puntuacionField");
        formLayout.add(nombreEmpleado, educacion, institucion, carreraCertificaciones, puntuacion);

        editorDiv.add(formLayout);
        createButtonLayout(editorLayoutDiv);

        splitLayout.addToSecondary(editorLayoutDiv);
    }

    private void createButtonLayout(Div editorLayoutDiv) {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setClassName("button-layout");
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        cancel.setId("cancelButton");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.setId("saveButton");
        buttonLayout.add(save, cancel);
        editorLayoutDiv.add(buttonLayout);
    }

    private void createGridLayout(SplitLayout splitLayout) {
        Div wrapper = new Div();
        wrapper.setClassName("grid-wrapper");
        splitLayout.addToPrimary(wrapper);
        wrapper.add(grid);
    }

    private void populateForm(Formacion formacion) {
        if (formacion != null) {
            nombreEmpleado.setValue(formacion.getNombreEmpleado());
            educacion.setValue(formacion.getEducacion());
            institucion.setValue(formacion.getInstitucion());
            carreraCertificaciones.setValue(formacion.getCarreraCertificaciones());
            puntuacion.setValue(String.valueOf(formacion.getPuntuacion()));
        }
    }

    private void clearForm() {
        nombreEmpleado.clear();
        educacion.clear();
        institucion.clear();
        carreraCertificaciones.clear();
        puntuacion.clear();
    }

    @Override
    public void mostrarFormacionEnGrid(List<Formacion> items) {
        grid.setItems(items);
    }

    @Override
    public void mostrarMensajeExito(String mensaje) {
        Notification.show(mensaje, 3000, Notification.Position.TOP_CENTER);
    }

    @Override
    public void mostrarMensajeError(String mensaje) {
        Notification.show(mensaje, 3000, Notification.Position.TOP_CENTER);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Optional<Long> formacionId = event.getRouteParameters().get(FORMACION_ID).map(Long::parseLong);
        if (formacionId.isPresent()) {
        }
    }
}
