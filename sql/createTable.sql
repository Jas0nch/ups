create database upsdb;
use upsdb;

create table upsuser
(
    id       int primary key not null auto_increment,
    username varchar(36)     null,
    password varchar(36)     null,
    role     varchar(36)     null
);


create table space
(
    ID       varchar(36) primary key,
    spaceNum int,
    type     enum ('regular','electric','handicap'),
    zoneID   varchar(36)
);

create table zone
(
    ID       varchar(36) primary key,
    spaceNum int,
    name     varchar(36),
    startNum int,
    lotID    varchar(36)
);

create table lot
(
    ID       varchar(36) primary key,
    spaceNum int,
    name     varchar(36),
    address  varchar(36),
    startNum int
);

create table vehicle
(
    carNum       varchar(36) primary key,
    manufacturer varchar(36),
    model        varchar(36),
    year         varchar(36),
    color        varchar(36),
    licensePlate varchar(36) unique

);

create table vpermit
(
    spaceNum   int,
    startTime  DATETIME,
    lotID      varchar(36),
    duration   int,
    permitType enum ('Visitor',
        'Student',
        'Employee'),
    identifier varchar(36) unique,
    uuid       varchar(36) primary key,
    spaceType  enum ('regular','electric','handicap'),
    carNum     varchar(36),
    startDate  DATETIME
);

create table nvpermit
(
    univid     varchar(36),
    carNum2    varchar(36),
    permitType enum ('Visitor',
        'Student',
        'Employee'),
    identifier varchar(36) unique,
    uuid       varchar(36) primary key,
    spaceType  enum ('regular','electric','handicap'),
    carNum     varchar(36),
    startDate  DATETIME
);