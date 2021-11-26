package com.github.crisdiazalv.billger.domain.service;

import com.github.crisdiazalv.billger.domain.model.Bill;
import com.github.crisdiazalv.billger.domain.model.Category;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Spring al compilar busca las etiquetas Service
 */
public interface BillService {

    List<Bill> findAll(Long category,Boolean paid);

    Map<LocalDate,List<Bill>> findAllGroupedByDate();

    Map<Category,List<Bill>> findAllGroupedByCategory();

    void save(Bill bill);

    Bill findById(long id);

    void deleteById(long id);


}
