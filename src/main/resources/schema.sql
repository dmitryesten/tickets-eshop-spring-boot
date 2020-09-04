
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
  cookie_name VARCHAR(50),
  cookie_value VARCHAR(50),
  cookie_path VARCHAR(50),
  cookie_max_age INT,
  cookie_is_sercure BOOLEAN default false,
  cookie_is_http_only BOOLEAN default false,
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

create index idx_cookie_login$value on cookie_login (cookie_name, cookie_value);


insert into login_user (id, login, password) values (1, 'Test1', '123');
insert into login_user (id, login, password) values (2, 'Test2', '123');
insert into user (id, id_login, first_name, last_name, patronymic_name, user_type, position)
values (3, 1, 'Admin', 'Adminov', 'Adminovich', 'admin', 'главный менеджер');
insert into user (id, id_login, first_name, last_name, patronymic_name, user_type, email, phone)
values (4, 2, 'Иван', 'Иванов', 'Иванович', 'client',  'iva@mail.ru', '+79008000000');

insert into cookie_login (id_login, cookie_name, cookie_value, cookie_path, cookie_max_age) values (2, 'JAVASESSIONID', '13', '/', 9000);
insert into cookie_login (id_login, cookie_name, cookie_value, cookie_path, cookie_max_age) values (2, 'JAVASESSIONID', '14', '/', 4000);
