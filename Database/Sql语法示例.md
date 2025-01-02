-- 从 `students` 表中返回所有字段的数据  

```sql
SELECT * FROM students;
```

-- 从 `students` 表中选择 `姓名`、`班级` 和 `成绩` 三个字段  

```sql
SELECT 姓名, 班級, 成績 FROM students;
```

-- 每次显示 5 条数据，跳过前 10 条数据  

```sql
SELECT 姓名, 班級, 成績
FROM students
LIMIT 5
OFFSET 10;
```

-- 显示属于 `1 年 2 班` 的学生数据  

```sql
SELECT 姓名, 班級, 成績
FROM students
WHERE 班級 = '1 年 2 班';
```

-- 显示不属于 `1 年 2 班` 的学生数据  

```sql
SELECT 姓名, 班級, 成績
FROM students
WHERE 班級 <> '1 年 2 班';
```

-- 按照班级名称排序学生数据  

```sql
SELECT 姓名, 班級, 成績
FROM students
WHERE 班級 <> '1 年 2 班'
ORDER BY 班級;
```

-- 按照班级名称和成绩排序学生数据  

```sql
SELECT 姓名, 班級, 成績
FROM students
WHERE 班級 <> '1 年 2 班'
ORDER BY 班級, 成績;
```

-- 按照班级名称和成绩进行排序（成绩由高到低）  

```sql
SELECT 姓名, 班級, 成績
FROM students
WHERE 班級 <> '1 年 2 班'
ORDER BY 班級, 成績 DESC;
```

-- 查询学生 `张小婷` 的信息  

```sql
SELECT 姓名, 班級, 成績
FROM students
WHERE 姓名 = '張小婷';
```

-- 查询所有姓 "张" 的学生  

```sql
SELECT 姓名, 班級, 成績
FROM students
WHERE 姓名 LIKE '張%';
```

-- 查询所有姓 "张" 且名字只有两个字的学生  

```sql
SELECT 姓名, 班級, 成績
FROM students
WHERE 姓名 LIKE '張_';
```

-- 查询成绩大于等于 80 分的学生  

```sql
SELECT 姓名, 班級, 成績
FROM students
WHERE 成績 >= 80;
```

-- 查询成绩在 80 到 90 分之间的学生  

```sql
SELECT 姓名, 班級, 成績
FROM students
WHERE 成績 BETWEEN 80 AND 90;
```

-- 查询成绩在 80 到 90 分之间，且班级为 `1 年 1 班` 或 `1 年 2 班` 的学生  

```sql
SELECT 姓名, 班級, 成績
FROM students
WHERE 成績 BETWEEN 80 AND 90 AND (班級 = '1 年 1 班' OR 班級 = '1 年 2 班');
```

-- 使用 `IN` 简化多个 `OR` 条件  

```sql
SELECT 姓名, 班級, 成績
FROM students
WHERE 成績 BETWEEN 80 AND 90 AND 班級 IN ('1 年 1 班', '1 年 2 班');
```

-- 计算成绩的平均值、总和、最大值、最小值和记录数  

```sql
SELECT AVG(成績), SUM(成績), MAX(成績), MIN(成績), COUNT(成績)
FROM students;
```

-- 使用 `AS` 设置别名  

```sql
SELECT AVG(成績) AS 成績平均, MAX(成績) AS 最高分
FROM students;
```

-- 四舍五入到小数点后一位  

```sql
SELECT ROUND(AVG(成績), 1) AS 成績平均
FROM students;
```

-- 四舍五入到整数  

```sql
SELECT ROUND(AVG(成績)) AS 成績平均
FROM students;
```

-- 查询各班级的成绩平均值，并按分数由高到低排序  

```sql
SELECT 班級, ROUND(AVG(成績)) AS 成績平均
FROM students
GROUP BY 班級
ORDER BY 成績平均 DESC;
```

-- 查询成绩平均大于等于 80 的班级，并按成绩由高到低排序  

```sql
SELECT 班級, ROUND(AVG(成績)) AS 成績平均
FROM students
GROUP BY 班級
HAVING 成績平均 >= 80
ORDER BY 成績平均 DESC;
```

-- 计算表中记录的总行数  

```sql
SELECT COUNT(*)
FROM students;
```

-- 计算 `社团` 字段的记录数（忽略空值）  

```sql
SELECT COUNT(社团)
FROM students;
```

-- 计算有多少种不同的社团  

```sql
SELECT COUNT(DISTINCT 社团)
FROM students;
```

-- 列出不同的社团名称  

```sql
SELECT DISTINCT 社团
FROM students;
```

-- 列出不包含空值的社团名称，并按名称排序  

```sql
SELECT DISTINCT 社团
FROM students
WHERE 社团 IS NOT NULL
ORDER BY 社团;
```

-- 创建一个名为 `clubs` 的表，包含 `社团编号`（主键）和 `社团名称` 两个字段  

```sql
CREATE TABLE clubs (
社团编号 INT PRIMARY KEY,
社团名称 VARCHAR(15)
);
```

-- 删除名为 `clubs2` 的表  

```sql
DROP TABLE clubs2;
```

-- 插入数据到 `clubs` 表  

```sql
INSERT INTO clubs (社团编号, 社团名称)
VALUES (101, '吉他社'), (102, '篮球社'), (103, '美术社'), (104, NULL);
```

-- 更新 `clubs` 表中 `社团编号` 为 104 的记录  

```sql
UPDATE clubs
SET 社团名称 = '舞蹈社'
WHERE 社团编号 = 104;
```

-- 删除 `社团编号` 为 104 的记录  

```sql
DELETE
FROM clubs
WHERE 社团编号 = 104;
```

-- 使用 `LEFT JOIN` 查询学生报名的社团名称  

```sql
SELECT students.姓名, students.社团, clubs.社团名称
FROM students
LEFT JOIN clubs
ON students.社团 = clubs.社团编号
WHERE 班級 = '1 年 1 班';
```

-- 使用 `INNER JOIN` 查询学生报名的社团名称  

```sql
SELECT students.姓名, students.社团, clubs.社团名称
FROM students
INNER JOIN clubs
ON students.社团 = clubs.社团编号
WHERE 班級 = '1 年 1 班';
```
