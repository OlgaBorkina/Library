create schema students;
create table if not exists students.student(
    id SERIAL PRIMARY key,
    name VARCHAR(255) NOT null,
    email VARCHAR(255) NOT null
    );
