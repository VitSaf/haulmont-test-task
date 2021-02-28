package com.task.web;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

@Route("add_client")
public class AddClient extends AppLayout  implements HasUrlParameter<Integer> {
    @Override
    public void setParameter(BeforeEvent beforeEvent, Integer integer) {

    }
}
