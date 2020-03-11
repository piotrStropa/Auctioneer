CREATE FUNCTION getUserByLogin(@login nvarchar(25))
RETURNS TABLE
AS
RETURN (SELECT * FROM Users WHERE @login = [login])