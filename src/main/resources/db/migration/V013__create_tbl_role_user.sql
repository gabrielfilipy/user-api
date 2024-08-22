CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE TBL_USERS_ROLES (
    user_id UUID NOT NULL,
    role_id UUID NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES tbl_user(user_id),
    FOREIGN KEY (role_id) REFERENCES tbl_roles(role_id)
);
