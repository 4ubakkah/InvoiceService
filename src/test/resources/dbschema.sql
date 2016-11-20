CREATE TABLE address
(
    id INT(11) auto_increment PRIMARY KEY,
    version INT(11),
    city VARCHAR(100) NOT NULL,
    street VARCHAR(100) NOT NULL,
    building VARCHAR(10) NOT NULL,
    postal_code VARCHAR(10) NOT NULL,
    country VARCHAR(100) NOT NULL
);

CREATE TABLE customer
(
    id INT(11) PRIMARY KEY auto_increment,
    version INT(11),
    address_id INT(11) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    CONSTRAINT customer_address_id_fk FOREIGN KEY (address_id) REFERENCES address (id)
);

CREATE INDEX customer_address_id_fk ON customer (address_id);
CREATE TABLE services
(
    id INT(11) PRIMARY KEY auto_increment,
    version INT(11),
    vat_amount DECIMAL(15,2) NOT NULL,
    total_amount DECIMAL(15,2) NOT NULL,
    service_type VARCHAR(50) NOT NULL,
    customer_id INT(11) NOT NULL,
    address_id INT(11) NOT NULL,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    amount DECIMAL(15,2) NOT NULL,
    CONSTRAINT services_customer_id_fk FOREIGN KEY (customer_id) REFERENCES customer (id),
    CONSTRAINT services_address_id_fk FOREIGN KEY (address_id) REFERENCES address (id)
);
CREATE INDEX services_address_id_fk ON services (address_id);
CREATE INDEX services_customer_id_fk ON services (customer_id);
CREATE TABLE invoice
(
    id INT(11) PRIMARY KEY auto_increment,
    version INT(11),
    type VARCHAR(50) NOT NULL,
    start_date DATE,
    end_date DATE,
    invoice_date DATE,
    payment_due_date DATE,
    address_id INT(11) NOT NULL,
    customer_id INT(11) NOT NULL,
    vat_amount DECIMAL(15,2) NOT NULL,
    amount DECIMAL(15,2) NOT NULL,
    total_amount DECIMAL(15,2) NOT NULL,
    period_description VARCHAR(100),
    number VARCHAR(50),
    CONSTRAINT invoice_address_id_fk FOREIGN KEY (address_id) REFERENCES address (id),
    CONSTRAINT invoice_customer_id_fk FOREIGN KEY (customer_id) REFERENCES customer (id)
);
CREATE INDEX invoice_address_id_fk ON invoice (address_id);
CREATE INDEX invoice_customer_id_fk ON invoice (customer_id);
CREATE UNIQUE INDEX invoice_number_uindex ON invoice (number);