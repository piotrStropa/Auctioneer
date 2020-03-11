CREATE TRIGGER totalBidsAndValueTrigger ON AuctionPriceHistory
AFTER INSERT
AS
	DECLARE iterator CURSOR
	FOR (SELECT auctionID FROM Inserted)
	FOR READ ONLY
	OPEN iterator
	DECLARE @auctionID Integer
	FETCH iterator INTO @auctionID
	WHILE(@@FETCH_STATUS = 0)
	BEGIN
		UPDATE Auctions
		SET totalBids = totalBids + 1
		WHERE auctionID = @auctionID
		FETCH iterator INTO @auctionID
	END
	CLOSE iterator
	DEALLOCATE iterator
GO