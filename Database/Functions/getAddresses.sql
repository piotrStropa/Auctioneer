CREATE FUNCTION getAddresses (@userID Integer)
RETURNS TABLE
AS
RETURN(
	SELECT * FROM AddressBook
	WHERE userID = @userID
)