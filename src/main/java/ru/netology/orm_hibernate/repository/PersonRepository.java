package ru.netology.orm_hibernate.repository;

import org.springframework.stereotype.Repository;
import ru.netology.orm_hibernate.db_entity.Citty;
import ru.netology.orm_hibernate.db_entity.NameSurnameAge;
import ru.netology.orm_hibernate.db_entity.Persons;
import ru.netology.orm_hibernate.enums.City;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class PersonRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public String getPersonsByCity(String city) {
//        fillDb();
        fillCitties();
        return city + " from repository";
    }

    private void fillDb() {
        final List<Persons> persons = new ArrayList<>();
        final Random rnd = new Random();
        System.out.println("before for");
        for (int i = 0; i < City.values().length; i++) {
            System.out.println(i);
            final int j = i + 1;
            final NameSurnameAge nsa =
                    new NameSurnameAge("Name" + j, "Surname" + j, rnd.nextInt(81) + 18);
            persons.add(new Persons(nsa, "+" + j, City.values()[i].toString()));
        }
        System.out.println("before persist");
        for (Persons p : persons) {
            persist(p);
        }
    }

    @Transactional
    void persist(Persons p) {
        System.out.println("persist " + p);
        entityManager.persist(p);
    }

    @Transactional
    public void fillCitties() {
        System.out.println(entityManager);
        List<Citty> citties = Stream.of("Орёл", "Рязань", "Тула")
                .map(n -> Citty.builder()
                        .name(n)
                        .build())
                .collect(Collectors.toList());

        for (Citty c : citties) {
            System.out.println(c);
            entityManager.persist(c);
        }
    }
}
