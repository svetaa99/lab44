insert into patient (name, surname, email, password, address, phone_num, points, category) values ('Filip', 'Volaric', 'filip.kresa@gmail.com', 'fickos123', 4, '0641345948', 15, 'GOLD');
insert into patient (name, surname, email, password, address, phone_num, points, category) values ('Svetozar', 'Vulin', 'svetozar.vulin@gmail.com', 'sveta123', 1, '065123123', 12, 'GOLD');
insert into patient (name, surname, email, password, address, phone_num, points, category) values ('Uros', 'Petric', 'uki.tricpe@gmail.com', 'urosplatinium', 3, '0651344891', 22, 'PLATINUM');
insert into patient (name, surname, email, password, address, phone_num, points, category) values ('Veljko', 'Kosanovic', 'cika.ljave@gmail.com', 'ljave123', 2, '061156678', 9, 'SILVER');
insert into patient (name, surname, email, password, address, phone_num, points, category) values ('Pera', 'Peric', 'pera.peric@gmail.com', 'pera123', 4, '022400400', 1, 'BRONZE');

insert into address (street, number, city, country) values ('JNA', 78, 'Ruma', 'Srbija');
insert into address (street, number, city, country) values ('Glavna', 123, 'Sremska Mitrovica', 'Srbija');
insert into address (street, number, city, country) values ('Bulevar', 55, 'Beograd', 'Srbija');
insert into address (street, number, city, country) values ('Nikole tesle', 71, 'Ruma', 'Srbija');
insert into address (street, number, city, country) values ('Doktorska', 100, 'Beograd', 'Srbija');

insert into pharmacy (name, address, description, rating) values ('Apoteka1', 1, 'opis1', 7);
insert into pharmacy (name, address, description, rating) values ('Apoteka2', 2, 'opis2', 6);
insert into pharmacy (name, address, description, rating) values ('Apoteka3', 3, 'opis3', 9);

insert into medicine(name, type, specification) values ('Lek1', 1, 'neeekaaa speeeec');
insert into medicine(name, type, specification) values ('Lek2', 2, 'sdfsefs');
insert into medicine(name, type, specification) values ('Lek3', 2, 'uiyuoirtl');

insert into pharmacy_medicines (pharmacy_id, medicine_id, price) values (1, 1, 100);
insert into pharmacy_medicines (pharmacy_id, medicine_id, price) values (1, 2, 120);
insert into pharmacy_medicines (pharmacy_id, medicine_id, price) values (1, 3, 90);
insert into pharmacy_medicines (pharmacy_id, medicine_id, price) values (2, 3, 70);

insert into dermatologist (id, name, surname, email, password, address, phone_num) values (1, 'Mika', 'Mikic', 'mika22@gmail.com', 'mika123', 5, '011100100');
insert into dermatologist (id, name, surname, email, password, address, phone_num) values (2, 'Djuka', 'Djukic', 'djuka22@gmail.com', 'djuka123', 5, '011101101');
insert into dermatologist (id, name, surname, email, password, address, phone_num) values (3, 'Ivan', 'Ivanovic', 'iivan@gmail.com', 'ivan123', 5, '011102102');
insert into dermatologist (id, name, surname, email, password, address, phone_num) values (4, 'Nikola', 'Nikolic', 'nikolicaprikolica@gmail.com', 'nidza123', 5, '011103103');

insert into pharmacist (id, name, surname, email, password, address, phone_num, pharmacy_id) values (1, 'Filip', 'Filipovic', 'filipfilip@gmail.com', 'fica123', 5, '011200200', 3);
insert into pharmacist (id, name, surname, email, password, address, phone_num, pharmacy_id) values (2, 'Djordje', 'Volas', 'djokica@gmail.com', 'djoka123', 5, '011201201', 1);
insert into pharmacist (id, name, surname, email, password, address, phone_num, pharmacy_id) values (3, 'Stefan', 'Stefanovic', 'stefke@gmail.com', 'stefi123', 5, '011202202', 1);
insert into pharmacist (id, name, surname, email, password, address, phone_num, pharmacy_id) values (4, 'Marko', 'Markovic', 'markelof@gmail.com', 'mare123', 5, '011203203', 2);

insert into pharmacy_dermatologists (pharmacy_id, dermatologist_id) values (1,1);
insert into pharmacy_dermatologists (pharmacy_id, dermatologist_id) values (1,2);
insert into pharmacy_dermatologists (pharmacy_id, dermatologist_id) values (2,3);
insert into pharmacy_dermatologists (pharmacy_id, dermatologist_id) values (2,1);

insert into doctor_terms (doctor_id, pharmacy_id, start, finish) values (1, 1, '2021-05-05 16:00:00', '2021-05-05 17:00:00');
insert into doctor_terms (doctor_id, pharmacy_id, start, finish) values (1, 1, '2021-05-05 18:00:00', '2021-05-05 18:30:00');
insert into doctor_terms (doctor_id, pharmacy_id, start, finish) values (2, 1, '2021-05-05 16:00:00', '2021-05-05 17:00:00');
insert into doctor_terms (doctor_id, pharmacy_id, start, finish) values (1, 2, '2021-05-05 15:00:00', '2021-05-05 15:30:00');

insert into visit (patient_id, doctor_id, start_time, finish_time) values (1, 1, '2021-04-20 16:00:00', '2021-04-20 16:30:00'); 
insert into visit (patient_id, doctor_id, start_time, finish_time) values (2, 1, '2021-04-20 17:00:00', '2021-04-20 18:00:00'); 
insert into visit (patient_id, doctor_id, start_time, finish_time) values (1, 2, '2021-04-27 15:00:00', '2021-04-27 15:40:00'); 
insert into visit (patient_id, doctor_id, start_time, finish_time) values (4, 2, '2021-04-28 11:00:00', '2021-04-28 11:45:00'); 
insert into visit (patient_id, doctor_id, start_time, finish_time) values (1, 3, '2021-05-05 16:00:00', '2021-05-05 17:00:00');

