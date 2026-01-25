create table perfiles(
   id bigint not null auto_increment,
   rol ENUM('USER','ADMIN') not null,
   primary key (id)
)