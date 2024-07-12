create table usersapi(
    id bigint not null auto_increment,
    login varchar(100) not null,
    key_user varchar(300) not null,

    primary key(id)
);