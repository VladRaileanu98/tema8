CREATE TABLE customers (
    id INT NOT NULL AUTO_INCREMENT,
    username varchar(50) NOT NULL UNIQUE,
    last_name varchar(50) NOT NULL,
    first_name varchar(50) NOT NULL,
    phone varchar(50) NOT NULL,
    address varchar(50),
    city varchar(50),
    postal_code varchar(50),
    country varchar(50),
    PRIMARY KEY (id)
);

CREATE TABLE payments (
	customer_id INT,
	payment_date DATE,
    amount DECIMAL(10,2),
    FOREIGN KEY (customer_id) REFERENCES customers(id)    
);

CREATE TABLE products (
	product_code VARCHAR(15) NOT NULL,
    product_name VARCHAR(50),
    product_description TEXT,
    stock SMALLINT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (product_code)
);
    
CREATE TABLE orderdetails (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    product_code VARCHAR(15) NOT NULL,
    quantity INT NOT NULL,
    FOREIGN KEY (product_code) REFERENCES products(product_code)
    
);

CREATE TABLE orders (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    order_date DATE NOT NULL,
    shipped_date DATE,
    status VARCHAR(15),
    comments TEXT,
    customer_id INT NOT NULL,
    order_details_id INT NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customers(id),
    FOREIGN KEY (order_details_id) REFERENCES orderdetails(id)
);

