create table if not exists contacts (
    id serial primary key,
    user_id integer,
    contact_id integer
);

alter table if exists contacts add constraint contact_user_fk foreign key (user_id) references users;
alter table if exists contacts add constraint contact_contact_fk foreign key (contact_id) references users;

