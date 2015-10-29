use kailz;
drop table if exists inventory;
create table inventory (
  empId int unsigned not null auto_increment,
  itemName varchar(24) not null,
  lastName varchar(24) not null,
  firstName varchar(18) not null,
  homePhone varchar(14) not null,
  quantity int not null,
  price double not null,
  primary key(empId)
);
