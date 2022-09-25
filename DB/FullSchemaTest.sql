

DROP DATABASE `UTD_DB`;
CREATE DATABASE `UTD_DB`;

USE `UTD_DB`;


DROP TABLE IF EXISTS Users;
Create table Users(
	User_ID bigint unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
	DOB date,
	First_Name varchar(200),
	Middle_Name varchar(200),
	Last_Name varchar(200),
	Address varchar(255),
	Gender varchar(100),
	UserName varchar(255),
	Password varchar(255),
	Email varchar(255)  -- Edited by: Morgan Pothoff
);

DROP TABLE IF EXISTS Manager;
Create Table Manager(
	Manager_ID bigint unsigned not null AUTO_INCREMENT Primary Key,
	User_ID bigint unsigned,
	Foreign key (User_ID) references Users(User_ID) ON DELETE
	SET NULL
);

DROP TABLE IF EXISTS Trip;
Create Table Trip(
	Trip_ID bigint unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
	User_ID bigint unsigned NOT NULL,
	Manager_ID bigint unsigned NOT NULL,
	Foreign key (User_ID) references Users(User_ID),
	Foreign key (Manager_ID) references Manager(Manager_ID),
	Set_Budget decimal(10,2) not null,
	Start_Date datetime,
	End_Time datetime, 
	MyDescription varchar(255),
	Location varchar(255)
);

DROP TABLE IF EXISTS Expenses;
CREATE TABLE Expenses
(
	Expense_ID BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	Company VARCHAR(64) NOT NULL,  -- the store where the purchase occurred
	Cost DECIMAL(10,2) NOT NULL,
	Date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	Location VARCHAR(64) NOT NULL,  -- the city where the purchase occurred
	ExpenseName VARCHAR(32) NOT NULL,
	Trip_ID bigint unsigned NOT NULL,  -- TODO: match to Trip table PRIMARY KEY,
	User_ID bigint unsigned NOT NULL,  -- TODO: match to Users table PRIMARY KEY,
	FOREIGN KEY (Trip_ID) REFERENCES Trip(Trip_ID),
	FOREIGN KEY (User_ID) REFERENCES Users(User_ID)
);
DROP TABLE IF EXISTS Payments;
CREATE TABLE Payments
(
	Payment_ID BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	account_number VARCHAR(32) NOT NULL,
	payment_method VARCHAR(64) NOT NULL,
	Expense_ID bigint unsigned NOT NULL,  -- TODO: match to Users table PRIMARY KEY
	User_ID bigint unsigned NOT NULL,  -- TODO: match to Users table PRIMARY KEY
	FOREIGN KEY (Expense_ID) REFERENCES Expenses(Expense_ID),
	FOREIGN KEY (User_ID) REFERENCES Users(User_ID)
);

DROP TABLE IF EXISTS Chat;
Create Table Chat(
	Chat_ID bigint unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
	Chat_Name varchar(255),
	Created_At datetime NOT NULL default current_timestamp,
	Last_Updated datetime
);

DROP TABLE IF EXISTS Participents;
Create Table Participents(
	Participents_ID bigint unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
	Chat_ID bigint unsigned,
	User_ID bigint unsigned,
	FOREIGN KEY (Chat_ID) REFERENCES Chat(Chat_ID),
	FOREIGN KEY (User_ID) REFERENCES Users(User_ID)
);

DROP TABLE IF EXISTS Messages;
Create Table Messages(
	Message_ID bigint unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
	Chat_ID bigint unsigned,
	User_ID bigint unsigned,
	FOREIGN KEY (Chat_ID) REFERENCES Chat(Chat_ID),
	FOREIGN KEY (User_ID) REFERENCES Users(User_ID),
	Message varchar(255)
);


INSERT INTO `Users` (`First_Name`, `Last_Name`, `Password`, `Email`, `UserName`) VALUES
('Morgan', 'Pothoff', 'Guest', 'Morgan.Pothoff@aol.com', 'mep');

INSERT INTO `Users` (`First_Name`, `Last_Name`, `Password`, `Email`, `UserName`) VALUES
('John', 'Do', '23456', 'John', 'jud');


INSERT INTO `Manager` (`User_ID`) VALUES
(1);

INSERT INTO `Trip` (`Trip_ID`, `User_ID`,`Manager_ID`,`Set_Budget`,`MyDescription`,`Location`)
VALUES
(1, 1, 1, 200.00, 'This is a test', 'UT Dallas');
