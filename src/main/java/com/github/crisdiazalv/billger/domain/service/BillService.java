package com.github.crisdiazalv.billger.domain.service;

import com.github.crisdiazalv.billger.domain.model.Bill;
import com.github.crisdiazalv.billger.domain.model.Category;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface BillService {

    List<Bill> findAll(Long category);

    Bill findById(long id);

    Map<LocalDate, List<Bill>> findAllGroupedByDate(Long category);

    Map<Category, List<Bill>> findAllGroupedByCategory();

    void save(Bill bill);

    void deleteById(long id);

}
