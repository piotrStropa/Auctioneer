CREATE TRIGGER warningCountTrigger ON UserWarnings
AFTER INSERT
AS
	DECLARE iterator CURSOR
	FOR (SELECT userID FROM Inserted)
	FOR READ ONLY
	OPEN iterator
	DECLARE @userID Integer
	FETCH iterator INTO @userID
	WHILE(@@FETCH_STATUS = 0)
	BEGIN
		UPDATE Users
		SET warningCount = warningCount + 1
		WHERE userID = @userID
		FETCH iterator INTO @userID
	END
	CLOSE iterator
	DEALLOCATE iterator
GO