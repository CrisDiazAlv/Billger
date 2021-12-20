package com.github.crisdiazalv.billger.interfaces.rest;

import com.github.crisdiazalv.billger.domain.model.Bill;
import com.github.crisdiazalv.billger.domain.model.Category;
import com.github.crisdiazalv.billger.domain.service.BillService;
import com.github.crisdiazalv.billger.interfaces.rest.dto.BillDTO;
import com.github.crisdiazalv.billger.interfaces.rest.dto.GroupedByCategoryBillDTO;
import com.github.crisdiazalv.billger.interfaces.rest.dto.GroupedByDateBillDTO;
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

    @GetMapping
    public ResponseEntity<List<BillDTO>> findAll(@RequestParam(value = "category", required = false) Long category,
                                                 @RequestParam(value = "paid", required = false) Boolean paid) {
        List<BillDTO> bills = mapper.toDTOList(service.findAll(category, paid));
        return ResponseEntity.status(HttpStatus.OK).body(bills);
    }

    @GetMapping("/groupedByDate")
    public ResponseEntity<List<GroupedByDateBillDTO>> findAllGroupedByDate() {
        List<GroupedByDateBillDTO> groupedBills = new ArrayList<>();
        for (Map.Entry<LocalDate, List<Bill>> gb : service.findAllGroupedByDate().entrySet()) {
            groupedBills.add(new GroupedByDateBillDTO(gb.getKey(), mapper.toDTOList(gb.getValue())));
        }
        return ResponseEntity.status(HttpStatus.OK).body(groupedBills);
    }

    @GetMapping("/groupedByCategory")
    public ResponseEntity<List<GroupedByCategoryBillDTO>> findAllGroupedByCategory() {
        List<GroupedByCategoryBillDTO> groupedBills = new ArrayList<>();
        for (Map.Entry<Category, List<Bill>> gb : service.findAllGroupedByCategory().entrySet()) {
            long sum = gb.getValue().stream().map(Bill::getAmount).mapToLong(Long::valueOf).sum();
            groupedBills.add(new GroupedByCategoryBillDTO(gb.getKey(), mapper.toDTOList(gb.getValue()), sum));
        }
        return ResponseEntity.status(HttpStatus.OK).body(groupedBills);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BillDTO> findById(@PathVariable long id) {
        BillDTO bill = mapper.toBillDTO(service.findById(id));
        return ResponseEntity.status(HttpStatus.OK).body(bill);
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody BillDTO bill) {
        service.save(mapper.toBill(bill));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        service.deleteById(id);
        return ResponseEntity.accepted().build();
    }

}
