package ru.netology.orm_hibernate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.netology.orm_hibernate.db_entity.NameSurnameAge;
import ru.netology.orm_hibernate.db_entity.Persons;
import ru.netology.orm_hibernate.exception.DbEntityExistException;
import ru.netology.orm_hibernate.repository.PersonRepository;

import java.util.List;

@Service
public class PersonService {
    private final PersonRepository repository;

    @Autowired
    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public Persons create(Persons person) {
        if (!repository.existsById(person.getNameSurnameAge())) {
            return repository.save(person);
        } else throw new DbEntityExistException(person, "already exist");
    }

    public Persons read(NameSurnameAge id) {
        if (repository.existsById(id)) {
            return repository.getById(id);
        } else throw new DbEntityExistException(id, "not exist");
    }

    public Persons update(Persons person) {
        if (repository.existsById(person.getNameSurnameAge())) {
            return repository.save(person);
        } else throw new DbEntityExistException(person, "not exist");
    }

    public boolean delete(NameSurnameAge id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        } else throw new DbEntityExistException(id, "not exist");
    }

    public List<Persons> getPersonsByCity(String city) {
        return repository.findByCityOfLiving(city,
                Sort.by("nameSurnameAge.surname").ascending()
                        .and(Sort.by("nameSurnameAge.name").ascending()));
    }

    public List<Persons> getByAgeYounger(int age) {
        return repository.findByNameSurnameAge_AgeLessThan(age,
                Sort.by("nameSurnameAge.age").ascending()
                        .and(Sort.by("nameSurnameAge.surname").ascending())
                        .and(Sort.by("nameSurnameAge.name").ascending()));
    }

    public Persons getByNameAndSurname(String name, String surname) {
        return repository.findByNameSurnameAge_NameAndNameSurnameAge_Surname(name, surname)
                .orElseThrow(() -> new DbEntityExistException(new NameSurnameAge(name, surname, 0), "not exist"));
    }
}
