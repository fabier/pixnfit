/*
UPDATE "user"
SET visibility_id = null;
*/

DELETE FROM visibility;

ALTER SEQUENCE "public".seq_visibility RESTART;

INSERT INTO visibility (id, "name", description, version, creator_id, date_created, last_updated)
VALUES (nextval('seq_visibility'), 'public', null, 0, 1, now(), now()),
(nextval('seq_visibility'), 'followers', null, 0, 1, now(), now()),
(nextval('seq_visibility'), 'private', null, 0, 1, now(), now());

SELECT * FROM visibility;