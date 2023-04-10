CREATE TABLE IF NOT EXISTS xss_stored_posts
(
    id bigserial NOT NULL,
    title text NOT NULL,
    content text NOT NULL,
    user_id bigint NOT NULL,
    user_profile_pictire text NOT NULL,
    user_names text NOT NULL,
    creation_ts timestamp with time zone NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS xss_stored_comments
(
    id bigserial NOT NULL,
    user_id bigint NOT NULL,
    user_profile_pictire text NOT NULL,
    user_names text NOT NULL,
    content text NOT NULL,
    post_id bigint REFERENCES xss_stored_posts(id),
    creation_ts timestamp with time zone NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE xss_stored_comments
    OWNER to "cySec";

ALTER TABLE xss_stored_posts
    OWNER to "cySec";