create table coletores_enviados(
      id bigint(30) not null auto_increment,
       mac varchar(30) default null,
       patrimonio varchar(30) default null,
       status varchar(255) default null,
       data varchar(50) default null,
       ativo tinyint not null,
       primary key(id)
);