create table conversations (id serial, creation_date timestamp, modification_date timestamp, usera_id int8, userb_id int8, primary key (id));
create table messages (id serial, date timestamp, text text, conversation_id int8, sender_id int8, primary key (id));
create table users (id serial, description text, name varchar(255), number varchar(255), pin varchar(255), pseudo varchar(255), surename varchar(255), primary key (id));
alter table if exists users add constraint UK_rausl0ctj9serlpp463b7ku92 unique (number);
alter table if exists conversations add constraint FKqswytneb7qn9355fmg7ao2kbc foreign key (usera_id) references users;
alter table if exists conversations add constraint FKelg0kbv82vfgb8rarlfqp24jc foreign key (userb_id) references users;
alter table if exists messages add constraint FK6yskk3hxw5sklwgi25y6d5u1l foreign key (conversation_id) references conversations;
alter table if exists messages add constraint FKcnj2qaf5yc36v2f90jw2ipl9b foreign key (sender_id) references users;
