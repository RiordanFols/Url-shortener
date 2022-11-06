alter table if exists user_role
    drop constraint if exists FKfpm8swft53ulq2hl11yplpr5;

drop table if exists usr cascade;
drop table if exists user_role cascade;

create sequence usr_sequence start with 1 increment by 1;

create table usr
(
    id       bigint       not null DEFAULT nextval('usr_sequence'::regclass),
    username varchar(30)  not null,
    password varchar(100) not null,
    active   boolean      not null,
    token    varchar(36)  not null,
    primary key (id)
);

create table user_role
(
    user_id bigint      not null,
    role    varchar(50) not null,
    primary key (user_id, role)
);

alter table if exists user_role
    add constraint fk_user_role_usr foreign key (user_id) references usr;