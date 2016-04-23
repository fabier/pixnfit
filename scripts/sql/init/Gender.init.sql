/*
UPDATE "user"
SET gender_id = null;
*/

DELETE FROM gender;

ALTER SEQUENCE "public".seq_gender RESTART;

INSERT INTO gender (id, "name", description, version, creator_id, date_created, last_updated)
VALUES (nextval('seq_gender'), 'male', null, 0, 1, now(), now()),
(nextval('seq_gender'), 'female', null, 0, 1, now(), now());

SELECT * FROM gender;