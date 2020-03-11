CREATE PROC cancelAuction(@auctionID Integer)
AS
BEGIN
DELETE FROM Auctions
WHERE auctionID = @auctionID
END