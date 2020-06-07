-- @author mdpinar

create table site (
  id                        bigint auto_increment not null,
  active                    boolean default true,
  name                      varchar(50) not null,
  domain                    varchar(100) not null,
  country                   varchar(50) not null,
  class_name                varchar(100) not null,
  logo_url                  varchar(250),
  created_at                timestamp not null default current_timestamp,
  primary key (id),
  unique key ix1 (name)
) engine=innodb;

create table plan (
  id                        bigint auto_increment not null,
  name                      varchar(15) not null,
  description               varchar(70),
  price                     decimal(9,2) default 0,
  product_limit             smallint,
  order_no                  smallint,
  primary key (id),
  unique key ix1 (name)
) engine=innodb;

create table user (
  id                        bigint auto_increment not null,
  email                     varchar(100) not null,
  name                      varchar(70) not null,
  timezone                  varchar(30),
  password_hash             varchar(255) not null,
  password_salt             varchar(255) not null,
  created_at                timestamp not null default current_timestamp,
  primary key (id),
  unique key ix1 (email)
) engine=innodb;

create table company (
  id                        bigint auto_increment not null,
  name                      varchar(70) not null,
  admin_id                  bigint not null,
  due_date                  datetime,
  retry                     smallint default 0,
  last_collecting_time      datetime,
  last_collecting_status    boolean default false,
  currency_code             char(3),
  currency_format           varchar(16),
  plan_id                   bigint,
  plan_status               enum('NOT_SET', 'ACTIVE', 'PAUSED', 'CANCELLED') not null default 'NOT_SET',
  product_limit             smallint default 0,
  product_count             smallint default 0,
  created_at                timestamp not null default current_timestamp,
  primary key (id),
  key ix1 (name)
) engine=innodb;
alter table company add foreign key (admin_id) references user (id);
alter table company add foreign key (plan_id) references plan (id);

create table membership (
  id                        bigint auto_increment not null,
  email                     varchar(100) not null,
  user_id                   bigint,
  company_id                bigint not null,
  role                      enum('ADMIN', 'EDITOR', 'VIEWER') not null default 'EDITOR',
  pre_status                enum('PENDING', 'JOINED', 'PAUSED', 'LEFT', 'DELETED') not null default 'PENDING',
  status                    enum('PENDING', 'JOINED', 'PAUSED', 'LEFT', 'DELETED') not null default 'PENDING',
  retry                     smallint default 1,
  updated_at                timestamp,
  created_at                timestamp not null default current_timestamp,
  primary key (id),
  key ix1 (email)
) engine=innodb;
alter table membership add foreign key (user_id) references user (id);
alter table membership add foreign key (company_id) references company (id);

create table user_session (
  _hash                     varchar(32) not null,
  user_id                   bigint not null,
  company_id                bigint not null,
  ip                        varchar(255),
  os                        varchar(30),
  browser                   varchar(100),
  user_agent                varchar(500),
  accessed_at               timestamp not null default current_timestamp,
  primary key (_hash),
  key ix1 (accessed_at)
) engine=innodb;
alter table user_session add foreign key (user_id) references user (id);
alter table user_session add foreign key (company_id) references company (id);

create table product (
  id                        bigint auto_increment not null,
  active                    boolean default true,
  code                      varchar(120) not null,
  name                      varchar(500) not null,
  brand                     varchar(100),
  category                  varchar(100),
  price                     decimal(9,2) default 0,
  last_price_id             bigint,
  company_id                bigint not null,
  updated_at                datetime,
  created_at                timestamp not null default current_timestamp,
  primary key (id),
  unique key ix1 (company_id, code),
  key ix2 (company_id, name)
) engine=innodb;
alter table product add foreign key (company_id) references company (id);

create table product_price (
  id                        bigint auto_increment not null,
  product_id                bigint not null,
  price                     decimal(9,2) default 0,
  min_platform              varchar(50),
  min_seller                varchar(50),
  min_price                 decimal(9,2) default 0,
  min_diff                  decimal(6,2) default 0,
  avg_price                 decimal(9,2) default 0,
  avg_diff                  decimal(6,2) default 0,
  max_platform              varchar(50),
  max_seller                varchar(50),
  max_price                 decimal(9,2) default 0,
  max_diff                  decimal(6,2) default 0,
  competitors               smallint default 0,
  position                  smallint default 3,
  ranking                   smallint default 0,
  ranking_with              smallint default 0,
  suggested_price           decimal(9,2) default 0,
  company_id                bigint not null,
  created_at                timestamp not null default current_timestamp,
  primary key (id),
  key ix1 (created_at)
) engine=innodb;
alter table product_price add foreign key (product_id) references product (id);

create table competitor (
  id                        bigint auto_increment not null,
  url                       varchar(1024) not null,
  url_hash                  varchar(32) not null,
  sku                       varchar(70),
  name                      varchar(500),
  brand                     varchar(150),
  seller                    varchar(150),
  shipment                  varchar(150),
  price                     decimal(9,2) default 0,
  last_check                datetime,
  last_update               datetime,
  pre_status                varchar(25) not null default 'TOBE_CLASSIFIED',
  status                    varchar(25) not null default 'TOBE_CLASSIFIED',
  retry                     smallint default 0,
  http_status               smallint default 0,
  website_class_name        varchar(100),
  product_id                bigint,
  site_id                   bigint,
  company_id                bigint,
  created_at                timestamp not null default current_timestamp,
  primary key (id),
  key ix1 (url_hash),
  key ix2 (status),
  key ix3 (name),
  key ix4 (last_update),
  key ix5 (last_check)
) engine=innodb;
alter table competitor add foreign key (product_id) references product (id);
alter table competitor add foreign key (site_id) references site (id);
alter table competitor add foreign key (company_id) references company (id);

create table competitor_price (
  id                        bigint auto_increment not null,
  competitor_id             bigint not null,
  price                     decimal(9,2) default 0,
  product_id                bigint not null,
  company_id                bigint not null,
  created_at                timestamp not null default current_timestamp,
  primary key (id),
  key ix1 (created_at)
) engine=innodb;
alter table competitor_price add foreign key (competitor_id) references competitor (id);

create table competitor_spec (
  id                        bigint auto_increment not null,
  competitor_id             bigint not null,
  _key                      varchar(100),
  _value                    varchar(500),
  product_id                bigint not null,
  company_id                bigint not null,
  primary key (id)
) engine=innodb;
alter table competitor_spec add foreign key (competitor_id) references competitor (id);

create table competitor_history (
  id                        bigint auto_increment not null,
  competitor_id             bigint not null,
  status                    varchar(25) not null,
  http_status               smallint default 0,
  product_id                bigint not null,
  company_id                bigint not null,
  created_at                timestamp not null default current_timestamp,
  primary key (id),
  key ix1 (created_at)
) engine=innodb;
alter table competitor_history add foreign key (competitor_id) references competitor (id);

create table import_product (
  id                        bigint auto_increment not null,
  import_type               enum('CSV', 'EBAY_SKU', 'AMAZON_ASIN') not null default 'CSV',
  data                      varchar(1024),
  status                    varchar(25) not null default 'TOBE_CLASSIFIED',
  last_check                datetime default now(),
  last_update               datetime,
  retry                     smallint default 0,
  http_status               smallint default 0,
  description               varchar(250),
  company_id                bigint not null,
  created_at                timestamp not null,
  primary key (id),
  key ix1 (status),
  key ix2 (last_update),
  key ix3 (last_check),
  key ix4 (created_at)
) engine=innodb;
alter table import_product add foreign key (company_id) references company (id);

create table coupon (
  code                      char(8) not null,
  description               varchar(50),
  days                      smallint default 14,
  plan_id                   bigint,
  issued_at                 timestamp,
  issued_company_id         bigint,
  created_at                timestamp not null default current_timestamp,
  primary key (code)
) engine=innodb;
alter table coupon add foreign key (plan_id) references plan (id);
alter table coupon add foreign key (issued_company_id) references company (id);
