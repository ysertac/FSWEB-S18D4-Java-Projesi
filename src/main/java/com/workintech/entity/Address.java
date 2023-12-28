package com.workintech.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address", schema = "s18d4")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "street")
    private String street;

    @Column(name = "no")
    private Integer no;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "description")
    private String description;

    @OneToOne(mappedBy = "address", cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    private Customer customer;
}
