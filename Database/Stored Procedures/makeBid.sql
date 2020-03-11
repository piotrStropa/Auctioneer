CREATE PROC makeBid(@price Integer, @userID Integer, @auctionID Integer)
AS
BEGIN
BEGIN TRAN
	DECLARE @currentPrice Money
	DECLARE @currentDate Date
	SET @currentDate = GETDATE()
	SET @currentPrice = (SELECT currentPrice FROM Auctions WHERE auctionID = @auctionID)
	INSERT INTO AuctionPriceHistory VALUES(@price, @currentDate, @userID, @auctionID)
	IF @currentPrice > @price
		ROLLBACK
	ELSE
		BEGIN
		UPDATE Auctions
		SET currentPrice = @price
		WHERE auctionID = @auctionID
		COMMIT
		END
END
		