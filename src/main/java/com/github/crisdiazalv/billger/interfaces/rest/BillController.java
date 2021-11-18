package com.github.crisdiazalv.billger.interfaces.rest;

import com.github.crisdiazalv.billger.domain.service.BillService;
import com.github.crisdiazalv.billger.interfaces.rest.dto.BillDTO;
import com.github.crisdiazalv.billger.interfaces.rest.mapper.BillMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/bill")
public class BillController {

    private final BillService service;
    private final BillMapper mapper;

    @Autowired
    public BillController(BillService service, BillMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody BillDTO bill) {
        service.save(mapper.toBill(bill));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<BillDTO>> findAll() {
        List<BillDTO> bills = mapper.toDTOList(service.findAll());
        return ResponseEntity.status(HttpStatus.OK).body(bills);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BillDTO> findById(@PathVariable long id) {
        BillDTO bill = mapper.toBillDTO(service.findById(id));
        return ResponseEntity.status(HttpStatus.OK).body(bill);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        service.deleteById(id);
        return ResponseEntity.accepted().build();
    }

}
