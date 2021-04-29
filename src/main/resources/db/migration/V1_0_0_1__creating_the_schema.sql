-- @author mdpinar

create table user (
  id                        bigint auto_increment not null,
  email                     varchar(100) not null,
  password                  varchar(88) not null,
  name                      varchar(70) not null,
  timezone                  varchar(30),
  created_at                timestamp not null default current_timestamp,
  primary key (id),
  unique key ix1 (email)
) engine=innodb;

create table account (
  id                        bigint auto_increment not null,
  name                      varchar(70) not null,
  currency_code             char(3),
  currency_format           varchar(30),
  link_limit                smallint default 0,
  link_count                smallint default 0,
  admin_id                  bigint not null,
  status                    enum('CREATED', 'FREE', 'COUPONED', 'SUBSCRIBED', 'CANCELLED', 'STOPPED') not null default 'CREATED',
  last_status_update        timestamp not null default current_timestamp,
  plan_name                 varchar(20),
  cust_id                   varchar(255),
  subs_id                   varchar(255),
  subs_started_at           timestamp,
  renewal_at                timestamp,
  title                     varchar(255),
  address_1                 varchar(255),
  address_2                 varchar(255),
  postcode                  varchar(8),
  city                      varchar(70),
  state                     varchar(70),
  country                   varchar(2),
  demo                      boolean default false,
  created_at                timestamp not null default current_timestamp,
  primary key (id),
  key ix1 (renewal_at),
  key ix2 (name),
  key ix3 (cust_id)
) engine=innodb;
alter table account add foreign key (admin_id) references user (id);

create table account_history (
  id                        bigint auto_increment not null,
  account_id                bigint not null,
  status                    enum('CREATED', 'FREE', 'COUPONED', 'SUBSCRIBED', 'CANCELLED', 'STOPPED') not null default 'CREATED',
  plan_name                 varchar(20),
  cust_id                   varchar(255),
  subs_id                   varchar(255),
  created_at                timestamp not null default current_timestamp,
  primary key (id)
) engine=innodb;
alter table account_history add foreign key (account_id) references account (id);

create table account_trans (
  id                        bigint auto_increment not null,
  account_id                bigint not null,
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
alter table account_trans add foreign key (account_id) references account (id);

create table member (
  id                        bigint auto_increment not null,
  email                     varchar(100) not null,
  user_id                   bigint,
  account_id                bigint not null,
  role                      enum('VIEWER', 'EDITOR', 'ADMIN') not null default 'EDITOR',
  pre_status                enum('PENDING', 'JOINED', 'PAUSED', 'LEFT', 'DELETED') not null default 'PENDING',
  status                    enum('PENDING', 'JOINED', 'PAUSED', 'LEFT', 'DELETED') not null default 'PENDING',
  retry                     smallint default 1,
  updated_at                timestamp,
  created_at                timestamp not null default current_timestamp,
  primary key (id),
  key ix1 (email)
) engine=innodb;
alter table member add foreign key (user_id) references user (id);
alter table member add foreign key (account_id) references account (id);

create table platform (
  id                        bigint auto_increment not null,
  name                      varchar(50) not null,
  domain                    varchar(70) not null,
  country                   varchar(100) not null,
  class_name                varchar(100) not null,
  currency_code             char(3) not null,
  currency_format           varchar(30) not null,
  status                    varchar(25),
  problem                   varchar(250),
  primary key (id),
  key ix1 (domain)
) engine=innodb;

create table link_group (
  id                        bigint auto_increment not null,
  name                      varchar(128) not null,
  actives                   smallint default 0,
  waitings                  smallint default 0,
  tryings                   smallint default 0,
  problems                  smallint default 0,
  price                     decimal(9,2) default 0,
  level                     enum('LOWEST', 'HIGHEST', 'LOWER', 'AVERAGE', 'HIGHER', 'EQUAL', 'NA') not null default 'NA',
  total                     decimal(9,2) default 0,
  min_platform              varchar(50),
  min_seller                varchar(50),
  min_price                 decimal(9,2) default 0,
  min_diff                  decimal(9,2) default 0,
  avg_price                 decimal(9,2) default 0,
  avg_diff                  decimal(9,2) default 0,
  max_platform              varchar(50),
  max_seller                varchar(50),
  max_price                 decimal(9,2) default 0,
  max_diff                  decimal(9,2) default 0,
  alarm_id                  bigint,
  account_id                bigint not null,
  updated_at                timestamp,
  created_at                timestamp not null default current_timestamp,
  primary key (id),
  key ix1 (name)
) engine=innodb;
alter table link_group add foreign key (account_id) references account (id);

create table link (
  id                        bigint auto_increment not null,
  url                       varchar(1024) not null,
  url_hash                  varchar(32) not null,
  sku                       varchar(70),
  name                      varchar(500),
  brand                     varchar(150),
  seller                    varchar(150),
  shipment                  varchar(150),
  price                     decimal(9,2) default 0,
  level                     enum('LOWEST', 'HIGHEST', 'LOWER', 'AVERAGE', 'HIGHER', 'EQUAL', 'NA') not null default 'NA',
  pre_status                varchar(25) not null default 'TOBE_CLASSIFIED',
  status                    varchar(25) not null default 'TOBE_CLASSIFIED',
  status_group              enum('ACTIVE', 'TRYING', 'WAITING', 'PROBLEM') not null default 'WAITING',
  problem                   varchar(250),
  http_status               smallint default 0,
  retry                     smallint default 0,
  platform_id               bigint,
  group_id                  bigint,
  account_id                bigint not null,
  alarm_id                  bigint,
  checked_at                datetime,
  updated_at                datetime,
  created_at                timestamp not null default current_timestamp,
  primary key (id),
  key ix1 (url_hash),
  key ix2 (status),
  key ix3 (checked_at),
  key ix4 (status_group)
) engine=innodb;
alter table link add foreign key (platform_id) references platform (id);
alter table link add foreign key (group_id) references link_group (id);
alter table link add foreign key (account_id) references account (id);

create table link_spec (
  id                        bigint auto_increment not null,
  link_id                   bigint not null,
  _key                      varchar(100),
  _value                    varchar(500),
  group_id                  bigint not null,
  account_id                bigint not null,
  primary key (id)
) engine=innodb;
alter table link_spec add foreign key (link_id) references link (id);

create table link_price (
  id                        bigint auto_increment not null,
  link_id                   bigint not null,
  price                     decimal(9,2) default 0,
  diff_amount               decimal(9,2) default 0,
  diff_rate                 decimal(9,2) default 0,
  group_id                  bigint not null,
  account_id                bigint not null,
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
  group_id                  bigint not null,
  account_id                bigint not null,
  created_at                timestamp not null default current_timestamp,
  primary key (id),
  key ix1 (created_at)
) engine=innodb;
alter table link_history add foreign key (link_id) references link (id);

create table coupon (
  code                      char(8) not null,
  plan_name                 varchar(20) not null,
  days                      smallint not null,
  description               varchar(128),
  issuer_id                 bigint,
  issued_id                 bigint,
  issued_at                 timestamp,
  created_at                timestamp not null default current_timestamp,
  primary key (code)
) engine=innodb;

create table notice (
  id                        bigint auto_increment not null,
  title                     varchar(100) not null,
  content                   varchar(1024) not null,
  level                     enum('USER', 'ACCOUNT', 'APPLICATION') not null default 'USER',
  email                     varchar(100),
  account_id                bigint,
  lasted_at                 timestamp not null,
  created_at                timestamp not null default current_timestamp,
  primary key (id),
  key ix1 (lasted_at)
) engine=innodb;

create table user_notice (
  id                        bigint auto_increment not null,
  email                     varchar(100) not null,
  account_id                bigint,
  notice_id                 bigint,
  created_at                timestamp not null default current_timestamp,
  primary key (id),
  key ix1 (email)
) engine=innodb;
alter table user_notice add foreign key (notice_id) references notice (id);

create table user_banned (
  id                        bigint auto_increment not null,
  email                     varchar(100) not null,
  reason                    varchar(255),
  voided                    boolean default false,
  created_at                timestamp not null default current_timestamp,
  primary key (id),
  key ix1 (email, voided)
) engine=innodb;

create table user_used (
  id                        bigint auto_increment not null,
  email                     varchar(100) not null,
  whitelisted               boolean default false,
  perm_type                 enum('FREE_USE') not null default 'FREE_USE',
  created_at                timestamp not null default current_timestamp,
  primary key (id),
  unique key ix1 (email, perm_type)
) engine=innodb;

create table user_session (
  _hash                     varchar(32) not null,
  user_id                   bigint not null,
  account_id                bigint not null,
  ip                        varchar(255),
  os                        varchar(30),
  browser                   varchar(100),
  user_agent                varchar(500),
  accessed_at               timestamp not null default current_timestamp,
  primary key (_hash),
  key ix1 (accessed_at)
) engine=innodb;
alter table user_session add foreign key (user_id) references user (id);
alter table user_session add foreign key (account_id) references account (id);

create table checkout (
  _hash                     varchar(32) not null,
  session_id                varchar(255),
  status                    enum('PENDING', 'SUCCESSFUL', 'EXPIRED', 'CANCELLED', 'FAILED') not null default 'PENDING',
  account_id                bigint not null,
  plan_name                 varchar(20) not null,
  description               varchar(255),
  updated_at                timestamp,
  created_at                timestamp not null default current_timestamp,
  primary key (_hash),
  key ix1 (created_at)
) engine=innodb;
alter table checkout add foreign key (account_id) references account (id);

create table alarm (
  id                        bigint auto_increment not null,
  subject                   enum('LINK', 'GROUP') not null default 'LINK',
  topic                     enum('PRICE', 'MINIMUM', 'AVERAGE', 'MAXIMUM', 'TOTAL', 'STATUS') not null default 'STATUS',
  status_change             enum('ANY', 'CERTAIN') not null default 'ANY',
  price_change              enum('ANY', 'INCREASED', 'DECREASED', 'OUT_OF_LIMITS') not null default 'ANY',
  certain_status            varchar(10) not null,
  price_lower_limit         decimal(9,2) default 0,
  price_upper_limit         decimal(9,2) default 0,
  last_status               varchar(10) not null,
  last_price                decimal(9,2) default 0,
  to_emails                 varchar(512) not null,
  link_id                   bigint,
  group_id                  bigint,
  account_id                bigint not null,
  triggered_at              timestamp,
  created_at                timestamp not null default current_timestamp,
  primary key (id)
) engine=innodb;
alter table alarm add foreign key (link_id) references link (id);
alter table alarm add foreign key (group_id) references link_group (id);
alter table alarm add foreign key (account_id) references account (id);

create table ticket (
  id                        bigint auto_increment not null,
  subject                   enum('OTHER', 'LINK', 'GROUP', 'ACCOUNT') not null default 'OTHER',
  type                      enum('REQUEST', 'FEEDBACK', 'SUPPORT', 'COMPLAINT') not null default 'REQUEST',
  status                    enum('WAITING', 'ANSWERED', 'CLOSED') not null default 'WAITING',
  description               varchar(1024) not null,
  parent_id                 bigint,
  link_id                   bigint,
  group_id                  bigint,
  account_id                bigint not null,
  answered_at               timestamp,
  created_at                timestamp not null default current_timestamp,
  primary key (id)
) engine=innodb;
alter table ticket add foreign key (account_id) references account (id);
