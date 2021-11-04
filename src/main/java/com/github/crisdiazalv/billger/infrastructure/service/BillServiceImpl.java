package com.github.crisdiazalv.billger.infrastructure.service;

import com.github.crisdiazalv.billger.domain.exception.AccountNotFoundException;
import com.github.crisdiazalv.billger.domain.exception.BillNotFoundException;
import com.github.crisdiazalv.billger.domain.exception.CategoryNotFoundException;
import com.github.crisdiazalv.billger.domain.model.Account;
import com.github.crisdiazalv.billger.domain.model.Bill;
import com.github.crisdiazalv.billger.domain.model.Category;
import com.github.crisdiazalv.billger.domain.service.BillService;
import com.github.crisdiazalv.billger.infrastructure.repository.AccountRepository;
import com.github.crisdiazalv.billger.infrastructure.repository.BillRepository;
import com.github.crisdiazalv.billger.infrastructure.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillServiceImpl implements BillService {


    private final BillRepository repository;
    private final CategoryRepository categoryRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public BillServiceImpl(BillRepository repository, CategoryRepository categoryRepository, AccountRepository accountRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Bill> findAll() {
        return repository.findAll();
    }

    @Override
    public void save(Bill bill) {

        Optional<Account> account = accountRepository.findById(bill.getAccount().getId());
        if (account.isEmpty()) {
            throw new AccountNotFoundException("La cuenta no existe");
        }
        bill.setAccount(account.get());

        Optional<Category> category = categoryRepository.findById(bill.getCategory().getId()); // Devuelve la categoria con un id
        if (category.isEmpty()) {
            throw new CategoryNotFoundException("La categoría no existe");
        }
        bill.setCategory(category.get());
        repository.save(bill);
    }

    @Override
    public Bill findById(long id) {
        Optional<Bill> bill = repository.findById(id);
        if (bill.isEmpty()) {
            throw new BillNotFoundException("El recibo no existe");
        }
        return bill.get();
    }

    @Override
    public void deleteById(long id) {
        Bill bill = findById(id);
        repository.delete(bill);
    }


}
