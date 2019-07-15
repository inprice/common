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
  price                     double,
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

-- customer tables

create table customer (
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
create unique index customer_ix1 on customer (email);
create index customer_ix2 on customer (company_name);
alter table customer add foreign key (country_id) references country (id);

create table customer_brand (
  id                        bigint auto_increment not null,
  name                      varchar(150) not null,
  customer_id               bigint,
  insert_at                 timestamp not null default current_timestamp,
  primary key (id)
) engine=innodb default charset=utf8mb4;
create unique index customer_brand_ix1 on customer_brand (customer_id, name);
alter table customer_brand add foreign key (customer_id) references customer (id);

create table customer_plan (
  id                        bigint auto_increment not null,
  active                    tinyint(1) default 1,
  name                      varchar(50) not null,
  monthly                   tinyint(1) default 1,
  customer_id               bigint,
  brand_id                  bigint,
  plan_id                   bigint,
  due_date                  datetime,
  last_collecting_time      datetime,
  last_collecting_status    tinyint(1),
  retry                     int default 0,
  insert_at                 timestamp not null default current_timestamp,
  primary key (id)
) engine=innodb default charset=utf8mb4;
create index customer_plan_ix1 on customer_plan (last_collecting_time);
create index customer_plan_ix2 on customer_plan (due_date);
alter table customer_plan add foreign key (customer_id) references customer (id);
alter table customer_plan add foreign key (brand_id) references customer_brand (id);
alter table customer_plan add foreign key (plan_id) references plan (id);

create table customer_plan_history (
  id                        bigint auto_increment not null,
  customer_plan_id          bigint,
  collecting_status         tinyint(1),
  insert_at                 timestamp not null default current_timestamp,
primary key (id)
) engine=innodb default charset=utf8mb4;
create index customer_plan_history_ix1 on customer_plan (insert_at);
alter table customer_plan_history add foreign key (customer_plan_id) references customer_plan (id);

create table product (
  id                        bigint auto_increment not null,
  active                    tinyint(1) default 1,
  code                      varchar(120),
  name                      varchar(500),
  brand                     varchar(100),
  price                     double,
  position                  int default 4,
  last_update               datetime,
  min_seller                varchar(150),
  max_seller                varchar(150),
  min_price                 double,
  avg_price                 double,
  max_price                 double,
  customer_plan_id          bigint,
  primary key (id)
) engine=innodb default charset=utf8mb4;
create unique index product_ix1 on product (customer_plan_id, code);
create unique index product_ix2 on product (customer_plan_id, name);
alter table product add foreign key (customer_plan_id) references customer_plan (id);

create table product_price (
  id                        bigint auto_increment not null,
  product_id                bigint not null,
  min_seller                varchar(150),
  max_seller                varchar(150),
  price                     double,
  position                  int default 3,
  min_price                 double,
  avg_price                 double,
  max_price                 double,
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
  price                     double,
  last_check                datetime default now(),
  last_update               datetime,
  status                    varchar(25) not null default 'NEW',
  previous_status           varchar(25) not null default 'NEW',
  retry                     int default 0,
  http_status               int default 0,
  customer_id               bigint,
  customer_plan_id          bigint,
  product_id                bigint,
  site_id                   bigint,
  website_class_name        varchar(100),
  primary key (id)
) engine=innodb default charset=utf8mb4;
create index link_ix1 on link (status);
create index link_ix2 on link (name);
create index link_ix3 on link (sku);
alter table link add foreign key (customer_id) references customer (id);
alter table link add foreign key (product_id) references product (id);
alter table link add foreign key (customer_plan_id) references customer_plan (id);
alter table link add foreign key (site_id) references site (id);

create table link_price (
  id                        bigint auto_increment not null,
  link_id                   bigint not null,
  price                     double,
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

