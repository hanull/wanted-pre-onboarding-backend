SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE company;
TRUNCATE TABLE job_posting;

insert into company (name) values ('원티드');
insert into company (name) values ('우아형제들');
insert into company (name) values ('네이버');
insert into company (name) values ('카카오');

insert into job_posting (company_id, position, reward, job_description, skill) values (1L, '백엔드 시니어 개발자', 3000000,'백엔드 시니어를 적극 채용합니다.', 'Java');

SET FOREIGN_KEY_CHECKS = 1;
