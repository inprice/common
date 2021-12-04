-- -----------------------
-- @author mdpinar
-- @since 2021-12-04
-- -----------------------

-- password for all users here is inprice
 
-- super user
insert into user (email, full_name, password, timezone, privileged) values ('super@inprice.io', 'Super User', 'CZQsFJCyYnTAaP92ceumAAThu4+t2GFASCwf4CTSvIQxlMjqNHxF38WOBLARFkvGvTEcDVSMmvE772S3H149aQ', 'Europe/Istanbul', true);

-- admin
insert into user (email, full_name, password, timezone) values ('admin@inprice.io', 'Admin User', 'fFL6hw26ZP+B+zf+TlTk1H6CPonKN3x7f0WQVb+vzPkYohFh2fPikZxuodkSTh6AYCZP6YyYMHZXrEvI3ki99A', 'Europe/Istanbul');
set @admin_id = last_insert_id();

-- demo user
insert into user (email, full_name, password, timezone) values ('demo@inprice.io', 'Demo User', 'fFL6hw26ZP+B+zf+TlTk1H6CPonKN3x7f0WQVb+vzPkYohFh2fPikZxuodkSTh6AYCZP6YyYMHZXrEvI3ki99A', 'Europe/Berlin');
set @demo_user_id = last_insert_id();

-- broker workspace
insert into workspace (name, plan_id, status, subs_started_at, subs_renewal_at, user_count, currency_code, currency_format, admin_id) 
values ('Broker Workspace', 6, 'SUBSCRIBED', now(), '2025-12-31 23:59', 1, 'EUR', '#,##0.00 €', @admin_id);
set @broker_ws_id = last_insert_id();

-- workspace history
insert into workspace_history (workspace_id, status) values (@broker_ws_id, 'CREATED');
insert into workspace_history (workspace_id, status) values (@broker_ws_id, 'SUBSCRIBED');

-- demo workspace
insert into workspace (name, plan_id, status, subs_started_at, subs_renewal_at, user_count, currency_code, currency_format, admin_id) 
values ('Demo Workspace', 2, 'SUBSCRIBED', now(), '2025-12-31 23:59', 2, 'EUR', '#,##0.00 €', @admin_id);
set @demo_ws_id = last_insert_id();

-- workspace history
insert into workspace_history (workspace_id, status) values (@demo_ws_id, 'CREATED');
insert into workspace_history (workspace_id, status) values (@demo_ws_id, 'SUBSCRIBED');

insert into membership (email, user_id, workspace_id, role, status) values ('admin@inprice.io', @admin_id, @demo_ws_id, 'ADMIN', 'JOINED');
insert into membership (email, user_id, workspace_id, role, status) values ('demo@inprice.io', @demo_user_id, @demo_ws_id, 'VIEWER', 'JOINED');
