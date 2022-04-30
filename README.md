## Модуль 8. Хранение данных и организация безопасности
### Лекция 8.4: ORM, Hibernate

# Задача DAO слой c Hibernate

## Описание
Попрактикуемся в работе с Hibernate через Spring, попутно закрепляя уже пройденные темы. Вам надо написать приложение для работы с БД, используя средства Spring по конфигурации и работе с Hibernate, адаптировав таблицы из задания [Таблица пользователей](https://github.com/netology-code/jd-homeworks/blob/master/sql-basic/task/README.md)

1. Создайте spring boot приложение, с зависимостями на два starter'а - `spring-boot-starter-data-jpa` и `spring-boot-starter-web`

2. Создайте Entity, которая соответствует таблице из условий задачи [Таблица пользователей](https://github.com/netology-code/jd-homeworks/blob/master/sql-basic/task/README.md).

3. Напишите репозиторий для работы с БД. Для этого:
- создайте класс и пометьте его аннотацией Repository, либо создайте бин репозитория в Java config классе
- правильно инжектируйте EntityManager
- создайте метод `getPersonsByCity(String city)`, который будет принимать название города и возвращать ваше Entity из базы данных, которые соответствуют этому `city`. Сделать это можно, например, получив всех пользователей и отфильтровав их по городу.

4. Напишите контроллер, с методом-обработчиком GET-метода запроса с маппингом на endpoint `/persons/by-city`. В query params запроса будет приходить строковый параметр `city`, который вам надо будет передавать дальше в репозиторий. То есть, ваш метод должен уметь обрабатывать запрос вида `localhost:8080/persons/by-city?city=Moscow`.
   Контроллер должен будет возвращать всех людей, который он получит от репозитория.

5. Написанные код выложите в отдельный репозиторий на гитхабе и прикрепите ссылку на него в домашнем задании.


# Задача Миграции c Hibernate*

## Описание
Теперь попрактикуемся в работе с механизмами миграции в Hibernate.

1. Вам надо адаптировать логику работы задания [DAO слой c Hibernate](https://github.com/netology-code/jd-homeworks/blob/master/hibernate/task1/README.md) так, чтобы ваше приложение работало одновременно и с миграциями, и с Hibernate. Не важно выберете ли вы Flyway или Liquibase.

2. Написанные код выложите в тот же репозиторий, что и первая задач [DAO слой c Hibernate](https://github.com/netology-code/jd-homeworks/blob/master/hibernate/task1/README.md) на гитхабe, только создайте под нее другую ветку `migration-hibernate` и прикрепите ссылку на нее в домашнем задании.


## Домашнее задание к занятию 8.5: Spring Data JPA
# Задача DAO слой c JPA Repositories

## Описание
Попрактикуемся в работе с JPA Repositories, переписав приложение для работы с БД [DAO слой c Hibernate](https://github.com/netology-code/jd-homeworks/blob/master/hibernate/task1/README.md)

1. Перепишите репозиторий для работы с БД на основе методов-запросов, чтобы:
- у вашего репозитория были основные методы для `CRUD` операций
- создайте метод, который будет принимать название города(`city`) и возвращать `Entity` из базы данных, которые соответствуют этому `city`.
- создайте метод, который будет принимать возраст(`age`) и возвращать `Entity` из базы данных, которые меньше переданного `age` и отсортированы по возрастанию.
- создайте метод, который будет принимать имя и фамилию(`name` и `surname`) и возвращать `Entity` из базы данных, которые соответствуют сочетанию `name` и `surname` и является `Optional`.

2. Допишите недостающие методы контроллера, в соответствии с появившимися новыми методами в репозитории.

3. Написанные код выложите в тот же репозиторий, что и задача [DAO слой c Hibernate](https://github.com/netology-code/jd-homeworks/blob/master/hibernate/task1/README.md) на гитхабe, только создайте под нее другую ветку `jpa-repository` и прикрепите ссылку на нее в домашнем задании.


# Задача @Query*

## Описание
Перепишем приложение из [первого задания](https://github.com/netology-code/jd-homeworks/blob/master/spring-jpa/task1/README.md) на запросы с помощью аннотации `@Query`.

1. Вам необходимо переписать код из [первого задания](https://github.com/netology-code/jd-homeworks/blob/master/spring-jpa/task1/README.md), чтобы он теперь работал не через методы-запросы, а через запросы в аннотации `@Query` и язык `JPQL`.

2. Написанные код выложите в тот же репозиторий, что и задача [DAO слой c Hibernate](https://github.com/netology-code/jd-homeworks/blob/master/hibernate/task1/README.md) на гитхабe, только создайте под нее другую ветку `jpa-repository-query` и прикрепите ссылку на нее в домашнем задании.


## Домашнее задание к занятию 8.6: Безопасность в веб-приложениях, Spring Security
# Задача Безопасное приложение

## Описание

Сегодня мы попрактикуемся в обеспечении безопасности для приложений. Мы ограничим доступ к endpoint'ам.

1. Возьмите любое из уже реализованных приложений на `Spring boot`, где есть контроллер и не менее одного метода-обработчика запросов на разные endpoint'ы.

2. Добавьте в приложение зависимость на `spring-boot-starter-security`

3. Реализуйте класс-наследник `WebSecurityConfigurerAdapter` так, чтобы:
- пользователь логинился через стандартную форму логина от Spring
- как минимум на один из endpoint'ов вашего приложения можно было попасть без авторизации, а на все остальные только после авторизации

4. Запуште изменения в репозиторий и прикрепите ссылку на него в домашнем задании.