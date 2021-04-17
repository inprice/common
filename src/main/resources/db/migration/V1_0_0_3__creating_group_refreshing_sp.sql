-- @author mdpinar
-- @since 2021-13-03
DELIMITER $$

create procedure sp_refresh_group(in in_group_id bigint)
begin
  start transaction;

  select @groupPrice := price from link_group where id = in_group_id for update;

  select
    @total    := sum(case when status_group = 'ACTIVE' then price else 0 end),
    @minPrice := min(case when status_group = 'ACTIVE' then price end),
    @avgPrice := avg(case when status_group = 'ACTIVE' then price end),
    @maxPrice := max(case when status_group = 'ACTIVE' then price end),
    @actives  := sum(case when status_group = 'ACTIVE' then 1 else 0 end),
    @tryings  := sum(case when status_group = 'TRYING' then 1 else 0 end),
    @waitings := sum(case when status_group = 'WAITING' then 1 else 0 end),
    @problems := sum(case when status_group = 'PROBLEM' then 1 else 0 end)
  from link where group_id = in_group_id;

  set @level       := 'NA';
  set @minSeller   := null;
  set @minPlatform := null;
  set @maxSeller   := null;
  set @maxPlatform := null;

  set @minDiff := 0;
  set @avgDiff := 0;
  set @maxDiff := 0;

  if @total is null then set @total := 0; end if;
  if @minPrice is null then set @minPrice := 0; end if;
  if @avgPrice is null then set @avgPrice := 0; end if;
  if @maxPrice is null then set @maxPrice := 0; end if;

  if @actives > 0 or @tryings > 0 or @waitings > 0 or @problems > 0 then

    set @total := ROUND(@total, 2);
    set @avgPrice := ROUND(@avgPrice, 2);
    
    update link set level='NA' where group_id=in_group_id;
      
    if @actives > 1 then
      select @minSeller := seller, @minPlatform:= p.name from link as l inner join platform as p on p.id = l.platform_id where price = @minPrice;
      select @maxSeller := seller, @maxPlatform:= p.name from link as l inner join platform as p on p.id = l.platform_id where price = @maxPrice;
  
      if @groupPrice > 0 then
        set @minDiff := ROUND(((@minPrice / @groupPrice)-1) * 100, 2);
        set @avgDiff := ROUND(((@avgPrice / @groupPrice)-1) * 100, 2);
        set @maxDiff := ROUND(((@maxPrice / @groupPrice)-1) * 100, 2);
          
        if @groupPrice <= @minPrice then 
          set @minSeller := 'You';
          set @minPlatform := 'Yours';
          set @minPrice := @groupPrice;
          set @level := 'LOWEST';
        elseif @groupPrice < @avgPrice then 
          set @level := 'LOWER';
        elseif @groupPrice = @avgPrice then 
          set @level := 'AVERAGE';
        elseif @groupPrice < @maxPrice then 
          set @level := 'HIGHER';
        elseif @groupPrice >= @maxPrice then 
          set @minSeller := 'You';
          set @minPlatform := 'Yours';
          set @maxPrice := @groupPrice;
          set @level := 'HIGHEST';
        end if;
      end if;
  
      update link set level='MIN' where group_id=in_group_id and status_group='ACTIVE' and price<=@minPrice;
      update link set level='AVG' where group_id=in_group_id and status_group='ACTIVE' and price>@minPrice and price<@maxPrice;
      update link set level='MAX' where group_id=in_group_id and status_group='ACTIVE' and price>=@maxPrice;
  
    end if;

  end if;
  
  -- select @minSeller, @minPlatform, @minPrice, @minDiff, @avgPrice, @avgDiff, @maxSeller, @maxPlatform, @maxPrice, @maxDiff, @actives, @tryings, @waitings, @problems, @level, @groupPrice, @total;

  update link_group set
    min_seller = @minSeller, min_platform = @minPlatform, min_price = @minPrice, min_diff = @minDiff,
    avg_price = @avgPrice, avg_diff = @avgDiff,
    max_seller = @maxSeller, max_platform = @maxPlatform, max_price = @maxPrice, max_diff = @maxDiff,
    actives = @actives, tryings = @tryings, waitings = @waitings, problems = @problems, 
    level = @level, total = @total, updated_at = now()
  where id = in_group_id;
    
  commit;
end$$

DELIMITER ;
