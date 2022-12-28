create table question(
    id bigint not null auto_increment,
    title varchar(50) not null,
    message varchar(300) not null,
    created_at datetime not null,
    status varchar(20) not null,
    course_id bigint not null,
    author_id bigint not null,
    primary key(id),
    foreign key(course_id) references course(id),
    foreign key(author_id) references users(id)
);