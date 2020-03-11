CREATE TRIGGER suspendDateTrigger ON accountsuspendeddates
FOR INSERT, UPDATE
AS
	DECLARE @incorrect Integer
	SET @incorrect = (SELECT COUNT(*) FROM Inserted WHERE startDate >= endDate)
	IF @incorrect <> 0 ROLLBACK
	GO