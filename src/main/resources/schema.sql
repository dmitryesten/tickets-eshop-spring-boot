
CREATE SEQUENCE IF NOT EXISTS SEQ_ID
START WITH 1
INCREMENT BY 1
NO CACHE;

create table IF NOT EXISTS login (
  id INT default seq_id.nextval primary key,
  nickname VARCHAR(250) NOT NULL,
  password VARCHAR(250) NOT NULL,
  UNIQUE KEY name (nickname)
);

create table IF NOT EXISTS cookie_login (
  id INT default seq_id.nextval primary key,
  id_login INT,
  value_cookie VARCHAR(250) NOT NULL,
  CONSTRAINT fk_id_login_cookie foreign key (id_login) references login(id)
);

create table IF NOT EXISTS user (
  id INT default seq_id.nextval primary key,
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



