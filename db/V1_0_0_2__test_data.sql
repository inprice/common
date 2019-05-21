-- @author mdpinar

insert into country (id, code, name, locale, lang, flag) values (1, 'us', 'United States', 'en_US', 'English', 'us.png');
insert into country (id, code, name, locale, lang, flag) values (2, 'uk', 'The United Kingdom', 'en_GB', 'English', 'uk.png');
insert into country (id, code, name, locale, lang, flag) values (3, 'ca', 'Canada', 'en_CA', 'English', 'ca.png');
insert into country (id, code, name, locale, lang, flag) values (4, 'au', 'Australia', 'en_AU', 'English', 'au.png');
insert into country (id, code, name, locale, lang, flag) values (5, 'de', 'Germany', 'de_DE', 'German', 'de.png');
insert into country (id, code, name, locale, lang, flag) values (6, 'nl', 'The Netherlands', 'nl_NL', 'Dutch', 'nl.png');
insert into country (id, code, name, locale, lang, flag) values (7, 'fr', 'France', 'fr_FR', 'French', 'fr.png');
insert into country (id, code, name, locale, lang, flag) values (8, 'it', 'Italy', 'it_IT', 'Italian', 'it.png');
insert into country (id, code, name, locale, lang, flag) values (9, 'es', 'Spain', 'es_ES', 'Spanish', 'es.png');
insert into country (id, code, name, locale, lang, flag) values (10,'tr', 'Turkey', 'tr_TR', 'Turkish', 'tr.png');
insert into country (id, code, name, locale, lang, flag) values (11,'jp', 'Japan', 'ja_JP', 'Japanese', 'jp.png');
insert into country (id, code, name, locale, lang, flag) values (12,'br', 'Brazil', 'pr_BR', 'Portuguese', 'br.png');

-- -------------------------------------------------------
insert into site (name, domain, url, logo, logo_mini, currency_code, currency_symbol, thousand_separator, decimal_separator, country_id, class_name)
values ('Amazon US', 'amazon.com', 'https://www.amazon.com', null, null, 'USD', '$',',','.', 1, 'xx.Amazon');

insert into site (name, domain, url, logo, logo_mini, currency_code, currency_symbol, thousand_separator, decimal_separator, country_id, class_name)
values ('Amazon UK', 'amazon.co.uk', 'https://www.amazon.co.uk', null, null, 'GBP', '£',',','.', 2, 'xx.Amazon');

insert into site (name, domain, url, logo, logo_mini, currency_code, currency_symbol, thousand_separator, decimal_separator, country_id, class_name)
values ('Amazon CA', 'amazon.ca', 'https://www.amazon.ca', null, null, 'CAD', '$',',','.', 3, 'xx.Amazon');

insert into site (name, domain, url, logo, logo_mini, currency_code, currency_symbol, thousand_separator, decimal_separator, country_id, class_name)
values ('Amazon AU', 'amazon.com.au', 'https://www.amazon.com.au', null, null, 'AUD', '$',',','.', 4, 'xx.Amazon');

insert into site (name, domain, url, logo, logo_mini, currency_code, currency_symbol, thousand_separator, decimal_separator, country_id, class_name)
values ('Amazon DE', 'amazon.de', 'https://www.amazon.de', null, null, 'EUR', '€',',','.', 5, 'xx.Amazon');

insert into site (name, domain, url, logo, logo_mini, currency_code, currency_symbol, thousand_separator, decimal_separator, country_id, class_name)
values ('Amazon NL', 'amazon.nl', 'https://www.amazon.nl', null, null, 'EUR', '€',',','.', 6, 'xx.Amazon');

insert into site (name, domain, url, logo, logo_mini, currency_code, currency_symbol, thousand_separator, decimal_separator, country_id, class_name)
values ('Amazon FR', 'amazon.fr', 'https://www.amazon.fr', null, null, 'EUR', '€',',','.', 7, 'xx.Amazon');

insert into site (name, domain, url, logo, logo_mini, currency_code, currency_symbol, thousand_separator, decimal_separator, country_id, class_name)
values ('Amazon IT', 'amazon.it', 'https://www.amazon.it', null, null, 'EUR', '€',',','.', 8, 'xx.Amazon');

insert into site (name, domain, url, logo, logo_mini, currency_code, currency_symbol, thousand_separator, decimal_separator, country_id, class_name)
values ('Amazon ES', 'amazon.es', 'https://www.amazon.es', null, null, 'EUR', '€',',','.', 9, 'xx.Amazon');

insert into site (name, domain, url, logo, logo_mini, currency_code, currency_symbol, thousand_separator, decimal_separator, country_id, class_name)
values ('Amazon TR', 'amazon.com.tr', 'https://www.amazon.com.tr', null, null, 'TRY', 'TL',',','.', 10, 'xx.Amazon');

insert into site (name, domain, url, logo, logo_mini, currency_code, currency_symbol, thousand_separator, decimal_separator, country_id, class_name)
values ('Amazon JP', 'amazon.jp', 'https://www.amazon.jp', null, null, 'YEN', '¥',',','.', 11, 'xx.Amazon');

insert into site (name, domain, url, logo, logo_mini, currency_code, currency_symbol, thousand_separator, decimal_separator, country_id, class_name)
values ('Amazon BR', 'amazon.com.br', 'https://www.amazon.com.br', null, null, 'BRL', '$',',','.', 12, 'xx.Amazon');

-- -------------------------------------------------------
insert into site (name, domain, url, logo, logo_mini, currency_code, currency_symbol, thousand_separator, decimal_separator, country_id, class_name)
values ('Ebay US', 'ebay.com', 'https://www.ebay.com', null, null, 'USD', '$',',','.', 1, 'xx.Ebay');

insert into site (name, domain, url, logo, logo_mini, currency_code, currency_symbol, thousand_separator, decimal_separator, country_id, class_name)
values ('Ebay UK', 'ebay.co.uk', 'https://www.ebay.co.uk', null, null, 'GBP', '£',',','.', 2, 'xx.Ebay');

insert into site (name, domain, url, logo, logo_mini, currency_code, currency_symbol, thousand_separator, decimal_separator, country_id, class_name)
values ('Ebay CA', 'ebay.ca', 'https://www.ebay.ca', null, null, 'CAD', '$',',','.', 3, 'xx.Ebay');

insert into site (name, domain, url, logo, logo_mini, currency_code, currency_symbol, thousand_separator, decimal_separator, country_id, class_name)
values ('Ebay AU', 'ebay.com.au', 'https://www.ebay.com.au', null, null, 'AUD', '$',',','.', 4, 'xx.Ebay');

insert into site (name, domain, url, logo, logo_mini, currency_code, currency_symbol, thousand_separator, decimal_separator, country_id, class_name)
values ('Ebay DE', 'ebay.de', 'https://www.ebay.de', null, null, 'EUR', '€',',','.', 5, 'xx.Ebay');

insert into site (name, domain, url, logo, logo_mini, currency_code, currency_symbol, thousand_separator, decimal_separator, country_id, class_name)
values ('Ebay NL', 'ebay.nl', 'https://www.ebay.nl', null, null, 'EUR', '€',',','.', 6, 'xx.Ebay');

insert into site (name, domain, url, logo, logo_mini, currency_code, currency_symbol, thousand_separator, decimal_separator, country_id, class_name)
values ('Ebay FR', 'ebay.fr', 'https://www.ebay.fr', null, null, 'EUR', '€',',','.', 7, 'xx.Ebay');

insert into site (name, domain, url, logo, logo_mini, currency_code, currency_symbol, thousand_separator, decimal_separator, country_id, class_name)
values ('Ebay IT', 'ebay.it', 'https://www.ebay.it', null, null, 'EUR', '€',',','.', 8, 'xx.Ebay');

insert into site (name, domain, url, logo, logo_mini, currency_code, currency_symbol, thousand_separator, decimal_separator, country_id, class_name)
values ('Ebay ES', 'ebay.es', 'https://www.ebay.es', null, null, 'EUR', '€',',','.', 9, 'xx.Ebay');

-- -------------------------------------------------------
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
