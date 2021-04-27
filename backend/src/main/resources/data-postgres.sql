insert into patient (id, name, surname, email, password, address, phone_num, points, category) values (10, 'Filip', 'Volaric', 'filip.kresa@gmail.com', 'fickos123', 4, '0641345948', 15, 'GOLD');
insert into patient (id, name, surname, email, password, address, phone_num, points, category) values (11, 'Svetozar', 'Vulin', 'svetozar.vulin@gmail.com', 'sveta123', 1, '065123123', 12, 'GOLD');
insert into patient (id, name, surname, email, password, address, phone_num, points, category) values (12, 'Uros', 'Petric', 'uki.tricpe@gmail.com', 'urosplatinium', 3, '0651344891', 22, 'PLATINUM');
insert into patient (id, name, surname, email, password, address, phone_num, points, category) values (13, 'Veljko', 'Kosanovic', 'cika.ljave@gmail.com', 'ljave123', 2, '061156678', 9, 'SILVER');
insert into patient (id, name, surname, email, password, address, phone_num, points, category) values (14, 'Pera', 'Peric', 'pera.peric@gmail.com', 'pera123', 4, '022400400', 1, 'BRONZE');

insert into address (street, number, city, country) values ('JNA', 78, 'Ruma', 'Srbija');
insert into address (street, number, city, country) values ('Glavna', 123, 'Sremska Mitrovica', 'Srbija');
insert into address (street, number, city, country) values ('Bulevar', 55, 'Beograd', 'Srbija');
insert into address (street, number, city, country) values ('Nikole tesle', 71, 'Ruma', 'Srbija');
insert into address (street, number, city, country) values ('Doktorska', 100, 'Beograd', 'Srbija');

insert into pharmacy (name, address, description, rating, pharmacist_price) values ('Apoteka1', 1, 'opis1', 7, 1000);
insert into pharmacy (name, address, description, rating, pharmacist_price) values ('Apoteka2', 2, 'opis2', 6, 2000);
insert into pharmacy (name, address, description, rating, pharmacist_price) values ('Apoteka3', 3, 'opis3', 9, 3000);

insert into medicine(name, type, specification) values ('Lek1', 1, 'neeekaaa speeeec');
insert into medicine(name, type, specification) values ('Lek2', 2, 'sdfsefs');
insert into medicine(name, type, specification) values ('Lek3', 2, 'uiyuoirtl');

insert into pharmacy_medicines (pharmacy_id, medicine_id, price, quantity) values (1, 1, 100, 100);
insert into pharmacy_medicines (pharmacy_id, medicine_id, price, quantity) values (1, 2, 120, 100);
insert into pharmacy_medicines (pharmacy_id, medicine_id, price, quantity) values (1, 3, 90, 100);
insert into pharmacy_medicines (pharmacy_id, medicine_id, price, quantity) values (2, 3, 70, 10);

insert into dermatologist (id, name, surname, email, password, address, phone_num) values (1, 'Mika', 'Mikic', 'mika22@gmail.com', 'mika123', 5, '011100100');
insert into dermatologist (id, name, surname, email, password, address, phone_num) values (2, 'Djuka', 'Djukic', 'djuka22@gmail.com', 'djuka123', 5, '011101101');
insert into dermatologist (id, name, surname, email, password, address, phone_num) values (3, 'Ivan', 'Ivanovic', 'iivan@gmail.com', 'ivan123', 5, '011102102');
insert into dermatologist (id, name, surname, email, password, address, phone_num) values (4, 'Nikola', 'Nikolic', 'nikolicaprikolica@gmail.com', 'nidza123', 5, '011103103');

insert into pharmacist (id, name, surname, email, password, address, phone_num, rating, pharmacy_id) values (5, 'Filip', 'Filipovic', 'filipfilip@gmail.com', 'fica123', 5, '011200200', 8, 3);
insert into pharmacist (id, name, surname, email, password, address, phone_num, rating, pharmacy_id) values (6, 'Djordje', 'Volas', 'djokica@gmail.com', 'djoka123', 5, '011201201', 7, 1);
insert into pharmacist (id, name, surname, email, password, address, phone_num, rating, pharmacy_id) values (7, 'Stefan', 'Stefanovic', 'stefke@gmail.com', 'stefi123', 5, '011202202', 6, 1);
insert into pharmacist (id, name, surname, email, password, address, phone_num, rating, pharmacy_id) values (8, 'Marko', 'Markovic', 'markelof@gmail.com', 'mare123', 5, '011203203', 9, 2);

insert into pharmacy_dermatologists (pharmacy_id, dermatologist_id) values (1,1);
insert into pharmacy_dermatologists (pharmacy_id, dermatologist_id) values (1,2);
insert into pharmacy_dermatologists (pharmacy_id, dermatologist_id) values (2,3);
insert into pharmacy_dermatologists (pharmacy_id, dermatologist_id) values (2,1);

insert into pharmacy_admin (id, name, surname, email, password, address, phone_num, pharmacy_id) values (15, 'Lazar', 'Lazarevic', 'laske@gmail.com', 'laza123', 5, '011203203', 1);
insert into pharmacy_admin (id, name, surname, email, password, address, phone_num, pharmacy_id) values (16, 'Mihajlo', 'Mihajlovic', 'mihajlomikimiki@gmail.com', 'miki123', 4, '022113113', 2);
insert into pharmacy_admin (id, name, surname, email, password, address, phone_num, pharmacy_id) values (17, 'Paun', 'Paunovic', 'paun@gmail.com', 'paun123', 3, '022213213', 3);
insert into pharmacy_admin (id, name, surname, email, password, address, phone_num, pharmacy_id) values (18, 'Zeljko', 'Zeljkovic', 'zelje@gmail.com', 'zelje123', 2, '022213213', 1);

insert into doctor_terms (doctor_id, pharmacy_id, start, finish) values (1, 1, '2021-05-05 16:00:00', '2021-05-05 17:00:00');
insert into doctor_terms (doctor_id, pharmacy_id, start, finish) values (1, 1, '2021-05-05 18:00:00', '2021-05-05 18:30:00');
insert into doctor_terms (doctor_id, pharmacy_id, start, finish) values (2, 1, '2021-05-05 16:00:00', '2021-05-05 17:00:00');
insert into doctor_terms (doctor_id, pharmacy_id, start, finish) values (1, 2, '2021-05-05 15:00:00', '2021-05-05 15:30:00');
insert into doctor_terms (doctor_id, pharmacy_id, start, finish) values (1, 2, '2021-04-28 15:00:00', '2021-04-28 16:00:00');
insert into doctor_terms (doctor_id, pharmacy_id, start, finish) values (1, 2, '2021-04-29 16:00:00', '2021-04-29 16:30:00');
insert into doctor_terms (doctor_id, pharmacy_id, start, finish) values (1, 2, '2021-04-28 16:30:00', '2021-04-28 17:00:00');
insert into doctor_terms (doctor_id, pharmacy_id, start, finish) values (1, 2, '2021-04-29 17:00:00', '2021-04-29 18:00:00');
insert into doctor_terms (doctor_id, pharmacy_id, start, finish) values (1, 2, '2021-04-30 18:00:00', '2021-04-30 19:00:00');
insert into doctor_terms (doctor_id, pharmacy_id, start, finish) values (1, 2, '2021-04-30 19:00:00', '2021-04-30 20:00:00');

insert into visit (patient_id, doctor_id, start_time, finish_time) values (10, 1, '2021-04-20 16:00:00', '2021-04-20 16:30:00'); 
insert into visit (patient_id, doctor_id, start_time, finish_time) values (10, 1, '2021-04-16 17:00:00', '2021-04-16 18:00:00'); 
insert into visit (patient_id, doctor_id, start_time, finish_time) values (10, 1, '2021-03-20 16:00:00', '2021-03-20 16:30:00'); 
insert into visit (patient_id, doctor_id, start_time, finish_time) values (10, 1, '2021-04-16 18:40:00', '2021-04-16 19:30:00'); 
insert into visit (patient_id, doctor_id, start_time, finish_time) values (10, 1, '2021-06-07 16:00:00', '2021-06-07 16:30:00'); 
insert into visit (patient_id, doctor_id, start_time, finish_time) values (10, 2, '2021-04-27 15:00:00', '2021-04-27 15:40:00'); 
insert into visit (patient_id, doctor_id, start_time, finish_time) values (10, 2, '2021-04-28 11:00:00', '2021-04-28 11:45:00'); 
insert into visit (patient_id, doctor_id, start_time, finish_time) values (10, 1, '2021-04-21 08:40:00', '2021-04-21 09:45:00'); 

insert into visit (patient_id, doctor_id, start_time, finish_time) values (11, 3, '2021-05-05 16:00:00', '2021-05-05 17:00:00'); 
insert into visit (patient_id, doctor_id, start_time, finish_time) values (12, 1, '2021-04-18 20:50:00', '2021-04-18 22:00:00'); 
insert into visit (patient_id, doctor_id, start_time, finish_time) values (10, 3, '2021-05-05 16:00:00', '2021-05-05 17:00:00');

insert into visit (patient_id, doctor_id, start_time, finish_time) values (10, 1, '2021-04-27 01:00:00', '2021-04-27 04:00:00');
insert into visit (patient_id, doctor_id, start_time, finish_time) values (11, 1, '2021-04-27 17:30:00', '2021-04-27 18:15:00');
insert into visit (patient_id, doctor_id, start_time, finish_time) values (10, 1, '2021-04-27 18:15:00', '2021-04-27 19:00:00');
insert into visit (patient_id, doctor_id, start_time, finish_time) values (11, 1, '2021-04-27 19:00:00', '2021-04-27 20:00:00');
insert into visit (patient_id, doctor_id, start_time, finish_time) values (14, 1, '2021-04-27 20:00:00', '2021-04-27 21:00:00');
insert into visit (patient_id, doctor_id, start_time, finish_time) values (14, 1, '2021-04-27 16:00:00', '2021-04-27 17:00:00');

insert into patients_allergies (patient_id, medicine_id) values (10, 1);
insert into patients_allergies (patient_id, medicine_id) values (10, 2);
insert into patients_allergies (patient_id, medicine_id) values (12, 1);
insert into patients_allergies (patient_id, medicine_id) values (12, 2);

insert into reservations (patient_id, pharmacy_id, medicine_id, date, quantity, total_price) values (10, 1, 1, 1588456800000, 2, 200);
insert into reservations (patient_id, pharmacy_id, medicine_id, date, quantity, total_price) values (10, 2, 3, 1587852000000, 1, 70);

insert into work_hours (doctor_id, pharmacy_id, start_time, finish_time) values (1, 1, '08:00:00', '21:00:00');
insert into work_hours (doctor_id, pharmacy_id, start_time, finish_time) values (2, 1, '14:00:00', '20:00:00');
insert into work_hours (doctor_id, pharmacy_id, start_time, finish_time) values (3, 2, '10:00:00', '16:00:00');
insert into work_hours (doctor_id, pharmacy_id, start_time, finish_time) values (4, 2, '16:00:00', '22:00:00');
insert into work_hours (doctor_id, pharmacy_id, start_time, finish_time) values (1, 2, '18:00:00', '20:00:00');

insert into ROLE (name) values ('ROLE_PATIENT');
insert into ROLE (name) values ('ROLE_DERMATOLOGIST');
insert into ROLE (name) values ('ROLE_PHARMACIST');
insert into ROLE (name) values ('ROLE_LAB_ADMIN');
insert into ROLE (name) values ('ROLE_HEAD_ADMIN');

insert into user_role (user_id, role_id) VALUES (1, 2);
insert into user_role (user_id, role_id) VALUES (2, 2);
insert into user_role (user_id, role_id) VALUES (3, 2);
insert into user_role (user_id, role_id) VALUES (4, 2);
insert into user_role (user_id, role_id) VALUES (5, 3);
insert into user_role (user_id, role_id) VALUES (6, 3);
insert into user_role (user_id, role_id) VALUES (7, 3);
insert into user_role (user_id, role_id) VALUES (8, 3);
insert into user_role (user_id, role_id) VALUES (10, 1);
insert into user_role (user_id, role_id) VALUES (11, 1);
insert into user_role (user_id, role_id) VALUES (12, 1);
insert into user_role (user_id, role_id) VALUES (13, 1);
insert into user_role (user_id, role_id) VALUES (14, 1);
insert into user_role (user_id, role_id) VALUES (15, 4);
insert into user_role (user_id, role_id) VALUES (16, 4);
insert into user_role (user_id, role_id) VALUES (17, 4);
insert into user_role (user_id, role_id) VALUES (18, 4);

insert into orders (deadline) values (1619560800000);
insert into orders (deadline) values (1620165600000);

insert into order_medicines (order_id, medicine_id, quantity) values (1, 1, 5);
insert into order_medicines (order_id, medicine_id, quantity) values (1, 2, 3);
insert into order_medicines (order_id, medicine_id, quantity) values (2, 3, 1);

insert into supplier_offers (supplier_id, order_id, price, deadline) values (1, 1, 1250, 1622325600000);
insert into supplier_offers (supplier_id, order_id, price, deadline) values (2, 1, 1000, 1622412000000);
insert into supplier_offers (supplier_id, order_id, price, deadline) values (3, 1, 1500, 1622584800000);

