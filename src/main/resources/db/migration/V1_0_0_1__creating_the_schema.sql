-- @author mdpinar

create table user (
  id                        bigint unsigned auto_increment not null,
  email                     varchar(100) not null,
  password                  varchar(88) not null,
  name                      varchar(70) not null,
  timezone                  varchar(30),
  privileged                boolean default false,
  banned                    boolean default false,
  banned_at                 timestamp,
  ban_reason                varchar(128),
  created_year              smallint not null default (year(curdate())),
  created_month             char(7) not null default (date_format(curdate(), '%Y-%m')),
  created_at                timestamp not null default current_timestamp,
  primary key (id),
  unique key ix1 (email)
) engine=innodb;

create table plan (
  id                        int not null,
  type                      enum('PUBLIC', 'PRIVATE') not null default 'PUBLIC',
  name                      varchar(15) not null,
  description               varchar(50),
  price                     decimal(6,2) default 0,
  user_limit                smallint default 0,
  link_limit                smallint default 0,
  alarm_limit               smallint default 0,
  created_at                timestamp not null default current_timestamp,
  primary key (id)
) engine=innodb;

create table feature (
  id                        int not null,
  description               varchar(50),
  allowed                   boolean default true,
  order_no                  int not null default 0,
  primary key (id)
) engine=innodb;

create table plan_feature (
  id                        int auto_increment not null,
  plan_id                   int not null,
  feature_id                int not null,
  primary key (id)
) engine=innodb;
alter table plan_feature add foreign key (plan_id) references plan (id);
alter table plan_feature add foreign key (feature_id) references feature (id);

create table account (
  id                        bigint unsigned auto_increment not null,
  name                      varchar(70) not null,
  title                     varchar(255),
  address_1                 varchar(255),
  address_2                 varchar(255),
  postcode                  varchar(8),
  city                      varchar(70),
  state                     varchar(70),
  country                   varchar(2),
  status                    enum('CREATED', 'FREE', 'COUPONED', 'SUBSCRIBED', 'CANCELLED', 'STOPPED', 'BANNED') not null default 'CREATED',
  pre_status                varchar(10) not null default 'CREATED',
  last_status_update        timestamp not null default current_timestamp,
  plan_id                   int,
  user_count                smallint default 1,
  link_count                smallint default 0,
  alarm_count               smallint default 0,
  subs_started_at           timestamp,
  subs_renewal_at           timestamp,
  admin_id                  bigint unsigned not null,
  currency_code             char(3),
  currency_format           varchar(30),
  created_year              smallint not null default (year(curdate())),
  created_month             char(7) not null default (date_format(curdate(), '%Y-%m')),
  created_at                timestamp not null default current_timestamp,
  demo                      boolean default false,
  primary key (id),
  key ix1 (name),
  key ix2 (subs_renewal_at)
) engine=innodb;
alter table account add foreign key (admin_id) references user (id);
alter table account add foreign key (plan_id) references plan (id);

create table account_history (
  id                        bigint unsigned auto_increment not null,
  account_id                bigint unsigned not null,
  status                    enum('CREATED', 'FREE', 'COUPONED', 'SUBSCRIBED', 'CANCELLED', 'STOPPED') not null default 'CREATED',
  plan_id                   int,
  created_year              smallint not null default (year(curdate())),
  created_month             char(7) not null default (date_format(curdate(), '%Y-%m')),
  created_at                timestamp not null default current_timestamp,
  primary key (id)
) engine=innodb;
alter table account_history add foreign key (account_id) references account (id);

create table account_trans (
  id                        bigint unsigned auto_increment not null,
  account_id                bigint unsigned not null,
  event_id                  varchar(255),
  event                     varchar(255) not null,
  successful                boolean default false,
  reason                    varchar(255),
  description               varchar(255),
  file_url                  varchar(255),
  created_year              smallint not null default (year(curdate())),
  created_month             char(7) not null default (date_format(curdate(), '%Y-%m')),
  created_at                timestamp not null default current_timestamp,
  primary key (id),
  key ix1 (created_at)
) engine=innodb;
alter table account_trans add foreign key (account_id) references account (id);

create table member (
  id                        bigint unsigned auto_increment not null,
  email                     varchar(100) not null,
  user_id                   bigint unsigned,
  account_id                bigint unsigned not null,
  role                      enum('VIEWER', 'EDITOR', 'ADMIN', 'SUPER') not null default 'EDITOR',
  pre_status                enum('PENDING', 'JOINED', 'PAUSED', 'LEFT', 'DELETED') not null default 'PENDING',
  status                    enum('PENDING', 'JOINED', 'PAUSED', 'LEFT', 'DELETED') not null default 'PENDING',
  retry                     smallint default 1,
  updated_at                timestamp,
  created_year              smallint not null default (year(curdate())),
  created_month             char(7) not null default (date_format(curdate(), '%Y-%m')),
  created_at                timestamp not null default current_timestamp,
  primary key (id),
  key ix1 (email)
) engine=innodb;
alter table member add foreign key (user_id) references user (id);
alter table member add foreign key (account_id) references account (id);

create table platform (
  id                        bigint unsigned auto_increment not null,
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
  id                        bigint unsigned auto_increment not null,
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
  alarm_id                  bigint unsigned,
  account_id                bigint unsigned not null,
  updated_at                timestamp,
  created_year              smallint not null default (year(curdate())),
  created_month             char(7) not null default (date_format(curdate(), '%Y-%m')),
  created_at                timestamp not null default current_timestamp,
  primary key (id),
  key ix1 (name)
) engine=innodb;
alter table link_group add foreign key (account_id) references account (id);

create table link (
  id                        bigint unsigned auto_increment not null,
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
  platform_id               bigint unsigned,
  group_id                  bigint unsigned,
  account_id                bigint unsigned not null,
  alarm_id                  bigint unsigned,
  checked_at                datetime,
  updated_at                datetime,
  created_year              smallint not null default (year(curdate())),
  created_month             char(7) not null default (date_format(curdate(), '%Y-%m')),
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
  id                        bigint unsigned auto_increment not null,
  link_id                   bigint unsigned not null,
  _key                      varchar(100),
  _value                    varchar(500),
  group_id                  bigint unsigned not null,
  account_id                bigint unsigned not null,
  primary key (id)
) engine=innodb;
alter table link_spec add foreign key (link_id) references link (id);

create table link_price (
  id                        bigint unsigned auto_increment not null,
  link_id                   bigint unsigned not null,
  price                     decimal(9,2) default 0,
  diff_amount               decimal(9,2) default 0,
  diff_rate                 decimal(9,2) default 0,
  group_id                  bigint unsigned not null,
  account_id                bigint unsigned not null,
  created_at                timestamp not null default current_timestamp,
  primary key (id),
  key ix1 (created_at)
) engine=innodb;
alter table link_price add foreign key (link_id) references link (id);

create table link_history (
  id                        bigint unsigned auto_increment not null,
  link_id                   bigint unsigned not null,
  status                    varchar(25) not null,
  http_status               smallint default 0,
  group_id                  bigint unsigned not null,
  account_id                bigint unsigned not null,
  created_at                timestamp not null default current_timestamp,
  primary key (id),
  key ix1 (created_at)
) engine=innodb;
alter table link_history add foreign key (link_id) references link (id);

create table coupon (
  code                      char(7) not null,
  plan_id                   int not null,
  days                      smallint not null,
  description               varchar(128),
  issuer_id                 bigint unsigned,
  issued_id                 bigint unsigned,
  issued_at                 timestamp,
  created_year              smallint not null default (year(curdate())),
  created_month             char(7) not null default (date_format(curdate(), '%Y-%m')),
  created_at                timestamp not null default current_timestamp,
  primary key (code)
) engine=innodb;
alter table coupon add foreign key (plan_id) references plan (id);

create table user_used (
  id                        bigint unsigned auto_increment not null,
  email                     varchar(100) not null,
  whitelisted               boolean default false,
  perm_type                 enum('FREE_USE') not null default 'FREE_USE',
  created_year              smallint not null default (year(curdate())),
  created_month             char(7) not null default (date_format(curdate(), '%Y-%m')),
  created_at                timestamp not null default current_timestamp,
  primary key (id),
  unique key ix1 (email, perm_type)
) engine=innodb;

create table user_session (
  _hash                     varchar(32) not null,
  user_id                   bigint unsigned not null,
  account_id                bigint unsigned not null,
  ip                        varchar(255),
  os                        varchar(30),
  browser                   varchar(100),
  user_agent                varchar(1024),
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
  account_id                bigint unsigned not null,
  plan_id                   int not null,
  description               varchar(255),
  updated_at                timestamp,
  created_year              smallint not null default (year(curdate())),
  created_month             char(7) not null default (date_format(curdate(), '%Y-%m')),
  created_at                timestamp not null default current_timestamp,
  primary key (_hash),
  key ix1 (created_at)
) engine=innodb;
alter table checkout add foreign key (account_id) references account (id);

create table alarm (
  id                        bigint unsigned auto_increment not null,
  subject                   enum('LINK', 'GROUP') not null default 'LINK',
  topic                     enum('PRICE', 'TOTAL', 'STATUS', 'MINIMUM', 'AVERAGE', 'MAXIMUM') not null default 'STATUS',
  status_change             enum('ANY', 'CERTAIN') not null default 'ANY',
  price_change              enum('ANY', 'INCREASED', 'DECREASED', 'OUT_OF_LIMITS') not null default 'ANY',
  certain_status            varchar(10) not null,
  price_lower_limit         decimal(9,2) default 0,
  price_upper_limit         decimal(9,2) default 0,
  last_status               varchar(10) not null,
  last_price                decimal(9,2) default 0,
  to_emails                 varchar(512) not null,
  link_id                   bigint unsigned,
  group_id                  bigint unsigned,
  account_id                bigint unsigned not null,
  triggered_at              timestamp,
  created_year              smallint not null default (year(curdate())),
  created_month             char(7) not null default (date_format(curdate(), '%Y-%m')),
  created_at                timestamp not null default current_timestamp,
  primary key (id)
) engine=innodb;
alter table alarm add foreign key (link_id) references link (id);
alter table alarm add foreign key (group_id) references link_group (id);
alter table alarm add foreign key (account_id) references account (id);

create table ticket (
  id                        bigint unsigned auto_increment not null,
  status                    enum('OPENED', 'IN_PROGRESS', 'WAITING_FOR_USER', 'WAITING_FOR_VERSION', 'CLOSED') not null default 'OPENED',
  priority                  enum('LOW', 'NORMAL', 'HIGH', 'CRITICAL') not null default 'NORMAL',
  type                      enum('SUPPORT', 'FEEDBACK', 'PROBLEM') not null default 'SUPPORT',
  subject                   enum('SUBSCRIPTION', 'PAYMENT', 'LINK', 'GROUP', 'ACCOUNT', 'COUPON', 'OTHER') not null default 'OTHER',
  body                      text not null,
  seen_by_user              boolean default true,
  seen_by_super             boolean default false,
  comment_count             smallint not null default 0,
  user_id                   bigint unsigned not null,
  account_id                bigint unsigned not null,
  progressed_at             timestamp default current_timestamp,
  created_year              smallint not null default (year(curdate())),
  created_month             char(7) not null default (date_format(curdate(), '%Y-%m')),
  created_at                timestamp not null default current_timestamp,
  primary key (id)
) engine=innodb;

create table ticket_comment (
  id                        bigint unsigned auto_increment not null,
  ticket_id                 bigint unsigned not null,
  body                      text not null,
  editable                  boolean default true,
  added_by_user             boolean default true,
  user_id                   bigint unsigned,
  account_id                bigint unsigned not null,
  created_at                timestamp not null default current_timestamp,
  primary key (id)
) engine=innodb;
alter table ticket_comment add foreign key (ticket_id) references ticket (id);

create table ticket_history (
  id                        bigint unsigned auto_increment not null,
  ticket_id                 bigint unsigned not null,
  status                    varchar(20) not null,
  priority                  varchar(8) not null,
  type                      varchar(8) not null,
  subject                   varchar(12) not null,
  user_id                   bigint unsigned,
  account_id                bigint unsigned not null,
  created_at                timestamp not null default current_timestamp,
  primary key (id)
) engine=innodb;
alter table ticket_history add foreign key (ticket_id) references ticket (id);

create table announce (
  id                        bigint unsigned auto_increment not null,
  type                      enum('USER', 'ACCOUNT', 'SYSTEM') not null default 'USER',
  level                     enum('INFO', 'WARNING') not null default 'INFO',
  title                     varchar(50) not null,
  body                      text,
  link                      varchar(128),
  starting_at               date not null,
  ending_at                 date not null,
  user_id                   bigint unsigned,
  account_id                bigint unsigned,
  created_year              smallint not null default (year(curdate())),
  created_month             char(7) not null default (date_format(curdate(), '%Y-%m')),
  created_at                timestamp not null default current_timestamp,
  primary key (id),
  key ix1 (user_id)
) engine=innodb;

create table announce_log (
  id                        bigint unsigned auto_increment not null,
  announce_id               bigint unsigned not null,
  user_id                   bigint unsigned,
  account_id                bigint unsigned,
  created_at                timestamp not null default current_timestamp,
  primary key (id)
) engine=innodb;
alter table announce_log add foreign key (announce_id) references announce (id);

create table access_log (
  id                        bigint unsigned auto_increment not null,
  user_id                   bigint unsigned,
  user_email                varchar(100),
  user_role                 varchar(8),
  account_id                bigint unsigned,
  account_name              varchar(70),
  method                    varchar(6) not null,
  path                      varchar(128) not null,
  path_ext                  varchar(70),
  req_body                  text,
  status                    int default 200,
  res_body                  varchar(1024),
  elapsed                   int default 0,
  slow                      boolean default false,
  ip                        varchar(128),
  agent                     varchar(1024),
  created_year              smallint not null default (year(curdate())),
  created_month             char(7) not null default (date_format(curdate(), '%Y-%m')),
  created_at                timestamp not null default current_timestamp,
  primary key (id)
) engine=innodb;
