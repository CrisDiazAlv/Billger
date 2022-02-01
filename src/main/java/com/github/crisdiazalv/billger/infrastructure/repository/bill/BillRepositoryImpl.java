package com.github.crisdiazalv.billger.infrastructure.repository.bill;

import com.github.crisdiazalv.billger.domain.model.Bill;
import com.github.crisdiazalv.billger.domain.repository.BillRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BillRepositoryImpl implements BillRepository {

    private final BillJpaRepository repository;

    public BillRepositoryImpl(BillJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Bill> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Bill> findAll(Long account) {
        return repository.findAll((root, query, cb) -> cb.equal(root.get("account"), account));
    }

    @Override
    public Bill save(Bill bill) {
        return repository.save(bill);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
