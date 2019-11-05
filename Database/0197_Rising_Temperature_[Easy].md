197. Rising Temperature

Given a `Weather` table, write a SQL query to find all dates' Ids with higher temperature compared to its previous (yesterday's) dates.

```
+---------+------------------+------------------+
| Id(INT) | RecordDate(DATE) | Temperature(INT) |
+---------+------------------+------------------+
|       1 |       2015-01-01 |               10 |
|       2 |       2015-01-02 |               25 |
|       3 |       2015-01-03 |               20 |
|       4 |       2015-01-04 |               30 |
+---------+------------------+------------------+
```

For example, return the following Ids for the above `Weather` table:

```
+----+
| Id |
+----+
|  2 |
|  4 |
+----+
```

------------------------------------------------------------------------------------------------

```sql
select w1.Id
from Weather w1 join Weather w2 on DATEDIFF(w1.RecordDate, w2.RecordDate) = 1
where w1.Temperature > w2.Temperature;
```



Note: 

日期函数使用:
	DATEDIFF(date1, date2)
	TO_DAYS(date)
	SUBDATE(date, INTERVAL)