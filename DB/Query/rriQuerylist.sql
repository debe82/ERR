select * from rescued_rat inner join city on rescued_rat.city=city.id;

SELECT rescued_rat.name, city.name DNAME 
FROM rescued_rat INNER join city
ON rescued_rat.city=city.id;