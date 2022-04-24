package ru.netology.orm_hibernate.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.netology.orm_hibernate.db_entity.NameSurnameAge;
import ru.netology.orm_hibernate.db_entity.Persons;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Persons, NameSurnameAge> {
    @Query("SELECT p FROM Persons p WHERE p.cityOfLiving = :cityOfLiving")
    List<Persons> findByCityOfLiving(@Param("cityOfLiving") String cityOfLiving, Sort sort);

    @Query("SELECT p FROM Persons p WHERE p.nameSurnameAge.age < :age")
    List<Persons> findByNameSurnameAge_AgeLessThan(@Param("age") int age, Sort sort);

    @Query("SELECT p FROM Persons p WHERE p.nameSurnameAge.name = :name AND p.nameSurnameAge.surname = :surname")
    Optional<Persons> findByNameSurnameAge_NameAndNameSurnameAge_Surname(@Param("name") String name, @Param("surname") String surname);
}
