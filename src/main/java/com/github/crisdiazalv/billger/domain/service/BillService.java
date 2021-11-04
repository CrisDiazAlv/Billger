package com.github.crisdiazalv.billger.domain.service;

import com.github.crisdiazalv.billger.domain.model.Bill;

import java.util.List;

/**
 * Spring al compilar busca las etiquetas Service
 */
public interface BillService {

    List<Bill> findAll();

    void save(Bill bill);

    Bill findById(long id);

    void deleteById(long id);
}
