/*
UPDATE "user"
SET mimetype_id = null;
*/

DELETE FROM mimetype;

ALTER SEQUENCE "public".seq_mimetype RESTART;

INSERT INTO mimetype (id, "name", mimetype, image_type_id, description, version, creator_id, date_created, last_updated)
VALUES (nextval('seq_mimetype'), 'image/jpeg', 'image/jpeg', 1, null, 0, 1, now(), now()),
(nextval('seq_mimetype'), 'image/gif', 'image/gif', 2, null, 0, 1, now(), now()),
(nextval('seq_mimetype'), 'image/png', 'image/png', 3, null, 0, 1, now(), now());

SELECT * FROM mimetype;