DELETE FROM post
USING post AS P
LEFT JOIN post_images PI
ON PI.post_id = P.id
WHERE P.id = post.id
AND PI.image_id IS NULL;

DELETE FROM image_data_cache;

DELETE FROM image
USING image I
LEFT JOIN post_images PI
ON PI.image_id = I.id
WHERE I.id = image.id
AND PI.image_id IS NULL;

DELETE FROM post_images
WHERE post_id = 1;

DELETE FROM post
WHERE id = 1;
