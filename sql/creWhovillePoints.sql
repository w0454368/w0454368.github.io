-- Script to create Whoville points
-- Assumes the PostgreSQL Whoville database is created and connected
drop table if exists whopoints;
create table whopoints(
  name text,
  type text,
  geom geometry(POINT)
);

INSERT INTO whopoints VALUES('Snowflake Lane','road','POINT(0 22)');
INSERT INTO whopoints VALUES('Snowflake Lane','road','POINT(53 22)');
INSERT INTO whopoints VALUES('Grinch Road','road','POINT(23 1)');
INSERT INTO whopoints VALUES('Grinch Road','road','POINT(23 41)');
INSERT INTO whopoints VALUES('Mount Crumpet Street','road','POINT(0 2)');
INSERT INTO whopoints VALUES('Mount Crumpet Street','road','POINT(53 2)');
INSERT INTO whopoints VALUES('Punker''s Road','road','POINT(23 22)');
INSERT INTO whopoints VALUES('Punker''s Road','road','POINT(52 1)');
INSERT INTO whopoints VALUES('Star District','district','POINT(7 24)');
INSERT INTO whopoints VALUES('Star District','district','POINT(12 28)');
INSERT INTO whopoints VALUES('Star District','district','POINT(17 24)');
INSERT INTO whopoints VALUES('Star District','district','POINT(16 30)');
INSERT INTO whopoints VALUES('Star District','district','POINT(20 34)');
INSERT INTO whopoints VALUES('Star District','district','POINT(14 34)');
INSERT INTO whopoints VALUES('Star District','district','POINT(12 40)');
INSERT INTO whopoints VALUES('Star District','district','POINT(10 34)');
INSERT INTO whopoints VALUES('Star District','district','POINT(4 34)');
INSERT INTO whopoints VALUES('Star District','district','POINT(8 30)');
INSERT INTO whopoints VALUES('Star District','district','POINT(7 24)');
INSERT INTO whopoints VALUES('Singer''s Diamond','district','POINT(39 24)');
INSERT INTO whopoints VALUES('Singer''s Diamond','district','POINT(49 31)');
INSERT INTO whopoints VALUES('Singer''s Diamond','district','POINT(39 39)');
INSERT INTO whopoints VALUES('Singer''s Diamond','district','POINT(29 31)');
INSERT INTO whopoints VALUES('Singer''s Diamond','district','POINT(39 24)');
INSERT INTO whopoints VALUES('Mayor''s Square','district','POINT(2 3)');
INSERT INTO whopoints VALUES('Mayor''s Square','district','POINT(19 3)');
INSERT INTO whopoints VALUES('Mayor''s Square','district','POINT(19 20)');
INSERT INTO whopoints VALUES('Mayor''s Square','district','POINT(2 20)');
INSERT INTO whopoints VALUES('Mayor''s Square','district','POINT(2 3)');
INSERT INTO whopoints VALUES('Sandwich Corner','district','POINT(25 4)');
INSERT INTO whopoints VALUES('Sandwich Corner','district','POINT(42 4)');
INSERT INTO whopoints VALUES('Sandwich Corner','district','POINT(25 17)');
INSERT INTO whopoints VALUES('Sandwich Corner','district','POINT(25 4)');
INSERT INTO whopoints VALUES('Punker''s Pond','district','POINT(49 6)');
INSERT INTO whopoints VALUES('Punker''s Pond','district','POINT(49 19)');
INSERT INTO whopoints VALUES('Punker''s Pond','district','POINT(32 19)');
INSERT INTO whopoints VALUES('Punker''s Pond','district','POINT(49 6)');
