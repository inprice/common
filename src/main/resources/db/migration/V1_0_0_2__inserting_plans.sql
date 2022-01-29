-- @author mdpinar
-- 2021-05-10

insert into plan (name, description, product_limit, alarm_limit, user_limit, price) values ('Standard Plan', 'Suitable for small businesses.', 100, 10, 1, 29.00);
set @standard_plan_id = last_insert_id();

insert into plan (name, description, product_limit, alarm_limit, user_limit, price) values ('Professional Plan', 'Suitable for midsize companies.', 250, 25, 2, 59.00);
set @professional_plan_id = last_insert_id();

insert into plan (name, description, product_limit, alarm_limit, user_limit, price) values ('Premium Plan', 'Suitable for large companies.', 1000, 200, 5, 129.00);
set @premium_plan_id = last_insert_id();

insert into plan (name, description, product_limit, alarm_limit, user_limit, price) values ('Enterprise Plan', 'Suitable for enterprises.', 5000, 1000, 10, 229.00);
set @enterprise_plan_id = last_insert_id();
