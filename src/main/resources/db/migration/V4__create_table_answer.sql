create table answer(
    id bigint not null auto_increment,
    message varchar(50) not null,
    created_at datetime not null,
    question_id bigint not null,
    author_id bigint not null,
    is_solution boolean not null,
    primary key(id),
    foreign key(question_id) references question(id),
    foreign key(author_id) references users(id)
);