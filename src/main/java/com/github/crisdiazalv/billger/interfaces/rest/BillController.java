package com.github.crisdiazalv.billger.interfaces.rest;

import com.github.crisdiazalv.billger.domain.model.Bill;
import com.github.crisdiazalv.billger.domain.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bill")
public class BillController {

    private final BillService service;

    @Autowired
    public BillController(BillService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody Bill bill) {
        service.save(bill);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Bill>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bill> findById(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        service.deleteById(id);
        return ResponseEntity.accepted().build();
    }

}
