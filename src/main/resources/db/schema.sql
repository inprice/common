-- @author mdpinar

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
) engine=innodb default charset=utf8;
create unique index country_ix1 on country (code);
create unique index country_ix2 on country (name);

create table site (
  id                        bigint auto_increment not null,
  active                    tinyint(1) default 1,
  name                      varchar(100) not null,
  domain                    varchar(100) not null,
  class_name                varchar(100) not null,
  country_id                bigint not null,
  created_at                 timestamp not null default current_timestamp,
  primary key (id)
) engine=innodb default charset=utf8;
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
) engine=innodb default charset=utf8;
create unique index plan_ix1 on plan (name);

create table plan_rows (
  id                        bigint auto_increment not null,
  description               varchar(120) not null,
  order_no                  smallint,
  plan_id                   bigint not null,
  primary key (id)
) engine=innodb default charset=utf8;

create table company (
  id                        bigint auto_increment not null,
  name                      varchar(250) not null,
  website                   varchar(150),
  admin_id                  bigint,
  country_id                bigint,
  created_at                 timestamp not null default current_timestamp,
  primary key (id)
) engine=innodb default charset=utf8;
create index company_ix1 on company (name);
alter table company add foreign key (country_id) references country (id);

create table user (
  id                        bigint auto_increment not null,
  active                    tinyint(1) default 1,
  user_type                 varchar(25) not null default 'READER',
  full_name                 varchar(150) not null,
  email                     varchar(250) not null,
  password_hash             varchar(255) not null,
  password_salt             varchar(255) not null,
  company_id                bigint not null,
  created_at                timestamp not null default current_timestamp,
  primary key (id)
) engine=innodb default charset=utf8;
create unique index user_ix1 on user (email);
alter table user add foreign key (company_id) references company (id);

create table workspace (
  id                        bigint auto_increment not null,
  active                    tinyint(1) default 1,
  name                      varchar(50) not null,
  due_date                  datetime not null default now(),
  last_collecting_time      datetime,
  last_collecting_status    tinyint(1),
  retry                     int default 0,
  plan_id                   bigint,
  company_id                bigint not null,
  created_at                timestamp not null default current_timestamp,
  primary key (id)
) engine=innodb default charset=utf8;
create index workspace_ix1 on workspace (last_collecting_time);
create index workspace_ix2 on workspace (due_date);
alter table workspace add foreign key (plan_id) references plan (id);
alter table workspace add foreign key (company_id) references company (id);

create table workspace_history (
  id                        bigint auto_increment not null,
  workspace_id              bigint,
  collecting_status         tinyint(1),
  company_id                bigint not null,
  created_at                timestamp not null default current_timestamp,
  primary key (id)
) engine=innodb default charset=utf8;
create index workspace_history_ix1 on workspace (created_at);
alter table workspace_history add foreign key (workspace_id) references workspace (id);

create table product (
  id                        bigint auto_increment not null,
  active                    tinyint(1) default 1,
  code                      varchar(120) not null,
  name                      varchar(500) not null,
  brand                     varchar(100),
  category                  varchar(100),
  price                     double default 0,
  position                  int default 4,
  last_update               datetime,
  min_seller                varchar(150),
  max_seller                varchar(150),
  min_price                 double default 0,
  avg_price                 double default 0,
  max_price                 double default 0,
  import_id                 bigint,
  company_id                bigint not null,
  workspace_id              bigint not null,
  created_at                timestamp not null default current_timestamp,
  primary key (id)
) engine=innodb default charset=utf8;
create index product_ix1 on product (workspace_id, code);
create index product_ix2 on product (workspace_id, name);
alter table product add foreign key (workspace_id) references workspace (id);

create table product_price (
  id                        bigint auto_increment not null,
  product_id                bigint not null,
  min_seller                varchar(150),
  max_seller                varchar(150),
  price                     double default 0,
  position                  int default 4,
  min_price                 double default 0,
  avg_price                 double default 0,
  max_price                 double default 0,
  company_id                bigint not null,
  workspace_id              bigint not null,
  created_at                timestamp not null default current_timestamp,
  primary key (id)
) engine=innodb default charset=utf8;
create index product_price_ix1 on product_price (created_at);
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
  website_class_name        varchar(100),
  company_id                bigint,
  workspace_id              bigint,
  product_id                bigint,
  site_id                   bigint,
  import_id                 bigint,
  import_row_id             bigint,
  primary key (id)
) engine=innodb default charset=utf8;
create index link_ix1 on link (status);
create index link_ix2 on link (name);
create index link_ix3 on link (sku);
create index link_ix4 on link (last_update);
create index link_ix5 on link (last_check);
alter table link add foreign key (company_id) references company (id);
alter table link add foreign key (workspace_id) references workspace (id);
alter table link add foreign key (product_id) references product (id);
alter table link add foreign key (site_id) references site (id);

create table link_price (
  id                        bigint auto_increment not null,
  link_id                   bigint not null,
  price                     double default 0,
  company_id                bigint not null,
  workspace_id              bigint not null,
  product_id                bigint not null,
  created_at                timestamp not null default current_timestamp,
  primary key (id)
) engine=innodb default charset=utf8;
create index link_price_ix1 on link_price (created_at);
alter table link_price add foreign key (link_id) references link (id);

create table link_spec (
  id                        bigint auto_increment not null,
  link_id                   bigint not null,
  _key                      varchar(100),
  _value                    varchar(500),
  company_id                bigint not null,
  workspace_id              bigint not null,
  product_id                bigint not null,
  primary key (id)
) engine=innodb default charset=utf8;
alter table link_spec add foreign key (link_id) references link (id);

create table link_history (
  id                        bigint auto_increment not null,
  link_id                   bigint not null,
  status                    varchar(25) not null,
  http_status               int default 0,
  company_id                bigint not null,
  workspace_id              bigint not null,
  product_id                bigint not null,
  created_at                timestamp not null default current_timestamp,
  primary key (id)
) engine=innodb default charset=utf8;
create index link_history_ix1 on link_history (created_at);
alter table link_history add foreign key (link_id) references link (id);

create table import_product (
  id                        bigint auto_increment not null,
  import_type               varchar(15) not null,
  status                    varchar(25) not null,
  result                    varchar(255),
  total_count               int default 0,
  insert_count              int default 0,
  duplicate_count           int default 0,
  problem_count             int default 0,
  company_id                bigint not null,
  workspace_id              bigint not null,
  created_at                timestamp not null default current_timestamp,
  primary key (id)
) engine=innodb default charset=utf8;
alter table import_product add foreign key (workspace_id) references workspace (id);
alter table link add foreign key (import_id) references import_product (id);
alter table product add foreign key (import_id) references import_product (id);

create table import_product_row (
  id                        bigint auto_increment not null,
  import_id                 bigint not null,
  import_type               varchar(15) not null,
  data                      varchar(1024) not null,
  status                    varchar(25) not null default 'NEW',
  last_update               datetime,
  description               varchar(255),
  link_id                   bigint,
  company_id                bigint not null,
  workspace_id              bigint not null,
  primary key (id)
) engine=innodb default charset=utf8;
alter table link add foreign key (import_row_id) references import_product_row (id);
