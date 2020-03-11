CREATE FUNCTION auctionMatch (@expr nvarchar(32))
RETURNS TABLE
AS
RETURN(
	SELECT * FROM Auctions
	WHERE name LIKE (SELECT CONCAT('%',@expr,'%')) AND expirationDate > GETDATE()
)