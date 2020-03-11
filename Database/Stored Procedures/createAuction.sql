CREATE PROCEDURE createAuctions(@startPrice Integer, @userID Integer, @name nvarchar(25), @description nvarchar(500), @categoryID Integer, @expirationDate Date, @addressID Integer)
AS
BEGIN
DECLARE @currentDate Date
SET @currentDate = GETDATE()
INSERT INTO Auctions VALUES(@startPrice, @userID, @name, @description, @categoryID, @currentDate, @expirationDate, 0, 0, @addressID)
END