create table enviados(
     id bigint(30) not null auto_increment,
     protocolo varchar(50) default null,
     setor varchar(10) default null,
     modelo varchar(10) default null,
     enviados_id bigint(250) not null,
     obs varchar(50) default null,
    primary key(id),
    foreign key (enviados_id) references coletores_enviados(id)
);