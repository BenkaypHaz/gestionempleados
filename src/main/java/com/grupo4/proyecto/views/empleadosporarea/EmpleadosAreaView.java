package com.grupo4.proyecto.views.empleadosporarea;


import com.grupo4.proyecto.controller.EmpleadosInteractorImpl;
import com.grupo4.proyecto.data.Empleados;
import com.grupo4.proyecto.views.MainLayout;
import com.grupo4.proyecto.views.informaciongeneral.EmpleadosViewModel;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import java.util.List;
import java.util.Random;

@PageTitle("Empleados por Area")
@Route(value = "empleados-area", layout = MainLayout.class)
public class EmpleadosAreaView extends Div implements AfterNavigationObserver, EmpleadosViewModel {

    private final Grid<Empleados> grid = new Grid<>(Empleados.class, false);
    private EmpleadosInteractorImpl empleadosInteractor;
    private final Random random = new Random();

    public EmpleadosAreaView() {
        addClassName("feed-view");
        setSizeFull();
        grid.setId("empleadosGrid");
        grid.setHeight("100%");
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_NO_ROW_BORDERS);
        grid.addComponentColumn(this::createCard);
        add(grid);
        empleadosInteractor = new EmpleadosInteractorImpl(this);
    }

    private HorizontalLayout createCard(Empleados empleado) {
        HorizontalLayout card = new HorizontalLayout();
        card.addClassName("card");
        card.setSpacing(false);
        card.getThemeList().add("spacing-s");

        int randomImageNumber = random.nextInt(70) + 1; 
        String imageUrl = "https://randomuser.me/api/portraits/men/" + randomImageNumber + ".jpg";

        Image image = new Image(imageUrl, "placeholder image");
        image.setId("imageEmpleado");
        VerticalLayout description = new VerticalLayout();
        description.addClassName("description");
        description.setSpacing(false);
        description.setPadding(false);

        HorizontalLayout header = new HorizontalLayout();
        header.addClassName("header");
        header.setSpacing(false);
        header.getThemeList().add("spacing-s");

        Span name = new Span(empleado.getNombreCompleto());
        name.addClassName("name");
        name.setId("nameEmpleado");
        header.add(name);

        Span post = new Span("Empleado en el área de " + empleado.getAreaNombre());
        post.addClassName("post");
        post.setId("postEmpleado");

        HorizontalLayout actions = new HorizontalLayout();
        actions.addClassName("actions");
        actions.setSpacing(false);
        actions.getThemeList().add("spacing-s");

        Icon likeIcon = VaadinIcon.HEART.create();
        likeIcon.addClassName("icon");
        likeIcon.setId("likeIconEmpleado");
        Span likes = new Span("0");
        likes.addClassName("likes");
        likes.setId("likesEmpleado");
        Icon commentIcon = VaadinIcon.COMMENT.create();
        commentIcon.addClassName("icon");
        commentIcon.setId("commentIconEmpleado");
        Span comments = new Span("0");
        comments.addClassName("comments");
        comments.setId("commentsEmpleado");
        Icon shareIcon = VaadinIcon.CONNECT.create();
        shareIcon.addClassName("icon");
        shareIcon.setId("shareIconEmpleado");
        Span shares = new Span("0");
        shares.addClassName("shares");
        shares.setId("sharesEmpleado");

        actions.add(likeIcon, likes, commentIcon, comments, shareIcon, shares);

        description.add(header, post, actions);
        card.add(image, description);
        return card;
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        empleadosInteractor.consultarEmpleados();
    }

    @Override
    public void mostrarEmpleadosEnGrid(List<Empleados> items) {
        for (int i = 0; i < items.size(); i++) {
            Empleados empleado = items.get(i);
            HorizontalLayout card = createCard(empleado);
            card.setId("empleadoCard-" + i);
            add(card);
        }
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
}
