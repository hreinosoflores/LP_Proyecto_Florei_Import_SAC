drop database if exists florei_import;
create database florei_import;
use florei_import;
create table usuario(
codus 	 int auto_increment primary key,
nom_usua varchar(100) not null,
ape_usua varchar(100) not null,
usua	 char(5) not null unique,
clav	 varchar(30) not null
);
insert into usuario(nom_usua,ape_usua,usua,clav) values
('Daniel','Reinoso','danre','1001'),
('Sandro','Flores','sanfl','1002'),
('Angello','Aguilar','angag','1003'),
('Clisman','Campos','ccamp','1001');
select*from usuario;
create table producto(
cod_prod  		int auto_increment primary key,
marca 			varchar(100) 	default '', 
desc_prod		varchar(200) 	not null,
uni_med_prod	char(1)			default 'u',
stk_prod		int				default 1,
pre_unit        decimal(10,2) 	default 0.00,
pes_unit		decimal(10,2) 	default 0.00,
usu_creador_prod	int			not null,
foreign key (usu_creador_prod) references usuario(codus),
check(uni_med_prod in ('u','c') ), /*combobox*/
check(stk_prod >= 0)
);
insert into producto(marca,desc_prod,pre_unit,stk_prod,usu_creador_prod) values 
('Sieled','Foco botella 38W 6500K AC 110V AC 250V',17,160,1),
('Sieled','Foco flor 38W 3650 lumens LED ufo low carbon',17,180,1 ),
('Sieled','Foco emergencia LED 4h duracion conector usb bat lion 1300mah',15,100,1),
('Eraled', 'Panel LED 60x60 40W long live',70,50,1),
('Eraled', 'Panel LED 60x60 48W long live',74,50,1),
('Eraled', 'Panel LED 60x60 53W long live',78,50,1),
('Eraled', 'Panel LED 120x30 40W long live',70,250,1),
('Eraled', 'Panel LED 120x30 48W long live',74,250,1),
('Eraled', 'Panel LED 120x30 53W long live',78,250,1),
('Soluxled','Luz de emergencia 4.2W 10h duracion 400 Lumens',48,100,1);
select*from producto;
create table transportista(
cod_trans 		int auto_increment primary key,
nom_trans		varchar(100)		not null,
ape_trans		varchar(100)		default '',
direc_trans		varchar(200)	default '',
telf_trans		char(9)			default '',
ruc_trans       char(11)    	default '',
num_lic         varchar(5) 		default 00000,
usu_creador_trans	int			not null,
foreign key (usu_creador_trans) references usuario(codus)
);
insert into transportista(nom_trans, telf_trans, num_lic,usu_creador_trans) values
('Rally',933623684,default,1),
('Jesus',985887848,19577,1),
('Roberto',978477145,20599,1),
('Aldair',default,14001,1);
select*from transportista;
create table cliente_destinatario(
cod_cli			int auto_increment primary key,
nom_cli			varchar(100) 	not null,
ape_cli			varchar(100)  	default '',
tip_doc			char(3)			default 'dni',
num_doc			varchar(12)		default '',
ruc_cli			char(11)		default '',
direc_cli		varchar(200) 	default '',
telef_cli		char(9)			default '',
email_cli		varchar(200)	default '',
usu_creador_cli int				not null,
foreign key (usu_creador_cli) references usuario(codus),
check(tip_doc in ('dni','cex')) /*combobox*/
);
/*truncate table cliente_destinatario*/
insert into cliente_destinatario(nom_cli,ape_cli,num_doc,ruc_cli,telef_cli,email_cli,usu_creador_cli) values
('Robinson','Rufino Jaramillo', 44789799,10447897992,950571109,'robert05_tauro@hotmail.com',2),
('Sonilda','Barzola Borja',  44623118,10446231184,955219855,'inversiones_cristiann@hotmail.com',2),
('Sara','Flores Carhuas', 41450886,10414508869,946092361,'electrokely.s.a.c@gmail.com',2),
('Edson','Vilca Barzola', 75672905,10756729051,927793694,'edson_15_fiel@hotmail.com',2),
('Negociaciones Kelly',default,default,20602133088,969670443,default,2);
insert into cliente_destinatario(nom_cli,ape_cli,tip_doc,num_doc,telef_cli,email_cli,usu_creador_cli) values
('Max','Robertino','cex',001052356,default,default,2),
('Placido','Martinez Campo','cex',001043328,default,default,2),
('Christian','Shürler','cex',002031542,default,default,2);
select*from cliente_destinatario;
create table empresa_remitente(
cod_emp			int auto_increment primary key,
raz_soc_emp		varchar(100)    not null,
direc_emp  		varchar(200)    default '',
ruc_emp			char(11)	   	not null unique,
email_emp		varchar(100)    default '',
telefono        char(9)        	default '',
usu_creado_emp 	int				not null,
foreign key (usu_creado_emp) references usuario(codus),
check (ruc_emp in (select ruc_cli from cliente_destinatario)) /*combobox*/
);
/*truncate table empresa_remitente*/
insert into empresa_remitente(raz_soc_emp,direc_emp,ruc_emp,email_emp,telefono,usu_creado_emp) values
('Electro Comercial Maxwell','Av Argentina nº327 CC la bellota psje 9 psto 3-j Lima',10447897992,'robert05_tauro@hotmail.com',950571109,2),
('Electrocomercial Cristhian','Av. Argentina nº 215 CC Nicolini psje 11 pto. ad-5 Lima',10446231184,'inversiones_cristiann@hotmail.com',955219855,2),
('Electro Kely SAC','psje. 11 psto. p-9 Av. Argentina nº 215 Lima',10414508869,'electrokely.s.a.c@gmail.com',946092361,2),
('Inversiones Edson','Av. Argentina 215 psje. 11 tda. ab-2 CC Nicolini - Lima',10756729051,'edson_15_fiel@hotmail.com',927793694,2),
('Negociaciones Kelly SAC','Av. Argentina nro. 215 pje. 6, psto bb1 (CC Nicolini) Lima - Lima - Lima',20602133088,default,969670443,2);
select*from empresa_remitente;
create table comprobante_pago (
num_comp   		int auto_increment primary key,
fec_comp		datetime 		default CURRENT_TIMESTAMP,
tip_comp		char(1)			default 'b',
lug_comp		varchar(100) 	default 'Nicolini',
cod_cli				int			not null, /*combobox*/
usu_creador_comp	int			not null,
foreign key (cod_cli) references cliente_destinatario(cod_cli),
foreign key (usu_creador_comp) references usuario(codus),
check(tip_comp in ('f','b') )/*combobox*/
);
/*truncate table comprobante_pago*/
insert into comprobante_pago(tip_comp,cod_cli,usu_creador_comp) values
('f',1,2),
('f',2,2),
('f',2,3),
('b',6,3),
('b',6,3),
('f',5,3),
('b',8,3);
select*from comprobante_pago;
create table guia(
num_gui  		int auto_increment primary key,
fec_guia 		datetime 		default CURRENT_TIMESTAMP,
fec_trasl 		datetime 		not null,
direc_part 		varchar(200)	default 'Centro Ferretero',
direc_lleg  	varchar(200)	not null,
motiv_trasl		char(6)			not null,
cod_cli 		int				not null,/*combobox*/
cod_trans 		int				not null,/*combobox*/
cod_emp			int				not null,/*combobox*/
usu_creador_gui	int				not null,
foreign key (cod_cli) references cliente_destinatario(cod_cli),
foreign key (cod_trans) references transportista(cod_trans),
foreign key (cod_emp) references empresa_remitente(cod_emp),
foreign key (usu_creador_gui) references usuario(codus),
check (motiv_trasl in ('vmayor','vmenor')), /*combobox*/
check (pes_tot > 0) /*try-catch*/
);
/*truncate table guia*/
insert into guia(fec_trasl,direc_lleg,motiv_trasl,cod_cli,cod_emp,cod_trans,usu_creador_gui) values 
('2018-06-19 15:00:00','Av Argentina nº327 CC la bellota psje 9 psto 3-j Lima','vmenor',1,1,1,3),
('2018-06-19 15:00:00','Av. Argentina nº 215 CC Nicolini psje 11 pto. ad-5 Lima','vmenor',2,2,1,3),
('2018-07-01 15:00:00','Av. Argentina nº 215 CC Nicolini psje 11 pto. ad-5 Lima','vmenor',2,2,1,3),
('2018-06-27 15:00:00','Av. Argentina nro. 215 pje. 6, psto bb1 (c.c. Nicolini) Lima - Lima - Lima','vmayor',5,5,2,3),
('2018-06-27 15:00:00','Av. Argentina 215 psje. 11 tda. ab-2 CC Nicolini - Lima','vmayor',4,4,3,3);
select*from guia;
create table detalle_comp(
num_comp    int   not null,
cod_prod   	int   not null,
cant        int   not null, 
primary key (num_comp,cod_prod),
foreign key (num_comp) references comprobante_pago(num_comp),
foreign key (cod_prod) references producto(cod_prod)
);
/*truncate table detalle_comp*/
insert into detalle_comp values
(1,1,10),
(1,2,5),
(2,3,50),
(3,4,5),
(6,6,20),
(6,8,35),
(6,10,30);
select*from detalle_comp;
create table detalle_gui(
num_gui     int   not null,
cod_prod   	int   not null,
cant        int   not null, 
primary key (num_gui,cod_prod),
foreign key (num_gui) references guia(num_gui),
foreign key (cod_prod) references producto(cod_prod)
);
/*truncate table detalle_gui*/
insert into detalle_gui values
(1,1,10),
(1,2,5),
(2,3,50),
(2,4,5),
(3,6,20),
(3,8,35),
(3,10,30);
select*from detalle_gui;

delimiter $$
create procedure usp_validaacceso (usr varchar(30), pas varchar(30))
begin
select * from usuario where usua = usr and clav = pas;
end$$
delimiter ;
call usp_validaacceso ('danre','1001');

create view vw_comprobante_tuneado as
select cp.num_comp as 'Código', 
cp.tip_comp as 'Tipo',
cp.fec_comp as 'FecComp',
date_format(cp.fec_comp,'%d/%m/%Y %T') as 'Fecha Registro' ,
concat(u.nom_usua,' ', u.ape_usua) as 'Creador',
concat(cl.nom_cli,' ',cl.ape_cli) as 'Cliente'
from comprobante_pago as cp 
join cliente_destinatario as cl on cp.cod_cli = cl.cod_cli
join usuario as u on cp.usu_creador_comp = u.codus;

select * from vw_comprobante_tuneado where date(FecComp) <= '2021-04-11';

delimiter $$
create procedure usp_reportarcomprobante(ty char(1), fec_ini varchar(20), fec_fin varchar(20))
begin 
if ty='' and fec_ini='' and fec_fin='' then select * from vw_comprobante_tuneado;
elseif ty='' and fec_ini='' then select * from vw_comprobante_tuneado where date(FecComp) <= fec_fin;
elseif ty='' and fec_fin='' then select * from vw_comprobante_tuneado where date(FecComp) >= fec_ini;
elseif ty='' then select * from vw_comprobante_tuneado where date(FecComp) between fec_ini and fec_fin;
elseif fec_ini='' and fec_fin='' then select * from vw_comprobante_tuneado where Tipo=ty;
elseif fec_ini='' then select * from vw_comprobante_tuneado where Tipo=ty and date(FecComp) <= fec_fin;
elseif fec_fin='' then select * from vw_comprobante_tuneado where Tipo=ty and date(FecComp) >= fec_ini;
else select * from vw_comprobante_tuneado where Tipo=ty and date(FecComp) between fec_ini and fec_fin;
end if;
end$$
delimiter ;
call usp_reportarcomprobante('','','');
call usp_reportarcomprobante('','','2021-04-10');
call usp_reportarcomprobante('','2021-04-11','');
call usp_reportarcomprobante('','2021-04-11','2021-04-11');
call usp_reportarcomprobante('b','','');
call usp_reportarcomprobante('b','','2021-04-11');
call usp_reportarcomprobante('b','2021-04-11','');
call usp_reportarcomprobante('b','2021-04-11','2021-04-11');

delimiter $$
create procedure usp_reportarproducto (tip_uni char(1),stock1 int,stock2 int,desc_cont varchar(50))
begin 
if tip_uni='' and stock1=0 and stock2=0 and desc_cont='' then select * from producto;
elseif tip_uni='' and stock1=0 and stock2=0 then select * from producto where desc_prod like concat('%',desc_cont,'%');
elseif tip_uni='' and stock1=0 and desc_cont='' then select * from producto where stk_prod <= stock2;
elseif tip_uni='' and stock1=0 then select * from producto where stk_prod <= stock2 and desc_prod like concat('%',desc_cont,'%');
elseif tip_uni='' and stock2=0 and desc_cont='' then select * from producto where stk_prod >= stock1;
elseif tip_uni='' and stock2=0 then select * from producto where stk_prod >= stock1 and desc_prod like concat('%',desc_cont,'%');
elseif tip_uni='' and desc_cont='' then select * from producto where stk_prod  between  stock1 and stock2;
elseif tip_uni='' then select * from producto where stk_prod  between  stock1 and stock2 and desc_prod like concat('%',desc_cont,'%');
elseif stock1=0 and stock2=0 and desc_cont='' then select * from producto where uni_med_prod = tip_uni;
elseif stock1=0 and stock2=0 then select * from producto where uni_med_prod = tip_uni and desc_prod like concat('%',desc_cont,'%');
elseif stock1=0 and desc_cont='' then select * from producto where uni_med_prod = tip_uni and stk_prod <= stock2;
elseif stock1=0 then select * from producto where uni_med_prod = tip_uni and stk_prod <= stock2 and desc_prod like concat('%',desc_cont,'%');
elseif stock2=0 and desc_cont='' then select * from producto where uni_med_prod = tip_uni and stk_prod >= stock1;
elseif stock2=0 then select * from producto where uni_med_prod = tip_uni and stk_prod >= stock1 and desc_prod like concat('%',desc_cont,'%');
elseif desc_cont='' then select * from producto where uni_med_prod = tip_uni and stk_prod  between  stock1 and stock2;
else select * from producto where uni_med_prod = tip_uni and stk_prod  between  stock1 and stock2 and desc_prod like concat('%',desc_cont,'%');
end if;
end $$
delimiter ;	

call usp_reportarproducto('',0,0,'');
call usp_reportarproducto('',0,0,'LED');
call usp_reportarproducto('',0,175,'');
call usp_reportarproducto('',0,175,'LED');
call usp_reportarproducto('',80,0,'');
call usp_reportarproducto('',80,0,'LED');
call usp_reportarproducto('',80,175,'');
call usp_reportarproducto('',80,175,'LED');
call usp_reportarproducto('b',0,0,'');
call usp_reportarproducto('b',0,0,'LED');
call usp_reportarproducto('b',0,175,'');
call usp_reportarproducto('b',0,175,'LED');
call usp_reportarproducto('b',80,0,'');
call usp_reportarproducto('b',80,0,'LED');
call usp_reportarproducto('b',80,175,'');
call usp_reportarproducto('b',80,175,'LED');