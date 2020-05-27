#SQL Command untuk database Aplikasi Pemesanan Tiket Kereta


connect reservation/manager;

create table admin6(uname varchar(40) primary key,name varchar(40),
	pword varchar(50),mail_id varchar(60),phone_no varchar(12));

create table train6(tr_no int primary key,tr_name varchar(70),
	from_stn varchar(20),to_stn varchar(20),available int,fare int);


create table register(uname varchar(40) primary key,pword varchar(50),
	fname varchar(40),lname varchar(40),
	addr varchar(100), phno varchar(12), mailid varchar(60));


insert into admin6 values('admin','admin','admin','admin@train.com','9874561230');

insert into train6 values(01,'Ambarawa Express','Jakarta','Semarang',152,200000);

insert into train6 values(02,'Argo Lawu','Jakarta','Surabaya',182,300000);

insert into register values('eben','password','hizkia','eben','jakarta','082298840430','williamhizkia@gmail.com');

commit;
