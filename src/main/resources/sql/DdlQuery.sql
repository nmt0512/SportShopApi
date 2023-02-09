CREATE DATABASE SportShop ON
(
name = sport_shop,
filename = 'D:\sport_shop.mdf',
size = 10mb,
maxsize = unlimited
)

USE SportShop

CREATE TABLE Item
(
ItemId int IDENTITY(1,1) PRIMARY KEY,
Code varchar(100) NOT NULL,
Name nvarchar(100) NOT NULL,
Description nvarchar(255),
CategoryCode varchar(50),
Image image,
Size nvarchar(3),
Color nvarchar(25),
Type nvarchar(30),
Quantity smallint NOT NULL CHECK(Quantity >= 0),
Price int CHECK(Price >= 1000),
CreatedDate date,
CONSTRAINT FK_Item_CategoryCode FOREIGN KEY(CategoryCode) 
	REFERENCES Category(Code)
)

CREATE TABLE Category
(
CategoryId int IDENTITY(1,1) PRIMARY KEY,
GeneralCode varchar(30),
Code varchar(50) NOT NULL UNIQUE,
Name nvarchar(50) NOT NULL,
CreatedDate date,
CONSTRAINT FK_Category_GeneralCode FOREIGN KEY(GeneralCode) 
	REFERENCES GeneralCategory(Code)
)

CREATE TABLE GeneralCategory
(
GeneralId int IDENTITY(1,1) PRIMARY KEY,
Name nvarchar(30) NOT NULL,
Code varchar(30) NOT NULL UNIQUE
)

CREATE TABLE BillItem
(
BillItemId int IDENTITY(1,1) PRIMARY KEY,
BillId int,
ItemId int,
Quantity smallint NOT NULL CHECK(Quantity >= 1),
Price int NOT NULL CHECK(Price >= 1000),
CONSTRAINT FK_BillItem_BillId FOREIGN KEY (BillId) 
	REFERENCES Bill(BillId),
CONSTRAINT FK_BillItem_ItemId FOREIGN KEY (ItemId) 
	REFERENCES Item(ItemId)
)

CREATE TABLE Bill
(
BillId int IDENTITY(1,1) PRIMARY KEY,
Username varchar(50),
TotalPrice int NULL CHECK(TotalPrice >= 1000),
Time datetime NOT NULL,
Confirm bit NOT NULL,
Status bit NOT NULL,
Delivered bit NULL,
CONSTRAINT FK_Bill_Username FOREIGN KEY (Username) 
	REFERENCES DBUser(Username)
)

CREATE TABLE DBUser
(
UserId int identity(1,1) PRIMARY KEY,
Username varchar(50) NOT NULL UNIQUE,
Password varchar(60) NOT NULL,
/* 0: USER 1: ADMIN */
Role bit NOT NULL,
Avatar varchar(255),
Name nvarchar(50) NOT NULL,
/* 0: Man 1: Woman*/
Gender bit,
Address nvarchar(255) NOT NULL,
Dob date,
Phone varchar(10) NOT NULL,
Email varchar(35) NOT NULL,
)

CREATE TABLE Cart
(
CartId int identity(1,1) PRIMARY KEY,
Username varchar(50),
ItemId int,
Quantity smallint NOT NULL CHECK(Quantity >= 1),
Price int NOT NULL CHECK(Price >= 1000),
CONSTRAINT FK_Cart_Username FOREIGN KEY(Username) 
	REFERENCES DBUser(Username),
CONSTRAINT FK_Cart_ItemId FOREIGN KEY(ItemId) 
	REFERENCES Item(ItemId)
)

CREATE TABLE Image
(
ImageId int identity(1,1) PRIMARY KEY,
Link varchar(255) NOT NULL,
ItemId int FOREIGN KEY REFERENCES Item(ItemId)
)

DROP TABLE GeneralCategory
DROP TABLE Category
DROP TABLE Item
DROP TABLE Bill
DROP TABLE BillItem
DROP TABLE DBUser
DROP TABLE Cart

ALTER TABLE DBUser
ADD CONSTRAINT UQ_Item_Email UNIQUE(Email)

ALTER TABLE Category
ADD CONSTRAINT UQ_Category_Code1 UNIQUE(Code)

ALTER TABLE BillItem
ADD CONSTRAINT FK_BillItem_BillId FOREIGN KEY (BillId) REFERENCES Bill(BillId)

ALTER TABLE Cart
ADD CONSTRAINT FK_Cart_Username FOREIGN KEY (Username) REFERENCES DBUser(Username)

ALTER TABLE Bill
ADD Delivered bit

ALTER TABLE DBUser
ADD Avatar varchar(255)

DROP TABLE Cart

ALTER TABLE Bill
ALTER COLUMN Delivered bit NULL
