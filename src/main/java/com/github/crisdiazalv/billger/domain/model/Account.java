package com.github.crisdiazalv.billger.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Account {

    @Id
    @GeneratedValue
    private long id;
    private long currentBalance;
    private long estimatedBalance;

    private String name;
    private String accountNumber;
    private String identityDocument;
    private LocalDate birthday;
    private String email;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account", fetch = FetchType.LAZY)
    private List<Bill> bills;

}

