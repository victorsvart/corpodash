-- seed_user
INSERT INTO users (name, email, PASSWORD, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled)
    VALUES ('Admin Admin', 'admin@admin.com', '', TRUE, TRUE, TRUE, TRUE);

INSERT INTO users (name, email, PASSWORD, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled)
    VALUES ('User User', 'user@user.com', '123qwe', TRUE, TRUE, TRUE, TRUE);

INSERT INTO user_roles
    VALUES (1, 'ADMIN');

INSERT INTO user_roles
    VALUES (1, 'USER');

INSERT INTO user_roles
    VALUES (2, 'USER');

