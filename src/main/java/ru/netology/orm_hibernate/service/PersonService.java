package ru.netology.orm_hibernate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.netology.orm_hibernate.repository.PersonRepository;

@Service
public class PersonService {
    private final PersonRepository repository;

    @Autowired
    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public String getPersonsByCity(String city) {
        return repository.getPersonsByCity(city);
    }
}
