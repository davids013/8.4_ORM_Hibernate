package ru.netology.orm_hibernate.db_entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Persons {
    @EmbeddedId
    private NameSurnameAge nameSurnameAge;
    @Column(length = 32, unique = true)
    private String phoneNumber;
    @Column(length = 32)
    private String cityOfLiving;
}
