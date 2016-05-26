/*
UPDATE "user"
SET state_id = null;
*/

DELETE FROM "state";

ALTER SEQUENCE "public".seq_state RESTART;

INSERT INTO "state" (id, "name", description, version, creator_id, date_created, last_updated)
VALUES (nextval('seq_state'), 'active', null, 0, 1, now(), now()),
(nextval('seq_state'), 'inactive', null, 0, 1, now(), now()),
(nextval('seq_state'), 'deleted', null, 0, 1, now(), now());

SELECT * FROM "state";