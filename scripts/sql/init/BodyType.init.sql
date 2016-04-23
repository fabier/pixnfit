/*
UPDATE "user"
SET body_type_id = null;
*/

DELETE FROM body_type;

ALTER SEQUENCE "public".seq_body_type RESTART;

INSERT INTO body_type (id, "name", description, version, creator_id, date_created, last_updated)
VALUES (nextval('seq_body_type'), '8 shaped', null, 0, 1, now(), now()),
(nextval('seq_body_type'), 'Inverted triangle', null, 0, 1, now(), now()),
(nextval('seq_body_type'), 'Triangle', null, 0, 1, now(), now()),
(nextval('seq_body_type'), 'Double triangle', null, 0, 1, now(), now()),
(nextval('seq_body_type'), 'Round', null, 0, 1, now(), now()),
(nextval('seq_body_type'), 'Square', null, 0, 1, now(), now());

SELECT * FROM body_type;