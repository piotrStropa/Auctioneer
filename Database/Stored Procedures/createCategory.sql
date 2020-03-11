CREATE PROC addCategory(@categoryName Nvarchar(25), @categoryPicture Varbinary(8000))
AS
BEGIN
INSERT INTO Categories VALUES(@categoryName, @categoryPicture)
END