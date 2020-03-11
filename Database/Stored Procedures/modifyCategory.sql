CREATE PROC modifyCategory(@categoryID Integer, @categoryName Nvarchar(25), @categoryPicture Varbinary(8000))
AS
BEGIN
UPDATE Categories
SET
@categoryName = categoryName,
@categoryPicture = categoryPicture
WHERE @categoryID = categoryID
END
