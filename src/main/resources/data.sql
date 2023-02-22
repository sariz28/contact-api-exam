-- TABLE CONTACT
insert into contact(id, name, email)
values(1, 'Soumitra', 'soumitra@email.com');
insert into contact(id, name, email)
values(2,'Liton','liton@email.com');
insert into contact(id, name, email)
values(3, 'Suman','suman@email.com');

-- TABLE PHONE
insert into phone(number, type, contact_id)
values('5528394817', 0, 1);
insert into phone(number, type, contact_id)
values('5528102938', 1, 1);
insert into phone(number, type, contact_id)
values('5528938472', 2, 2);
insert into phone(number, type, contact_id)
values('7793827364', 3, 3);
