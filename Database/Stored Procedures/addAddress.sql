CREATE PROC addAddress(@userID integer, @addressFirstLane nvarchar(25), @addressSecondLane nvarchar(25), @zipCode nvarchar(5), @city nvarchar(25))
AS
BEGIN
	INSERT INTO AddressBook VALUES(@userID, @addressFirstLane, @addressSecondLane, @zipCode, @city)	
END
	