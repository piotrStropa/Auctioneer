CREATE FUNCTION getPriceHistory (@auctionID Integer)
RETURNS TABLE
AS
RETURN(
	SELECT TOP 5 * FROM AuctionPriceHistory
	WHERE auctionID = @auctionID
	ORDER BY price DESC
)