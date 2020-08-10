drop table projeto;
drop table serie;

create sequence projeto_id_seq;
create sequence serie_id_seq;

CREATE TABLE projeto (
	id bigint NOT null DEFAULT nextval('projeto_id_seq'),
	name varchar(255),
	CONSTRAINT projeto_pkey PRIMARY KEY (id)
);

CREATE TABLE serie (
	id bigint NOT null DEFAULT nextval('serie_id_seq'),
	name varchar(255),
	"start" varchar(255),
	"end" varchar(255),
	id_projeto bigint,
	CONSTRAINT serie_pkey PRIMARY KEY (id),
	CONSTRAINT serie_projeto_fkey FOREIGN KEY (id_projeto) REFERENCES projeto(id)
);

CREATE INDEX idx_serie_projeto ON serie USING btree (id_projeto);