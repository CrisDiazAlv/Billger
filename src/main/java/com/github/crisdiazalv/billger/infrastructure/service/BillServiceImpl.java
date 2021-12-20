package com.github.crisdiazalv.billger.infrastructure.service;

import com.github.crisdiazalv.billger.domain.exception.NotFoundException;
import com.github.crisdiazalv.billger.domain.model.Account;
import com.github.crisdiazalv.billger.domain.model.Bill;
import com.github.crisdiazalv.billger.domain.model.Category;
import com.github.crisdiazalv.billger.domain.service.BillService;
import com.github.crisdiazalv.billger.infrastructure.repository.AccountRepository;
import com.github.crisdiazalv.billger.infrastructure.repository.BillRepository;
import com.github.crisdiazalv.billger.infrastructure.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<Bill> findAll(Long category, Boolean paid) {
        if (category != null) {
            Optional<Category> categoryOptional = categoryRepository.findById(category);
            if (categoryOptional.isEmpty()) {
                throw new NotFoundException("La categoría no existe");
            }
            return repository.findByCategory(categoryOptional.get());
        }


        if (paid != null) {
            // Filtra los recibos por pagados y los devuelve en una lista
            return repository.findAll().stream().filter(bill -> bill.isPaid() == paid).toList();
        }


        return repository.findAll();
    }

    @Override
    public Map<LocalDate, List<Bill>> findAllGroupedByDate() {
        List<Bill> bills = repository.findAll();
        // Coger todas las facturas y agruparlas por fecha
        return bills.stream().collect(Collectors.groupingBy(bill -> bill.getDate().toLocalDate()));
    }

    @Override
    public Map<Category, List<Bill>> findAllGroupedByCategory() {
        List<Bill> bills = repository.findAll();
        // Coger todas las facturas y agruparlas por categoria
        return bills.stream().collect(Collectors.groupingBy(Bill::getCategory));
    }

    @Override
    public void save(Bill bill) {

        Optional<Account> account = accountRepository.findById(bill.getAccount().getId());
        if (account.isEmpty()) {
            throw new NotFoundException("La cuenta no existe");
        }
        account.get().setCurrentBalance(account.get().getCurrentBalance() + bill.getAmount());
        accountRepository.save(account.get());
        bill.setAccount(account.get());

        Optional<Category> category = categoryRepository.findById(bill.getCategory().getId()); // Devuelve la categoria con un id
        if (category.isEmpty()) {
            throw new NotFoundException("La categoría no existe");
        }
        bill.setCategory(category.get());
        repository.save(bill);
    }

    @Override
    public Bill findById(long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("El recibo no existe"));
    }

    @Override
    public void deleteById(long id) {
        Bill bill = findById(id);
        repository.delete(bill);
    }

}
