CREATE FUNCTION newestMessages (@userID Integer)
RETURNS TABLE
AS
RETURN (
	SELECT TOP 20 *  FROM Messages WHERE recipentID = @userID
	ORDER BY msgDate DESC
)
