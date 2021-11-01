-- @author mdpinar

-- demo websites
insert into platform (name, domain, country, class_name, currency_code, currency_format) values
  ('Demo Magaza', 'sanal.com', 'Neverland', 'zz.Sanal', 'TRY', '#,##0.00 TL')
  ;

-- standard websites
insert into platform (name, domain, country, class_name, currency_code, currency_format) values
  ('Amazon US', 'amazon.com', 'United States', 'xx.AmazonXX', 'USD', '$#,##0.00'),
  ('Ebay US', 'ebay.com', 'United States', 'xx.EbayXX', 'USD', '$#,##0.00'),
  ('Apple US', 'apple.com/us-hed', 'United States', 'xx.AppleXX', 'USD', '$#,##0.00'),
  ('BestBuy US', 'bestbuy.com', 'United States', 'us.BestBuyUS', 'USD', '$#,##0.00'),
  ('Bonanza US', 'bonanza.com', 'United States', 'us.BonanzaUS', 'USD', '$#,##0.00'),
  ('Etsy US', 'etsy.com', 'United States', 'us.EtsyUS', 'USD', '$#,##0.00'),
  ('Lidl US', 'lidl.com', 'United States', 'us.LidlUS', 'USD', '$#,##0.00'),
  ('Target US', 'target.com', 'United States', 'us.TargetUS', 'USD', '$#,##0.00'),
  ('VidaXL US', 'vidaxl.us', 'United States', 'xx.VidaXLXX', 'USD', '$#,##0.00'),
  ('Walmart US', 'walmart.com', 'United States', 'us.WalmartUS', 'USD', '$#,##0.00'),

  ('Amazon UK', 'amazon.co.uk', 'United Kingdom', 'xx.AmazonXX', 'GBP', '£#,##0.00'),
  ('Ebay UK', 'ebay.co.uk', 'United Kingdom', 'xx.EbayXX', 'GBP', '£#,##0.00'),
  ('Apple UK', 'apple.com/uk', 'United Kingdom', 'xx.AppleXX', 'GBP', '£#,##0.00'),
  ('Argos UK', 'argos.co.uk', 'United Kingdom', 'uk.ArgosUK', 'GBP', '£#,##0.00'),
  ('Asda Direct UK', 'global.direct.asda.com', 'United Kingdom', 'uk.AsdaDirectUK', 'GBP', '£#,##0.00'),
  ('Asda Grocery UK', 'groceries.argos.com', 'United Kingdom', 'uk.AsdaGroceryUK', 'GBP', '£#,##0.00'),
  ('Asda Photo UK', 'asda-photo.co.uk', 'United Kingdom', 'uk.AsdaDirectUK', 'GBP', '£#,##0.00'),
  ('Asos UK', 'asos.com', 'United Kingdom', 'uk.AsosUK', 'GBP', '£#,##0.00'),
  ('Bonprix UK', 'bonprix.co.uk', 'United Kingdom', 'xx.BonprixXX', 'GBP', '£#,##0.00'),
  ('Currys UK', 'currys.co.uk', 'United Kingdom', 'uk.CurrysUK', 'GBP', '£#,##0.00'),
  ('Debenhams UK', 'debenhams.com', 'United Kingdom', 'uk.DebenhamsUK', 'GBP', '£#,##0.00'),
  ('Euronics UK', 'euronics.co.uk', 'United Kingdom', 'uk.EuronicsUK', 'GBP', '£#,##0.00'),
  ('Lidl UK', 'lidl.co.uk', 'United Kingdom', 'xx.LidlXX', 'GBP', '£#,##0.00'),
  ('NewLook UK', 'newlook.com', 'United Kingdom', 'uk.NewLookUK', 'GBP', '£#,##0.00'),
  ('Zalando UK', 'zalando.co.uk', 'United Kingdom', 'xx.ZalandoXX', 'GBP', '£#,##0.00'),
  ('Zavvi UK', 'zavvi.com', 'United Kingdom', 'uk.ZavviUK', 'GBP', '£#,##0.00'),

  ('Amazon CA', 'amazon.ca', 'Canada', 'xx.AmazonXX', 'CAD', '$#,##0.00'),
  ('Ebay CA', 'ebay.ca', 'Canada', 'xx.EbayXX', 'CAD', '$#,##0.00'),
  ('Apple CA', 'apple.com/ca', 'Canada', 'xx.AppleXX', 'CAD', '$#,##0.00'),
  ('BestBuy CA', 'bestbuy.ca', 'Canada', 'ca.BestBuyCA', 'CAD', '$#,##0.00'),
  ('Canadian Tire CA', 'canadiantire.ca', 'Canada', 'ca.CanadianTireCA', 'CAD', '$#,##0.00'),

  ('Amazon AU', 'amazon.com.au', 'Australia', 'xx.AmazonXX', 'AUD', '$#,##0.00'),
  ('Ebay AU', 'ebay.com.au', 'Australia', 'xx.EbayXX', 'AUD', '$#,##0.00'),
  ('Apple AU', 'apple.com/au', 'Australia', 'xx.AppleXX', 'AUD', '$#,##0.00'),
  ('Appliances Online AU', 'appliancesonline.com.au', 'Australia', 'au.AppliancesOnlineAU', 'AUD', '$#,##0.00'),
  ('BigW AU', 'bigw.com.au', 'Australia', 'au.BigWAU', 'AUD', '$#,##0.00'),
  ('Harvey Norman AU', 'harveynorman.com.au', 'Australia', 'au.HarveyNormanAU', 'AUD', '$#,##0.00'),
  ('Kogan AU', 'kogan.com', 'Australia', 'au.KoganAU', 'AUD', '$#,##0.00'),
  ('VidaXL AU', 'vidaxl.au', 'Australia', 'xx.VidaXLXX', 'AUD', '$#,##0.00'),
  ('The Good Guys AU', 'thegoodguys.com.au', 'Australia', 'au.TheGoodGuysAU', 'AUD', '$#,##0.00'),

  ('Amazon DE', 'amazon.de', 'Germany', 'xx.AmazonXX', 'EUR', '#,##0.00 €'),
  ('Ebay DE', 'ebay.de', 'Germany', 'xx.EbayXX', 'EUR', '#,##0.00 €'),
  ('Apple DE', 'apple.com/de', 'Germany', 'xx.AppleXX', 'EUR', '#,##0.00 €'),
  ('Bonprix DE', 'bonprix.de', 'Germany', 'xx.BonprixXX', 'EUR', '#,##0.00 €'),
  ('Euronics DE', 'euronics.de', 'Germany', 'de.EuronicsDE', 'EUR', '#,##0.00 €'),
  ('MediaMarkt DE', 'mediamarkt.de', 'Germany', 'xx.MediaMarktXX_1', 'EUR', '#,##0.00 €'),
  ('Notebooks Billiger DE', 'notebooksbilliger.de', 'Germany', 'de.NotebooksBilligerDE', 'EUR', '#,##0.00 €'),
  ('Otto DE', 'otto.de', 'Germany', 'de.OttoDE', 'EUR', '#,##0.00 €'),
  ('Saturn DE', 'saturn.de', 'Germany', 'xx.MediaMarktXX_1', 'EUR', '#,##0.00 €'),
  ('Lidl DE', 'lidl.de', 'Germany', 'de.LidlDE', 'EUR', '#,##0.00 €'),
  ('Zalando DE', 'zalando.de', 'Germany', 'xx.ZalandoXX', 'EUR', '#,##0.00 €'),

  ('Amazon NL', 'amazon.nl', 'Netherlands', 'xx.AmazonXX', 'EUR', '€ #,##0.00'),
  ('Ebay NL', 'ebay.nl', 'Netherlands', 'xx.EbayXX', 'EUR', '€ #,##0.00'),
  ('Apple NL', 'apple.com/nl', 'Netherlands', 'xx.AppleXX', 'EUR', '€ #,##0.00'),
  ('Bol NL', 'bol.com', 'Netherlands', 'nl.BolNL', 'EUR', '€ #,##0.00'),
  ('Bonprix NL', 'bonprix.nl', 'Netherlands', 'xx.BonprixXX', 'EUR', '€ #,##0.00'),
  ('CoolBlue NL', 'coolblue.nl', 'Netherlands', 'nl.CoolBlueNL', 'EUR', '€ #,##0.00'),
  ('De Bijenkorf NL', 'debijenkorf.nl', 'Netherlands', 'nl.DeBijenkorfNL', 'EUR', '€ #,##0.00'),
  ('Lidl NL', 'lidl.nl', 'Netherlands', 'xx.LidlXX', 'EUR', '€ #,##0.00'),
  ('MediaMarkt NL', 'mediamarkt.nl', 'Netherlands', 'xx.MediaMarktXX_2', 'EUR', '€ #,##0.00'),
  ('VidaXL NL', 'vidaxl.nl', 'Netherlands', 'xx.VidaXLXX', 'EUR', '€ #,##0.00'),
  ('Wehkamp NL', 'wehkamp.nl', 'Netherlands', 'nl.WehkampNL', 'EUR', '€ #,##0.00'),
  ('Zalando NL', 'zalando.nl', 'Netherlands', 'xx.ZalandoXX', 'EUR', '€ #,##0.00'),

  ('Amazon FR', 'amazon.fr', 'France', 'xx.AmazonXX', 'EUR', '#,##0.00 €'),
  ('Ebay FR', 'ebay.fr', 'France', 'xx.EbayXX', 'EUR', '#,##0.00 €'),
  ('Apple FR', 'apple.com/fr', 'France', 'xx.AppleXX', 'EUR', '#,##0.00 €'),
  ('Auchan FR', 'auchan.fr', 'France', 'fr.AuchanFR', 'EUR', '#,##0.00 €'),
  ('Bonprix FR', 'bonprix.fr', 'France', 'xx.BonprixXX', 'EUR', '#,##0.00 €'),
  ('CDiscount FR', 'cdiscount.com', 'France', 'fr.CDiscountFR', 'EUR', '#,##0.00 €'),
  ('Laredoute FR', 'laredoute.fr', 'France', 'fr.LaredouteFR', 'EUR', '#,##0.00 €'),
  ('Lidl FR', 'lidl.fr', 'France', 'xx.LidlXX', 'EUR', '#,##0.00 €'),
  ('Zalando FR', 'zalando.fr', 'France', 'xx.ZalandoXX', 'EUR', '#,##0.00 €'),

  ('Amazon IT', 'amazon.it', 'Italy', 'xx.AmazonXX', 'EUR', '€ #,##0.00'),
  ('Ebay IT', 'ebay.it', 'Italy', 'xx.EbayXX', 'EUR', '€ #,##0.00'),
  ('Apple IT', 'apple.com/it', 'Italy', 'xx.AppleXX', 'EUR', '€ #,##0.00'),
  ('Bonprix IT', 'bonprix.it', 'Italy', 'xx.BonprixXX', 'EUR', '€ #,##0.00'),
  ('EPrice IT', 'eprice.it', 'Italy', 'it.EPriceIT', 'EUR', '€ #,##0.00'),
  ('Euronics IT', 'euronics.it', 'Italy', 'it.EuronicsIT', 'EUR', '€ #,##0.00'),
  ('Lidl IT', 'lidl.it', 'Italy', 'xx.LidlXX', 'EUR', '€ #,##0.00'),
  ('MediaWorld IT', 'mediaworld.it', 'Italy', 'it.MediaWorldIT', 'EUR', '€ #,##0.00'),
  ('VidaXL IT', 'vidaxl.it', 'Italy', 'xx.VidaXLXX', 'EUR', '€ #,##0.00'),
  ('Zalando IT', 'zalando.it', 'Italy', 'xx.ZalandoXX', 'EUR', '€ #,##0.00'),

  ('Amazon ES', 'amazon.es', 'Spain', 'xx.AmazonXX', 'EUR', '€ #,##0.00'),
  ('Ebay ES', 'ebay.es', 'Spain', 'xx.EbayXX', 'EUR', '€ #,##0.00'),
  ('Apple ES', 'apple.com/es', 'Spain', 'xx.AppleXX', 'EUR', '€ #,##0.00'),
  ('Electro King ES', 'electroking.es', 'Spain', 'es.ElectroKingES', 'EUR', '€ #,##0.00'),
  ('Euronics ES', 'euronics.es', 'Spain', 'es.EuronicsES', 'EUR', '€ #,##0.00'),
  ('101 Gigas ES', '101gigas.com', 'Spain', 'es.Gigas101ES', 'EUR', '€ #,##0.00'),
  ('Lidl ES', 'lidl.es', 'Spain', 'xx.LidlXX', 'EUR', '€ #,##0.00'),
  ('UlaBox ES', 'ulabox.com', 'Spain', 'es.UlaBoxES', 'EUR', '€ #,##0.00'),
  ('Zalando ES', 'zalando.es', 'Spain', 'xx.ZalandoXX', 'EUR', '€ #,##0.00'),

  ('Amazon TR', 'amazon.com.tr', 'Turkey', 'xx.AmazonXX', 'TRY', '#,##0.00 TL'),
  ('Apple TR', 'apple.com/tr', 'Turkey', 'xx.AppleXX', 'TRY', '#,##0.00 TL'),
  ('Gittigidiyor TR', 'gittigidiyor.com', 'Turkey', 'tr.GittiGidiyorTR', 'TRY', '#,##0.00 TL'),
  ('Hepsiburada TR', 'hepsiburada.com', 'Turkey', 'tr.HepsiBuradaTR', 'TRY', '#,##0.00 TL'),
  ('MediaMarkt TR', 'mediamarkt.com.tr', 'Turkey', 'xx.MediaMarktXX_2', 'TRY', '#,##0.00 TL'),
  ('n11 TR', 'n11.com', 'Turkey', 'tr.N11TR', 'TRY', '#,##0.00 TL'),
  ('Teknosa TR', 'teknosa.com', 'Turkey', 'tr.TeknosaTR', 'TRY', '#,##0.00 TL'),
  ('Trendyol TR', 'trendyol.com', 'Turkey', 'tr.TrendyolTR', 'TRY', '#,##0.00 TL')
  ;

-- websites having separated queue
insert into platform (name, domain, country, class_name, currency_code, currency_format, queue) values
  ('Walmart CA', 'walmart.ca', 'Canada', 'ca.WalmartCA', 'CAD', '$#,##0.00', 'active.links.queue.cap1')
  ;

-- blocked websites
insert into platform (name, domain, country, class_name, currency_code, currency_format, blocked) values
  ('Fnac FR', 'fnac.com', 'France', 'fr.FnacFR', 'EUR', '#,##0.00 €', true),
  ('MediaMarkt ES', 'mediamarkt.es', 'Spain', 'xx.MediaMarktXX_1', 'EUR', '€ #,##0.00', true)
  ;
  