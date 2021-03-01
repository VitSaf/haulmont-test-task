package com.task.web;

import com.task.model.Bank;
import com.task.model.Credit;
import com.task.service.BankService;
import com.task.service.CreditService;
import com.task.service.OfferService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Route("man_offer")
public class ManageOffer extends AppLayout implements HasUrlParameter<Integer> {
    private final OfferService offerService;
    private final BankService bankService;

    FormLayout offerForm;
    TextField clientIdField;
    TextField creditIdField;
    TextField durationField;

    Button saveOffer;
    @Autowired
    public ManageOffer(OfferService offerService, BankService bankService) {
        this.offerService = offerService;
        this.bankService = bankService;

        offerForm = new FormLayout();
        clientIdField = new TextField("id клиента, для которого предложение");
        creditIdField = new TextField("id кредита, который предлагаете");
        durationField = new TextField("Сколько месяцев выплачивать");
        saveOffer = new Button("Cохранить");

        offerForm.add(clientIdField, creditIdField, durationField, saveOffer);
        setContent(offerForm);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, Integer integer) {
        addToNavbar(new H3("Создание кредита"));
        fillForm();
    }
    private void fillForm(){
        saveOffer.addClickListener(clickEvent -> {
            offerService.createOffer(Integer.valueOf(clientIdField.getValue()), Integer.valueOf(creditIdField.getValue()), Integer.valueOf(durationField.getValue()));

            Notification notification = new Notification("Кредит успешно создан", 1000);
            notification.setPosition(Notification.Position.MIDDLE);
            notification.addDetachListener(detachEvent -> {
                UI.getCurrent().navigate(MainMenu.class);
            });
            offerForm.setEnabled(false);
            notification.open();
        });
    }
}
