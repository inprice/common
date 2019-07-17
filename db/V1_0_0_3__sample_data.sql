-- @author mdpinar

insert into customer (company_name, contact_name, email, password_hash, password_salt) values ('inprice', 'mustafa', 'aaa@bb.com', 'ab', 'cd');
insert into customer_brand (name, customer_id) values ('limon', 1);
insert into customer_plan (name, customer_id, brand_id, plan_id, due_date) values ('limon için alan', 1, 1, 1, '2020-04-12 10:10:10');

insert into product (code, name, brand, price, customer_plan_id) values ('455930318', 'SAMSUNG GALAXY A10 32 GB 2 GB RAM ÇİFT HATLI', 'Samsung', 1000, 1);

insert into link (url, product_id, customer_plan_id)
values
   ('https://urun.gittigidiyor.com/cep-telefonu-ve-aksesuar/samsung-galaxy-a10-32-gb-2-gb-ram-cift-hatli-cep-telefonu-ithalatci-garantili-455930318?scrf=haftanin-cok-satanlari', 1, 1),
   ('https://www.trendyol.com/samsung/galaxy-a10-32-gb-mavi-samsung-turkiye-garantili-p-6053363?boutiqueId=318682&merchantId=107289', 1, 1),
   ('https://www.hepsiburada.com/samsung-galaxy-a10-32-gb-samsung-turkiye-garantili-p-HBV00000JFE8Q', 1, 1),
   ('https://www.teknosa.com/samsung-galaxy-tab-a-smt590-32gb-105-siyah-wifi-tablet-p-125046710', 1, 1),
   ('https://www.teknosa.com/', 1, 1),
   ('ehdfkashkdf', 1, 1),
   ('https://www.hepsiburada.com', 1, 1);
