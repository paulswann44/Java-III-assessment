# USE products_db;
#
# TRUNCATE products;
#
# INSERT INTO products(name, price) VALUES
# ('Hammer', 20),
# ('Drill', 100),
# ('Mower', 200),
# ('Screwdriver', 10);
USE products_db;

TRUNCATE products;

INSERT INTO products(name, price, quantity) VALUES
                                                ('Hammer', 20, 10),
                                                ('Drill', 100, 5),
                                                ('Mower', 200, 3),
                                                ('Screwdriver', 10, 20);
