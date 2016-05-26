/*
UPDATE "user"
SET post_type_id = null;
*/

DELETE FROM post_type;

ALTER SEQUENCE "public".seq_post_type RESTART;

INSERT INTO post_type (id, "name", description, version, creator_id, date_created, last_updated)
VALUES (nextval('seq_post_type'), 'help', null, 0, 1, now(), now()),
(nextval('seq_post_type'), 'dressing', null, 0, 1, now(), now());

SELECT * FROM post_type;