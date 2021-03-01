package com.task.web;

import com.task.model.Bank;
import com.task.model.Client;
import com.task.service.BankService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

@Route("menu")
public class MainMenu extends AppLayout {
    private VerticalLayout layout;
    private Grid<Bank> grid;
    private final BankService bankService;

    @Autowired
    public MainMenu(BankService bankService) {
        layout = new VerticalLayout();
        grid = new Grid<>();

        this.bankService = bankService;

        layout.add(grid);
        addToNavbar(new H3("Выбор банка"));
        setContent(layout);
    }

    @PostConstruct
    public void fillGrid() {
        List<Bank> banks = bankService.getAll();

        if (!banks.isEmpty()) {
            grid.addColumn(Bank::getName).setHeader("Название банка");
            grid.addColumn(Bank::getNumberOfClients).setHeader("Количество клиентов в банке");
            grid.addColumn(Bank::getNumberOfCredits).setHeader("Количество вариантов кредита");

            grid.addColumn(new NativeButtonRenderer<>("Выбрать", bank -> {
                UI.getCurrent().navigate(BankMenu.class, bank.getId());
            }));
        }
        grid.setItems(banks);
    }
}
