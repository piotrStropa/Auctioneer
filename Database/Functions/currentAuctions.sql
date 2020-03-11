CREATE FUNCTION currentAuctions(@userID Integer)
RETURNS TABLE
AS
	RETURN (SELECT TOP 200 * FROM Auctions 
			WHERE userID = @userID AND expirationDate > GETDATE())