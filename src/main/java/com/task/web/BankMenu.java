package com.task.web;

import com.task.model.Bank;
import com.task.model.Client;
import com.task.model.Credit;
import com.task.model.CreditOffer;
import com.task.service.BankService;
import com.task.service.ClientService;
import com.task.service.CreditService;
import com.task.service.OfferService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route("bank")
public class BankMenu extends AppLayout implements HasUrlParameter<Integer> {

    private VerticalLayout layout;
    private Grid<Credit> creditGrid;
    private Grid<Client> clientGrid;
    private Grid<CreditOffer> creditOfferGrid;
    private final CreditService creditService;
    private final ClientService clientService;
    private final OfferService offerService;
    private final BankService bankService;

    @Autowired
    public BankMenu(CreditService creditService, ClientService clientService, OfferService offerService, BankService bankService) {
        this.creditService = creditService;
        this.clientService = clientService;
        this.offerService = offerService;
        this.bankService = bankService;

        layout = new VerticalLayout();
        creditGrid = new Grid<>();
        clientGrid = new Grid<>();
        creditOfferGrid = new Grid<>();

        layout.add(clientGrid);
        layout.add(creditGrid);
        layout.add(creditOfferGrid);
        addToNavbar(new H3("Обзор банка"));
        setContent(layout);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, Integer bankId) {

        fillForm(bankId);
    }

    private void fillForm(int bankId) {
        Bank bank = bankService.findById(bankId);
        List<Client> clients = clientService.getByBank(bank);
        List<Credit> credits = creditService.getByBank(bank);
        List<CreditOffer> offers = offerService.findByClients(clients);

        if (!clients.isEmpty()) {
            clientGrid.addColumn(Client::getFullName).setHeader("ФИО клиента");
            clientGrid.addColumn(Client::getPhoneNumber).setHeader("Номер телефона");
            clientGrid.addColumn(Client::getEmail).setHeader("email");
            clientGrid.addColumn(Client::getPhoneNumber).setHeader("Паспорт");

            clientGrid.addColumn(new NativeButtonRenderer<>("Редактировать", client -> {
                UI.getCurrent().navigate(ManageClient.class, client.getId());
            }));
            clientGrid.addColumn(new NativeButtonRenderer<>("Удалить", client -> {
                Dialog dialog = new Dialog();
                Button confirm = new Button("Удалить");
                Button cancel = new Button("Отмена");
                dialog.add("Вы уверены что хотите удалить контакт?");
                dialog.add(confirm);
                dialog.add(cancel);

                confirm.addClickListener(clickEvent -> {
                    clientService.removeClient(client.getId());
                    dialog.close();
                    Notification notification = new Notification("Контакт удален", 1000);
                    notification.setPosition(Notification.Position.MIDDLE);
                    notification.open();

                    clientGrid.setItems(clientService.getByBank(bank));
                });

                cancel.addClickListener(clickEvent -> {
                    dialog.close();
                });

                dialog.open();

            }));
            clientGrid.setItems(clients);
        }
    }
}
