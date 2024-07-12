create table patients(
    id bigint not null auto_increment,
    nombre varchar(100) not null,
    email varchar(100) not null unique,
    documento varchar(14) not null unique,
    calle varchar(100) not null,
    distrito varchar(100) not null,
    ciudad varchar(100) not null,
    numero varchar(20),
    complemento varchar(100),
    telefono varchar(20) not null,
    active tinyint not null,

    primary key(id)
);