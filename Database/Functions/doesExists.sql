CREATE FUNCTION doesExists (@login Nvarchar(25))
RETURNS Bit
AS
BEGIN
	DECLARE @Count Integer;
	SET @Count = (SELECT COUNT(*) FROM Users
	WHERE @login = [login]);
	
	IF @Count = 1
		RETURN 1;
	RETURN 0;
END