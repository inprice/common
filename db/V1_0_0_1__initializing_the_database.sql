-- @author mdpinar

-- info tables

create table country (
  id                        bigint auto_increment not null,
  code                      varchar(2) not null,
  name                      varchar(50) not null,
  locale                    varchar(5) not null,
  lang                      varchar(30),
  flag                      varchar(10),
  currency_code             varchar(5) not null,
  currency_symbol           varchar(5),
  primary key (id)
) engine=innodb default charset=utf8mb4;
create unique index country_ix1 on country (code);
create unique index country_ix2 on country (name);

create table site (
  id                        bigint auto_increment not null,
  active                    tinyint(1) default 1,
  name                      varchar(100) not null,
  domain                    varchar(100) not null,
  country_id                bigint not null,
  class_name                varchar(100) not null,
  insert_at                 timestamp not null default current_timestamp,
  primary key (id)
) engine=innodb default charset=utf8mb4;
create unique index site_ix1 on site (name);
alter table site add foreign key (country_id) references country (id);

create table plan (
  id                        bigint auto_increment not null,
  active                    tinyint(1) default 1,
  name                      varchar(30) not null,
  price                     double default 0,
  row_limit                 int,
  order_no                  smallint,
  primary key (id)
) engine=innodb default charset=utf8mb4;
create unique index plan_ix1 on plan (name);

create table plan_rows (
  id                        bigint auto_increment not null,
  plan_id                   bigint not null,
  description               varchar(120) not null,
  order_no                  smallint,
  primary key (id)
) engine=innodb default charset=utf8mb4;

-- user tables

create table user (
  id                        bigint auto_increment not null,
  user_type                 varchar(25) not null default 'ADMIN',
  company_name              varchar(250) not null,
  contact_name              varchar(150) not null,
  email                     varchar(150) not null,
  website                   varchar(150),
  password_hash             varchar(255) not null,
  password_salt             varchar(255) not null,
  country_id                bigint,
  insert_at                 timestamp not null default current_timestamp,
  primary key (id)
) engine=innodb default charset=utf8mb4;
create unique index user_ix1 on user (email);
create index user_ix2 on user (company_name);
alter table user add foreign key (country_id) references country (id);

create table user_brand (
  id                        bigint auto_increment not null,
  name                      varchar(150) not null,
  user_id                   bigint,
  insert_at                 timestamp not null default current_timestamp,
  primary key (id)
) engine=innodb default charset=utf8mb4;
create unique index user_brand_ix1 on user_brand (user_id, name);
alter table user_brand add foreign key (user_id) references user (id);

create table user_plan (
  id                        bigint auto_increment not null,
  active                    tinyint(1) default 1,
  name                      varchar(50) not null,
  monthly                   tinyint(1) default 1,
  user_id                   bigint not null,
  brand_id                  bigint,
  plan_id                   bigint,
  due_date                  datetime not null,
  last_collecting_time      datetime,
  last_collecting_status    tinyint(1),
  retry                     int default 0,
  insert_at                 timestamp not null default current_timestamp,
  primary key (id)
) engine=innodb default charset=utf8mb4;
create index user_plan_ix1 on user_plan (last_collecting_time);
create index user_plan_ix2 on user_plan (due_date);
alter table user_plan add foreign key (user_id) references user (id);
alter table user_plan add foreign key (brand_id) references user_brand (id);
alter table user_plan add foreign key (plan_id) references plan (id);

create table user_plan_history (
  id                        bigint auto_increment not null,
  user_plan_id              bigint,
  collecting_status         tinyint(1),
  insert_at                 timestamp not null default current_timestamp,
primary key (id)
) engine=innodb default charset=utf8mb4;
create index user_plan_history_ix1 on user_plan (insert_at);
alter table user_plan_history add foreign key (user_plan_id) references user_plan (id);

create table product (
  id                        bigint auto_increment not null,
  active                    tinyint(1) default 1,
  code                      varchar(120),
  name                      varchar(500),
  brand                     varchar(100),
  price                     double default 0,
  position                  int default 4,
  last_update               datetime,
  min_seller                varchar(150),
  max_seller                varchar(150),
  min_price                 double default 0,
  avg_price                 double default 0,
  max_price                 double default 0,
  user_plan_id              bigint not null,
  primary key (id)
) engine=innodb default charset=utf8mb4;
create unique index product_ix1 on product (user_plan_id, code);
create unique index product_ix2 on product (user_plan_id, name);
alter table product add foreign key (user_plan_id) references user_plan (id);

create table product_price (
  id                        bigint auto_increment not null,
  product_id                bigint not null,
  min_seller                varchar(150),
  max_seller                varchar(150),
  price                     double default 0,
  position                  int default 3,
  min_price                 double default 0,
  avg_price                 double default 0,
  max_price                 double default 0,
  insert_at                 timestamp not null default current_timestamp,
primary key (id)
) engine=innodb default charset=utf8mb4;
create index product_price_ix1 on product_price (insert_at);
alter table product_price add foreign key (product_id) references product (id);

create table link (
  id                        bigint auto_increment not null,
  url                       varchar(2000) not null,
  sku                       varchar(70),
  name                      varchar(500),
  brand                     varchar(150),
  seller                    varchar(150),
  shipment                  varchar(150),
  price                     double default 0,
  last_check                datetime default now(),
  last_update               datetime,
  status                    varchar(25) not null default 'NEW',
  previous_status           varchar(25) not null default 'NEW',
  retry                     int default 0,
  http_status               int default 0,
  user_id                   bigint,
  user_plan_id              bigint,
  product_id                bigint,
  site_id                   bigint,
  website_class_name        varchar(100),
  primary key (id)
) engine=innodb default charset=utf8mb4;
create index link_ix1 on link (status);
create index link_ix2 on link (name);
create index link_ix3 on link (sku);
create index link_ix4 on link (last_update);
create index link_ix5 on link (last_check);
alter table link add foreign key (user_id) references user (id);
alter table link add foreign key (product_id) references product (id);
alter table link add foreign key (user_plan_id) references user_plan (id);
alter table link add foreign key (site_id) references site (id);

create table link_price (
  id                        bigint auto_increment not null,
  link_id                   bigint not null,
  price                     double default 0,
  insert_at                 timestamp not null default current_timestamp,
  primary key (id)
) engine=innodb default charset=utf8mb4;
create index link_price_ix1 on link_price (insert_at);
alter table link_price add foreign key (link_id) references link (id);

create table link_spec (
  id                        bigint auto_increment not null,
  link_id                   bigint not null,
  _key                      varchar(100),
  _value                    varchar(500),
  primary key (id)
) engine=innodb default charset=utf8mb4;
alter table link_spec add foreign key (link_id) references link (id);

create table link_history (
  id                        bigint auto_increment not null,
  link_id                   bigint not null,
  status                    varchar(25) not null,
  http_status               int default 0,
  insert_at                 timestamp not null default current_timestamp,
 primary key (id)
) engine=innodb default charset=utf8mb4;
create index link_history_ix1 on link_history (insert_at);
alter table link_history add foreign key (link_id) references link (id);

