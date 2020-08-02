# Да се напише заявка, която извежда име на клас, годината на първата битка, в която кораб на този клас е участвал,
# годината на последната битка, в която кораб на този клас е участвал, и броя на всички различни битки, в които кораби на този клас са участвали,
# само за тези класове, започващи с буквата N. Ако за даден клас няма кораб, който да е участвал в битка, за съответните години да се върне стойност null.

SELECT CLASSES.CLASS,
  MIN(BATTLES.DATE) AS YEAR_FIRST_BATTLE,
  MAX(BATTLES.DATE) AS YEAR_LAST_BATTLE,
  COUNT(OUTCOMES.BATTLE) AS BATTLES
FROM CLASSES
   JOIN SHIPS ON CLASSES.CLASS = SHIPS.NAME 
   LEFT JOIN OUTCOMES ON SHIPS.NAME = OUTCOMES.SHIP
   LEFT JOIN BATTLES ON OUTCOMES.BATTLE = BATTLES.NAME
WHERE CLASSES.CLASS LIKE 'N%'
GROUP BY CLASSES.CLASS;