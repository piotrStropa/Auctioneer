CREATE PROCEDURE createBackup
AS
BACKUP DATABASE Alledrogo
TO DISK = 'C:\AlledrogoBackup.BAK'
WITH DESCRIPTION = 'Full backup for Alledrogo Database'
GO