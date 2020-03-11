CREATE PROC changePermissions(@userID Integer, @newPermissions Nvarchar(10))
AS
BEGIN
UPDATE Employees
SET [permissions] = @newPermissions
WHERE userID = @userID
END