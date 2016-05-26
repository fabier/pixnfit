/*
UPDATE "user"
SET image_type_id = null;
*/

DELETE FROM image_type;

ALTER SEQUENCE "public".seq_image_type RESTART;

INSERT INTO image_type
(id, "name",
default_file_extension_id, default_mime_type_id,
java_format_name, description,
version, creator_id, date_created, last_updated)
VALUES
(nextval('seq_image_type'), 'JPEG', null, null, 'JPEG', null, 0, 1, now(), now()),
(nextval('seq_image_type'), 'GIF', null, null, 'GIF', null, 0, 1, now(), now()),
(nextval('seq_image_type'), 'PNG', null, null, 'PNG', null, 0, 1, now(), now());

SELECT * FROM image_type;