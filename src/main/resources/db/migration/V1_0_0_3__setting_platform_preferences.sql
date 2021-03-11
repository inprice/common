-- @author mdpinar
-- 2021-02-21

update platform set browser_possibility=60 where domain like 'ebay%';
update platform set browser_possibility=60 where name like 'mediamarkt%';
update platform set browser_possibility=75 where domain like 'zalando%';
update platform set browser_possibility=80 where domain like 'kogan%';
update platform set browser_possibility=90 where domain like 'amazon%';
update platform set browser_possibility=100 where domain like 'lidl%';
update platform set browser_possibility=80, extra_timeout=5 where domain like 'thegoodguys%';
update platform set browser_possibility=100, extra_timeout=7 where domain like 'bigw%';
update platform set browser_possibility=100, extra_timeout=5, load_url_first=domain where domain like 'bonprix%';
update platform set browser_possibility=100, extra_timeout=5, js_rendered_body=true where domain like 'canadiantire%';

update platform set htmlunit_rendering=true where domain like 'walmart%';

update platform set browser_possibility=100, extra_timeout=5 where domain = 'bestbuy.ca';
update platform set htmlunit_rendering=true, extra_timeout=5 where domain = 'bestbuy.com';
