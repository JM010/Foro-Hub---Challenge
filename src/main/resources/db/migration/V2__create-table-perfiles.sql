create table perfiles(
   id bigint not null auto_increment,
   rol ENUM('ROLE_USER','ROLE_ADMIN') not null,
   primary key (id)
)