# Students REST API
### Инструкция по запуску
В файле `src/main/resources/application.properties` укажите свои данные о бд (`spring.datasource.url`, `spring.datasource.username`, `spring.datasource.password`)

Затем, находясь в корне проекта, запустите команду для сборки `gradlew bootJar`.

После сборки, выполните команду `java -jar build/libs/students-application-0.0.1-SNAPSHOT.jar`. Приложение будет доступно на `localhost:8080/`

Также понадобится java версии 21+ (так как классы были скомпилированы java 21.0.1 2023-10-17 LTS)

### Задание
Для выполнения задания выбери любую реляционную базу данных (oracle, sql server, mysql, postgresql, sqlite и т.д.).

1. Заведи в БД таблицу данных о студентах, которая будет содержать: имя, фамилия, отчество, дата рождения, группа, уникальный номер.
2. Создай веб-приложение (клиентскую и серверную части), с помощью которого можно добавить студента, удалить студента по уникальному номеру, вывести список студентов.

☝ **Реализация серверной части должна быть написана на любом из следующих языков: C, C++, Java, Kotlin, Rust**

☝ **Реализация клиентской части должна быть написана на JavaScript без использования сторонних библиотек и фреймворков. Исключением является jQuery.**

**☝ При работе с БД используй ручное написание запросов, а не ORM. Примени технологию REST API.**