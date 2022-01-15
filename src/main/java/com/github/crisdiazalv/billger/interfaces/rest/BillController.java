package com.github.crisdiazalv.billger.interfaces.rest;

import com.github.crisdiazalv.billger.domain.model.Bill;
import com.github.crisdiazalv.billger.domain.model.Category;
import com.github.crisdiazalv.billger.domain.service.BillService;
import com.github.crisdiazalv.billger.interfaces.rest.dto.BillDTO;
import com.github.crisdiazalv.billger.interfaces.rest.dto.BillsGroupedByDateDTO;
import com.github.crisdiazalv.billger.interfaces.rest.dto.CategoryWithBillsDTO;
import com.github.crisdiazalv.billger.interfaces.rest.dto.NewBillDTO;
import com.github.crisdiazalv.billger.interfaces.rest.mapper.BillMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        return ResponseEntity.ok(bills);
    }

    @GetMapping("/groupedByDate")
    public ResponseEntity<List<BillsGroupedByDateDTO>> findAllGroupedByDate() {
        List<BillsGroupedByDateDTO> groupedBills = new ArrayList<>();
        for (Map.Entry<LocalDate, List<Bill>> gb : service.findAllGroupedByDate().entrySet()) {
            groupedBills.add(new BillsGroupedByDateDTO(gb.getKey(), mapper.toDTOList(gb.getValue())));
        }
        return ResponseEntity.ok(groupedBills);
    }

    @GetMapping("/groupedByCategory")
    public ResponseEntity<List<CategoryWithBillsDTO>> findAllGroupedByCategory() {
        List<CategoryWithBillsDTO> groupedBills = new ArrayList<>();
        for (Map.Entry<Category, List<Bill>> gb : service.findAllGroupedByCategory().entrySet()) {
            long sum = gb.getValue().stream().map(Bill::getAmount).mapToLong(Long::valueOf).sum();
            groupedBills.add(new CategoryWithBillsDTO(gb.getKey(), mapper.toDTOList(gb.getValue()), sum));
        }
        return ResponseEntity.ok(groupedBills);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BillDTO> findById(@PathVariable long id) {
        BillDTO bill = mapper.toBillDTO(service.findById(id));
        return ResponseEntity.ok(bill);
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody NewBillDTO bill) {
        service.save(mapper.toBill(bill));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        service.deleteById(id);
        return ResponseEntity.accepted().build();
    }

}
