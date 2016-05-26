/*
UPDATE "user"
SET fashion_style_id = null;
*/

DELETE FROM fashion_style;

ALTER SEQUENCE "public".seq_fashion_style RESTART;

INSERT INTO fashion_style (id, "name", description, version, creator_id, date_created, last_updated)
VALUES (nextval('seq_fashion_style'), 'Casual', null, 0, 1, now(), now()),
(nextval('seq_fashion_style'), 'Chic', null, 0, 1, now(), now()),
(nextval('seq_fashion_style'), 'Classic', null, 0, 1, now(), now()),
(nextval('seq_fashion_style'), 'Hipster', null, 0, 1, now(), now()),
(nextval('seq_fashion_style'), 'Rocker', null, 0, 1, now(), now()),
(nextval('seq_fashion_style'), 'Sporty', null, 0, 1, now(), now()),
(nextval('seq_fashion_style'), 'Urban', null, 0, 1, now(), now()),
(nextval('seq_fashion_style'), 'Vintage', null, 0, 1, now(), now());

SELECT * FROM fashion_style;