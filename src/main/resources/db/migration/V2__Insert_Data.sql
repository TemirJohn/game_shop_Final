INSERT INTO category (id, name) VALUES (1, 'RPG');
INSERT INTO category (id, name) VALUES (2, 'Shooter');
INSERT INTO category (id, name) VALUES (3, 'Strategy');

INSERT INTO permissions (id, name) VALUES (1, 'ROLE_USER');
INSERT INTO permissions (id, name) VALUES (2, 'ROLE_ADMIN');

INSERT INTO users (id, username, email, password)
VALUES (1, 'admin', 'admin@game.kz', '$2a$12$sometesthashorexistingpassword');

INSERT INTO users (id, username, email, password)
VALUES (2, 'gamer_pro', 'gamer@game.kz', 'gamer123');

INSERT INTO user_permissions (user_id, permission_id) VALUES (1, 2);
INSERT INTO user_permissions (user_id, permission_id) VALUES (2, 1);

INSERT INTO game (id, title, description, price, category_id)
VALUES (1, 'The Witcher 3', 'Geralt of Rivia hunting monsters', 29.99, 1);

INSERT INTO game (id, title, description, price, category_id)
VALUES (2, 'CS:GO', 'Counter-Terrorists vs Terrorists', 0.00, 2);

INSERT INTO game (id, title, description, price, category_id)
VALUES (3, 'Civilization VI', 'Build an empire to stand the test of time', 59.99, 3);

INSERT INTO review (content, rating, user_id, game_id)
VALUES ('Best game ever!', 5, 2, 1);

INSERT INTO review (content, rating, user_id, game_id)
VALUES ('Good for competitive play', 4, 1, 2);