package com.github.crisdiazalv.billger.interfaces.rest;

import com.github.crisdiazalv.billger.domain.model.Bill;
import com.github.crisdiazalv.billger.domain.service.BillService;
import com.github.crisdiazalv.billger.interfaces.rest.dto.BillDTO;
import com.github.crisdiazalv.billger.interfaces.rest.dto.GroupedBillDTO;
import com.github.crisdiazalv.billger.interfaces.rest.mapper.BillMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<List<BillDTO>> findAll(@RequestParam(value = "category", required = false) Long category,
                                                 @RequestParam(value = "paid", required = false) Boolean paid) {
        List<BillDTO> bills = mapper.toDTOList(service.findAll(category, paid));
        return ResponseEntity.status(HttpStatus.OK).body(bills);
    }

    @GetMapping("/grouped")
    public ResponseEntity<List<GroupedBillDTO>> findAllGroupedByDate() {
        List<GroupedBillDTO> groupedBills = new ArrayList<>();
        for (Map.Entry<LocalDate, List<Bill>> gb : service.findAllGroupedByDate().entrySet()) {
            groupedBills.add(new GroupedBillDTO(gb.getKey(), mapper.toDTOList(gb.getValue())));
        }
        return ResponseEntity.status(HttpStatus.OK).body(groupedBills);
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
