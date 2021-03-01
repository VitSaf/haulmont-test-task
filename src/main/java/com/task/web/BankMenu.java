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
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
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

    RouterLink linkCreateClient;
    RouterLink linkCreateCredit;
    RouterLink linkCreateOffer;

    static int id;

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


        linkCreateClient = new RouterLink("Добавить клиента", ManageClient.class, -1);
        linkCreateCredit = new RouterLink("Добавить кредит", ManageCredit.class, -1);
       linkCreateOffer = new RouterLink("Добавить предложение", ManageOffer.class, -1);

        layout.add(new H2("Клиенты банка"));
        layout.add(linkCreateClient);
        layout.add(clientGrid);
        layout.add(new H2("Варианты кредита в банке"));
        layout.add(linkCreateCredit);
        layout.add(creditGrid);
        layout.add(new H2("Доступные предложения"));
        layout.add(linkCreateOffer);
        layout.add(creditOfferGrid);
        addToNavbar(new H3("Обзор банка"));
        setContent(layout);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, Integer bankId) {
        id=bankId;
        fillForm(bankId);
    }

    private void fillForm(int bankId) {
        Bank bank = bankService.findById(bankId);
        List<Client> clients = clientService.getByBank(bank);
        List<Credit> credits = creditService.getByBank(bank);
        List<CreditOffer> offers = offerService.getOffers(clients);


        addClientGrid(clients);
        addCreditGrid(credits);
        addCreditOfferGrid(offers);
    }

    private void addCreditOfferGrid(List<CreditOffer> offers){
        if (!offers.isEmpty()) {
            creditOfferGrid.addColumn(new NativeButtonRenderer<>("Удалить", creditOffer -> {
                Dialog dialog = new Dialog();
                Button confirm = new Button("Удалить");
                Button cancel = new Button("Отмена");
                dialog.add("Вы уверены что хотите удалить предложение?");
                dialog.add(confirm);
                dialog.add(cancel);

                confirm.addClickListener(clickEvent -> {
                    offerService.removeCreditOffer(creditOffer.getId());
                    offers.remove(creditOffer);
                    creditOfferGrid.setItems(offers);
                    dialog.close();
                    Notification notification = new Notification("Предложение удалено", 1000);
                    notification.setPosition(Notification.Position.MIDDLE);
                    notification.open();
                });

                cancel.addClickListener(clickEvent -> {
                    dialog.close();
                });

                dialog.open();

            }));
            creditOfferGrid.addColumn(CreditOffer::getClient).setAutoWidth(true).setHeader("Клиент");
            creditOfferGrid.addColumn(CreditOffer::getCredit).setAutoWidth(true).setHeader("Кредит");
            creditOfferGrid.addColumn(CreditOffer::getSumOfPayments).setHeader("Сумма выплат");
            creditOfferGrid.addColumn(CreditOffer::getDurationInMonths).setHeader("Срок, месяцев");
            creditOfferGrid.addColumn(CreditOffer::getPaymentTmp).setAutoWidth(true).setHeader("Выплаты");

            creditOfferGrid.setItems(offers);
        }
    }

    private void addCreditGrid(List<Credit> credits){
        if (!credits.isEmpty()) {

            creditGrid.addColumn(new NativeButtonRenderer<>("Редактировать", credit -> {
                UI.getCurrent().navigate(ManageCredit.class, credit.getId());
            }));
            creditGrid.addColumn(new NativeButtonRenderer<>("Удалить", credit -> {
                Dialog dialog = new Dialog();
                Button confirm = new Button("Удалить");
                Button cancel = new Button("Отмена");
                dialog.add("Вы уверены что хотите удалить кредит?");
                dialog.add(confirm);
                dialog.add(cancel);

                confirm.addClickListener(clickEvent -> {
                    creditService.removeCredit(credit.getId());
                    credits.remove(credit);
                    creditGrid.setItems(credits);
                    dialog.close();
                    Notification notification = new Notification("Кредит удален", 1000);
                    notification.setPosition(Notification.Position.MIDDLE);
                    notification.open();
                });

                cancel.addClickListener(clickEvent -> {
                    dialog.close();
                });

                dialog.open();
            }));

            creditGrid.addColumn(Credit::getCreditLimit).setHeader("Размер кредита, р.");
            creditGrid.addColumn(Credit::getRate).setHeader("Ставка, %");

            creditGrid.setItems(credits);
        }
    }

    private void addClientGrid(List<Client> clients){
        if (!clients.isEmpty()) {

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
                    clientService.removeClient(client);
                    clients.remove(client);
                    clientGrid.setItems(clients);
                    dialog.close();
                    Notification notification = new Notification("Контакт удален", 1000);
                    notification.setPosition(Notification.Position.MIDDLE);
                    notification.open();
                });

                cancel.addClickListener(clickEvent -> {
                    dialog.close();
                });

                dialog.open();

            }));

            clientGrid.addColumn(Client::getFullName).setHeader("ФИО клиента");
            clientGrid.addColumn(Client::getPhoneNumber).setHeader("Номер телефона");
            clientGrid.addColumn(Client::getEmail).setHeader("email");
            clientGrid.addColumn(Client::getPassportNumber).setHeader("Паспорт");

            clientGrid.setItems(clients);
        }
    }

}
