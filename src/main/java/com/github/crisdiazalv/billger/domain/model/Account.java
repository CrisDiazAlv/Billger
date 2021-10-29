package com.github.crisdiazalv.billger.domain.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Account {

    @Id
    @GeneratedValue
    private long id;
    private long currentBalance;
    private long estimatedBalance;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account", fetch = FetchType.LAZY)
    private List<Bill> bills;

}

