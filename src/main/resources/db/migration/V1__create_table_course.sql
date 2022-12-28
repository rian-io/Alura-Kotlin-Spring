create table course(
    id bigint not null auto_increment,
    name varchar(50) not null,
    category varchar(50) not null,
    primary key(id)
);

insert into course (id, name, category) values (1, 'Kotlin', 'Programming');