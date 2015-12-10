
# Users schema

# --- !Ups
 CREATE TABLE Addvertisements (
	uid INT(64) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	title VARCHAR(32) NOT NULL,
	fuel ENUM('gasoline', 'diesel') NOT NULL,
	price INT(32) UNSIGNED NOT NULL DEFAULT 0,
	new TINYINT(1) NOT NULL DEFAULT 1,
	mileage INT(32),
	registration DATE NOT NULL
);
 
# --- !Downs
DROP TABLE Addvertisements;
