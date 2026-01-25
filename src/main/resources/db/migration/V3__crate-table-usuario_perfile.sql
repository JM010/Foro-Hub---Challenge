create table usuario_perfil(
    usuario_id bigint not null,
    perfil_id bigint not null,
    primary key (usuario_id, perfil_id),
    constraint fk_usuario_perfil_usuario foreign key (usuario_id) references usuarios(id),
    constraint fk_usuario_perfil_perfil foreign key (perfil_id) references perfiles(id)
);