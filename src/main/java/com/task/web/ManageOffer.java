package com.task.web;

import com.task.model.Client;
import com.task.model.Credit;
import com.task.model.CreditOffer;
import com.task.service.ClientService;
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

@Route("man_offer")
public class ManageOffer extends AppLayout implements HasUrlParameter<Integer> {
    private final OfferService offerService;
    private final CreditService creditService;
    private final ClientService clientService;

    FormLayout offerForm;
    TextField clientIdField;
    TextField creditIdField;
    TextField durationField;

    Button saveOffer;
    @Autowired
    public ManageOffer(OfferService offerService, CreditService creditService, ClientService clientService) {
        this.offerService = offerService;
        this.creditService = creditService;
        this.clientService = clientService;

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
        addToNavbar(new H3("Создание предложения о кредите"));
        fillForm();
    }
    private void fillForm(){
        saveOffer.addClickListener(clickEvent -> {
            CreditOffer offer = new CreditOffer();
            offer.setDurationInMonths(Integer.parseInt(durationField.getValue()));
            Credit cred = creditService.findById(Integer.parseInt(creditIdField.getValue()));
            Client cli = clientService.getById(Integer.parseInt(creditIdField.getValue()));

            offerService.createOffer(offer,cli, cred);

            Notification notification = new Notification("Предложение о кредит успешно создано", 1000);
            notification.setPosition(Notification.Position.MIDDLE);
            notification.addDetachListener(detachEvent -> {
                UI.getCurrent().navigate(MainMenu.class);
            });
            offerForm.setEnabled(false);
            notification.open();
        });
    }
}
