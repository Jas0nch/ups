INSERT INTO upsdb.upsuser (id, username, password, role)
VALUES (1, 'student', 's', 'student');
INSERT INTO upsdb.upsuser (id, username, password, role)
VALUES (2, 'admin', 'a', 'admin');
INSERT INTO upsdb.upsuser (id, username, password, role)
VALUES (3, 'employee', 'e', 'employee');


INSERT INTO upsdb.lot(ID, name, Address, spaceNum)
VALUES ('1', 'Freedom Lot', '2105 Daniel Allen St, NC 27505', '150');
INSERT INTO upsdb.lot(ID, name, Address, spaceNum)
VALUES ('2', 'Premiere Lot', '2108 McKent St, NC 27507', '200');
INSERT INTO upsdb.lot(ID, name, Address, spaceNum)
VALUES ('3', 'Justice Lot', '2704 Ben Clark St, NC 26701', '175');

	
INSERT INTO upsdb.vehicle(carNum, manufacturer, model, year, color, licensePlate)
VALUES ('AKL1732', 'Tesla', 'model X', '2019', 'Silver', 'AKL1732');
INSERT INTO upsdb.vehicle(carNum, manufacturer, model, year, color, licensePlate)
VALUES ('CDF5731', 'Toyota', 'Camry', '2018', 'Red', 'CDF5731');
INSERT INTO upsdb.vehicle(carNum, manufacturer, model, year, color, licensePlate)
VALUES ('UGY9123', 'Nissan', 'Maxima', '2015', 'Black', 'UGY9123');
INSERT INTO upsdb.vehicle(carNum, manufacturer, model, year, color, licensePlate)
VALUES ('TRK1093', 'Kia', 'Rio', '2017', 'Blue', 'TRK1093');
INSERT INTO upsdb.vehicle(carNum, manufacturer, model, year, color, licensePlate)
VALUES ('UWA1118', 'Audi', 'Q3', '2016', 'White', 'UWA1118');
INSERT INTO upsdb.vehicle(carNum, manufacturer, model, year, color, licensePlate)
VALUES ('UGB9020', 'Chevrolet', 'Cruze', '2014', 'Silver', 'UGB9020');
INSERT INTO upsdb.vehicle(carNum, manufacturer, model, year, color, licensePlate)
VALUES ('VTZ87543', 'Nissan', 'LEAF', '2018', 'Black', 'VTZ87543');
INSERT INTO upsdb.vehicle(carNum, manufacturer, model, year, color, licensePlate)
VALUES ('TIR3487', 'BMW', 'X5', '2017', 'White', 'TIR3487');
INSERT INTO upsdb.vehicle(carNum, manufacturer, model, year, color, licensePlate)
VALUES ('RPU1824', 'Honda', 'Odyssey', '2016', 'Blue', 'RPU1824');
INSERT INTO upsdb.vehicle(carNum, manufacturer, model, year, color, licensePlate)
VALUES ('NEV9889', 'Hyundai', 'Elantra', '2011', 'Red', 'NEV9889');
INSERT INTO upsdb.vehicle(carNum, manufacturer, model, year, color, licensePlate)
VALUES ('KTP2003', 'Acura', 'RDX', '2009', 'Black', 'KTP2003');

INSERT INTO upsdb.citation(id, carNum, model, color, date, lotID, time, type, status) 
VALUES ('10001', 'TRK1093',  'Rio', 'Blue', '2020-08-14 14:40:00', '3', '2020-08-14 14:40:00', '2', '1');
INSERT INTO upsdb.citation(id, carNum, model, color, date, lotID, time, type, status) 
VALUES ('10002', 'UGY9123', 'Maxima', 'Black', '2020-08-17 12:55:00', '3', '2020-08-17 12:55:00', '2', '2');
INSERT INTO upsdb.citation(id, carNum, model, color, date, lotID, time, type, status) 
VALUES ('10003', 'AKL1732', 'model X', 'Silver', '2020-08-17 13:00:00', '3', '2020-08-17 13:00:00', '2', '2');
INSERT INTO upsdb.citation(id, carNum, model, color, date, lotID, time, type, status) 
VALUES ('10004', 'NEV9889', 'Elantra', 'Red', '2020-09-10 15:50:00', '3', '2020-09-10 15:50:00', '1', '2');
INSERT INTO upsdb.citation(id, carNum, model, color, date, lotID, time, type, status) 
VALUES ('10005', 'PTL5642', 'Sentra', 'Black', '2020-09-14 10:05:00', '1', '2020-09-14 10:05:00', '3', '1');
INSERT INTO upsdb.citation(id, carNum, model, color, date, lotID, time, type, status) 
VALUES ('10006', 'TRK1093', 'Rio', 'Blue', '2020-09-21 14:00:00', '2', '2020-09-21 14:00:00', '2', '2');


INSERT INTO upsdb.vpermit(permitType, uuid, identifier, carNum, Startdate, Starttime, duration, spacetype, spaceNum)  
VALUES ('1', '20V0001A', '20V0001A', 'CDF5731', '2020-08-12 14:00:00', '2020-08-12 14:00:00', '2', '1', '200');
INSERT INTO upsdb.vpermit(permitType, uuid, identifier, carNum, Startdate, Starttime, duration, spacetype, spaceNum)  
VALUES ('1', '20V0012B', '20V0012B', 'TRK1093', '2020-08-14 11:00:00', '2020-08-14 11:00:00', '3', '1', '160');
INSERT INTO upsdb.vpermit(permitType, uuid, identifier, carNum, Startdate, Starttime, duration, spacetype, spaceNum)  
VALUES ('1', '20V0015J', '20V0015J', 'UGY9123', '2020-08-17 10:10:00', '2020-08-17 10:10:00', '2', '3', '151');
INSERT INTO upsdb.vpermit(permitType, uuid, identifier, carNum, Startdate, Starttime, duration, spacetype, spaceNum)    
VALUES ('1', '20V0021L', '20V0021L', 'AKL1732',  '2020-08-17 11:45:00', '2020-08-17 11:45:00', '1', '2', '173');
INSERT INTO upsdb.vpermit(permitType, uuid, identifier, carNum, Startdate, Starttime, duration, spacetype, spaceNum) 
VALUES ('1', '20V0026P', '20V0026P', 'UWA1118', '2020-08-19 14:50:00', '2020-08-19 14:50:00', '2', '3',  '153');
INSERT INTO upsdb.vpermit(permitType, uuid, identifier, carNum, Startdate, Starttime, duration, spacetype, spaceNum)  
VALUES ('1', '20V0025B', '20V0025B', 'TRK1093', '2020-09-21 09:30:00', '2020-09-21 09:30:00', '4', '1', '200');

INSERT INTO upsdb.nvpermit(uuid, permitType, carNum, identifier, startdate, spaceType, univid) 
VALUES ('20B0001B', '3', 'VTZ87543', '20B0001B', '2020-08-10 00:00:00', '2', '1007999');
INSERT INTO upsdb.nvpermit(uuid, permitType, carNum, identifier, startdate, spaceType, univid) 
VALUES ('20CS001C', '3', 'UGB9020', '20CS001C', '2020-08-15 00:00:00', '3', '1006003');
INSERT INTO upsdb.nvpermit(uuid, permitType, carNum, carNum2, identifier, startdate, permitType, spaceType, univid) 
VALUES ('20D0021D', '3', 'TIR3487', 'RPU1824', '20D0021D', '2020-07-10 00:00:00', '1', '1006020');
INSERT INTO upsdb.nvpermit(uuid, permitType, carNum, identifier, startdate, spaceType, univid) 
VALUES ('20AS016S', '3', 'NEV9889', '20AS016S', '2020-09-01 00:00:00', '1', '100615');
INSERT INTO upsdb.nvpermit(uuid, permitType, carNum, identifier, startdate, spaceType, univid) 
VALUES ('20A0052A', '3', 'KTP2003', '20A0052A', '2020-07-29 00:00:00', '1', '1006022');
