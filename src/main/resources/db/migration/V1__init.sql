create table if not exists client(
id serial,
nui varchar(10) not null,
fullname varchar (50) not null ,
address varchar (50) not null,
PRIMARY KEY (id),
UNIQUE(nui)
);
