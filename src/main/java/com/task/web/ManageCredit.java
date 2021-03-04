package com.task.web;

import com.task.model.Bank;
import com.task.model.Client;
import com.task.model.Credit;
import com.task.service.BankService;
import com.task.service.ClientService;
import com.task.service.CreditService;
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

@Route("man_credit")
public class ManageCredit extends AppLayout implements HasUrlParameter<Integer> {

    private final CreditService creditService;
    private final BankService bankService;

    FormLayout creditForm;
    TextField creditSizeField;
    TextField rateField;
    Button saveCredit;

    @Autowired
    public ManageCredit(CreditService creditService, BankService bankService) {
        this.creditService = creditService;
        this.bankService = bankService;

        creditForm = new FormLayout();
        creditSizeField = new TextField("Кредит");
        rateField = new TextField("Проценты");
        saveCredit = new Button("Сохранить");
        //Добавим все элементы на форму
        creditForm.add(creditSizeField, rateField, saveCredit);
        setContent(creditForm);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, Integer integer) {
        if (!integer.equals(-1)) {
            addToNavbar(new H3("Редактирование кредита"));
        } else {
            addToNavbar(new H3("Создание кредита"));
        }
        fillForm(integer);
    }

    private void fillForm(Integer id){
        if (!id.equals(-1)) {
            Optional<Credit> credit = Optional.ofNullable(creditService.findById(id));
            credit.ifPresent(x -> {
                creditSizeField.setValue(String.valueOf(x.getCreditLimit()));
                rateField.setValue(String.valueOf(x.getRate()));
            });
        }

        saveCredit.addClickListener(clickEvent -> {

            Credit credit = new Credit();
            if (!id.equals(-1)) {
                credit.setId(id);
            }
            Bank bank = bankService.findById(BankMenu.id);
            credit.setCreditLimit(Double.valueOf(creditSizeField.getValue()));
            credit.setRate(Double.valueOf(rateField.getValue()));

            credit.setBank(bank);
            //System.out.println(client + "|" + client.getId()  + "|" + bank);

            if(id.equals(-1))
                creditService.createCredit(credit,bank);
            else{
                creditService.updateCredit(credit.getId(),credit, bank,null);;
            }


            Notification notification = new Notification(id.equals(-1) ? "Кредит успешно создан" : "Кредит был изменен", 1000);
            notification.setPosition(Notification.Position.MIDDLE);
            notification.addDetachListener(detachEvent -> {
                UI.getCurrent().navigate(MainMenu.class);
            });
            creditForm.setEnabled(false);
            notification.open();
        });
    }
}
