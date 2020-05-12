CREATE DATABASE IF NOT EXISTS users;

ALTER DATABASE users
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;

GRANT ALL PRIVILEGES ON users.* TO ua@localhost IDENTIFIED BY 'userapi';

USE users;

CREATE TABLE IF NOT EXISTS user (
  id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(30),
  salary DECIMAL (15,2)
) engine=InnoDB;

