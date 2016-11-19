INSERT INTO address (id, city, street, building, postal_code, country) VALUES (1, 'Tallinn', 'Kotka', '1', '10129', 'Estonia');
INSERT INTO address (id, city, street, building, postal_code, country) VALUES (2, 'Tallinn', 'Viru', '1', '10129', 'Estonia');
INSERT INTO address (id, city, street, building, postal_code, country) VALUES (3, 'Tallinn', 'Viru', '3', '10129', 'Estonia');


INSERT INTO customer (id, address_id, full_name) VALUES (1, 1, 'Michael');
INSERT INTO customer (id, address_id, full_name) VALUES (2, 2, 'Jeff');
INSERT INTO customer (id, address_id, full_name) VALUES (3, 2, 'Marry');

INSERT INTO invoice (id, type, start_date, end_date, invoice_date, payment_due_date, address_id, customer_id, vat_amount, amount, total_amount, period_description, number) VALUES (1, 'ShopPurchase', '2016-11-01', '2016-11-18', '2016-11-17', '2016-11-18', 1, 1, 20.00, 100.00, 120.00, 'none', null);
INSERT INTO invoice (id, type, start_date, end_date, invoice_date, payment_due_date, address_id, customer_id, vat_amount, amount, total_amount, period_description, number) VALUES (2, 'AdvancePayment', '2016-11-01', '2016-11-18', '2016-11-17', '2016-11-18', 2, 2, 20.00, 100.00, 120.00, 'none', null);
INSERT INTO invoice (id, type, start_date, end_date, invoice_date, payment_due_date, address_id, customer_id, vat_amount, amount, total_amount, period_description, number) VALUES (3, 'AdvancePayment', '2016-11-01', '2016-11-18', '2016-11-15', '2016-11-17', 2, 2, 21.00, 102.00, 123.00, 'none', null);

INSERT INTO services (id, vat_amount, total_amount, service_type, customer_id, address_id, timestamp, amount) VALUES (1, 20.00, 120.00, 'ShopPurchase', 1, 1, '2016-11-19 13:25:50', 100.00);
INSERT INTO services (id, vat_amount, total_amount, service_type, customer_id, address_id, timestamp, amount) VALUES (2, 40.00, 240.00, 'AdvancePayment', 1, 2, '2016-11-19 13:26:36', 200.00);
INSERT INTO services (id, vat_amount, total_amount, service_type, customer_id, address_id, timestamp, amount) VALUES (3, 40.00, 240.00, 'AdvancePayment', 3, 3, '2016-11-19 13:26:36', 200.00);
