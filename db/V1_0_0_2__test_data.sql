-- @author mdpinar

insert into country (id, code, name, locale, lang, flag, currency_code, currency_symbol) values (1, 'us', 'United States', 'en_US', 'English', 'us.png', 'USD', '$');
insert into country (id, code, name, locale, lang, flag, currency_code, currency_symbol) values (2, 'uk', 'The United Kingdom', 'en_GB', 'English', 'uk.png', 'GBP', '£');
insert into country (id, code, name, locale, lang, flag, currency_code, currency_symbol) values (3, 'ca', 'Canada', 'en_CA', 'English', 'ca.png', 'CAD', '$');
insert into country (id, code, name, locale, lang, flag, currency_code, currency_symbol) values (4, 'au', 'Australia', 'en_AU', 'English', 'au.png', 'AUD', '$');
insert into country (id, code, name, locale, lang, flag, currency_code, currency_symbol) values (5, 'de', 'Germany', 'de_DE', 'German', 'de.png', 'EUR', '€');
insert into country (id, code, name, locale, lang, flag, currency_code, currency_symbol) values (6, 'nl', 'The Netherlands', 'nl_NL', 'Dutch', 'nl.png', 'EUR', '€');
insert into country (id, code, name, locale, lang, flag, currency_code, currency_symbol) values (7, 'fr', 'France', 'fr_FR', 'French', 'fr.png', 'EUR', '€');
insert into country (id, code, name, locale, lang, flag, currency_code, currency_symbol) values (8, 'it', 'Italy', 'it_IT', 'Italian', 'it.png', 'EUR', '€');
insert into country (id, code, name, locale, lang, flag, currency_code, currency_symbol) values (9, 'es', 'Spain', 'es_ES', 'Spanish', 'es.png', 'EUR', '€');
insert into country (id, code, name, locale, lang, flag, currency_code, currency_symbol) values (10,'tr', 'Turkey', 'tr_TR', 'Turkish', 'tr.png', 'TRY', '₺');

-- -------------------------------------------------------
--  Amazon site, domain, country_id, class_name)
insert into site (name, domain, country_id, class_name)
values
    ('Amazon US', 'amazon.com', 1, 'xx.Amazon'),
    ('Amazon UK', 'amazon.co.uk', 2, 'xx.Amazon'),
    ('Amazon CA', 'amazon.ca', 3, 'xx.Amazon'),
    ('Amazon AU', 'amazon.com.au', 4, 'xx.Amazon'),
    ('Amazon DE', 'amazon.de', 5, 'xx.Amazon'),
    ('Amazon NL', 'amazon.nl', 6, 'xx.Amazon'),
    ('Amazon FR', 'amazon.fr', 7, 'xx.Amazon'),
    ('Amazon IT', 'amazon.it', 8, 'xx.Amazon'),
    ('Amazon ES', 'amazon.es', 9, 'xx.Amazon'),
    ('Amazon TR', 'amazon.com.tr', 10, 'xx.Amazon');

-- -------------------------------------------------------
-- Ebay sites
-- -------------------------------------------------------
insert into site (name, domain, country_id, class_name)
values
    ('Ebay US', 'ebay.com', 1, 'xx.Ebay'),
    ('Ebay UK', 'ebay.co.uk', 2, 'xx.Ebay'),
    ('Ebay CA', 'ebay.ca', 3, 'xx.Ebay'),
    ('Ebay AU', 'ebay.com.au', 4, 'xx.Ebay'),
    ('Ebay DE', 'ebay.de', 5, 'xx.Ebay'),
    ('Ebay NL', 'ebay.nl', 6, 'xx.Ebay'),
    ('Ebay FR', 'ebay.fr', 7, 'xx.Ebay'),
    ('Ebay IT', 'ebay.it', 8, 'xx.Ebay'),
    ('Ebay ES', 'ebay.es', 9, 'xx.Ebay');

-- -------------------------------------------------------
-- Apple sites
-- -------------------------------------------------------
insert into site (name, domain, country_id, class_name)
values
    ('Apple US', 'apple.com', 1, 'xx.Apple'),
    ('Apple UK', 'apple.com/uk', 2, 'xx.Amazon'),
    ('Apple CA', 'apple.com/ca', 3, 'xx.Amazon'),
    ('Apple AU', 'apple.com/au', 4, 'xx.Amazon'),
    ('Apple DE', 'apple.com/de', 5, 'xx.Amazon'),
    ('Apple NL', 'apple.com/nl', 6, 'xx.Amazon'),
    ('Apple FR', 'apple.com/fr', 7, 'xx.Amazon'),
    ('Apple IT', 'apple.com/it', 8, 'xx.Amazon'),
    ('Apple ES', 'apple.com/es', 9, 'xx.Amazon'),
    ('Apple TR', 'apple.com/tr', 10, 'xx.Amazon');

-- -------------------------------------------------------
-- Lidl sites
-- -------------------------------------------------------
insert into site (name, domain, country_id, class_name)
values
    ('Lidl US', 'lidl.com', 1, 'us.Lidl'),
    ('Lidl UK', 'lidl.co.uk', 2, 'xx.Lidl'),
    ('Lidl DE', 'lidl.de', 5, 'xx.Lidl'),
    ('Lidl NL', 'lidl.nl', 6, 'xx.Lidl'),
    ('Lidl FR', 'lidl.fr', 7, 'xx.Lidl'),
    ('Lidl IT', 'lidl.it', 8, 'xx.Lidl'),
    ('Lidl ES', 'lidl.es', 9, 'xx.Lidl');

-- -------------------------------------------------------
-- Medimarkt sites
-- -------------------------------------------------------
insert into site (name, domain, country_id, class_name)
values
    ('Medimarkt DE', 'medimarkt.de', 5, 'de.Medimarkt'),
    ('Medimarkt NL', 'medimarkt.nl', 6, 'xx.Medimarkt'),
    ('Medimarkt ES', 'medimarkt.es', 9, 'xx.Medimarkt'),
    ('Medimarkt TR', 'medimarkt.com.tr', 10, 'xx.Medimarkt');

-- -------------------------------------------------------
-- Zalando sites
-- -------------------------------------------------------
insert into site (name, domain, country_id, class_name)
values
    ('Zalando UK', 'zalado.co.uk', 2, 'xx.Zalando'),
    ('Zalando DE', 'zalado.de', 5, 'xx.Zalando'),
    ('Zalando NL', 'zalado.nl', 6, 'xx.Zalando'),
    ('Zalando FR', 'zalado.fr', 7, 'xx.Zalando'),
    ('Zalando IT', 'zalado.it', 8, 'xx.Zalando'),
    ('Zalando ES', 'zalado.es', 9, 'xx.Zalando');

-- -------------------------------------------------------
-- Bonprix sites
-- -------------------------------------------------------
insert into site (name, domain, country_id, class_name)
values
    ('Bonprix UK', 'bonprix.co.uk', 2, 'xx.Bonprix'),
    ('Bonprix DE', 'bonprix.de', 5, 'xx.Bonprix'),
    ('Bonprix NL', 'bonprix.nl', 6, 'xx.Bonprix'),
    ('Bonprix FR', 'bonprix.fr', 7, 'xx.Bonprix'),
    ('Bonprix IT', 'bonprix.it', 8, 'xx.Bonprix');

-- -------------------------------------------------------
-- Rakuten sites
-- -------------------------------------------------------
insert into site (name, domain, country_id, class_name)
values
    ('Rakuten DE', 'rakuten.de', 5, 'xx.Rakuten'),
    ('Rakuten FR', 'fr.shopping.rakuten.com', 7, 'xx.Rakuten');

-- -------------------------------------------------------
-- Australian sites
-- -------------------------------------------------------
insert into site (name, domain, country_id, class_name)
values
    ('BigW AU', 'bigw.com.au', 4, 'au.BigW'),
    ('Kogan AU', 'kogan.com', 4, 'au.Kogan'),
    ('The Good Guys AU', 'thegoodguys.com.au', 4, 'au.TheGoodGuys'),
    ('Harvey Norman AU', 'harveynorman.com.au', 4, 'au.HarveyNorman'),
    ('Appliances Online AU', 'appliancesonline.com.au', 4, 'au.AppliancesOnline');

-- -------------------------------------------------------
-- Canadian sites
-- -------------------------------------------------------
insert into site (name, domain, country_id, class_name)
values
    ('BestBuy CA', 'bestbuy.ca', 5, 'ca.BestBuy'),
    ('Canadian Tire CA', 'canadiantire.ca', 5, 'ca.CanadianTire'),
    ('Walmart CA', 'walmart.ca', 5, 'ca.Walmart');

-- -------------------------------------------------------
-- Germany sites
-- -------------------------------------------------------
insert into site (name, domain, country_id, class_name)
values
    ('Notebooks Billiger DE', 'notebooksbilliger.de', 3, 'de.NotebooksBilliger'),
    ('Otto DE', 'otto.de', 3, 'de.Otto');

-- -------------------------------------------------------
-- Spain sites
-- -------------------------------------------------------
insert into site (name, domain, country_id, class_name)
values
    ('Electro King ES', 'electroking.es', 9, 'es.ElectroKing'),
    ('101 Gigas ES', '101gigas.com', 9, 'es.Gigas101'),
    ('Pixmania ES', 'pixmania.es', 9, 'es.Pixmania'),
    ('UlaBox ES', 'ulabox.com', 9, 'es.UlaBox');

-- -------------------------------------------------------
-- France sites
-- -------------------------------------------------------
insert into site (name, domain, country_id, class_name)
values
    ('Auchan FR', 'auchan.fr', 7, 'fr.Auchan'),
    ('CDiscount FR', 'cdiscount.com', 7, 'fr.CDiscount'),
    ('Fnac FR', 'fnac.com', 7, 'fr.Fnac'),
    ('Laredoute FR', 'laredoute.fr', 7, 'fr.Laredoute');

-- -------------------------------------------------------
-- Italy sites
-- -------------------------------------------------------
insert into site (name, domain, country_id, class_name)
values
    ('EPrice IT', 'eprice.it', 8, 'it.EPrice'),
    ('Euronics IT', 'euronics.it', 8, 'it.Euronics'),
    ('MediaWorld IT', 'mediaworld.it', 8, 'it.MediaWorld'),
    ('VidaXL IT', 'vidaxl.it', 8, 'it.VidaXL');

-- -------------------------------------------------------
-- the Netherlands sites
-- -------------------------------------------------------
insert into site (name, domain, country_id, class_name)
values
    ('Bol NL', 'bol.com', 6, 'nl.Bol'),
    ('CoolBlue NL', 'coolblue.nl', 6, 'nl.CoolBlue'),
    ('De Bijenkorf NL', 'debijenkorf.nl', 6, 'nl.DeBijenkorf'),
    ('Wehkamp NL', 'wehkamp.nl', 6, 'nl.Wehkamp');

-- -------------------------------------------------------
-- Turkey sites
-- -------------------------------------------------------
insert into site (name, domain, country_id, class_name)
values
    ('Gittigidiyor TR', 'gittigidiyor.com', 10, 'tr.GittiGidiyor'),
    ('Hepsiburada TR', 'hepsiburada.com', 10, 'tr.HepsiBurada'),
    ('n11 TR', 'n11.com', 10, 'tr.N11'),
    ('Teknosa TR', 'teknosa.com', 10, 'tr.Teknosa'),
    ('Trendyol TR', 'trendyol.com', 10, 'tr.Trendyol');

insert into site (name, domain, country_id, class_name)
values
    ('Argos UK', 'argos.co.uk', 2, 'uk.Argos'),
    ('Asda UK', 'groceries.argos.com', 2, 'uk.Asda'),
    ('Asos UK', 'asos.com', 2, 'uk.Asos'),
    ('Currys UK', 'currys.co.uk', 2, 'uk.Currys'),
    ('Debenhams UK', 'debenhams.com', 2, 'uk.Debenhams'),
    ('NewLook UK', 'newlook.com', 2, 'uk.NewLook'),
    ('Zavvi UK', 'zavvi.com', 2, 'uk.Zavvi');

insert into plan (name, row_limit, price, order_no)
values
    ('Micro', 30, 15, 1),
    ('Small', 100, 30, 2),
    ('Medium', 250, 50, 3),
    ('Professional', 500, 80, 4),
    ('Business', 1000, 120, 5),
    ('Enterprise', 2500, 200, 6);


insert into plan_rows (plan_id, description, order_no)
values
    (1, 'Suitable for trials', 1),
    (1, 'Up to 30 product URLs', 2),
    (1, 'Example: 15 products x 2 competitors, or 10 products x 3 competitors', 3),
    (1, 'Price updates 4 times a Day', 4),
    (1, 'Stock availability monitoring', 5),
    (1, 'Price position comparison', 6),
    (1, 'Batch product import', 7),

    (2, 'Suitable for startups', 1),
    (2, 'Up to 100 product URLs', 2),
    (2, 'Example: 50 products x 2 competitors, or 25 products x 4 competitors', 3),
    (2, 'Includes all other features in Micro Plan', 20),

    (3, 'Suitable for middle-sized companies', 1),
    (3, 'Up to 250 product URLs', 2),
    (3, 'Example: 125 products x 2 competitors, or 50 products x 5 competitors', 3),
    (3, 'Includes all other features in Small Plan', 20),

    (4, 'Suitable for large companies', 1),
    (4, 'Up to 500 product URLs', 2),
    (4, 'Example: 250 products x 2 competitors, or 100 products x 5 competitors', 3),
    (4, 'Automated daily reports in Excel & PDF format', 4),
    (4, 'Includes all other features in Medium Plan', 20),

    (5, 'Suitable for large companies', 1),
    (5, 'Up to 1000 product URLs', 2),
    (5, 'Example: 250 products x 4 competitors, or 100 products x 10 competitors', 3),
    (5, 'API Access', 4),
    (5, 'Includes all other features in Professional Plan', 20),

    (6, 'Suitable for large companies', 1),
    (6, 'Up to 2500 product URLs', 2),
    (6, 'Example: 250 products x 10 competitors, or 500 products x 5 competitors', 3),
    (6, 'Instant price change notification', 4),
    (6, 'Includes all other features in Business Plan', 20);
