CREATE TABLE projects (
    id serial PRIMARY KEY,
    name varchar(255) NOT NULL,
    creator_id bigint NOT NULL,
    created_at timestamp,
    updated_at timestamp,
    CONSTRAINT fk_creator FOREIGN KEY (creator_id) REFERENCES users (id)
);

CREATE TABLE project_team (
    project_id bigint NOT NULL,
    user_id bigint NOT NULL,
    PRIMARY KEY (project_id, user_id),
    CONSTRAINT fk_project FOREIGN KEY (project_id) REFERENCES projects (id),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE INDEX idx_project_creator ON projects (creator_id);

CREATE INDEX idx_team_user ON project_team (user_id);

