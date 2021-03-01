package com.task.web;

import com.task.model.*;
import com.task.service.*;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
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
    Grid<Credit> grid1;
    Grid<Payment> grid2;
    Grid<Bank> grid3;
    Grid<CreditOffer> grid4;



    private final ClientService clientService;
    private final CreditService creditService;
    private final PaymentService paymentService;
    private final BankService bankService;
    private final OfferService offerService;


    @Autowired
    public ClientList(ClientService clientService, CreditService creditService,PaymentService paymentService, BankService bankService, OfferService offerService){
        this.clientService = clientService;
        this.creditService = creditService;
        this.paymentService = paymentService;
        this.bankService = bankService;
        this.offerService = offerService;



        layout = new VerticalLayout();

        grid = new Grid<>();
        grid1 = new Grid<>();
        grid2 = new Grid<>();
        grid3 = new Grid<>();
        grid4 = new Grid<>();

        //linkCreate = new RouterLink("Добавить клиента",AddClient.class, 0);
        //layout.add(linkCreate);
        layout.add(grid);
        layout.add(grid1);
        layout.add(grid2);
        layout.add(grid3);
        layout.add(grid4);
        addToNavbar(new H3("Список клиентов"));
        setContent(layout);
    }

    @PostConstruct
    public void fillGrid() {
        List<Client> contacts = clientService.getAll();
        List<Credit> credits = creditService.getAll();
        List<Bank> banks = bankService.getAll();
        List<CreditOffer> offers = offerService.findAll();
        List<Payment> paymentsForOffer = offerService.findById(0).getPayments();
        List<Payment> payments = paymentService.getAll();
        payments.addAll(paymentsForOffer);

        if (!contacts.isEmpty() && !credits.isEmpty()) {

            //Выведем столбцы в нужном порядке
            grid.addColumn(Client::getFullName).setHeader("ФИО");
            grid.addColumn(Client::getPhoneNumber).setHeader("Номер телефона");
            grid.addColumn(Client::getEmail).setHeader("Номер паспорта");
            grid.addColumn(Client::getPassportNumber).setHeader("email");
            grid.addColumn(Client::getBank).setHeader("Банк");

            grid1.addColumn(Credit::getCreditLimit).setHeader("Credit Limit (Test)");
            grid1.addColumn(Credit::getBank).setHeader("Банк");

            grid2.addColumn((Payment::getId)).setHeader("Номер выплаты в БД");
            grid2.addColumn(Payment::getSumOfPayment).setHeader("Сумма кредита из предложения");
            grid2.addColumn(Payment::getRatePartOfPayment).setHeader("Проценты кредита из предложения");
            grid2.addColumn(Payment::getCreditPartOfPayment).setHeader("Часть кредита из предложения");
            grid2.addColumn(Payment::getCreditOffer).setHeader("Предложение, по которому выплата");

            grid3.addColumn(Bank::getName).setHeader("Имя банка");

            grid4.addColumn(CreditOffer::getClient).setHeader("Клиент, для которого предложение");
            grid4.addColumn(CreditOffer::getSizeOfCredit).setHeader("Размер кредита");
            grid4.addColumn(CreditOffer::getPayments).setHeader("Выплаты");



            //Добавим кнопку удаления и редактирования
//            grid.addColumn(new NativeButtonRenderer<>("Редактировать", client -> {
//                UI.getCurrent().navigate(AddClient.class);
//            }));
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
            grid1.setItems(credits);
            grid2.setItems(payments);
            grid3.setItems(banks);
            grid4.setItems(offers);
        }
    }

}
