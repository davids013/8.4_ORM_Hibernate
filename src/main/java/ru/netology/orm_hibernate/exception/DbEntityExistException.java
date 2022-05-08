package ru.netology.orm_hibernate.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.netology.orm_hibernate.db_entity.NameSurnameAge;
import ru.netology.orm_hibernate.db_entity.Persons;

@Data
@EqualsAndHashCode(callSuper = true)
public class DbEntityExistException extends RuntimeException {
    private final NameSurnameAge nameSurnameAge;
    private final String entityProblem;

    public DbEntityExistException(Persons person, String entityProblem) {
        this.nameSurnameAge = person.getNameSurnameAge();
        this.entityProblem = String.format("Database entity is %s", entityProblem);
    }

    public DbEntityExistException(NameSurnameAge nameSurnameAge, String entityProblem) {
        this.nameSurnameAge = nameSurnameAge;
        this.entityProblem = String.format("Database entity is %s", entityProblem);
    }
}
