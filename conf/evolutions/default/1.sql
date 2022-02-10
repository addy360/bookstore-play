# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table book (
  id                            double auto_increment not null,
  title                         varchar(255),
  thumbnail                     varchar(255),
  user_id                       double,
  constraint pk_book primary key (id)
);

create table user (
  id                            double auto_increment not null,
  fullname                      varchar(255),
  username                      varchar(255),
  password                      varchar(255),
  constraint pk_user primary key (id)
);

create index ix_book_user_id on book (user_id);
alter table book add constraint fk_book_user_id foreign key (user_id) references user (id) on delete restrict on update restrict;


# --- !Downs

alter table book drop foreign key fk_book_user_id;
drop index ix_book_user_id on book;

drop table if exists book;

drop table if exists user;

