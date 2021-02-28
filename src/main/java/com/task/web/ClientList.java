package com.task.web;

import com.task.model.Client;
import com.task.repositories.ClientRepository;
import com.task.service.ClientService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

@Route("clients")
public class ClientList extends AppLayout {
    VerticalLayout layout;
    Grid<Client> grid;
    RouterLink linkCreate;
    //private final ClientService clientService;

    ClientService clientService;


    @Autowired
    public ClientList(ClientService clientService){
        this.clientService = clientService;
        //this.repo = repo;
        layout = new VerticalLayout();
        grid = new Grid<>();
        linkCreate = new RouterLink("Добавить клиента",AddClient.class, 0);
        layout.add(linkCreate);
        layout.add(grid);
        addToNavbar(new H3("Список клиентов"));
        setContent(layout);
    }

    @PostConstruct
    public void fillGrid() {
        List<Client> contacts = clientService.getAll();
        //List<Client> contacts = repo.findAll();
        if (!contacts.isEmpty()) {

            //Выведем столбцы в нужном порядке
            grid.addColumn(Client::getFullName).setHeader("ФИО");
            grid.addColumn(Client::getPhoneNumber).setHeader("Номер телефона");
            grid.addColumn(Client::getEmail).setHeader("Номер паспорта");
            grid.addColumn(Client::getPassportNumber).setHeader("email");
            //Добавим кнопку удаления и редактирования
            grid.addColumn(new NativeButtonRenderer<>("Редактировать", client -> {
                UI.getCurrent().navigate(AddClient.class);
            }));
            grid.addColumn(new NativeButtonRenderer<>("Удалить", client -> {
                Dialog dialog = new Dialog();
                Button confirm = new Button("Удалить");
                Button cancel = new Button("Отмена");
                dialog.add("Вы уверены что хотите удалить клиента из базы данных?");
                dialog.add(confirm);
                dialog.add(cancel);

//                confirm.addClickListener(clickEvent -> {
//                    clientRepo.delete(client);
//                    dialog.close();
//                    Notification notification = new Notification("Клиент удален", 1000);
//                    notification.setPosition(Notification.Position.MIDDLE);
//                    notification.open();
//
//                    grid.setItems(clientRepo.findAll());
//
//                });

                cancel.addClickListener(clickEvent -> {
                    dialog.close();
                });

                dialog.open();

            }));

            grid.setItems(contacts);
        }
    }

}
