package ru.netology.orm_hibernate;

import lombok.var;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.netology.orm_hibernate.db_entity.Citty;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CommandLineApp implements CommandLineRunner {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        System.out.println(entityManager);
        List<Citty> citties = Stream.of("Самара", "Адлер", "Казань")
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
