
CREATE SEQUENCE IF NOT EXISTS SEQ_ID
START WITH 100
INCREMENT BY 1
NO CACHE;

create table if not exists login_user (
  id INT default seq_id.nextval primary key,
  login VARCHAR(250) NOT NULL,
  password VARCHAR(250) NOT NULL,
  UNIQUE KEY name (login)
);

create table if not exists cookie_login (
  id INT default seq_id.nextval primary key,
  id_login INT,
  value_cookie VARCHAR(250) NOT NULL,
  CONSTRAINT fk_id_login_cookie foreign key (id_login) references login_user(id)
);

create table if not exists user (
  id INT default seq_id.nextval primary key,
  id_login INT,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  patronymic_name VARCHAR(250),
  user_type ENUM('admin', 'client') NOT NULL,
  position VARCHAR(250),
  email VARCHAR(250),
  phone VARCHAR(250),
  constraint fk_id_login foreign key (id_login) references login_user(id),
  constraint ck_position check (case user_type when 'client' then null end),
  constraint ck_email check (case user_type when 'admin' then null end),
  constraint ck_phone check (case user_type when 'admin' then null end)
);

create index if not exists  idx_cookie_login$value on cookie_login (value_cookie);


insert into login_user (id, login, password) values (1, 'Test11', '123');
