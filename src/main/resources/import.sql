-- Insert Clubs
INSERT INTO clubes (name, country) VALUES ('FC Barcelona', 'Spain');
INSERT INTO clubes (name, country) VALUES ('Real Madrid', 'Spain');
INSERT INTO clubes (name, country) VALUES ('Manchester United', 'England');
INSERT INTO clubes (name, country) VALUES ('Bayern Munich', 'Germany');
INSERT INTO clubes (name, country) VALUES ('Juventus', 'Italy');
INSERT INTO clubes (name, country) VALUES ('Paris Saint-Germain', 'France');

-- Insert Permissions
INSERT INTO permissions (name) VALUES ('READ_PRIVILEGES');
INSERT INTO permissions (name) VALUES ('WRITE_PRIVILEGES');
INSERT INTO permissions (name) VALUES ('DELETE_PRIVILEGES');

-- Insert Roles
INSERT INTO roles (role_name) VALUES ('ADMIN');
INSERT INTO roles (role_name) VALUES ('USER');
INSERT INTO roles (role_name) VALUES ('DEVELOPER');

-- Map Permissions to Roles
-- Assume role IDs: ADMIN = 1, USER = 2, DEVELOPER = 3
-- Assume permission IDs: READ_PRIVILEGES = 1, WRITE_PRIVILEGES = 2, DELETE_PRIVILEGES = 3
INSERT INTO role_permissions (role_id, permission_id) VALUES (1, 1);
INSERT INTO role_permissions (role_id, permission_id) VALUES (1, 2);
INSERT INTO role_permissions (role_id, permission_id) VALUES (1, 3);
INSERT INTO role_permissions (role_id, permission_id) VALUES (2, 1);
INSERT INTO role_permissions (role_id, permission_id) VALUES (3, 1);
INSERT INTO role_permissions (role_id, permission_id) VALUES (3, 2);

-- Insert Users
INSERT INTO users (username, password, is_enabled, account_no_expired, account_no_locked, credential_no_expired)
VALUES ('admin_user', '$2a$10$hashedpassword1', 1, 1, 1, 1);

INSERT INTO users (username, password, is_enabled, account_no_expired, account_no_locked, credential_no_expired)
VALUES ('normal_user', '$2a$10$hashedpassword2', 1, 1, 1, 1);

INSERT INTO users (username, password, is_enabled, account_no_expired, account_no_locked, credential_no_expired)
VALUES ('dev_user', '$2a$10$hashedpassword3', 1, 1, 1, 1);

-- Add tareg user with password '123456789' (BCrypt hashed)
-- Example hashed password: $2a$10$5eTR.SHRz9srrzP.9ZWqSe7H40Bo7x5kgN9FHEqBWc6Zxubw9IEje
-- You can replace this with your own hashed password if needed.
INSERT INTO users (username, password, is_enabled, account_no_expired, account_no_locked, credential_no_expired)
VALUES ('tareg', '$2a$10$5eTR.SHRz9srrzP.9ZWqSe7H40Bo7x5kgN9FHEqBWc6Zxubw9IEje', 1, 1, 1, 1);

-- Map Users to Roles
-- Assume IDs: admin_user = 1, normal_user = 2, dev_user = 3, tareg = 4
INSERT INTO user_roles (user_id, role_id) VALUES (1, 1); -- admin_user -> ADMIN
INSERT INTO user_roles (user_id, role_id) VALUES (2, 2); -- normal_user -> USER
INSERT INTO user_roles (user_id, role_id) VALUES (3, 3); -- dev_user -> DEVELOPER
INSERT INTO user_roles (user_id, role_id) VALUES (4, 2); -- tareg -> USER
