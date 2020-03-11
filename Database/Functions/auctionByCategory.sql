CREATE FUNCTION auctionByCategory (@categoryID integer)
RETURNS TABLE
AS
RETURN(
	SELECT * FROM Auctions
	WHERE categoryID = @categoryID AND expirationDate > GETDATE()
)