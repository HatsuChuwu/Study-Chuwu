use scoresys;
create table employee
(
	id varchar(10) primary key,
	employee_name varchar(20),
  sex char(1),
	department_id varchar(36)references department(department_id),
	leader_id varchar(10)	references employee(id)
);

insert into employee values('3001','刘明强','M','D01','3001');
insert into employee values('3002','李明萍','F','D01','3001');
insert into employee values('4001','蒋明华','F','D02','4001');
insert into employee values('4002','翁明祥','M','D02','4001');
