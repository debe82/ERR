
SELECT rescued_rat.name, city.name DNAME 
FROM rescued_rat INNER join city
ON rescued_rat.city=city.id;

