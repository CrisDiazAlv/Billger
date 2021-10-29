package com.github.crisdiazalv.billger.domain.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Bill {

    @Id
    @GeneratedValue
    private long id;
    private long amount;
    private LocalDateTime date;
    private boolean paid;
    private LocalDateTime estimatedDate;

    @OneToOne
    private Category category;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(optional = false)
    private Account account;

}
