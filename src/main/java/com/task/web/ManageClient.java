package com.task.web;

import com.task.model.Bank;
import com.task.model.Client;
import com.task.service.BankService;
import com.task.service.ClientService;
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

@Route("man_client")
public class ManageClient extends AppLayout implements HasUrlParameter<Integer> {

    private final ClientService clientService;
    private final BankService bankService;

    FormLayout clientForm;
    TextField fullNameField;
    TextField phoneNumberField;
    TextField emailField;
    TextField passportNumberField;
    Button saveClient;

    @Autowired
    public ManageClient(ClientService clientService,BankService bankService){
        this.clientService=clientService;
        this.bankService= bankService;

        clientForm = new FormLayout();
        fullNameField = new TextField("ФИО");
        phoneNumberField = new TextField("Номер телефона");
        emailField = new TextField("email");
        passportNumberField = new TextField("Номер пасспорта");
        saveClient = new Button("Сохранить");
        //Добавим все элементы на форму
        clientForm.add(fullNameField, phoneNumberField, emailField, passportNumberField, saveClient);
        setContent(clientForm);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, Integer integer) {
        if (!integer.equals(-1)) {
            addToNavbar(new H3("Редактирование клиента"));
        } else {
            addToNavbar(new H3("Создание клиента"));
        }
        fillForm(integer);
    }

    private void fillForm(Integer id){
        if (!id.equals(-1)) {
            Optional<Client> client = Optional.ofNullable(clientService.getById(id));
            client.ifPresent(x -> {
                fullNameField.setValue(x.getFullName());
                phoneNumberField.setValue(x.getPhoneNumber());
                emailField.setValue(x.getEmail());
                passportNumberField.setValue(x.getPassportNumber());
            });
        }

        saveClient.addClickListener(clickEvent -> {

            Client client = new Client();
            if (!id.equals(-1)) {
                client.setId(id);
            }
            Bank bank = bankService.findById(BankMenu.id);
            client.setFullName(fullNameField.getValue());
            client.setPhoneNumber(phoneNumberField.getValue());
            client.setEmail(emailField.getValue());
            client.setPassportNumber(passportNumberField.getValue());
            client.setBank(bank);
            //System.out.println(client + "|" + client.getId()  + "|" + bank);
            clientService.updateClient(client.getId(),client.getFullName(),client.getPhoneNumber(),client.getEmail(), client.getPassportNumber());

            Notification notification = new Notification(id.equals(-1) ? "Клиент успешно создан" : "Клиент был изменен", 1000);
            notification.setPosition(Notification.Position.MIDDLE);
            notification.addDetachListener(detachEvent -> {
                UI.getCurrent().navigate(MainMenu.class);
            });
            clientForm.setEnabled(false);
            notification.open();
        });
    }
}
