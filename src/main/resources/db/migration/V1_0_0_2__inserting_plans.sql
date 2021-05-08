-- @author mdpinar
-- 2021-05-05

insert into plan (name, features, alarm_limit, link_limit, price) values
  ('Basic Plan', 'Up to 25 links<br>Up to 5 alarms', 5, 25, 20.00),
  ('Starter Plan', 'Up to 50 links<br>Up to 10 alarms', 10, 50, 40.00),
  ('Standard Plan', 'Up to 100 links<br>Up to 20 alarms', 20, 100, 60.00),
  ('Pro Plan', 'Up to 250 links<br>Up to 50 alarms', 50, 250, 80.00),
  ('Premium Plan', 'Up to 500 links<br>Up to 100 alarms', 100, 500, 150.00),
  ('Enterprise Plan', 'Up to 1000 links<br>Up to 250 alarms', 250, 1000, 220.00)
  ;
