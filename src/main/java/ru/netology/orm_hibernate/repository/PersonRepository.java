package ru.netology.orm_hibernate.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.netology.orm_hibernate.db_entity.NameSurnameAge;
import ru.netology.orm_hibernate.db_entity.Persons;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Persons, NameSurnameAge> {
    List<Persons> findByCityOfLiving(String cityOfLiving, Sort sort);

    List<Persons> findByNameSurnameAge_AgeLessThan(int age, Sort sort);

    Optional<Persons> findByNameSurnameAge_NameAndNameSurnameAge_Surname(String name, String surname);
}
