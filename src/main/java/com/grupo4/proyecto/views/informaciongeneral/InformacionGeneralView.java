package com.grupo4.proyecto.views.informaciongeneral;


import com.grupo4.proyecto.controller.EmpleadosInteractorImpl;
import com.grupo4.proyecto.data.Empleados;
import com.grupo4.proyecto.views.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
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
import com.vaadin.flow.data.renderer.LocalDateRenderer;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@PageTitle("Informacion General")
@Route(value = "/:empleadoID?/:action?(edit)", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@Uses(Icon.class)
public class InformacionGeneralView extends Div implements BeforeEnterObserver, EmpleadosViewModel {

    private final String EMPLEADO_ID = "empleadoID";
    private final String EMPLEADO_EDIT_ROUTE_TEMPLATE = "/%s/edit";

    private final Grid<Empleados> grid = new Grid<>(Empleados.class, false);

    private TextField nombreCompleto;
    private TextField identidad;
    private TextField edad;
    private TextField sexo;
    private TextField areaNombre;
    private TextField cargoNombre;
    private DatePicker fechaIngreso;

    private final Button cancel = new Button("Cancel");
    private final Button save = new Button("Save");

    private EmpleadosInteractorImpl empleadosInteractor;

    public InformacionGeneralView() {
        addClassNames("informacion-general-view");

        SplitLayout splitLayout = new SplitLayout();

        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);

        empleadosInteractor = new EmpleadosInteractorImpl(this);
        add(splitLayout);

        grid.addColumn(Empleados::getNombreCompleto).setHeader("Nombre Completo").setAutoWidth(true).setId("nombreCompletoColumn");
        grid.addColumn(Empleados::getIdentidad).setHeader("Identidad").setAutoWidth(true).setId("identidadColumn");
        grid.addColumn(Empleados::getEdad).setHeader("Edad").setAutoWidth(true).setId("edadColumn");
        grid.addColumn(Empleados::getSexo).setHeader("Sexo").setAutoWidth(true).setId("sexoColumn");
        grid.addColumn(Empleados::getAreaNombre).setHeader("Area").setAutoWidth(true).setId("areaNombreColumn");
        grid.addColumn(Empleados::getCargoNombre).setHeader("Cargo").setAutoWidth(true).setId("cargoNombreColumn");
        grid.addColumn(new LocalDateRenderer<>(
                empleado -> convertToLocalDate(empleado.getFechaIngreso()), 
                "yyyy-MM-dd"))
            .setHeader("Fecha de Ingreso")
            .setAutoWidth(true)
            .setId("fechaIngresoColumn");

        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);

        empleadosInteractor.consultarEmpleados();

        UI.getCurrent().getPage().executeJs("setTimeout(() => { $0.click(); }, 100);", grid);

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
        nombreCompleto = new TextField("Nombre Completo");
        nombreCompleto.setId("nombreCompletoField");
        identidad = new TextField("Identidad");
        identidad.setId("identidadField");
        edad = new TextField("Edad");
        edad.setId("edadField");
        sexo = new TextField("Sexo");
        sexo.setId("sexoField");
        areaNombre = new TextField("Area");
        areaNombre.setId("areaNombreField");
        cargoNombre = new TextField("Cargo");
        cargoNombre.setId("cargoNombreField");
        fechaIngreso = new DatePicker("Fecha de Ingreso");
        fechaIngreso.setId("fechaIngresoField");
        formLayout.add(nombreCompleto, identidad, edad, sexo, areaNombre, cargoNombre, fechaIngreso);

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
        grid.setId("empleadosGrid");
    }

    private void populateForm(Empleados empleado) {
        if (empleado != null) {
            nombreCompleto.setValue(empleado.getNombreCompleto());
            identidad.setValue(empleado.getIdentidad());
            edad.setValue(String.valueOf(empleado.getEdad()));
            sexo.setValue(empleado.getSexo());
            areaNombre.setValue(empleado.getAreaNombre());
            cargoNombre.setValue(empleado.getCargoNombre());
            fechaIngreso.setValue(convertToLocalDate(empleado.getFechaIngreso()));
        }
    }

    private void clearForm() {
        nombreCompleto.clear();
        identidad.clear();
        edad.clear();
        sexo.clear();
        areaNombre.clear();
        cargoNombre.clear();
        fechaIngreso.clear();
    }

    @Override
    public void mostrarEmpleadosEnGrid(List<Empleados> items) {
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
        Optional<Long> empleadoId = event.getRouteParameters().get(EMPLEADO_ID).map(Long::parseLong);
        if (empleadoId.isPresent()) {
            // Handle the event if required
        }
    }

    private LocalDate convertToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
