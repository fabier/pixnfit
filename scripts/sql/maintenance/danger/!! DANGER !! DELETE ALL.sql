/*
UPDATE "user"
SET gender_id = NULL;

DELETE FROM post;
ALTER SEQUENCE "public".seq_post RESTART;

DELETE FROM post_images;

DELETE FROM image_data_cache;
DELETE FROM image_data;
DELETE FROM image;

DELETE FROM user_role;
DELETE FROM "role";
DELETE FROM "user";

DELETE FROM user_fashion_style;

ALTER SEQUENCE "public".seq_image RESTART;

DELETE FROM body_type;
ALTER SEQUENCE "public".seq_body_type RESTART;

DELETE FROM country;
ALTER SEQUENCE "public".seq_country RESTART;

DELETE FROM fashion_style;
ALTER SEQUENCE "public".seq_fashion_style RESTART;

DELETE FROM file_extension;
ALTER SEQUENCE "public".seq_file_extension RESTART;

DELETE FROM gender;
ALTER SEQUENCE "public".seq_gender RESTART;

DELETE FROM image_type;
ALTER SEQUENCE "public".seq_image_type RESTART;

DELETE FROM "language";
ALTER SEQUENCE "public".seq_language RESTART;

DELETE FROM mimetype;
ALTER SEQUENCE "public".seq_mimetype RESTART;

DELETE FROM post_type;
ALTER SEQUENCE "public".seq_post_type RESTART;

DELETE FROM "state";
ALTER SEQUENCE "public".seq_state RESTART;

DELETE FROM visibility;
ALTER SEQUENCE "public".seq_visibility RESTART;

DELETE FROM vote_reason;
ALTER SEQUENCE "public".seq_vote_reason RESTART;
*/