-- @author mdpinar
-- @since 2021-03-13
DELIMITER $$

create procedure sp_refresh_product (
    in in_product_id bigint,
    out productPrice decimal(9,2),
    out basePrice decimal(9,2),
    out minPrice decimal(9,2),
    out avgPrice decimal(9,2),
    out maxPrice decimal(9,2),
    out position varchar(10),
    out alarmId bigint unsigned,
    out smartPriceId bigint unsigned,
    out actives int
  )
begin
  select price, base_price, alarm_id, smart_price_id into @productPrice, @basePrice, @alarmId, @smartPriceId from product where id = in_product_id for update;

  select
    @minPrice := min(case when grup = 'ACTIVE' then price end),
    @avgPrice := avg(case when grup = 'ACTIVE' then price end),
    @maxPrice := max(case when grup = 'ACTIVE' then price end),
    @actives  := sum(case when grup = 'ACTIVE' then 1 else 0 end),
    @tryings  := sum(case when grup = 'TRYING' then 1 else 0 end),
    @waitings := sum(case when grup = 'WAITING' then 1 else 0 end),
    @problems := sum(case when grup = 'PROBLEM' then 1 else 0 end)
  from link where product_id = in_product_id;

  if @minPrice is null then set @minPrice := 0; end if;
  if @avgPrice is null then set @avgPrice := 0; end if;
  if @maxPrice is null then set @maxPrice := 0; end if;
  if @actives  is null then set @actives  := 0; end if;
  if @tryings  is null then set @tryings  := 0; end if;
  if @waitings is null then set @waitings := 0; end if;
  if @problems is null then set @problems := 0; end if;

  set @position    := 'NotSet';
  set @minSeller   := 'NA';
  set @minPlatform := 'NA';
  set @maxSeller   := 'NA';
  set @maxPlatform := 'NA';

  set @minDiff := 0;
  set @avgDiff := 0;
  set @maxDiff := 0;

  if @actives > 0 or @tryings > 0 or @waitings > 0 or @problems > 0 then

    update link set position='NotSet' where product_id=in_product_id;

    if @actives > 1 then

      if @minPrice != @maxPrice then
        select @minSeller := seller, @minPlatform:= p.name from link as l inner join platform as p on p.id = l.platform_id where price = @minPrice;
        select @maxSeller := seller, @maxPlatform:= p.name from link as l inner join platform as p on p.id = l.platform_id where price = @maxPrice;
      else
        set @position := 'Equal';
        set @avgPrice := @minPrice;
      end if;

      if @minPrice != @maxPrice then

        if @minPrice != 0 then set @minDiff := @minPrice - @productPrice; end if;
        if @avgPrice != 0 then set @avgDiff := @avgPrice - @productPrice; end if;
        if @maxPrice != 0 then set @maxDiff := @maxPrice - @productPrice; end if;

        if @productPrice <= @minPrice then 
          set @position := 'Lowest';
          set @minSeller := 'You';
          set @minPlatform := 'Yours';
          set @minPrice := @productPrice;
          set @minDiff := 0;
        elseif @productPrice < @avgPrice then 
          set @position := 'Lower';
        elseif @productPrice = @avgPrice then 
          set @position := 'Average';
          set @avgDiff := 0;
        elseif @productPrice < @maxPrice then 
          set @position := 'Higher';
        elseif @productPrice >= @maxPrice then 
          set @position := 'Highest';
          set @maxSeller := 'You';
          set @maxPlatform := 'Yours';
          set @maxPrice := @productPrice;
          set @maxDiff := 0;
        end if;
      end if;

      if @minPrice != @maxPrice then
        update link set position = 
        (case
            when price <= @minPrice then 'Lowest'
            when price > @minPrice and price < @avgPrice then 'Lower'
            when price = @avgPrice then 'Average'
            when price > @avgPrice and price < @maxPrice then 'Higher'
            when price >= @maxPrice then 'Highest'
          end)
        where product_id=in_product_id and grup='ACTIVE';
      else
        update link set position = 'Equal' where product_id=in_product_id and grup='ACTIVE';
      end if;

    end if;

  end if;

  update product set
    min_seller = @minSeller, min_platform = @minPlatform, min_price = @minPrice, min_diff = @minDiff,
    avg_price = @avgPrice, avg_diff = @avgDiff,
    max_seller = @maxSeller, max_platform = @maxPlatform, max_price = @maxPrice, max_diff = @maxDiff,
    actives = @actives, tryings = @tryings, waitings = @waitings, problems = @problems, 
    position = @position, updated_at = now()
  where id = in_product_id;

  select @productPrice, @basePrice, @minPrice, @avgPrice, @maxPrice, @position, @alarmId, @smartPriceId, @actives 
  into productPrice, basePrice, minPrice, avgPrice, maxPrice, position, alarmId, smartPriceId, actives;

end$$

DELIMITER ;
