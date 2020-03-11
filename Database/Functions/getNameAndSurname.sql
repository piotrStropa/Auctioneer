CREATE FUNCTION getNameAndSurname(@userID Integer)
RETURNS Nvarchar(50)
AS
BEGIN
	DECLARE @name Nvarchar(25)
	DECLARE @surname Nvarchar(25)

	SET @name = (SELECT name FROM Users WHERE name = @userID)
	SET @surname = (SELECT surname FROM Users WHERE surname = @userID)
	RETURN CONCAT(@name, ' ' , @surname)
END