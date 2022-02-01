package com.github.crisdiazalv.billger.domain.repository;

import com.github.crisdiazalv.billger.domain.model.Bill;

import java.util.List;

public interface BillRepository {

    List<Bill> findAll();

    List<Bill> findAll(Long account);

    Bill save(Bill bill);

    void deleteById(Long id);

}
