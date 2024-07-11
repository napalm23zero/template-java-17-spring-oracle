-- init.sql

-- Create a user (Every database needs a chosen one)
CREATE USER TEMPLATE_APP IDENTIFIED BY darkSide123;

-- Grant necessary privileges (With great power comes great responsibility)
GRANT CONNECT, RESOURCE, DBA TO TEMPLATE_APP;

-- Make sure to commit changes (Seal the deal)
COMMIT;