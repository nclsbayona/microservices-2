-- Base script: https://github.com/forana/postgres-init/blob/master/init.sql
\echo -------------------------------
\echo | Clearing out any old tables
\echo -------------------------------

drop table if exists Paseos;

\echo -------------------------------
\echo | Creating tables
\echo -------------------------------


create table Paseos (
    ID SERIAL primary key,
    NOMBRE text NOT NULL,
    LUGAR_SALIDA text NOT NULL,
    LUGAR_LLEGADA text NOT NULL,
    FECHA timestamp with time zone NOT NULL
);

\echo -------------------------------
\echo | Inserting data
\echo -------------------------------

insert into Paseos (NOMBRE, LUGAR_SALIDA, LUGAR_LLEGADA, FECHA) values
    ('El paseo millonario 1', 'Bogotá, Colombia', 'Medellin, Colombia', TIMESTAMP '2017-08-10 10:30:20.15 US/Pacific'), 
    ('Vicente', 'Medellin, Colombia', 'Ciudad de Mexico, Mexico', TIMESTAMP '2018-08-10 10:30:20.15 America/Argentina/Buenos_Aires'),
    ('Paseando ando', 'Londres, Reino Unido', 'Seoul, Korea', TIMESTAMP '2019-08-10 10:30:20.15 Asia/Seoul'),
    ('El paseo', 'Paris, Francia', 'Praga, Polonia', TIMESTAMP '2020-08-10 10:30:20.15 Europe/Prague'),
    ('El paseo a la U','Mi casita','La uni',TIMESTAMP'2019-08-24T07:00:00-05:00')
;

\echo -------------------------------
\echo | All done!
\echo -------------------------------

/*
To connect
psql -h <hostname> -p <port> -U <username> -d <database> (If not local, password is prompted)
psql -U postgres -d postgres (Local)
*/