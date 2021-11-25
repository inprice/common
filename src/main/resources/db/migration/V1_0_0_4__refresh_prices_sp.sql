-- _author mdpinar
-- _since 2021-03-13
DELIMITER $$

create procedure sp_refresh_product (
    in in_product_id bigint,
    out productPrice decimal(10,2),
    out basePrice decimal(10,2),
    out minPrice decimal(10,2),
    out avgPrice decimal(10,2),
    out maxPrice decimal(10,2),
    out position varchar(10),
    out alarmId bigint unsigned,
    out smartPriceId bigint unsigned,
    out actives int
  )
begin
  declare _productPrice decimal(10,2) default 0;
  declare _basePrice decimal(10,2) default 0;
  declare _alarmId bigint unsigned;
  declare _smartPriceId bigint unsigned;

  declare _position varchar(7) default 'NotSet';
  declare _minPrice decimal(10,2) default 0;
  declare _minSeller varchar(50) default 'NA';
  declare _minPlatform varchar(50) default 'NA';
  declare _avgPrice decimal(10,2) default 0;
  declare _maxPrice decimal(10,2) default 0;
  declare _maxSeller varchar(50) default 'NA';
  declare _maxPlatform varchar(50) default 'NA';
  
  declare _actives smallint default 0;
  declare _tryings smallint default 0;
  declare _waitings smallint default 0;
  declare _problems smallint default 0;

  select price, base_price, alarm_id, smart_price_id into _productPrice, _basePrice, _alarmId, _smartPriceId from product where id = in_product_id for update;

  select sum(if(grup = 'ACTIVE', 1, 0)), sum(if(grup = 'TRYING', 1, 0)), sum(if(grup = 'WAITING', 1, 0)), sum(if(grup = 'PROBLEM', 1, 0))
  into _actives, _tryings, _waitings, _problems from link where product_id = in_product_id;

  if _actives > 0 or _tryings > 0 or _waitings > 0 or _problems > 0 then
    update link set position='NotSet' where product_id=in_product_id;

    if _actives > 1 then
      select ifnull(min(price),0), ifnull(avg(price),0), ifnull(max(price),0) into _minPrice, _avgPrice, _maxPrice from link where product_id = in_product_id and grup = 'ACTIVE';

      if _minPrice != _maxPrice then
        select seller, p.name into _minSeller, _minPlatform from link as l inner join platform as p on p.id = l.platform_id where price = _minPrice;
        select seller, p.name into _maxSeller, _maxPlatform from link as l inner join platform as p on p.id = l.platform_id where price = _maxPrice;

        if _productPrice <= _minPrice then 
          set _position := 'Lowest';
          set _minSeller := 'You';
          set _minPlatform := 'Yours';
          set _minPrice := _productPrice;
        elseif _productPrice < _avgPrice then 
          set _position := 'Lower';
        elseif _productPrice = _avgPrice then 
          set _position := 'Average';
        elseif _productPrice < _maxPrice then 
          set _position := 'Higher';
        elseif _productPrice >= _maxPrice then 
          set _position := 'Highest';
          set _maxSeller := 'You';
          set _maxPlatform := 'Yours';
          set _maxPrice := _productPrice;
        end if;

        update link set position = 
        (case
            when price <= _minPrice then 'Lowest'
            when price > _minPrice and price < _avgPrice then 'Lower'
            when price = _avgPrice then 'Average'
            when price > _avgPrice and price < _maxPrice then 'Higher'
            when price >= _maxPrice then 'Highest'
          end)
        where product_id=in_product_id and grup='ACTIVE';
      else
        set _position := 'Equal';
        set _avgPrice := _minPrice;
        update link set position = 'Equal' where product_id=in_product_id and grup='ACTIVE';
      end if;

    end if;
  end if;

  update product set
    min_seller = _minSeller, min_platform = _minPlatform, min_price = _minPrice, min_diff = (_minPrice - _productPrice),
    avg_price = _avgPrice, avg_diff = (_avgPrice - _productPrice),
    max_seller = _maxSeller, max_platform = _maxPlatform, max_price = _maxPrice, max_diff = (_maxPrice - _productPrice),
    actives = _actives, tryings = _tryings, waitings = _waitings, problems = _problems, 
    position = _position, updated_at = now()
  where id = in_product_id;

  select _productPrice, _basePrice, _minPrice, _avgPrice, _maxPrice, _position, _alarmId, _smartPriceId, _actives 
  into productPrice, basePrice, minPrice, avgPrice, maxPrice, position, alarmId, smartPriceId, actives;

end$$

DELIMITER ;
