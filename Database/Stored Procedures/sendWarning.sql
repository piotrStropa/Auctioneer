CREATE PROC sendWarning(@warningDescription Nvarchar(125), @accountSuspended Integer, @userID Integer, @endDate Date)
AS
BEGIN
INSERT INTO UserWarnings VALUES(@warningDescription, @accountSuspended, @userID)
DECLARE @currentDate Date
SET @currentDate = GETDATE()
IF @accountSuspended = 1
	INSERT INTO accountsuspendeddates VALUES(@userID, @currentDate, @endDate)
END