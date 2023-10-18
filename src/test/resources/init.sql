SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE company;
TRUNCATE TABLE job_posting;

insert into company (name) values ('원티드');
insert into company (name) values ('우아형제들');
insert into company (name) values ('네이버');
insert into company (name) values ('카카오');

SET FOREIGN_KEY_CHECKS = 1;
