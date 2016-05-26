/*
UPDATE "user"
SET country_id = null;
*/

DELETE FROM country;

ALTER SEQUENCE "public".seq_country RESTART;

INSERT INTO country (id, "name", native_name, iso_code31661, description, version, creator_id, date_created, last_updated)
VALUES (nextval('seq_country'), 'France', 'France', 'FR', null, 0, 1, now(), now()),
(nextval('seq_country'), 'USA', 'USA', 'US', null, 0, 1, now(), now());

SELECT * FROM country;