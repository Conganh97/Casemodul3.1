CREATE DATABASE OrderManager;
use ordermanager;
CREATE TABLE user (
iduser int(5) primary key auto_increment,
tk varchar(50) NOT NULL,
mk varchar(50) NOT NULL,
chucvu varchar(50) NOT NULL,
sdt varchar(15) not null,
ten varchar (50) not null,
gioitinh varchar (50) not null,
diachi varchar (50) not null
);
	
CREATE TABLE SanPham (
idsp int primary key auto_increment,
tensp varchar(50) NOT NULL,
dvt varchar(25),
mota varchar(50),
gia float,
img varchar (255),
loaisp varchar (255)
);

CREATE TABLE HoaDon (
idhd int primary key auto_increment,
ngxuathd date,
iduser int,
trigia float default 0,
foreign key(iduser) references user(iduser)
);

CREATE TABLE cthd(
idhd int,
idsp int,
sl int,
primary key(idhd, idsp),
foreign key(idhd) references HOADON(idhd),
foreign key(idsp) references SANPHAM(idsp));

insert into user values (1,'admin','admin','admin','0394935883','Cong Anh','Nam','Tay Ho'),
						(2,'conganh','conganh123','khach','0123456789','Bui Cong Anh','Nam','Ba Dinh');
                        
insert into sanpham values (1,'Hawallian','psc','OK',20,'Anh','Pizza'),
						   (2,'Beef Burger','psc','OK',15,'Anh','Burger'),
                           (3,'Marinara','psc','',20,'Anh','Pizza'),
                           (4,'Beef Pasta','psc','',18,'Anh','Pasta'),
                           (5,'French Fries','psc','',10,'Anh','Fries'),
                           (6,'Classical','psc','',15,'Anh','Pizza'),
                           (7,'Chicken Burger','psc','',12,'Anh','Burger'),
                           (8,'Fish Burger','psc','',14,'Anh','Burger'),
                           (9,'Tomato Pasta','psc','',10,'Anh','Pasta');

drop trigger if exists tgSetTriGia;                           
DELIMITER $$
CREATE TRIGGER tgSetTriGia
 after insert on cthd
 for each row
 BEGIN
	update hoadon 
    set trigia = trigia + (new.sl * (select gia from sanpham where idsp = new.idsp)) where idhd = new.idhd;
End;
$$

drop trigger if exists tgDeleteTriGia;

DELIMITER $$
CREATE TRIGGER tgDeleteTriGia
 after delete on cthd
 for each row
 BEGIN
	update hoadon 
    set trigia = trigia - (old.sl * (select gia from sanpham where idsp = old.idsp)) where idhd = old.idhd;
End;
$$

DELIMITER $$
CREATE TRIGGER tgUpdateTriGia
 after update on cthd
 for each row
 BEGIN
	update hoadon 
    set trigia = trigia + (new.sl - old.sl) * (select gia from sanpham where idsp = old.idsp) where idhd = old.idhd ;
End;
$$

insert into hoadon values (1,'2022-07-06',2,0) , (2,'2022-07-06',2,0);
insert into cthd values (1,2,3),(1,3,5);

ALTER TABLE `ordermanager`.`user` 
ADD UNIQUE INDEX `tk_UNIQUE` (`tk` ASC) VISIBLE;