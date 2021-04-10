DROP DATABASE IF EXISTS Florei_Import;

CREATE DATABASE Florei_Import;

USE Florei_Import;


/*FORMATO PARA INSERTAR FECHA: YYYY-mm-dd*/

/*Â¿Por que MySql Almacena fecha en ese formato?
La explicaciÃ³n es muy amplia y no lo trataremos ahora, pero lo podemos resumir en los siguientes puntos:
â€“ Evitar formatos ambiguos
â€“ Tener un formato Ãºnico y universal
â€“ Poder ser organizados de mÃ¡s a menos significativo
â€“ Estar en formato ordenable, incluso si tratÃ¡ramos a las fechas como cadenas de texto, podrÃ­amos ordenarlas alfabÃ©ticamente.

Si ingresamos una fecha que no cumpla con ese formato, simplemente provocarÃ¡ un error, ya que no sera recocido como fecha vÃ¡lida.*/


create table Usuario(
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

select*from Usuario;


create table Producto(
cod_prod  		int auto_increment primary key,
marca 			varchar(100) 	default '', 
desc_prod		varchar(200) 	not null,
uni_med_prod	char(1)			default 'U',
stk_prod		int				default 1,
pre_unit        decimal(10,2) 	default 2.00,
check(uni_med_prod in ('U','C') ), /*COMBOBOX*/
check(stk_prod >= 0)
);
insert into producto(marca,desc_prod,pre_unit,stk_prod) values 
('sieled','foco botella 38w 6500k ac 110v ac 250v',17,160),
('sieled','foco flor 38w 3650 lumens led ufo low carbon',17,180 ),
('sieled','foco emergencia led 4h duracion conector usb bat lion 1300mah',15,100),
('eraled', 'panel led 60x60 40w long live',70,50),
('eraled', 'panel led 60x60 48w long live',74,50),
('eraled', 'panel led 60x60 53w long live',78,50),
('eraled', 'panel led 120x30 40w long live',70,250),
('eraled', 'panel led 120x30 48w long live',74,250),
('eraled', 'panel led 120x30 53w long live',78,250),
('soluxled','luz de emergencia 4.2w 10h duracion 400 lumens',48,100);

select*from Producto;


create table Transportista(
cod_trans 		int auto_increment primary key,
nom_trans		varchar(100)		not null,
ape_trans		varchar(100)		default '',
direc_trans		varchar(200)	default '',
telf_trans		char(9)			default '',
ruc_trans       char(11)    	default '',
num_lic         varchar(5) 		default 00000
);

insert into transportista(nom_trans, telf_trans, num_lic) values
('rally',933623684,default),
('jesus',985887848,19577),
('Roberto',978477145,20599),
('Aldair',default,14001);


select*from Transportista;



create table Cliente_Destinatario(
cod_cli			int auto_increment primary key,
nom_cli			varchar(100) 	not null,
ape_cli			varchar(100)  	default '',
tip_doc			char(3)			default 'dni',
num_doc			varchar(12)		default '',
ruc_cli			char(11)		default '',
direc_cli		varchar(200) 	default '',
telef_cli		char(9)			default '',
email_cli		varchar(200)		default '',
check(tip_doc in ('dni','cex')) /*COMBOBOX*/
);
/*truncate table Cliente_destinatario*/
insert into cliente_destinatario(nom_cli,ape_cli,num_doc,ruc_cli,telef_cli,email_cli) values
('Robinson','Rufino Jaramillo', 44789799,10447897992,950571109,'robert05_tauro@hotmail.com'),
('Sonilda','Barzola Borja',  44623118,10446231184,955219855,'inversiones_cristiann@hotmail.com'),
('Sara','Flores Carhuas', 41450886,10414508869,946092361,'electrokely.s.a.c@gmail.com'),
('Edson','Vilca Barzola', 75672905,10756729051,927793694,'edson_15_fiel@hotmail.com'),
('negociaciones kelly',default,default,20602133088,969670443,default);

insert into cliente_destinatario(nom_cli,ape_cli,tip_doc,num_doc,telef_cli,email_cli) values
('Max','Robertino','cex',001052356,default,default),
('Placido','Martinez Campo','cex',001043328,default,default),
('Christian','Shürler','cex',002031542,default,default);



select*from Cliente_Destinatario;


create table Empresa_Remitente(
cod_emp			int auto_increment primary key,
raz_soc_emp		varchar(100)    not null,
direc_emp  		varchar(200)    default '',
ruc_emp			char(11)	   	not null unique,
email_emp		varchar(100)    	default '',
telefono        char(9)        	default '',
cod_cli			int			   	not null, /*COMBOBOX*/
foreign key (cod_cli) references Cliente_Destinatario(cod_cli),
check (ruc_emp in (select ruc_cli from Cliente_destinatario)) /*COMBOBOX*/
);
/*truncate table empresa_remitente*/
insert into empresa_remitente(raz_soc_emp,direc_emp,ruc_emp,email_emp,telefono,cod_cli) values
('Electro Comercial Maxwell','av argentina nº327 cc la bellota psje 9 Psto 3-J Lima',10447897992,'robert05_tauro@hotmail.com',950571109,1),
('ELECTROCOMERCIAL CRISTHIAN','av. argentina nº 215 cc nicolini psje 11 pto. AD-5 Lima',10446231184,'inversiones_cristiann@hotmail.com',955219855,2),
('Electro Kely SAC','psje. 11 psto. P-9 av. argentina nº 215 Lima',10414508869,'electrokely.s.a.c@gmail.com',946092361,3),
('INVERSIONES EDSON','av. argentina 215 psje. 11 tda. AB-2 cc nicolini - Lima',10756729051,'edson_15_fiel@hotmail.com',927793694,4),
('NEGOCIACIONES KELLY S.A.C','AV. ARGENTINA NRO. 215 PJE. 6, PSTO BB1 (C.C. NICOLINI) LIMA - LIMA - LIMA',20602133088,default,969670443,5);

select*from Empresa_Remitente;


create table Comprobante_Pago (
num_comp   		int auto_increment primary key,
fec_comp		date not null,
hor_comp		varchar(50) not null,
tip_comp		char(1)		default 'B',
lug_comp		varchar(100) default 'Nicolini',
cod_cli			int			not null, /*COMBOBOX*/
foreign key (cod_cli) references Cliente_Destinatario(cod_cli),
check(tip_comp in ('F','B') )/*COMBOBOX*/
);
/*truncate table comprobante_pago*/
insert into comprobante_pago(fec_comp,hor_comp,tip_comp,cod_cli) values
('2018-06-19','10:50:15','F',1),
('2018-06-19','11:30:42','F',2),
('2018-07-01','11:12:09','F',2),
('2018-07-01','12:22:03','B',6),
('2018-06-20','15:07:51','B',6),
('2018-06-20','15:43:30','F',5),
('2018-06-20','18:03:10','B',8);

select*from Comprobante_Pago;


create table Guia(
num_gui  		int auto_increment primary key,
fec_guia 		date 			not null,
fec_trasl 		date 			not null,
direc_part 		varchar(200)	default 'Centro Ferretero',
direc_lleg  	varchar(200)	not null,
motiv_trasl		char(6)			not null,
pes_tot  		decimal(5,2)	not null, 
cod_cli 		int				not null,
cod_trans 		int				not null,
cod_emp			int				not null,
foreign key (cod_trans) references Transportista(cod_trans),
foreign key (cod_emp) references Empresa_Remitente(cod_emp),
foreign key (cod_cli) references Cliente_Destinatario(cod_cli),
check (motiv_trasl in ('Vmayor','Vmenor')), /*COMBOBOX*/
check (pes_tot > 0) /*try-catch*/
);
/*truncate table Guia*/
insert into Guia(fec_guia,fec_trasl,direc_lleg,motiv_trasl,pes_tot,cod_cli,cod_emp,cod_trans) values 
('2018-06-19','2018-06-19','av argentina nº327 cc la bellota psje 9 Psto 3-J Lima','Vmenor',10,1,1,1),
('2018-06-19','2018-06-19','av. argentina nº 215 cc nicolini psje 11 pto. AD-5 Lima','Vmenor',18,2,2,1),
('2018-07-01','2018-07-01','av. argentina nº 215 cc nicolini psje 11 pto. AD-5 Lima','Vmenor',20,2,2,1),
('2018-06-22','2018-06-27','AV. ARGENTINA NRO. 215 PJE. 6, PSTO BB1 (C.C. NICOLINI) LIMA - LIMA - LIMA','Vmayor',135.50,5,5,2),
('2018-06-21','2018-06-27','av. argentina 215 psje. 11 tda. AB-2 cc nicolini - Lima','Vmayor',110.30,4,4,3);
select*from Guia;


create table Detalle_Guia_Comprobante_Producto(
num_comp    int   not null,
num_gui     int   not null,
cod_prod   	int   not null,
cant        int   not null, 
primary key (num_comp,num_gui,cod_prod),
foreign key (num_comp) references Comprobante_Pago(num_comp),/*COMBOBOX*/
foreign key (num_gui) references Guia(num_gui),/*COMBOBOX*/
foreign key (cod_prod) references Producto(cod_prod) /*COMBOBOX*/
);
/*truncate table Detalle_Guia_Comprobante_Producto*/
insert into Detalle_Guia_Comprobante_Producto values
(1,1,1,10),
(1,1,2,5),
(2,2,3,50),
(3,2,4,5),
(6,4,6,20),
(6,4,8,35),
(6,4,10,30);


select*from Detalle_Guia_Comprobante_Producto;


DELIMiTER $$
create procedure usp_validaAcceso (usr varchar(30), pas varchar(30))
begin
select * from Usuario where usua = usr and clav = pas;
end$$
DELIMiTER ;

CALL usp_validaAcceso ('danre','1001');




select * from Comprobante_Pago;
select * from  Cliente_Destinatario;
select*from Detalle_Guia_Comprobante_Producto;
select * from producto;
delimiter $$

create procedure usp_reportarcomprobante(tipo char(1), fec_ini date, fec_fin date)
begin  /*nro comp, fec y hora compr, nom cli, pedido (nomprod, cant, pago(can*pre))*/
select cp.num_comp as 'Codigo', 
concat(cp.fec_comp,' ',cp.hor_comp) as 'Registro en el sistema' ,
concat(cl.nom_cli,' ',cl.ape_cli) as 'Cliente',
p.desc_prod as 'Producto',
d.cant as 'Cantidad',
d.cant*p.pre_unit as 'Pago'
from comprobante_pago as cp 
join cliente_destinatario as cl on cp.cod_cli = cl.cod_cli
join detalle_guia_comprobante_producto as d on cp.num_comp = d.num_comp
join producto as p on d.cod_prod = p.cod_prod
where tip_comp = tipo and fec_comp between fec_ini and fec_fin
order by fec_comp desc;
end$$
delimiter ;

call usp_reportarcomprobante('f','2018-06-19','2018-06-19');






Delimiter $$
create procedure usp_ReporteComprobante (tip_uni char(1),stock1 int,stock2 int)
begin 


select * from producto where uni_med_prod = tip_uni and  stk_prod  between  stock1 and stock2;

end $$
Delimiter ;	


call usp_ReporteComprobante('U',0,100);


