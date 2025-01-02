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