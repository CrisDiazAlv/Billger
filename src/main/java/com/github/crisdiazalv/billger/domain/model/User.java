package com.github.crisdiazalv.billger.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="member")
@Data
public class User {

    @Id
    @GeneratedValue
    private long id;

    private String name;
    private String identityDocument;
    private LocalDate birthday;
    private String email;
    private String username;
    private String password;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    private List<Account> accounts;

}
