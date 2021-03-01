package com.task.web;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;

public class ManageClient  extends AppLayout implements HasUrlParameter<Integer> {
    @Override
    public void setParameter(BeforeEvent beforeEvent, Integer integer) {

    }
}
