CREATE FUNCTION isSuspended (@userID Integer)
RETURNS Bit
AS
BEGIN
	DECLARE @currentDate Date;
	DECLARE @Count Integer;
	SET @currentDate = GETDATE();
	DECLARE @dates TABLE(
		endDate Date
	) 
	INSERT INTO @dates 
		SELECT endDate FROM AccountSuspendedDates
		WHERE userID = @userID AND endDate > @currentDate
	
	SET @Count = (SELECT COUNT(*) FROM @dates)
	
	IF @Count = 0
		RETURN 0;
	RETURN 1;
END