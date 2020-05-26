CREATE TABLE `clients` (
	`name` VARCHAR(255) NOT NULL,
	`id` INTEGER NOT NULL,
	`balance` INTEGER NOT NULL,
	`discount` DOUBLE NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `admins` (
	`name` VARCHAR(255) NOT NULL,
	`id` INTEGER NOT NULL,
	PRIMARY KEY (`id`)
);


CREATE TABLE `phones` (
	`brand` VARCHAR(255) NOT NULL,
	`deviceID` INTEGER NOT NULL,
	`price` INTEGER NOT NULL,
	`storage` INTEGER NOT NULL,
	`ram` INTEGER NOT NULL,
	`batterylife` INTEGER NOT NULL,
	`cameraMP` INTEGER NOT NULL,
	PRIMARY KEY (`deviceID`)
);

CREATE TABLE `laptops` (
	`brand` VARCHAR(255) NOT NULL,
	`deviceID` INTEGER NOT NULL,
	`price` INTEGER NOT NULL,
	`storage` INTEGER NOT NULL,
	`ram` INTEGER NOT NULL,
	`storagetype` VARCHAR(255) NOT NULL,
	`gputype` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`deviceID`)
);


