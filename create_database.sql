CREATE TABLE users
(
    id           INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    name         VARCHAR(255) UNIQUE            NOT NULL,
    password     VARCHAR(255)                   NOT NULL,
    date_created DATE
);

CREATE TABLE attributes
(
    id      INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    damage  INT                            NOT NULL DEFAULT 10,
    life    INT                            NOT NULL DEFAULT 100,
    rating  INT                            NOT NULL DEFAULT 0,
    user_id INT                            NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE session
(
    id             INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    active         BOOLEAN,
    ready_to_fight BOOLEAN,
    user_id        INT                            NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id)
)
