drop table if exists company;
drop table if exists member;

create table company
(
    id   bigint not null auto_increment,
    name varchar(255),
    primary key (id)
);

create table member
(
    id   bigint not null auto_increment,
    name varchar(255),
    primary key (id)
);
