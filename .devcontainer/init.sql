CREATE USER TEMPLATE_APP IDENTIFIED BY darkSide123;

GRANT ALL PRIVILEGES TO TEMPLATE_APP;

ALTER DATABASE SET TIME_ZONE = 'UTC';

ALTER SESSION SET TIME_ZONE = 'UTC';