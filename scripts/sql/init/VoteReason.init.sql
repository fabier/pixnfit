/*
UPDATE "user"
SET vote_reason_id = null;
*/

DELETE FROM vote_reason;

ALTER SEQUENCE "public".seq_vote_reason RESTART;

INSERT INTO vote_reason (id, "name", description, version, creator_id, date_created, last_updated)
VALUES (nextval('seq_vote_reason'), 'Size', null, 0, 1, now(), now()),
(nextval('seq_vote_reason'), 'Style', null, 0, 1, now(), now()),
(nextval('seq_vote_reason'), 'Color', null, 0, 1, now(), now());

SELECT * FROM vote_reason;