package com.github.crisdiazalv.billger.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
@Data
public class Bill {

    @Id
    @GeneratedValue
    private long id;
    private double amount;
    private LocalDateTime date;
    private boolean paid;
    private LocalDateTime estimatedDate;

    private String description;
    private String notes;

    @OneToOne
    private Category category;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(optional = false)
    private Account account;

}
