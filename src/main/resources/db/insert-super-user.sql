-- super user --> pass: 1n.S4per1ce
insert into user (email, password, full_name, timezone, privileged) values ('super@inprice.io', 'CZQsFJCyYnTAaP92ceumAAThu4+t2GFASCwf4CTSvIQxlMjqNHxF38WOBLARFkvGvTEcDVSMmvE772S3H149aQ', 'Super user', 'Europe/Istanbul', true);

-- demo user --> pass: inprice
insert into user (email, full_name, password, timezone) values ('demo@inprice.io', 'Demo User', 'fFL6hw26ZP+B+zf+TlTk1H6CPonKN3x7f0WQVb+vzPkYohFh2fPikZxuodkSTh6AYCZP6YyYMHZXrEvI3ki99A', 'Europe/Berlin');
insert into membership (email, user_id, workspace_id, role, status) values ('demo@inprice.io', 2, 1, 'VIEWER', 'JOINED');