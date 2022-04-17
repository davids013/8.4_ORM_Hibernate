package ru.netology.orm_hibernate.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.netology.orm_hibernate.db_entity.NameSurnameAge;
import ru.netology.orm_hibernate.db_entity.Persons;
import ru.netology.orm_hibernate.enums.City;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@Repository
public class PersonRepository {
    private boolean isDbEmpty = true;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<Persons> getPersonsByCity(String city) {
        if (isDbEmpty) {
            if (checkDbSize() > 0) {
                isDbEmpty = false;
            } else fillDb();
        }
        final Query q = entityManager.createQuery(
                String.format("select p from Persons p where p.cityOfLiving = '%s'", city.toUpperCase(Locale.ROOT)));
        return (List<Persons>) q.getResultList();
    }

    private void fillDb() {
        final List<Persons> persons = new ArrayList<>();
        final Random rnd = new Random();
        for (int i = 0; i < City.values().length; i++) {
            final int j = i + 1;
            final NameSurnameAge nsa =
                    new NameSurnameAge("Name" + j, "Surname" + j, rnd.nextInt(81) + 18);
            persons.add(new Persons(nsa, null, City.values()[i].toString()));
        }
        for (Persons p : persons)
            entityManager.persist(p);
    }

    private int checkDbSize() {
        final Query q = entityManager.createQuery("SELECT p FROM Persons p");
        return (int) q.getResultStream().count();
    }
}
