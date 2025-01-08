# MySQL eshop查询与解释

### 步骤1：生成eshop数据库

```sql
mysql -h localhost -u root -p

-- 创建数据库
create database eshop;
use eshop;

-- 创建客户表
create table shop_customer (
    shop_customer_id varchar(36) not null primary key,
    account varchar(20) not null unique,
    password varchar(50) null,
    name varchar(8) null,
    sex char(1) null,
    age tinyint null,
    tel varchar(20) null,
    email varchar(32) null,
    shipping_address varchar(100) null, -- 送货地址
    `rank` tinyint null, -- 客户星级，0 - 5级
    `status` tinyint null -- 客户状态，0=禁用，1=正常
);

-- 创建员工角色表
create table shop_role ( 
    shop_role_id varchar(36) not null primary key,
    name varchar(50) null
);

-- 创建员工表
create table shop_employee (
    shop_employee_id varchar(36) not null primary key,
    account varchar(20) not null unique,
    password varchar(50) null,
    name varchar(8) null,
    sex char(1) null,
    tel varchar(20) null,
    shop_role_id varchar(36) not null
);

-- 创建商品类别表
create table shop_goods_category (
    shop_goods_category_id varchar(36) not null primary key,
    name varchar(50) null
);

-- 创建商品表
create table shop_goods (
    shop_goods_id varchar(36) not null primary key,
    name varchar(50) not null, -- 商品名称
    brand varchar(50) null, -- 品牌
    size varchar(50) null, -- 规格
    price decimal(8,2) not null, -- 标准价格
    stock decimal(8,2) not null, -- 库存数量
    image_url varchar(50) null, -- 图片路径
    description varchar(50) null, -- 商品描述
    shop_goods_category_id varchar(36) not null
);

-- 创建订单头表
create table shop_order_head (
    shop_order_head_id varchar(36) not null primary key,
    order_no varchar(20) not null unique,
    order_date datetime null,
    audit_date datetime null,
    shipping_date datetime null,
    ammount decimal(8,2) null, -- 金额，可能是去除零头后的金额
    `status` tinyint null, -- 0=订单，1=已审核，2=已发货，3=收货结清
    note varchar(200) null, -- 订货要求
    comment varchar(200) null, -- 评论
    shop_customer_id varchar(36) not null,
    shop_employee_id_audit varchar(36) null,
    shop_employee_id_shipping varchar(36) null
);

-- 创建订单项表
create table shop_order_line (
    shop_order_line_id varchar(36) not null primary key,
    price decimal(8,2) null, -- 实际销售价格
    quantity decimal(8,2) null, -- 销售数量
    shop_goods_id varchar(36) not null,
    shop_order_head_id varchar(36) not null
);

-- 添加外键约束
alter table shop_employee add constraint fk_shop_employee_shop_role
    foreign key (shop_role_id) references shop_role (shop_role_id);

alter table shop_goods add constraint fk_shop_goods_shop_goods_category
    foreign key (shop_goods_category_id) references shop_goods_category (shop_goods_category_id);

alter table shop_order_head add constraint fk_shop_order_head_shop_customer
    foreign key (shop_customer_id) references shop_customer (shop_customer_id);

alter table shop_order_head add constraint fk_shop_order_head_shop_employee1 
    foreign key (shop_employee_id_audit) references shop_employee (shop_employee_id);

alter table shop_order_head add constraint fk_shop_order_head_shop_employee2 
    foreign key (shop_employee_id_shipping) references shop_employee (shop_employee_id);

alter table shop_order_line add constraint fk_shop_order_line_shop_goods
    foreign key (shop_goods_id) references shop_goods (shop_goods_id);

alter table shop_order_line add constraint fk_shop_order_line_shop_order_head
    foreign key (shop_order_head_id) references shop_order_head (shop_order_head_id);

-- 插入客户数据
insert into shop_customer values ('C001','zhouym','123','周永明','M',46,'13912341234','zhouym@qq.com','江苏省东台市经济园货场东巷23号',3,1);
insert into shop_customer values ('C002','yuanxw','123','袁晓伟','M',48,'12912341235','yuanxw@qq.com','江苏南京六合县高桥镇田夏家',4,1);

-- 插入员工角色数据
insert into shop_role values ('R01','经理');
insert into shop_role values ('R02','仓库管理');

-- 插入员工数据
insert into shop_employee values ('E01','wuqq','123','吴琪琪','F','13912341234','R01');
insert into shop_employee values ('E02','zhoukw','123','周可望','M','12912341235','R02');
insert into shop_employee values ('E03','wangpl','123','王培林','M','11912341236','R02');

-- 插入商品类别数据
insert into shop_goods_category values ('C01','电器类');
insert into shop_goods_category values ('C02','户外类');

-- 插入商品数据
insert into shop_goods values ('P10001','MP3','苹果','MP3 iPod 6代2G ',345,50,'','','C01');
insert into shop_goods values ('P10002','MP3','清华紫光','T39 2G',68,50,'','','C01');
insert into shop_goods values ('P10003','MP4','台电','C520VE 8G 5寸高清',295,50,'','','C01');
insert into shop_goods values ('P10004','电子书','汉王','N510精华版',990,50,'','','C01');
insert into shop_goods values ('P10005','电子书','亚马逊','Kindle 3',1135,50,'','','C01');
insert into shop_goods values ('P10006','帐篷','阿珂姆ACME','铝杆、双人双层',188,50,'','','C02');
insert into shop_goods values ('P10007','帐篷','牧高笛','冷山2air 双人双层铝杆',368,50,'','','C02');
insert into shop_goods values ('P10008','睡袋','英国Mountain','户外露营羽绒1500g零下25度',238,50,'','','C02');
insert into shop_goods values ('P10009','睡袋','艾斯塔','户外野营超细绒抓绒睡袋',78,50,'','','C02');

-- 插入订单头数据
insert into shop_order_head values ('OH101','2011-03-12-101','2011-3-12','2011-3-15','2011-3-15',0,3,'礼盒包装','质量很好','C001','E01','E02');
insert into shop_order_head values ('OH102','2011-03-14-101','2011-3-14','2011-3-15','2011-3-15',0,3,'','发货速度很快','C002','E01','E03');
insert into shop_order_head values ('OH103','2011-03-15-101','2011-3-15','2011-3-16', null,0,1,'','','C002','E01', null);

-- 插入订单项数据
insert into shop_order_line values ('OL100001',68,1,'P10001','OH101');
insert into shop_order_line values ('OL100002',295,1,'P10003','OH101');
insert into shop_order_line values ('OL100003',990,1,'P10004','OH102');
insert into shop_order_line values ('OL100004',68,1,'P10002','OH102');
insert into shop_order_line values ('OL100005',1135,1,'P10005','OH102');
insert into shop_order_line values ('OL100006',368,1,'P10007','OH103');
insert into shop_order_line values ('OL100007',238,1,'P10008','OH103');

-- 更新订单项数据
update shop_order_line set shop_goods_id = 'P10002' where shop_order_line_id = 'OL100001';
update shop_order_line set shop_order_head_id = 'OH101' where shop_order_line_id = 'OL100003';
```

### 步骤2：查询所有客户的所有信息，按账号排序

```sql
select * from shop_customer
order by account;
```

* **解释**：
  * `select *`：选择表 `shop_customer` 中的所有列（字段）。
  * `from shop_customer`：数据来源于 `shop_customer` 表。
  * `order by account`：按照 `account` 列进行升序排序，结果按账号顺序排列。
* **说明**：`*` 是通配符，表示选择该表中的所有列。这是最常见的查询方式，适用于获取表中的完整数据。

### 步骤3：查询员工的账号、姓名和电话，并按员工工号排序

```sql
select account, name, tel from shop_employee
order by shop_employee_id;
```

* **解释**：
  * `select account, name, tel`：选择 `shop_employee` 表中的 `account`、`name` 和 `tel` 列。
  * `from shop_employee`：数据来源于 `shop_employee` 表。
  * `order by shop_employee_id`：根据 `shop_employee_id`（员工工号）进行升序排序。
* **说明**：这里不使用 `*`，而是显式列出要查询的列名。通常在需要获取特定列数据时使用。

### 步骤4：查询两种 MP3 的总库存量和总价值（价格 * 数量）

```sql
select sum(stock) as total_stock, sum(price * stock) as total_value
from shop_goods
where name like '%mp3%';
```

* **解释**：
  * `sum(stock) as total_stock`：使用 `SUM()` 聚合函数计算所有 MP3 商品的库存总量。
  * `sum(price * stock) as total_value`：计算每个商品的库存价值（`price * stock`）并求和，返回总价值。
  * `from shop_goods`：数据来源于 `shop_goods` 表。
  * `where name like '%mp3%'`：过滤出商品名中包含 "mp3" 的所有记录。
* **说明**：
  * `SUM()` 聚合函数用于求和，常用于统计数值列的总和。
  * `LIKE` 操作符用于模糊匹配，在这里匹配所有包含 "mp3" 的商品名称。`%` 是通配符，表示任意数量的字符。

### 步骤5：查询订单 OH101 的购货人信息

```sql
select sc.name as 购货人姓名, sc.tel as 购货人电话, sc.email as 购货人邮件, soh.order_date as 订货日期, sc.shipping_address as 送货地址
from shop_customer sc
join shop_order_head soh on sc.shop_customer_id = soh.shop_customer_id
where soh.order_no = '2011-03-12-101';
```

* **解释**：
  * `select sc.name, sc.tel, sc.email, soh.order_date, sc.shipping_address`：选择购货人姓名、电话、邮件、订货日期和送货地址。
  * `from shop_customer sc`：数据来源于 `shop_customer` 表，并为其起别名 `sc`。
  * `join shop_order_head soh ON sc.shop_customer_id = soh.shop_customer_id`：通过 `shop_customer_id` 字段连接 `shop_customer` 表和 `shop_order_head` 表。
  * `where soh.order_no = '2011-03-12-101'`：筛选出订单号为 '2011-03-12-101' 的记录。
* **说明**：
  * `JOIN` 用于连接两个表，通常基于一个公共字段进行关联，这里是 `shop_customer_id`。
  * `AS` 用于给列或表起别名，方便在查询中引用。

### 步骤6：查询订单 OH101 的购买产品详细信息

```sql
select sg.*
from shop_goods sg
join shop_order_line sol on sg.shop_goods_id = sol.shop_goods_id
join shop_order_head soh on sol.shop_order_head_id = soh.shop_order_head_id
where soh.order_no = '2011-03-12-101';
```

* **解释**：
  * `select sg.*`：选择 `shop_goods` 表中的所有列（字段）。
  * `from shop_goods sg`：数据来源于 `shop_goods` 表，并为其起别名 `sg`。
  * `join shop_order_line sol ON sg.shop_goods_id = sol.shop_goods_id`：通过 `shop_goods_id` 连接 `shop_goods` 表和 `shop_order_line` 表。
  * `join shop_order_head soh ON sol.shop_order_head_id = soh.shop_order_head_id`：通过 `shop_order_head_id` 连接 `shop_order_line` 和 `shop_order_head` 表。
  * `where soh.order_no = '2011-03-12-101'`：筛选出订单号为 '2011-03-12-101' 的记录。
* **说明**：
  * `JOIN` 是连接多个表的常见方法。每个 `JOIN` 都指定了连接的条件，确保只有满足条件的记录被返回。

### 步骤7：查询订单 OH101 的订货总金额信息

```sql
select sum(sol.price * sol.quantity) as 总计（人民币）
from shop_order_line sol
join shop_order_head soh on sol.shop_order_head_id = soh.shop_order_head_id
where soh.order_no = '2011-03-12-101';
```

* **解释**：
  * `SUM(sol.price * sol.quantity) AS 总计（人民币）`：计算每个产品的总金额（`price * quantity`）并求和，返回订单的总金额。
  * `from shop_order_line sol`：数据来源于 `shop_order_line` 表。
  * `join shop_order_head soh ON sol.shop_order_head_id = soh.shop_order_head_id`：通过 `shop_order_head_id` 字段连接 `shop_order_line` 表和 `shop_order_head` 表。
  * `where soh.order_no = '2011-03-12-101'`：筛选出订单号为 '2011-03-12-101' 的记录。
* **说明**：
  * `SUM()` 聚合函数用于计算总金额。
  * 该查询计算了所有商品的总金额，而不是每个商品的金额，返回的是整个订单的总金额。

### 步骤8：查询订单 OH101 的信息

```sql
select soh.note as 订货要求, e_audit.name as 审核人, soh.audit_date as 审核日期, e_ship.name as 发货人, soh.shipping_date as 发货日期
from shop_order_head soh
join shop_employee e_audit on soh.shop_employee_id_audit = e_audit.shop_employee_id
join shop_employee e_ship on soh.shop_employee_id_shipping = e_ship.shop_employee_id
where soh.order_no = '2011-03-12-101';
```

* **解释**：
  * `select soh.note, e_audit.name, soh.audit_date, e_ship.name, soh.shipping_date`：选择订单要求、审核人、审核日期、发货人和发货日期。
  * `from shop_order_head soh`：数据来源于 `shop_order_head` 表，并为其起别名 `soh`。
  * `join shop_employee e_audit ON soh.shop_employee_id_audit = e_audit.shop_employee_id`：连接 `shop_order_head` 表和 `shop_employee` 表，获取审核人的信息。
  * `join shop_employee e_ship ON soh.shop_employee_id_shipping = e_ship.shop_employee_id`：连接 `shop_order_head` 表和 `shop_employee` 表，获取发货人的信息。
  * `where soh.order_no = '2011-03-12-101'`：筛选出订单号为 '2011-03-12-101' 的记录。
* **说明**：
  * 本查询通过连接两次 `shop_employee` 表来获取不同员工（审核人和发货人）的信息。
