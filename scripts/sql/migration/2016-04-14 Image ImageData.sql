ALTER TABLE image ADD COLUMN image_data_id bigint;

UPDATE image
SET image_data_id = ID.id
FROM image_data ID
WHERE image.id = ID.image_id;

ALTER TABLE image ALTER COLUMN image_data_id SET NOT NULL;

CREATE UNIQUE INDEX "IX_image_image_data" ON image (image_data_id ASC NULLS LAST);

ALTER TABLE image_data DROP COLUMN image_id;