CREATE TRIGGER dateAndCountTrigger ON Auctions
AFTER INSERT
AS
	DECLARE @count Integer
	SET @count = (SELECT COUNT(*) FROM Inserted WHERE expirationDate <= creationDate)
	IF(@count > 0) ROLLBACK

	DECLARE iterator CURSOR FOR
		SELECT userID FROM Inserted
	FOR READ ONLY

	OPEN iterator
	DECLARE @userID Integer
	FETCH iterator INTO @userID
	
	WHILE(@@FETCH_STATUS = 0) 
	BEGIN
		UPDATE CommonUsers
		SET auctioncount = auctioncount + 1
		WHERE @userID = userID
		FETCH iterator INTO @userID
	END
	CLOSE iterator
	DEALLOCATE iterator
GO
 	 



