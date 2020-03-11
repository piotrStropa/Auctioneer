CREATE PROCEDURE addPicture(@auctionID Integer, @pictureDate Varbinary(max))
AS
BEGIN
	INSERT INTO AuctionPictures VALUES(@auctionID, @pictureDate)
END