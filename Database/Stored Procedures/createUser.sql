CREATE PROC createUser(@login nvarchar(25), @passwordMD5 nvarchar(32), @name nvarchar(25), @surname nvarchar(25), @userType nvarchar(1), @permissions nvarchar(10), @bossID Integer)
AS
BEGIN
DECLARE @exists BIT;
SET @exists = (SELECT COUNT(*) FROM Users WHERE @login = [login]);
INSERT INTO Users VALUES (@login, @passwordMD5, @userType, @name, @surname, 0)
DECLARE @userID Integer
DECLARE @date Date
SET @date = GETDATE()
SET @userID = (SELECT userID FROM Users WHERE @login = [login])

IF @exists = 1
	RETURN
IF @userType LIKE 'C'
	INSERT INTO CommonUsers VALUES(@userID, @date, 0)
ELSE
	INSERT INTO Employees VALUES(@userID, @permissions, @bossID)
END