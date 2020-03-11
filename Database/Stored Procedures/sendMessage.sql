CREATE PROCEDURE sendMessage(@topicName nvarchar(25), @text nvarchar(200), @senderID Integer, @recipentID Integer, @auctionID Integer)
AS
BEGIN
DECLARE @currentDate Date
SET @currentDate = GETDATE()
INSERT INTO Messages VALUES(@topicName, @text, @senderID, @recipentID, @auctionID, @currentDate)
END