-- @author mdpinar

update platform set browser_possibility=60 where domain like 'ebay%';
update platform set browser_possibility=60 where name like 'mediamarkt%';
update platform set browser_possibility=90 where domain like 'amazon%';
update platform set browser_possibility=100 where domain like 'lidl%';
update platform set browser_possibility=100, loading_main_page_first=true, extra_timeout=5 where domain like 'bonprix%';
