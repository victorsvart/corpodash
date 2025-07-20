-- Migration: user_roles
CREATE TABLE user_roles (
    user_id bigint NOT NULL,
    role varchar(50) NOT NULL,
    PRIMARY KEY (user_id, ROLE),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

