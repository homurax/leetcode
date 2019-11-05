1179. Reformat Department Table

Table: `Department`

```
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| id            | int     |
| revenue       | int     |
| month         | varchar |
+---------------+---------+
(id, month) is the primary key of this table.
The table has information about the revenue of each department per month.
The month has values in ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"].
```

Write an SQL query to reformat the table such that there is a department id column and a revenue column **for each month**.

The query result format is in the following example:

```
Department table:
+------+---------+-------+
| id   | revenue | month |
+------+---------+-------+
| 1    | 8000    | Jan   |
| 2    | 9000    | Jan   |
| 3    | 10000   | Feb   |
| 1    | 7000    | Feb   |
| 1    | 6000    | Mar   |
+------+---------+-------+

Result table:
+------+-------------+-------------+-------------+-----+-------------+
| id   | Jan_Revenue | Feb_Revenue | Mar_Revenue | ... | Dec_Revenue |
+------+-------------+-------------+-------------+-----+-------------+
| 1    | 8000        | 7000        | 6000        | ... | null        |
| 2    | 9000        | null        | null        | ... | null        |
| 3    | null        | 10000       | null        | ... | null        |
+------+-------------+-------------+-------------+-----+-------------+

Note that the result table has 13 columns (1 for the department id + 12 for the months).
```
------------------------------------------------------------------------------------------------

```sql
SELECT id, MAX(CASE WHEN month = 'Jan' THEN revenue END) AS Jan_Revenue, 
           MAX(CASE WHEN month = 'Feb' THEN revenue END) AS Feb_Revenue,
           MAX(CASE WHEN month = 'Mar' THEN revenue END) AS Mar_Revenue,
           MAX(CASE WHEN month = 'Apr' THEN revenue END) AS Apr_Revenue,
           MAX(CASE WHEN month = 'May' THEN revenue END) AS May_Revenue,
           MAX(CASE WHEN month = 'Jun' THEN revenue END) AS Jun_Revenue,
           MAX(CASE WHEN month = 'Jul' THEN revenue END) AS Jul_Revenue,
           MAX(CASE WHEN month = 'Aug' THEN revenue END) AS Aug_Revenue,
           MAX(CASE WHEN month = 'SEP' THEN revenue END) AS Sep_Revenue,
           MAX(CASE WHEN month = 'Oct' THEN revenue END) AS Oct_Revenue,
           MAX(CASE WHEN month = 'Nov' THEN revenue END) AS Nov_Revenue,
           MAX(CASE WHEN month = 'Dec' THEN revenue END) AS Dec_Revenue 
FROM Department
GROUP BY id
ORDER BY id;
```





