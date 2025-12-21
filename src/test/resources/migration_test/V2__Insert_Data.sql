INSERT INTO category (name) VALUES ('RPG');
INSERT INTO category (name) VALUES ('Shooter');
INSERT INTO category (name) VALUES ('Strategy');

INSERT INTO permissions (name) VALUES ('ROLE_USER');
INSERT INTO permissions (name) VALUES ('ROLE_ADMIN');

INSERT INTO users (username, email, password)
VALUES ('admin', 'admin@game.kz', 'admin123');

INSERT INTO users (username, email, password)
VALUES ('gamer_pro', 'gamer@game.kz', 'gamer123');

INSERT INTO user_permissions (user_id, permission_id) VALUES (1, 2);
INSERT INTO user_permissions (user_id, permission_id) VALUES (2, 1);

INSERT INTO game (title, description, price, image_url, category_id)
VALUES ('The Witcher 3', 'Geralt of Rivia hunting monsters', 29.99, 'https://example.com/witcher.jpg', 1);

INSERT INTO game (title, description, price, image_url, category_id)
VALUES ('CS:GO', 'Counter-Terrorists vs Terrorists', 0.00, 'https://example.com/csgo.jpg', 2);

INSERT INTO game (title, description, price, image_url, category_id)
VALUES ('Civilization VI', 'Build an empire to stand the test of time', 59.99, 'https://example.com/civ6.jpg', 3);

INSERT INTO review (content, rating, user_id, game_id)
VALUES ('Best game ever!', 5, 2, 1);

INSERT INTO review (content, rating, user_id, game_id)
VALUES ('Good for competitive play', 4, 1, 2);