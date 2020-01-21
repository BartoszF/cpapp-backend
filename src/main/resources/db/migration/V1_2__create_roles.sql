create table roles (

    ID serial PRIMARY KEY,
    user_id integer references users(ID),
    role_name varchar(25)
);

alter table if exists roles add constraint roles_user_fk foreign key (user_id) references users;
