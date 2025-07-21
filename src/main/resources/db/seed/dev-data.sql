-- seed_user
INSERT INTO users (name, email, PASSWORD, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled)
    VALUES ('Admin Admin', 'admin@admin.com', '', TRUE, TRUE, TRUE, TRUE),
    ('User User', 'user@user.com', '123qwe', TRUE, TRUE, TRUE, TRUE);

-- seed_user_roles
INSERT INTO user_roles (user_id, ROLE)
    VALUES (1, 'ADMIN'),
    (1, 'USER'),
    (2, 'USER');

-- seed_projects
INSERT INTO projects (name, description, status, creator_id, created_at, updated_at)
    VALUES ('Corp Dashboard', 'Internal dashboard for corporate metrics', 'ACTIVE', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('User Portal', 'Frontend portal for users to manage their accounts', 'PLANNING', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- seed_project_repos
INSERT INTO project_repos (project_id, url, branch, last_commit, created_at, updated_at)
    VALUES (1, 'https://github.com/org/corp-dashboard', 'main', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, 'https://github.com/org/user-portal', 'develop', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- seed_project_team
-- Assign Admin and User to Project 1
INSERT INTO project_team (project_id, user_id)
    VALUES (1, 1),
    (1, 2);

-- Assign only User to Project 2
INSERT INTO project_team (project_id, user_id)
    VALUES (2, 2);

-- seed_plans
-- Plans for Project 1
INSERT INTO plans (url, last_deploy, environment, status, project_id, created_at, updated_at)
    VALUES ('https://corp.example.com', CURRENT_TIMESTAMP, 'PROD', 'DEPLOYED', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('https://staging.corp.example.com', CURRENT_TIMESTAMP, 'STAGING', 'PENDING', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Plan for Project 2
INSERT INTO plans (url, last_deploy, environment, status, project_id, created_at, updated_at)
    VALUES ('https://dev.user-portal.com', CURRENT_TIMESTAMP, 'DEV', 'FAILED', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- seed_servers
INSERT INTO servers (name, cores, memory, status, environment, type, created_at, updated_at)
    VALUES ('prod-web-1', 4, 8192, 'ONLINE', 'PROD', 'WEB', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('prod-db-1', 8, 16384, 'ONLINE', 'PROD', 'DB', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('dev-web-1', 2, 4096, 'OFFLINE', 'DEV', 'WEB', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('staging-web-1', 4, 8192, 'ONLINE', 'STAGING', 'WEB', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- seed_project_server (associating projects to servers)
-- Project 1: Corp Dashboard
INSERT INTO project_server (project_id, server_id)
    VALUES (1, 1),
    (1, 2);

-- Project 2: User Portal
INSERT INTO project_server (project_id, server_id)
    VALUES (2, 3),
    (2, 4);

