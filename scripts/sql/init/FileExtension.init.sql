/*
UPDATE "user"
SET file_extension_id = null;
*/

DELETE FROM file_extension;

ALTER SEQUENCE "public".seq_file_extension RESTART;

INSERT INTO file_extension (id, "name", extension, image_type_id, description, version, creator_id, date_created, last_updated)
VALUES (nextval('seq_file_extension'), 'JPEG', 'jpeg', 1, null, 0, 1, now(), now()),
(nextval('seq_file_extension'), 'JPG', 'jpg', 1, null, 0, 1, now(), now()),
(nextval('seq_file_extension'), 'GIF', 'gif', 2, null, 0, 1, now(), now()),
(nextval('seq_file_extension'), 'PNG', 'png', 3, null, 0, 1, now(), now());

SELECT * FROM file_extension;