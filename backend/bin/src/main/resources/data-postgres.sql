insert into patient (name, surname, address, category) values ('Filip', 'Volaric', 'Nikole Tesle 71/A/6', 'GOLD');
insert into patient (name, surname, address, category) values ('Svetozar', 'Vulin', 'JNA 50', 'GOLD');
insert into patient (name, surname, address, category) values ('Uros', 'Petric', 'Zabaljska 1', 'PLATINUM');
insert into patient (name, surname, address, category) values ('Veljko', 'Kosanovic', '123', 'BRONZE');
insert into patient (name, surname, address, category) values ('Pera', 'Peric', 'Pere Perića 20', 'SILVER');

insert into address (street, number, city, country) values ('JNA', 78, 'Ruma', 'Srbija');
insert into address (street, number, city, country) values ('Glavna', 123, 'Sremska Mitrovica', 'Srbija');
insert into address (street, number, city, country) values ('Bulevar', 55, 'Beograd', 'Srbija');

insert into pharmacy (name, address, description, rating) values ('Apoteka1', 1, 'opis1', 7);
insert into pharmacy (name, address, description, rating) values ('Apoteka2', 2, 'opis2', 6);
insert into pharmacy (name, address, description, rating) values ('Apoteka3', 3, 'opis3', 9);

insert into medicine(name, type, specification) values ('Lek1', 1, 'neeekaaa speeeec');
insert into medicine(name, type, specification) values ('Lek2', 2, 'sdfsefs');
insert into medicine(name, type, specification) values ('Lek3', 2, 'uiyuoirtl');

insert into pharmacy_medicines (pharmacy_id, medicine_id) values (1, 1);
insert into pharmacy_medicines (pharmacy_id, medicine_id) values (1, 2);
insert into pharmacy_medicines (pharmacy_id, medicine_id) values (1, 3);
insert into pharmacy_medicines (pharmacy_id, medicine_id) values (2, 3);