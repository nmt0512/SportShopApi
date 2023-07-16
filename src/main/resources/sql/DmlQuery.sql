USE SportShop

INSERT INTO Item(Code, Name, CategoryCode, Quantity, Image)
SELECT 'ao-clb-juventus-2020-away', N'Áo clb Juventus 2020 away', 'ao-bong-da-nam', 250, BulkColumn 
FROM OPENROWSET(Bulk 'D:\Workspace\Java Web Eclipse\SportShop\src\main\resources\images\ao-juventus-away-2020.jpg', SINGLE_BLOB) AS img

INSERT INTO Item(Code, Name, CategoryCode, Quantity, Size, Image)
SELECT 'qua-bong-da-dong-luc', N'Quả bóng đá Động Lực', 'qua-bong-da', 50, '5', BulkColumn 
FROM OPENROWSET(Bulk 'D:\Workspace\Java Web Eclipse\SportShop\src\main\resources\images\bong-dong-luc-size-5.jpg', SINGLE_BLOB) AS img

INSERT INTO Category(Code, Name, GeneralCode) VALUES('ao-bong-da-nam', N'Áo bóng đá nam', 'bong-da')

INSERT INTO DBUser(Username, Password, Role, Name, Address, Phone, Email) VALUES('admin2', '$2a$12$qzzPn9q1bGOBKIuQD7RCY.wUrIq2hwTsYzN3lsj8qThH/ckakor/u', 1, 'admin1', 'Ha Noi', '0123456788', 'admin2@gmail.com')

SELECT * FROM DBUser WHERE Role = 1

SELECT * FROM Cart

SELECT * FROM Bill

SELECT * FROM BillItem

SELECT * FROM Item

SELECT Code, COUNT(*) FROM Item WHERE Size = 'XL' GROUP BY Code HAVING COUNT(*) > 2

SELECT * FROM GeneralCategory

SELECT * FROM Category

SELECT * FROM Image

SELECT * FROM Bill WHERE YEAR(Time) = 2023 AND MONTH(Time) = 1



UPDATE Item SET Quantity = 1 WHERE ItemId = 62

UPDATE Item SET Image = (SELECT BulkColumn
FROM OPENROWSET(Bulk 'C:\Users\Thieu\Downloads\ao-juventus-away-2020.jpg', SINGLE_BLOB) AS img )
WHERE ItemId = 11

INSERT INTO GeneralCategory(Name, Code) VALUES(N'Dụng cụ', 'dung-cu')

INSERT INTO Category(Code, Name, GeneralCode) VALUES('quan-bo', N'Quần bò', 'trang-phuc')

ALTER TABLE Item
ADD Image varchar(255)

SELECT * FROM Item
SELECT * FROM Image

INSERT INTO Image(Link, ItemId) 
VALUES('https://res.cloudinary.com/chidung2302/image/upload/v1675961220/WebSport/images/products/astrel-100-2_feko56.jpg', 61)
INSERT INTO Image(Link, ItemId) 
VALUES('https://res.cloudinary.com/chidung2302/image/upload/v1675960842/WebSport/images/products/mu-away-kit-2023_a7d7ah.jpg', 2)
INSERT INTO Image(Link, ItemId) 
VALUES('https://res.cloudinary.com/chidung2302/image/upload/v1672905572/WebSport/images/products/ball-c1-2020-2.webp', 8)

SELECT * FROM Bill

SELECT * FROM BillItem

INSERT INTO Bill(Username, Time, Confirm, Status, TotalPrice) VALUES('u2', '11/02/2022', 0, 1, 8000000)

UPDATE Bill SET TotalPrice = 4000000 WHERE Month(Time) = 11

UPDATE BillItem SET Price = 4000000 WHERE BillId IN (13,18)

INSERT INTO BillItem(BillId, ItemId, Quantity, Price) VALUES(18, 61, 2, 8000000)

INSERT INTO Cart(Username, ItemId, Quantity, Price) VALUES('u2', 1, 2, 240000)

INSERT INTO Item(Code, Name, CategoryCode, Size, Color, Quantity, Price, CreatedDate) 
VALUES('qua-bong-da-uefa-champions-league-2022', N'Quả bóng đá UEFA Champions League 2022', 'dung-cu_bong-da', '5', 'white', 20, 700000, GETDATE())

SELECT * FROM Item WHERE DATEDIFF(DAY, CreatedDate, GETDATE()) <= 7

INSERT INTO Category(GeneralCode, Code, Name) VALUES('trang-phuc', 'trang-phuc_boi-loi', N'Bơi lội')

SELECT SUM(TotalPrice) TotalPriceOfMonth FROM Bill WHERE YEAR(Time) = 2023 AND MONTH(Time) = 2

SELECT FORMAT(Time, 'MM/yyyy') MonthTime, SUM(TotalPrice) TotalRevenue FROM Bill GROUP BY FORMAT(Time, 'MM/yyyy')

SELECT DISTINCT MONTH(Time), YEAR(Time) FROM Bill

SELECT * FROM Item WHERE ItemId IN
(SELECT TOP 4 ItemId FROM BillItem WHERE BillId IN (SELECT BillId FROM Bill WHERE DATEDIFF(DAY, CreatedDate, GETDATE()) <= 7) 
GROUP BY ItemId ORDER BY COUNT(ItemId) DESC)

SELECT * FROM Bill WHERE FORMAT(Time, 'dd/MM/yyyy') = '05/01/2023'

UPDATE Bill SET Delivered = 0 WHERE BillId IN (3,4,5,9)

UPDATE Item SET Size = 'Medium' WHERE Size = N'Vừa'

UPDATE Item SET Color = 'white' WHERE ItemId = 20
