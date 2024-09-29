create table if not exists students (
    id integer primary key generated always as identity,
    first_name   varchar(255) not null,
    last_name    varchar(255) not null,
    fathers_name varchar(255) not null,
    birth_date   date         not null,
    group_name   varchar(50)  not null
);


truncate table students restart identity;


