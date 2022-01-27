package com.github.crisdiazalv.billger.interfaces.rest.controller;

import com.github.crisdiazalv.billger.domain.model.Bill;
import com.github.crisdiazalv.billger.domain.model.Category;
import com.github.crisdiazalv.billger.application.service.BillService;
import com.github.crisdiazalv.billger.interfaces.rest.dto.bill.BillDTO;
import com.github.crisdiazalv.billger.interfaces.rest.dto.bill.BillsGroupedByDateDTO;
import com.github.crisdiazalv.billger.interfaces.rest.dto.category.CategoryWithBillsDTO;
import com.github.crisdiazalv.billger.interfaces.rest.dto.bill.NewBillDTO;
import com.github.crisdiazalv.billger.interfaces.rest.mapper.BillMapper;
import com.github.crisdiazalv.billger.interfaces.rest.mapper.CategoryMapper;
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
    private final CategoryMapper categoryMapper;

    @Autowired
    public BillController(BillService service, BillMapper mapper, CategoryMapper categoryMapper) {
        this.service = service;
        this.mapper = mapper;
        this.categoryMapper = categoryMapper;
    }

    @GetMapping
    public ResponseEntity<List<BillDTO>> findAll(@RequestParam(value = "account") long accountId) {
        List<BillDTO> bills = mapper.toDTOList(service.findAll(accountId));
        return ResponseEntity.ok(bills);
    }

    @GetMapping("/groupedByDate")
    public ResponseEntity<List<BillsGroupedByDateDTO>> findAllGroupedByDate(@RequestParam(value = "category", required = false) Long category) {
        List<BillsGroupedByDateDTO> groupedBills = new ArrayList<>();
        for (Map.Entry<LocalDate, List<Bill>> gb : service.findAllGroupedByDate(category).entrySet()) {
            groupedBills.add(new BillsGroupedByDateDTO(gb.getKey(), mapper.toDTOList(gb.getValue())));
        }
        return ResponseEntity.ok(groupedBills);
    }

    @GetMapping("/group/category")
    public ResponseEntity<List<CategoryWithBillsDTO>> findAllGroupedByCategory() {
        List<CategoryWithBillsDTO> groupedBills = new ArrayList<>();
        for (Map.Entry<Category, List<Bill>> gb : service.findAllGroupedByCategory().entrySet()) {
            long sum = gb.getValue().stream().map(Bill::getAmount).mapToLong(Long::valueOf).sum();
            groupedBills.add(new CategoryWithBillsDTO(categoryMapper.toCategoryDTO(gb.getKey()), mapper.toDTOList(gb.getValue()), sum));
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