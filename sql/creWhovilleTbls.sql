-- creWhovilleTbls.sql
-- Justin Sumrall
DROP TABLE IF EXISTS whoplace;
CREATE TABLE whoplace(
	id serial PRIMARY KEY,
	name text,
	geom geometry(POINT)
);

DROP TABLE IF EXISTS whoroad;
CREATE TABLE whoroad(
	id serial PRIMARY KEY,
	name text,
	geom geometry(LINESTRING)
);

DROP TABLE IF EXISTS whodistrict;
CREATE TABLE whodistrict(
	id serial PRIMARY KEY,
	name text,
	geom geometry(POLYGON)
);

INSERT INTO whoroad(name, geom) VALUES('Snowflake Road','LINESTRING(0 22, 53 22)');
INSERT INTO whoroad(name, geom) VALUES('Grinch Road', 'LINESTRING(23 1, 23 41)');
INSERT INTO whoroad(name, geom) VALUES('Mount Crumpet Street', 'LINESTRING(0 2, 53 2)');
INSERT INTO whoroad(name, geom) VALUES('Punker''s Road', 'LINESTRING(23 22, 52 1)');

INSERT INTO whodistrict(name, geom) VALUES('Star District', 'POLYGON((7 24, 12 28, 17 24, 16 30,
	20 34, 14 34, 12 40, 10 34, 4 34, 8 30, 7 24))');
INSERT INTO whodistrict(name, geom) VALUES('Singer''s Diamond', 'POLYGON((39 24, 49 31, 39 39, 29 31, 39 24))');
INSERT INTO whodistrict(name, geom) VALUES('Mayor''s Square', 'POLYGON((2 3, 19 3, 19 20, 2 20, 2 3))');
INSERT INTO whodistrict(name, geom) VALUES('Sandwich Corner', 'POLYGON((25 4, 42 4, 25 17, 25 4))');
INSERT INTO whodistrict(name, geom) VALUES('Punker''s Pond', 'POLYGON((49 6, 49 19, 32 19, 49 6))');

INSERT INTO whoplace(name, geom) VALUES('Cindy Lou''s House', 'POINT(12 35)');
INSERT INTO whoplace(name, geom) VALUES('Town Christmas Tree', 'POINT(25 24)');
INSERT INTO whoplace(name, geom) VALUES('Christmas Feast Hall', 'POINT(28 12)');
INSERT INTO whoplace(name, geom) VALUES('Mayor''s House', 'POINT(16 17)');