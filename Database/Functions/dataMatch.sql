CREATE FUNCTION dataMatch (@login nvarchar(32), @passwordMD5 nvarchar(32))
RETURNS Bit
AS
BEGIN
	DECLARE @Count Integer;
	
	SET @Count = (SELECT COUNT(*) FROM Users
	WHERE @login = [login] AND @passwordMD5 = passwordMD5);
	
	IF @Count = 1
		RETURN 1;
	RETURN 0;
END