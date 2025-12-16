CREATE TABLE category (
                          id BIGSERIAL PRIMARY KEY,
                          name VARCHAR(255)
);

CREATE TABLE permissions (
                             id BIGSERIAL PRIMARY KEY,
                             name VARCHAR(255)
);

CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       email VARCHAR(255),
                       password VARCHAR(255),
                       username VARCHAR(255)
);

CREATE TABLE game (
                      id BIGSERIAL PRIMARY KEY,
                      description VARCHAR(255),
                      image_url VARCHAR(255),
                      price DOUBLE PRECISION,
                      title VARCHAR(255),
                      category_id BIGINT REFERENCES category(id)
);

CREATE TABLE review (
                        id BIGSERIAL PRIMARY KEY,
                        content VARCHAR(255),
                        rating INTEGER,
                        game_id BIGINT REFERENCES game(id),
                        user_id BIGINT REFERENCES users(id)
);

CREATE TABLE user_permissions (
                                  user_id BIGINT NOT NULL REFERENCES users(id),
                                  permission_id BIGINT NOT NULL REFERENCES permissions(id)
);

CREATE TABLE user_games (
                            user_id BIGINT NOT NULL REFERENCES users(id),
                            game_id BIGINT NOT NULL REFERENCES game(id)
);

INSERT INTO permissions (name) VALUES ('ROLE_USER');
INSERT INTO category (name) VALUES ('RPG'), ('Shooter'), ('Strategy');