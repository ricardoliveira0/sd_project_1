--CREATE DATABASE runningevents-db;

DROP TABLE IF EXISTS event_list, "Corrida Júnior de Évora", "Corrida Solidária Nacional", "Corta mato Alentejano", "Maratona anual do Alentejo", "Maratona de Natal", "Sprint 300m Eborense", "Sprint 500m Nacional" CASCADE;
CREATE TABLE event_list(
    name text,
    date date,
    type text
);

INSERT INTO event_list ("name","date","type") VALUES
	('Maratona anual do Alentejo','2022-01-03','Road'),
	('Maratona de Natal','2021-12-23','Road'),
	('Sprint 300m Eborense','2022-01-03','Track'),
	('Corrida Solidária Nacional','2022-01-03','Road'),
	('Corrida Júnior de Évora','2021-12-23','Trails'),
	('Corta mato Alentejano','2022-02-01','Trails'),
	('Sprint 500m Nacional','2022-02-01','Track');

CREATE TABLE "Maratona anual do Alentejo"(
    id serial,
    name text,
    gender char(1),
    echelon text,
    trial_time time; 
);

CREATE TABLE "Maratona de Natal"(
    id serial,
    name text,
    gender char(1),
    echelon text,
    trial_time time
);

CREATE TABLE "Sprint 300m Eborense"(
    id serial,
    name text,
    gender char(1),
    echelon text,
    trial_time time
);

CREATE TABLE "Corrida Solidária Nacional"(
    id serial,
    name text,
    gender char(1),
    echelon text,
    trial_time time
);

CREATE TABLE "Corrida Júnior de Évora"(
    id serial,
    name text,
    gender char(1),
    echelon text,
    trial_time time; 
);

CREATE TABLE "Corta mato Alentejano"(
    id serial,
    name text,
    gender char(1),
    echelon text,
    trial_time time; 
);

CREATE TABLE "Sprint 500m Nacional"(
    id serial,
    name text,
    gender char(1),
    echelon text,
    trial_time time; 
);


INSERT INTO "Maratona anual do Alentejo" ("name",gender,echelon,trial_time) VALUES
	('Miguel Carvalho','M','Seniors',NULL),
	('Ricardo Oliveira','M','Seniors','00:33:23.07'),
	('Pedro Pires','M','Seniors','00:32:44.39'),
	('António Cruz','M','Veterans 35','00:38:02.44'),
	('Ana Beatriz','F','Seniors','00:33:22.01'),
	('Joana Cruz','F','Seniors','00:34:30.52'),
	('Joana Calado','F','Seniors','00:37:11.22'),
	('António Barnabé','M','Juniors','00:38:01.55'),
	('José Santos','M','Veterans 35','00:46:08.31'),
	('Miguel Oliveira','M','Veterans 40','00:38:05.22'),
	('João Casalinho','M','Veterans 50','01:02:35.09'),
	('Miguel Carvalho','M','Veterans 35','00:39:20.31'),
	('Ana Marques','F','Veterans 35','00:57:20'),
	('Guilherme Pinto','M','Juniors','00:39:15.09'),
	('Hugo Silveira','M','Veterans 50','01:00:22.06'),
	('João Cruz','M','Veterans 65+','01:15:22.52'),
	('Carolina Pinto','F','Veterans 35','00:57:20.36'),
	('Ana Sofia','F','Seniors','00:39:22.08'),
	('José Antunes','M','Veterans 50',NULL);
