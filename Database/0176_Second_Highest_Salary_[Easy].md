176. Second Highest Salary

Write a SQL query to get the second highest salary from the `Employee` table.

```
+----+--------+
| Id | Salary |
+----+--------+
| 1  | 100    |
| 2  | 200    |
| 3  | 300    |
+----+--------+
```

For example, given the above Employee table, the query should return `200` as the second highest salary. If there is no second highest salary, then the query should return `null`.

```
+---------------------+
| SecondHighestSalary |
+---------------------+
| 200                 |
+---------------------+
```

------------------------------------------------------------------------------------------------

```sql
select max(Salary) as SecondHighestSalary from Employee where Salary < (select max(Salary) from Employee);

select IFNULL((select DISTINCT Salary from Employee order by Salary desc limit 1,1), NULL) AS SecondHighestSalary;
```



Note:
解决第几...而使用limit时需要注意目标可能不存在



