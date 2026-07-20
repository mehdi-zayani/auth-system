CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,

                       first_name VARCHAR(100) NOT NULL,
                       last_name VARCHAR(100) NOT NULL,

                       username VARCHAR(50) NOT NULL UNIQUE,
                       email VARCHAR(255) NOT NULL UNIQUE,

                       password VARCHAR(255) NOT NULL,

                       enabled BOOLEAN NOT NULL DEFAULT TRUE,
                       account_locked BOOLEAN NOT NULL DEFAULT FALSE,
                       credentials_expired BOOLEAN NOT NULL DEFAULT FALSE,
                       account_expired BOOLEAN NOT NULL DEFAULT FALSE,
                       email_verified BOOLEAN NOT NULL DEFAULT FALSE,

                       created_at TIMESTAMP NOT NULL,
                       updated_at TIMESTAMP NOT NULL
);

CREATE TABLE roles (
                       id BIGSERIAL PRIMARY KEY,

                       code VARCHAR(50) NOT NULL UNIQUE,
                       description VARCHAR(255),

                       created_at TIMESTAMP NOT NULL,
                       updated_at TIMESTAMP NOT NULL
);

CREATE TABLE user_roles (
                            user_id BIGINT NOT NULL,
                            role_id BIGINT NOT NULL,

                            PRIMARY KEY (user_id, role_id),

                            CONSTRAINT fk_user_roles_user
                                FOREIGN KEY (user_id)
                                    REFERENCES users(id)
                                    ON DELETE CASCADE,

                            CONSTRAINT fk_user_roles_role
                                FOREIGN KEY (role_id)
                                    REFERENCES roles(id)
);
CREATE INDEX idx_users_email
    ON users(email);

CREATE INDEX idx_users_username
    ON users(username);

CREATE INDEX idx_roles_code
    ON roles(code);