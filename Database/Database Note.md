# Database

这里是做题时随手记录的内容。

## 595. Big Countries
连续的or => union, 会运行的快一些，但没有太大区别

## 627. Swap Salary
`if(sex = 'm', 'f','m') <=> case sex when 'm' then 'f' else 'm' end`
使用`case when then else end...`

```sql
update salary set sex = CHAR(ASCII('f') ^ ASCII('m') ^ ASCII(sex));
```



## 620. Not Boring Movies
`id % 2 = 1 <=> mod(id, 2) = 1`
`instr(str, substr)`, 返回指定字符串在目标字符串中的位置，从1开始，不存在时返回0;

## 183. Customers Who Never Order
not in需要注意效率问题

## 197. Rising Temperature
日期函数使用
`DATEDIFF(date1, date2)`
`TO_DAYS(date)`
`SUBDATE(date, INTERVAL)`

## 196. Delete Duplicate Emails
```sql
delete p1 from Person p1, Person p2
where p1.Email = p2.Email and p1.Id > p2.Id;
```



## 176. Second Highest Salary
解决第几...而使用limit时需要注意可能目标不存在

```sql
select max(Salary) as SecondHighestSalary from Employee where Salary < (select max(Salary) from Employee);

select IFNULL((select DISTINCT Salary from Employee order by Salary desc limit 1,1), NULL) AS SecondHighestSalary;
```



## 626. Exchange Seats

`COALESCE(val1, val2, ...., val_n)`
The COALESCE() function returns the first non-null value in a list.

## 178. Rank Scores

```sql
select Score, (select count(*) from (select distinct Score as s from Scores) as new_scores where s >= Score) Rank 
from Scores order by Score desc;
```



## 180. Consecutive Numbers

使用局部变量

```sql
select distinct Num from (
    select Num,
        case
            when @prevNum = Num then @count := @count + 1
            when (@prevNum := Num) is not null then @count := 1
        end n
    from Logs, (select @prevNum := NULL) r
    order by Id
) a where n >= 3;

select distinct L1.Num ConsecutiveNums 
from Logs L1
left join Logs L2 on L1.Id = L2.Id - 1
left join Logs L3 on L1.Id = L3.Id - 2
where L1.Num = L2.Num and L2.Num = L3.Num;
```




## 601. Human Traffic of Stadium
```sql
SELECT DISTINCT s1.*
FROM stadium s1, stadium s2, stadium s3 
WHERE s1.people >= 100 AND s2.people >= 100 AND s3.people >= 100  
AND ( 
    (s1.id - s2.id =1 AND s2.id - s3.id =1 )  
    OR (s2.id - s1.id = 1 AND s1.id - s3.id =1)
    OR (s3.id - s2.id = 1 AND s2.id - s1.id = 1)  
)
ORDER BY s1.id;

select distinct s0.* 
from stadium as s0, (
    select case
            when people>=100 then @count:=@count+1
            else @count:=0
        end as total, id
    from stadium, (select @count:=0) as temp
) as s1 
where 
s1.total >=3 and 
s0.id <= s1.id and 
s0.id >= s1.id - s1.total + 1;
```



## 185. Department Top Three Salaries

```sql
SELECT D1.Name Department, E1.Name Employee,  E1.Salary
FROM Employee E1, Employee E2, Department D1
WHERE E1.DepartmentID = E2.DepartmentID
AND E2.Salary >= E1.Salary 
AND E1.DepartmentID = D1.ID      
GROUP BY E1.Name
HAVING COUNT(DISTINCT E2.Salary) <= 3
ORDER BY D1.Name, E1.Salary DESC;
```



## 262. Trips and Users

```sql
select t.Request_at Day, ROUND(sum((case when t.Status like 'cancelled%' then 1 else 0 end))/count(*),2) as'Cancellation Rate'  
from Trips t join Users u on u.Users_Id =t.Client_Id and u.Banned = 'No'  
where t.Request_at between '2013-10-01' and '2013-10-03' 
group by t.Request_at;
```

正则匹配
`id regexp '[13579]$'`
`id regexp '[02468]$'`