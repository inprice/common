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
--  Amazon sites
-- -------------------------------------------------------
insert into site (name, domain, url, country_id, class_name)
values
    ('Amazon US', 'amazon.com', 'https://www.amazon.com', 1, 'xx.Amazon'),
    ('Amazon UK', 'amazon.co.uk', 'https://www.amazon.co.uk', 2, 'xx.Amazon'),
    ('Amazon CA', 'amazon.ca', 'https://www.amazon.ca', 3, 'xx.Amazon'),
    ('Amazon AU', 'amazon.com.au', 'https://www.amazon.com.au', 4, 'xx.Amazon'),
    ('Amazon DE', 'amazon.de', 'https://www.amazon.de', 5, 'xx.Amazon'),
    ('Amazon NL', 'amazon.nl', 'https://www.amazon.nl', 6, 'xx.Amazon'),
    ('Amazon FR', 'amazon.fr', 'https://www.amazon.fr', 7, 'xx.Amazon'),
    ('Amazon IT', 'amazon.it', 'https://www.amazon.it', 8, 'xx.Amazon'),
    ('Amazon ES', 'amazon.es', 'https://www.amazon.es', 9, 'xx.Amazon'),
    ('Amazon TR', 'amazon.com.tr', 'https://www.amazon.com.tr', 10, 'xx.Amazon');

-- -------------------------------------------------------
-- Ebay sites
-- -------------------------------------------------------
insert into site (name, domain, url, country_id, class_name)
values
    ('Ebay US', 'ebay.com', 'https://www.ebay.com', 1, 'xx.Ebay'),
    ('Ebay UK', 'ebay.co.uk', 'https://www.ebay.co.uk', 2, 'xx.Ebay'),
    ('Ebay CA', 'ebay.ca', 'https://www.ebay.ca', 3, 'xx.Ebay'),
    ('Ebay AU', 'ebay.com.au', 'https://www.ebay.com.au', 4, 'xx.Ebay'),
    ('Ebay DE', 'ebay.de', 'https://www.ebay.de', 5, 'xx.Ebay'),
    ('Ebay NL', 'ebay.nl', 'https://www.ebay.nl', 6, 'xx.Ebay'),
    ('Ebay FR', 'ebay.fr', 'https://www.ebay.fr', 7, 'xx.Ebay'),
    ('Ebay IT', 'ebay.it', 'https://www.ebay.it', 8, 'xx.Ebay'),
    ('Ebay ES', 'ebay.es', 'https://www.ebay.es', 9, 'xx.Ebay');

-- -------------------------------------------------------
-- Apple sites
-- -------------------------------------------------------
insert into site (name, domain, url, country_id, class_name)
values
    ('Apple US', 'apple.com', 'https://www.apple.com', 1, 'xx.Apple'),
    ('Apple UK', 'apple.com/uk', 'https://www.amazon.com/uk', 2, 'xx.Amazon'),
    ('Apple CA', 'apple.com/ca', 'https://www.amazon.com/ca', 3, 'xx.Amazon'),
    ('Apple AU', 'apple.com/au', 'https://www.amazon.com/au', 4, 'xx.Amazon'),
    ('Apple DE', 'apple.com/de', 'https://www.amazon.com/de', 5, 'xx.Amazon'),
    ('Apple NL', 'apple.com/nl', 'https://www.amazon.com/nl', 6, 'xx.Amazon'),
    ('Apple FR', 'apple.com/fr', 'https://www.amazon.com/fr', 7, 'xx.Amazon'),
    ('Apple IT', 'apple.com/it', 'https://www.amazon.com/it', 8, 'xx.Amazon'),
    ('Apple ES', 'apple.com/es', 'https://www.amazon.com/es', 9, 'xx.Amazon'),
    ('Apple TR', 'apple.com/tr', 'https://www.amazon.com/tr', 10, 'xx.Amazon');

-- -------------------------------------------------------
-- Zalando sites
-- -------------------------------------------------------
insert into site (name, domain, url, country_id, class_name)
values
    ('Zalando UK', 'zalado.co.uk', 'https://www.zalando.co.uk', 2, 'xx.Zalando'),
    ('Zalando DE', 'zalado.de', 'https://www.zalando.de', 5, 'xx.Zalando'),
    ('Zalando NL', 'zalado.nl', 'https://www.zalando.nl', 6, 'xx.Zalando'),
    ('Zalando FR', 'zalado.fr', 'https://www.zalando.fr', 7, 'xx.Zalando'),
    ('Zalando IT', 'zalado.it', 'https://www.zalando.it', 8, 'xx.Zalando'),
    ('Zalando ES', 'zalado.es', 'https://www.zalando.es', 9, 'xx.Zalando');


-- -------------------------------------------------------
-- Australian sites
-- -------------------------------------------------------
insert into site (name, domain, url, country_id, class_name)
values
    ('AppliancesOnline AU', 'appliancesonline.com.au', 'https://www.appliancesonline.com.au', 4, 'au.AppliancesOnline'),
    ('AppliancesOnline AU', 'appliancesonline.com.au', 'https://www.appliancesonline.com.au', 4, 'au.AppliancesOnline'),
    ('Ebay US', 'ebay.com', 'https://www.ebay.com', 1, 'xx.Ebay');


-- /


insert into site (name, domain, url, logo, logo_mini, currency_code, currency_symbol, thousand_separator, decimal_separator, country_id, class_name)
values ('Argos UK', 'argos.co.uk', 'https://www.argos.co.uk', 'https://www.argos.co.uk/homepage-assets/static/img/favicon.ico',
        'https://www.argos.co.uk/homepage-assets/static/img/favicon.ico', 'GBP', '£',',','.', 1, 'uk.Argos');

insert into site (name, domain, url, logo, logo_mini, currency_code, currency_symbol, thousand_separator, decimal_separator, country_id, class_name)
values ('Gittigidiyor TR', 'gittigidiyor.com', 'https://www.gittigidiyor.com', 'https://www.gittigidiyor.com/fred/framework/assets/img/core/apple-touch-icons/apple-icon-76x76-precomposed.png',
        'https://www.gittigidiyor.com/fred/framework/assets/img/core/apple-touch-icons/apple-icon-precomposed.png', 'TRY', 'TL',',','.', 3, 'tr.GittiGidiyor');

insert into site (name, domain, url, logo, logo_mini, currency_code, currency_symbol, thousand_separator, decimal_separator, country_id, class_name)
values ('Hepsiburada TR', 'hepsiburada.com', 'https://www.hepsiburada.com', 'https://images.hepsiburada.net/assets/sfstatic/Content/images/Icon.png',
        'https://images.hepsiburada.net/assets/sfstatic/Content/images/favicon.ico', 'TRY', 'TL',',','.', 3, 'tr.HepsiBurada');

insert into site (name, domain, url, logo, logo_mini, currency_code, currency_symbol, thousand_separator, decimal_separator, country_id, class_name)
values ('n11 TR', 'n11.com', 'https://www.n11.com', 'https://n11scdn.akamaized.net/a1/org/15/11/30/54/12/08/66/82/53/32/07/07/87650256438692757713.png',
        'https://n11scdn.akamaized.net/static/favicon.ico', 'TRY', 'TL',',','.', 3, 'io.inprice.scrapper.manager.websites.tr.N11');

insert into plan (plan_type, name, desc_1, desc_2, desc_3, row_limit, price, price_1, order_no, css_class)
values ('NORMAL', 'Basic', '30 Days Plan', '$10 for a month', '$100 for a year', 30, 10, 100, 1, '');

insert into sector (name) values ('Finance');
insert into sector (name) values ('Telecommunication');
insert into sector (name) values ('Gaming');
insert into sector (name) values ('Entertainment');
