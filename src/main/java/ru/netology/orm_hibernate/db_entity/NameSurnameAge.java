package ru.netology.orm_hibernate.db_entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NameSurnameAge implements Serializable {
    @Column(nullable = false, length = 32)
    private String name;
    @Column(nullable = false, length = 32)
    private String surname;
    @Column(length = 100)
    private int age;
}
