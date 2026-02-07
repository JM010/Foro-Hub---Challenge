create table respuestas(
    id bigint not null auto_increment,
    mensaje text not null,
    topico_id bigint not null,
    fecha_creacion datetime not null,
    autor_id bigint not null,
    solucion boolean not null default false,

    primary key (id),
    constraint fk_respuestas_usuario foreign key (autor_id) references usuarios(id),
    constraint fk_respuestas_pregunta foreign key (topico_id) references topicos(id)
);