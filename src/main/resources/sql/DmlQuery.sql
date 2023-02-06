USE SportShop

INSERT INTO Item(Code, Name, CategoryCode, Quantity, Image)
SELECT 'ao-clb-juventus-2020-away', N'Áo clb Juventus 2020 away', 'ao-bong-da-nam', 250, BulkColumn 
FROM OPENROWSET(Bulk 'D:\Workspace\Java Web Eclipse\SportShop\src\main\resources\images\ao-juventus-away-2020.jpg', SINGLE_BLOB) AS img

INSERT INTO Item(Code, Name, CategoryCode, Quantity, Size, Image)
SELECT 'qua-bong-da-dong-luc', N'Quả bóng đá Động Lực', 'qua-bong-da', 50, '5', BulkColumn 
FROM OPENROWSET(Bulk 'D:\Workspace\Java Web Eclipse\SportShop\src\main\resources\images\bong-dong-luc-size-5.jpg', SINGLE_BLOB) AS img

INSERT INTO Category(Code, Name, GeneralCode) VALUES('ao-bong-da-nam', N'Áo bóng đá nam', 'bong-da')

INSERT INTO DBUser(Username, Password, Role, Name, Address, Phone, Email) VALUES('admin1', 'pw1', 1, 'admin1', 'Ha Noi', '0123456788', 'admin1@gmail.com')

SELECT * FROM DBUser

SELECT * FROM Cart

SELECT * FROM Bill

SELECT * FROM BillItem

SELECT * FROM Item

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

INSERT INTO Image(Link, ItemId) 
VALUES('https://res.cloudinary.com/chidung2302/image/upload/v1672906549/WebSport/images/products/england-away-kit-2022-2_dsrlbf.webp', 5)
INSERT INTO Image(Link, ItemId) 
VALUES('https://res.cloudinary.com/chidung2302/image/upload/v1672906549/WebSport/images/products/england-away-kit-2022-2_dsrlbf.webp', 6)
INSERT INTO Image(Link, ItemId) 
VALUES('https://res.cloudinary.com/chidung2302/image/upload/v1672906549/WebSport/images/products/england-away-kit-2022-2_dsrlbf.webp', 7)


INSERT INTO Bill(Username, Time, Confirm, Status) VALUES('u2', GETDATE(), 0, 1)

INSERT INTO BillItem(BillId, ItemId, Quantity, Price) VALUES(3, 8, 1, 600000)

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
