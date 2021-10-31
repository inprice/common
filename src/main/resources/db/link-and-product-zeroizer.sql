set foreign_key_checks=0;

truncate link_history;
truncate link_price;
truncate link_spec;

update inprice.link
set sku=null, name=null, brand=null, seller=null, shipment=null, price=0, price_direction=0, position='NotSet',
pre_status='TOBE_CLASSIFIED', status='TOBE_CLASSIFIED', grup='WAITING', parse_code='OK', parse_problem=null,
retry=0, platform_id=null, checked_at=null, updated_at=null
where id> 0;

update inprice.product
set actives=0, waitings=0, tryings=0, problems=0, position='NotSet', 
min_platform=null, min_seller=null, min_price=0, min_diff=0,
avg_price=0, avg_diff=0,
max_platform=null, max_seller=null, max_price=0, max_diff=0,
suggested_price=0, suggested_price_problem=null, updated_at=null
where id>0;

set foreign_key_checks=1;
