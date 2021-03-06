180. Consecutive Numbers

Write a SQL query to find all numbers that appear at least three times consecutively.

```
+----+-----+
| Id | Num |
+----+-----+
| 1  |  1  |
| 2  |  1  |
| 3  |  1  |
| 4  |  2  |
| 5  |  1  |
| 6  |  2  |
| 7  |  2  |
+----+-----+
```

For example, given the above `Logs` table, `1` is the only number that appears consecutively for at least three times.

```
+-----------------+
| ConsecutiveNums |
+-----------------+
| 1               |
+-----------------+
```

------------------------------------------------------------------------------------------------

```sql
select distinct L1.Num ConsecutiveNums 
from Logs L1
left join Logs L2 on L1.Id = L2.Id - 1
left join Logs L3 on L1.Id = L3.Id - 2
where L1.Num = L2.Num and L2.Num = L3.Num;

select distinct Num ConsecutiveNums from (
    select Num,
        case
            when @prevNum = Num then @count := @count + 1
            when (@prevNum := Num) is not null then @count := 1
        end n
    from Logs, (select @prevNum := null, @count := 0) as temp
    order by Id
) a where n >= 3;
```

