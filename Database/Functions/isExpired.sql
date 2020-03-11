CREATE FUNCTION isExpired (@auctionID Integer)
RETURNS Bit
AS
BEGIN

	DECLARE @currentDate Date;
	DECLARE @expiration Date;
	DECLARE @Count Integer;
	SET @currentDate = GETDATE();
	SET @expiration = (SELECT expirationDate FROM Auctions WHERE auctionID = @auctionID);
	IF @expiration > @currentDate 
		RETURN 0;
	RETURN 1;
END