# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table advertisement (
  uid                       bigint auto_increment not null,
  title                     varchar(255),
  price                     integer,
  fuel                      varchar(255),
  state                     tinyint(1) default 0,
  mileage                   integer,
  registration              datetime(6),
  constraint pk_advertisement primary key (uid))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table advertisement;

SET FOREIGN_KEY_CHECKS=1;

