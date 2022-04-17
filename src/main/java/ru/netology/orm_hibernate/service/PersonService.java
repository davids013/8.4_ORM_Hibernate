package ru.netology.orm_hibernate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.netology.orm_hibernate.db_entity.Persons;
import ru.netology.orm_hibernate.repository.PersonRepository;

import java.util.List;

@Service
public class PersonService {
    private final PersonRepository repository;

    @Autowired
    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public List<Persons> getPersonsByCity(String city) {
        return repository.getPersonsByCity(city);
    }
}
