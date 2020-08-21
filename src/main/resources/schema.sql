DROP TABLE IF EXISTS billionaires;

create table login (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  nickname VARCHAR(250) NOT NULL,
  password VARCHAR(250) NOT NULL,
  UNIQUE KEY name (nickname)
);

create table user (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  id_login INT,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  patronymic_name VARCHAR(250),
  user_type ENUM('admin', 'client') NOT NULL,
  position VARCHAR(250),
  email VARCHAR(250),
  phone VARCHAR(250),
  CONSTRAINT fk_id_login foreign key (id_login) references login(id)
);

INSERT INTO login (id, nickname, password) VALUES (12, 'Nickname1', '123');
INSERT INTO login (id, nickname, password) VALUES
    (13, 'Nickname2', '134'),
    (14, 'Nickname4', '312');

//insert into  user (id_login, first_name, last_name, patronymic_name, user_type, position)
// VALUES (1, 'Dima', 'Denisenko', 'Adminovich', 'admin', 'dev')


