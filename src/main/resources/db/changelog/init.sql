create table booting
(
    id uuid PRIMARY KEY,
    name character varying(255)
);

create table user_profile
(
    id uuid PRIMARY KEY,
    name character varying(255),
    password character varying(255)
);
