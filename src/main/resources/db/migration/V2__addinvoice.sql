CREATE TABLE IF NOT EXISTS invoice(
id SERIAL,
code VARCHAR(100) NOT NULL,
create_at DATE,
total DECIMAL (8,2) NOT NULL,
client_id INT NOT NULL,
PRIMARY KEY (id),
UNIQUE(code),
FOREIGN KEY (client_id) REFERENCES client (id)
);