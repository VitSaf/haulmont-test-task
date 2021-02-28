package com.task.service;

import com.task.model.Credit;

import java.util.List;

public interface CreditService {
    List<Credit> getAll();
    Credit findById(int credit_id);
}
