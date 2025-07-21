-- Migration: adding_projects_and_details
-- PROJECTS TABLE
CREATE TABLE projects (
    id bigserial PRIMARY KEY,
    name varchar(255) NOT NULL,
    description text,
    status varchar(50),
    creator_id bigint NOT NULL,
    created_at timestamp,
    updated_at timestamp,
    CONSTRAINT fk_creator FOREIGN KEY (creator_id) REFERENCES users (id)
);

-- SERVERS TABLE
CREATE TABLE servers (
    id bigserial PRIMARY KEY,
    name varchar(255) NOT NULL,
    cores int NOT NULL,
    memory int NOT NULL,
    status varchar(50) NOT NULL,
    environment varchar(50) NOT NULL,
    type varchar(50) NOT NULL,
    created_at timestamp,
    updated_at timestamp,
    CONSTRAINT chk_server_status CHECK (status IN ('ONLINE', 'OFFLINE')),
    CONSTRAINT chk_server_environment CHECK (environment IN ('PROD', 'DEV', 'STAGING')),
    CONSTRAINT chk_server_type CHECK (type IN ('WEB', 'DB'))
);

-- PROJECT_TEAM JOIN TABLE
CREATE TABLE project_team (
    project_id bigint NOT NULL,
    user_id bigint NOT NULL,
    PRIMARY KEY (project_id, user_id),
    CONSTRAINT fk_team_project FOREIGN KEY (project_id) REFERENCES projects (id) ON DELETE CASCADE,
    CONSTRAINT fk_team_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

-- PROJECT_SERVER JOIN TABLE
CREATE TABLE project_server (
    project_id bigint NOT NULL,
    server_id bigint NOT NULL,
    PRIMARY KEY (project_id, server_id),
    CONSTRAINT fk_project_server_project FOREIGN KEY (project_id) REFERENCES projects (id) ON DELETE CASCADE,
    CONSTRAINT fk_project_server_server FOREIGN KEY (server_id) REFERENCES servers (id) ON DELETE CASCADE
);

-- PROJECT_REPOS TABLE
CREATE TABLE project_repos (
    id bigserial PRIMARY KEY,
    project_id bigint NOT NULL UNIQUE,
    url varchar(255) NOT NULL,
    branch varchar(255) NOT NULL,
    last_commit timestamp,
    created_at timestamp,
    updated_at timestamp,
    CONSTRAINT fk_project FOREIGN KEY (project_id) REFERENCES projects (id)
);

-- PLANS TABLE
CREATE TABLE plans (
    id bigserial PRIMARY KEY,
    url varchar(255),
    last_deploy timestamp,
    environment varchar(50),
    status varchar(50),
    project_id bigint NOT NULL,
    created_at timestamp,
    updated_at timestamp,
    CONSTRAINT fk_deployment_project FOREIGN KEY (project_id) REFERENCES projects (id)
);

