/*
UPDATE "user"
SET language_id = null;
*/

DELETE FROM "language";

ALTER SEQUENCE "public".seq_language RESTART;

INSERT INTO "language" (id, "name", native_name, iso_code6391, description, version, creator_id, date_created, last_updated)
VALUES (nextval('seq_language'), 'French', 'Français', 'fr', null, 0, 1, now(), now()),
(nextval('seq_language'), 'English', 'English', 'en', null, 0, 1, now(), now());

SELECT * FROM "language";