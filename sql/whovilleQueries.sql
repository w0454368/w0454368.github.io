-- whovilleQueries.sql
-- Justin Sumrall

-- Find area of all districts, order from largest area to smallest area.
SELECT name, CAST(ST_Area(geom) AS DECIMAL(5,1)) AS Area FROM whodistrict ORDER BY Area DESC;

-- Find sum of the length of all roads in Whoville with 2 places of decimal precision.
SELECT CAST(SUM(ST_Length(geom)) AS DECIMAL(5,2)) AS RoadLength FROM whoroad;

-- Find distance between Cindy Lou's House and the Town Christmas Tree.
SELECT ST_Distance(a.geom, b.geom) AS Distance FROM whoplace a, whoplace b WHERE a.name LIKE '%Lou%' AND b.name = 'Town Christmas Tree';

-- Determine which points from whoplace table are in the Sandwich Corner district.
SELECT p.name AS Places FROM whoplace p, whodistrict d WHERE ST_Within(p.geom, d.geom) = true AND d.name = 'Sandwich Corner';