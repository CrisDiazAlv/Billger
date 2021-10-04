package com.github.crisdiazalv.billger.interfaces.rest;

import com.github.crisdiazalv.billger.domain.model.Bill;
import com.github.crisdiazalv.billger.domain.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bill")
public class BillController {

    @Autowired
    private BillService service;

    @PostMapping
    public void save(@RequestBody Bill bill) {
        service.save(bill);
    }

    @GetMapping
    public List<Bill> findAll() {
        return service.findAll();
    }


}
