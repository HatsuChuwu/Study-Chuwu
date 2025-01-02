# 在MySQL的`Gradem`数据库中创建管理数据表

### 1\. 连接到数据库

首先，连接到MySQL服务器并选择`Gradem`数据库：

```sql
mysql -h localhost -u root -p
Create database Gradem;
USE Gradem;
```

### 2\. 创建表

根据提供的表结构，使用`CREATE TABLE`语句创建各个表：

#### `student` 表

```sql
CREATE TABLE student (
    sno CHAR(10) NOT NULL,
    sname VARCHAR(8),
    ssex CHAR(2),
    sbirthday DATE,
    saddress VARCHAR(50),
    sdept CHAR(16),
    speciality VARCHAR(20),
    PRIMARY KEY (sno)
);

```

#### `course` 表

```sql
CREATE TABLE course (
    cno CHAR(5) NOT NULL,
    cname VARCHAR(20) NOT NULL,
    PRIMARY KEY (cno)
);

```

#### `sc` 表

```sql
CREATE TABLE sc (
    sno CHAR(10) NOT NULL,
    cno CHAR(5) NOT NULL,
    degree DECIMAL(4, 1),
    PRIMARY KEY (sno, cno),
    FOREIGN KEY (sno) REFERENCES student(sno),
    FOREIGN KEY (cno) REFERENCES course(cno)
);

```

#### `teacher` 表

```sql
CREATE TABLE teacher (
    tno CHAR(3) NOT NULL,
    tname VARCHAR(8),
    tsex CHAR(2),
    tbirthday DATE,
    tdept CHAR(16),
    PRIMARY KEY (tno)
);

```

#### `teaching` 表

```sql
CREATE TABLE teaching (
    cno CHAR(5) NOT NULL,
    tno CHAR(3) NOT NULL,
    cterm TINYINT,
    PRIMARY KEY (cno, tno),
    FOREIGN KEY (cno) REFERENCES course(cno),
    FOREIGN KEY (tno) REFERENCES teacher(tno)
);

```

### 3\. 插入数据

根据提供的数据，插入相应的记录：

#### `student` 表

```sql
INSERT INTO student (sno, sname, ssex, sbirthday, saddress, sdept, speciality) VALUES
('20050101', '李勇', '男', '1987-01-12', '山东济南', '计算机工程系', '计算机应用'),
('20050201', '刘晨', '女', '1988-06-04', '山东青岛', '信息工程系', '电子商务'),
('20050301', '王敏', '女', '1989-12-23', '江苏苏州', '数学系', '数学'),
('20050202', '张立', '男', '1988-08-25', '河北唐山', '信息工程系', '电子商务');

```

#### `course` 表

```sql
INSERT INTO course (cno, cname) VALUES
('C01', '数据库'),
('C02', '数学'),
('C03', '信息系统'),
('C04', '操作系统');

```

#### `sc` 表

```sql
INSERT INTO sc (sno, cno, degree) VALUES
('20050101', 'C01', 92),
('20050101', 'C02', 85),
('20050101', 'C03', 88),
('20050201', 'C02', 90),
('20050201', 'C03', 80);

```

#### `teacher` 表

```sql
INSERT INTO teacher (tno, tname, tsex, tbirthday, tdept) VALUES
('101', '李新', '男', '1977-01-12', '计算机工程系'),
('102', '钱军', '女', '1968-06-04', '计算机工程系'),
('201', '王小花', '女', '1979-12-23', '信息工程系'),
('202', '张小青', '男', '1968-08-25', '信息工程系');

```

#### `teaching` 表

```sql
INSERT INTO teaching (cno, tno, cterm) VALUES
('C01', '101', 2),
('C02', '102', 1),
('C03', '201', 3),
('C04', '202', 4);

```

### 4\. 修改表结构

根据实验要求，对表结构进行修改：

#### 复制 `student` 表

```sql
CREATE TABLE student_copy1 AS SELECT * FROM student;

```

#### 增加 “入学时间” 列

```sql
ALTER TABLE student_copy1 ADD COLUMN entry_date DATE;

```

#### 修改 `sdept` 字段长度

```sql
ALTER TABLE student_copy1 MODIFY COLUMN sdept CHAR(20);

```

#### 删除 `speciality` 字段

```sql
ALTER TABLE student_copy1 DROP COLUMN speciality;

```

#### 删除 `student_copy1` 表

```sql
DROP TABLE student_copy1;

```


