-- @author mdpinar

create table user (
  id                        bigint auto_increment not null,
  email                     varchar(100) not null,
  password                  varchar(88) not null,
  name                      varchar(70) not null,
  timezone                  varchar(30),
  stripe_cust_id            varchar(255),
  created_at                timestamp not null default current_timestamp,
  primary key (id),
  unique key ix1 (email)
) engine=innodb;

create table company (
  id                        bigint auto_increment not null,
  name                      varchar(70) not null,
  currency_code             char(3),
  currency_format           varchar(16),
  product_limit             smallint default 0,
  product_count             smallint default 0,
  admin_id                  bigint not null,
  plan_id                   smallint,
  subs_id                   varchar(255),
  subs_status               enum('NOT_SET', 'ACTIVE', 'COUPONED', 'STOPPED', 'CANCELLED') not null default 'NOT_SET',
  subs_renewal_at           timestamp,
  subs_customer_id          varchar(255),
  title                     varchar(255),
  address_1                 varchar(255),
  address_2                 varchar(255),
  postcode                  varchar(8),
  city                      varchar(70),
  state                     varchar(70),
  country                   varchar(2),
  created_at                timestamp not null default current_timestamp,
  primary key (id),
  key ix1 (subs_renewal_at),
  key ix2 (name),
  key ix3 (subs_customer_id)
) engine=innodb;
alter table company add foreign key (admin_id) references user (id);

create table subs_trans (
  id                        bigint auto_increment not null,
  company_id                bigint not null,
  event_source              enum('SUBSCRIPTION', 'COUPON') not null default 'SUBSCRIPTION',
  event_id                  varchar(255),
  event                     varchar(255) not null,
  successful                boolean default false,
  reason                    varchar(255),
  description               varchar(255),
  file_url                  varchar(255),
  created_at                timestamp not null default current_timestamp,
  primary key (id),
  key ix1 (created_at)
) engine=innodb;
alter table subs_trans add foreign key (company_id) references company (id);

create table member (
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
alter table member add foreign key (user_id) references user (id);
alter table member add foreign key (company_id) references company (id);

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
  code                      varchar(50) not null,
  name                      varchar(500) not null,
  price                     decimal(9,2) default 0,
  position                  smallint default 3,
  ranking                   smallint default 0,
  ranking_with              smallint default 0,
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
  suggested_price           decimal(9,2) default 0,
  company_id                bigint not null,
  updated_at                datetime,
  created_at                timestamp not null default current_timestamp,
  primary key (id),
  unique key ix1 (company_id, code),
  key ix2 (company_id, name)
) engine=innodb;
alter table product add foreign key (company_id) references company (id);

create table product_tag (
  id                        bigint auto_increment not null,
  name                      varchar(30) not null,
  product_id                bigint not null,
  company_id                bigint not null,
  primary key (id),
  key ix1 (name)
) engine=innodb;
alter table product_tag add foreign key (product_id) references product (id);
alter table product_tag add foreign key (company_id) references company (id);

-- used for storing import headers
create table import_ (
  id                        bigint auto_increment not null,
  type                      enum('CSV', 'URL', 'AMAZON', 'EBAY') not null default 'CSV',
  is_file                   boolean default true,
  success_count             smallint default 0,
  problem_count             smallint default 0,
  company_id                bigint not null,
  created_at                timestamp not null default current_timestamp,
  primary key (id)
) engine=innodb;
alter table import_ add foreign key (company_id) references company (id);

create table import_detail (
  id                        bigint auto_increment not null,
  data                      varchar(1024) not null,
  eligible                  boolean default true,
  imported                  boolean default false,
  problem                   varchar(250),
  last_check                timestamp default current_timestamp,
  import_id                 bigint not null,
  company_id                bigint not null,
  primary key (id)
) engine=innodb;
alter table import_detail add foreign key (company_id) references company (id);

create table link (
  id                        bigint auto_increment not null,
  active                    boolean default true,
  url                       varchar(1024) not null,
  url_hash                  varchar(32) not null,
  sku                       varchar(70),
  name                      varchar(500),
  brand                     varchar(150),
  seller                    varchar(150),
  shipment                  varchar(150),
  price                     decimal(9,2) default 0,
  position                  smallint default 3,
  last_check                datetime,
  last_update               datetime,
  status                    varchar(25) not null default 'TOBE_CLASSIFIED',
  pre_status                varchar(25) not null default 'TOBE_CLASSIFIED',
  problem                   varchar(250),
  retry                     smallint default 0,
  http_status               smallint default 0,
  class_name                varchar(100),
  platform                  varchar(50),
  import_detail_id          bigint,
  product_id                bigint,
  company_id                bigint not null,
  created_at                timestamp not null default current_timestamp,
  primary key (id),
  key ix1 (url_hash),
  key ix2 (status),
  key ix3 (last_check),
  key ix4 (last_update)
) engine=innodb;
alter table link add foreign key (product_id) references product (id);
alter table link add foreign key (company_id) references company (id);

create table link_spec (
  id                        bigint auto_increment not null,
  link_id                   bigint not null,
  _key                      varchar(100),
  _value                    varchar(500),
  product_id                bigint not null,
  company_id                bigint not null,
  primary key (id)
) engine=innodb;
alter table link_spec add foreign key (link_id) references link (id);

create table link_price (
  id                        bigint auto_increment not null,
  link_id                   bigint not null,
  price                     decimal(9,2) default 0,
  position                  smallint default 3,
  product_id                bigint not null,
  company_id                bigint not null,
  created_at                timestamp not null default current_timestamp,
  primary key (id),
  key ix1 (created_at)
) engine=innodb;
alter table link_price add foreign key (link_id) references link (id);

create table link_history (
  id                        bigint auto_increment not null,
  link_id                   bigint not null,
  status                    varchar(25) not null,
  http_status               smallint default 0,
  problem                   varchar(250),
  product_id                bigint not null,
  company_id                bigint not null,
  created_at                timestamp not null default current_timestamp,
  primary key (id),
  key ix1 (created_at)
) engine=innodb;
alter table link_history add foreign key (link_id) references link (id);

create table coupon (
  code                      char(8) not null,
  description               varchar(50),
  days                      smallint default 14,
  issued_company_id         bigint,
  issued_at                 timestamp,
  plan_id                   smallint not null,
  created_at                timestamp not null default current_timestamp,
  primary key (code)
) engine=innodb;
