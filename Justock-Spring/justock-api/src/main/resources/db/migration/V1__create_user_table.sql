CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50)
);


INSERT INTO users (name, email, password, role) VALUES 
('Admin Teste', 'admin@test.com', '123456', 'ADMIN');
