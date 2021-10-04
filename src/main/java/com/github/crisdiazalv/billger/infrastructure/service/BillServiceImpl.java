package com.github.crisdiazalv.billger.infrastructure.service;

import com.github.crisdiazalv.billger.domain.model.Bill;
import com.github.crisdiazalv.billger.domain.model.Category;
import com.github.crisdiazalv.billger.domain.service.BillService;
import com.github.crisdiazalv.billger.infrastructure.repository.CategoryRepository;
import com.github.crisdiazalv.billger.infrastructure.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillRepository repository;
    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public List<Bill> findAll() {
        return repository.findAll();
    }

    @Override
    public void save(Bill bill) {
        // No existe ni recibo ni categoria:
        Category category = bill.getCategory(); // No sabemos si ya existe o no
        Category savedCategory = categoryRepository.save(category); // Devuelve la categoria con un id
        bill.setCategory(savedCategory); // Relacion con el recibo
        repository.save(bill);
    }


}
