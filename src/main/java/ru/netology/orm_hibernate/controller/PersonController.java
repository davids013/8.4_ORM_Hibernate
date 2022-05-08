package ru.netology.orm_hibernate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.orm_hibernate.db_entity.NameSurnameAge;
import ru.netology.orm_hibernate.db_entity.Persons;
import ru.netology.orm_hibernate.service.PersonService;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/persons")
public class PersonController {
    private final PersonService service;

    @Autowired
    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping("/by-city")
    public List<Persons> getPersonsByCity(@RequestParam String cityOfLiving) {
        return service.getPersonsByCity(cityOfLiving.toUpperCase(Locale.ROOT));
    }

    @GetMapping("/create")
    public Persons create(@RequestParam String name,
                          @RequestParam String surname,
                          @RequestParam int age,
                          @RequestParam String phoneNumber,
                          @RequestParam String cityOfLiving) {
        if (phoneNumber.isEmpty()) phoneNumber = null;
        cityOfLiving = cityOfLiving.isEmpty() ? null : cityOfLiving.toUpperCase(Locale.ROOT);
        final NameSurnameAge nsa = new NameSurnameAge(name, surname, age);
        final Persons person = new Persons(nsa, phoneNumber, cityOfLiving);
        return service.create(person);
    }

    @GetMapping("/read")
    public Persons read(@RequestParam String name,
                        @RequestParam String surname,
                        @RequestParam int age) {
        final NameSurnameAge id = new NameSurnameAge(name, surname, age);
        final Persons p = service.read(id);
        final Persons pp = new Persons(p.getNameSurnameAge(), p.getPhoneNumber(), p.getCityOfLiving());
//        return p;
        return pp;
    }

    @GetMapping("/update")
    public Persons update(@RequestParam String name,
                          @RequestParam String surname,
                          @RequestParam int age,
                          @RequestParam String phoneNumber,
                          @RequestParam String cityOfLiving) {
        if (phoneNumber.isEmpty()) phoneNumber = null;
        cityOfLiving = cityOfLiving.isEmpty() ? null : cityOfLiving.toUpperCase(Locale.ROOT);
        final NameSurnameAge nsa = new NameSurnameAge(name, surname, age);
        final Persons person = new Persons(nsa, phoneNumber, cityOfLiving);
        return service.update(person);
    }

    @GetMapping("/delete")
    public boolean delete(@RequestParam String name,
                          @RequestParam String surname,
                          @RequestParam int age) {
        final NameSurnameAge id = new NameSurnameAge(name, surname, age);
        return service.delete(id);
    }

    @GetMapping("/by-age-younger")
    public List<Persons> getByAgeYounger(@RequestParam int age) {
        return service.getByAgeYounger(age);
    }

    @GetMapping("/by-name-surname")
    public Persons getByNameAndSurname(@RequestParam String name,
                                       @RequestParam String surname) {
        return service.getByNameAndSurname(name, surname);
    }
}
